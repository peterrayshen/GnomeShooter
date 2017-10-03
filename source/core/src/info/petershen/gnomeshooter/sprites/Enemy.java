package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;
import info.petershen.gnomeshooter.sprites.Player.State;
import info.petershen.gnomeshooter.tools.AssetLoader;

public class Enemy extends Sprite {

	private PlayScreen screen;
	public World world;
	public Body b2body;
	private float health;
	public boolean remove;
	public float startingX;
	public float startingY;
	private float stateTimer;
	private float timeToTurn;
	private float turnLeftTimer, turnRightTimer;
	private float barWidth;
	private boolean goingRight;
	public float reward;
	private float speed;

	public Enemy(PlayScreen screen, World world, float x, float y, float health, float timeToTurn, float speed) {
		this.speed = speed;
		this.timeToTurn = timeToTurn;
		this.health = health;
		this.reward = 100;
		stateTimer = 0;
		startingX = x;
		startingY = y;
		this.screen = screen;
		this.world = world;
		defineEnemy();
		setBounds(b2body.getPosition().x - 10 / GnomeShooter.PPM, b2body.getPosition().y - 26 / GnomeShooter.PPM,
				30 / GnomeShooter.PPM, 45 / GnomeShooter.PPM);
		setRegion(AssetLoader.gnomeWalk.getKeyFrame(stateTimer));

	}

	public void defineEnemy() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(startingX / GnomeShooter.PPM, startingY / GnomeShooter.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(10 / GnomeShooter.PPM, 18 / GnomeShooter.PPM);

		fdef.shape = shape;
		fdef.density = 120;
		fdef.filter.groupIndex = GnomeShooter.ENEMY_INDEX;
		b2body.createFixture(fdef).setUserData(this);
	


		MassData mdata = new MassData();
		mdata.mass = 10000 / GnomeShooter.PPM;
		b2body.setMassData(mdata);

		if (b2body.getPosition().x > screen.player.b2body.getPosition().x) {
			goingRight = false;
		}

		else {
			goingRight = true;
		}

	}

	public boolean shouldRemove() {
		return remove;
	}

	public void hit(float damage) {
		this.setColor(Color.RED);
		health -= damage;
	}

	public void update(float delta) {
		barWidth = health / 100 * this.getWidth();

		stateTimer += delta;
		setRegion(AssetLoader.gnomeWalk.getKeyFrame(stateTimer));

		if (health <= 0) {
			remove = true;
		}

		setPosition(b2body.getPosition().x - 17.5f / GnomeShooter.PPM, b2body.getPosition().y - 22 / GnomeShooter.PPM);

		updateMovement(delta);

	}

	public void drawBar(ShapeRenderer sr) {

		sr.begin(ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.rect(b2body.getPosition().x - 16 / GnomeShooter.PPM, b2body.getPosition().y + 25 / GnomeShooter.PPM, barWidth,
				3.5f / GnomeShooter.PPM);
		sr.end();
	}

	public void updateMovement(float delta) {
		

		if (b2body.getLinearVelocity().x > 0) {
			goingRight = true;
			this.b2body.setLinearVelocity(speed, b2body.getLinearVelocity().y);
		} else {
			goingRight = false;
			this.b2body.setLinearVelocity(-speed, b2body.getLinearVelocity().y);
		}

		if (b2body.getPosition().x > screen.player.b2body.getPosition().x && goingRight) {
			turnLeftTimer += delta;
		} else
			turnLeftTimer = 0;

		if (b2body.getPosition().x < screen.player.b2body.getPosition().x && !goingRight) {
			turnRightTimer += delta;
		} else
			turnRightTimer = 0;

		if (turnLeftTimer > timeToTurn) {
			b2body.setLinearVelocity(-speed, b2body.getLinearVelocity().y);
			if (this.isFlipX())
				flip(true, false);
		}

		if (turnRightTimer > timeToTurn) {
			b2body.setLinearVelocity(speed, b2body.getLinearVelocity().y);
			if (!this.isFlipX())
				flip(true, false);
		}

		if (goingRight && !this.isFlipX())
			this.flip(true, false);

		if (!goingRight && this.isFlipX())
			this.flip(true, false);
	}

}
