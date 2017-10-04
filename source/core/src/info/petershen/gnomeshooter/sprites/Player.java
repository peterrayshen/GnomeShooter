package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;

public class Player extends Sprite {

	public static float maxSpeed = 2.5f;
	public static float playerWidth = 25f;
	public static float playerHeight = 50f;
	public static int linearDamping = 15;
	public static float startingX = 700f;
	public static float startingY = 30f;
	public static float playerBoxYOffset = .003f;
	public static float boxWidth = 13;
	public static float boxHeight = 23;
	public static float initialHealth = 100;

	public enum State {
		FALLING, JUMPING, STANDING, RUNNINGFORWARD, RUNNINGBACKWARD
	};

	public State currentState;
	public State previousState;
	private float stateTimer;
	public int timesJumped, speedLevel, healthLevel, jumpLevel, healthcost, speedcost, jumpcost;
	public boolean touchingGround, touchingEnemy;
	public float health;
	public World world;
	public Body b2body;
	public int enemiesTouching = 0;

	private PlayScreen screen;

	public Player(World world, PlayScreen screen) {
		this.screen = screen;

		this.health = initialHealth;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		this.world = world;
		definePlayer();
		setBounds(0, 0, playerWidth / GnomeShooter.PPM, playerHeight / GnomeShooter.PPM);
		setRegion(screen.game.assets.astroJump1);

	}

	public void definePlayer() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(startingX / GnomeShooter.PPM, startingY / GnomeShooter.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(boxWidth / GnomeShooter.PPM, boxHeight / GnomeShooter.PPM);

		fdef.shape = shape;
		fdef.filter.groupIndex = GnomeShooter.PLAYER_INDEX;

		b2body.createFixture(fdef).setUserData(this);

		MassData mdata = new MassData();
		mdata.mass = 500 / GnomeShooter.PPM;
		b2body.setMassData(mdata);

	}

	public State getState() {
		if (b2body.getLinearVelocity().y > 0)
			return State.JUMPING;
		else if (((b2body.getLinearVelocity().x > 0 && b2body.getLinearVelocity().y <= 0)
				&& screen.mouse.x / GnomeShooter.PPM > this.getX() / GnomeShooter.PPM)
				|| ((b2body.getLinearVelocity().x < 0 && b2body.getLinearVelocity().y <= 0)
						&& screen.mouse.x / GnomeShooter.PPM < this.getX() / GnomeShooter.PPM))
			return State.RUNNINGFORWARD;
		else if (((b2body.getLinearVelocity().x < 0 && b2body.getLinearVelocity().y <= 0)
				&& screen.mouse.x / GnomeShooter.PPM > this.getX() / GnomeShooter.PPM)
				|| ((b2body.getLinearVelocity().x > 0 && b2body.getLinearVelocity().y <= 0)
						&& screen.mouse.x / GnomeShooter.PPM < this.getX() / GnomeShooter.PPM))
			return State.RUNNINGFORWARD;
		else if (b2body.getLinearVelocity().y < 0)
			return State.FALLING;
		else
			return State.STANDING;
	}

	public void update(float delta) {

		if (touchingEnemy) {
			this.setColor(Color.RED);
			health -= delta * 40 * enemiesTouching;
			maxSpeed = 1.2f;
		}

		else {
			maxSpeed = 2.5f;
			this.setColor(Color.WHITE);
		}

		if (this.b2body.getLinearVelocity().x > maxSpeed)
			this.b2body.setLinearVelocity(maxSpeed, this.b2body.getLinearVelocity().y);
		if (this.b2body.getLinearVelocity().x < -maxSpeed)
			this.b2body.setLinearVelocity(-maxSpeed, this.b2body.getLinearVelocity().y);
		this.b2body.setLinearDamping(linearDamping);

		setPosition(b2body.getPosition().x - getWidth() / 2,
				b2body.getPosition().y - playerBoxYOffset - getHeight() / 2);
		setRegion(getFrame(delta));
	}

	public TextureRegion getFrame(float delta) {
		currentState = getState();

		TextureRegion region;
		switch (currentState) {
		case JUMPING:
			region = screen.game.assets.astroJumpAnimation.getKeyFrame(stateTimer);
			break;
		case RUNNINGFORWARD:
			region = screen.game.assets.astroWalkFAnimation.getKeyFrame(stateTimer, true);
			break;
		case RUNNINGBACKWARD:
			region = screen.game.assets.astroWalkBAnimation.getKeyFrame(stateTimer, true);
			break;
		case FALLING:
			region = screen.game.assets.astroJump2;
			break;
		case STANDING:
		default:
			region = screen.game.assets.astroJump1;
			break;

		}

		if (screen.mouse.x / GnomeShooter.PPM < (this.getX() + this.getWidth() / 2) / GnomeShooter.PPM
				&& !region.isFlipX()) {
			region.flip(true, false);
		} else if (screen.mouse.x / GnomeShooter.PPM > (this.getX() + this.getWidth() / 2) / GnomeShooter.PPM
				&& region.isFlipX()) {
			region.flip(true, false);
		}

		if (currentState == previousState)
			stateTimer = stateTimer + delta;
		else
			stateTimer = 0;

		previousState = currentState;

		return region;
	}

	public void upgradeSpeed() {
		speedLevel++;
	}

	public void upgradeHealth() {
		healthLevel++;
	}

	public void upgradeJump() {
		jumpLevel++;
	}

	public void reset() {
		health = initialHealth;
		b2body.setTransform(startingX / GnomeShooter.PPM, startingY / GnomeShooter.PPM, 0);
		b2body.setLinearVelocity(0, 0);
		b2body.setAngularVelocity(0);
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
	}
}
