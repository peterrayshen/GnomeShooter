package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;
import info.petershen.gnomeshooter.tools.AssetLoader;

public class Bullet extends Sprite {

	private float lifeTime;
	public float lifeTimer;
	private float dx, dy;

	public World world;

	public Body b2body;

	public float damage = 8;

	private float speed;
	public float health;
	private float radius, height, width;
	private float b2radius, b2height, b2width;
	private Color color;
	private boolean isShotgun;

	private PlayScreen screen;

	public boolean remove, isCircle;

	public Bullet(World world, float x, float y, float radians, PlayScreen screen, float damage, float speed,
			float lifeTime, float health, float radius, float b2radius, Color color) {
		this.screen = screen;
		setRegion(AssetLoader.pistol1);
		this.world = world;
		defineCircleBullet(x, y, radians);
		this.speed = speed / GnomeShooter.PPM;
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;
		b2body.setLinearVelocity(dx, dy);
		remove = false;
		lifeTimer = 0;
		this.lifeTime = lifeTime;
		this.health = health;
		this.radius = radius;
		this.b2radius = radius;
		this.damage = damage;
		isCircle = true;
	}

	public Bullet(World world, float x, float y, float radians, PlayScreen screen, float damage, float speed,
			float lifeTime, float health, float width, float height, float b2width, float b2height, Color color) {
		this.screen = screen;
		setRegion(AssetLoader.pistol1);
		this.world = world;
		defineRectBullet(x, y, radians);
		this.speed = speed / GnomeShooter.PPM;
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;

		b2body.setLinearVelocity(dx, dy);
		remove = false;
		lifeTimer = 0;
		this.lifeTime = lifeTime;
		this.health = health;
		this.height = height;
		this.width = width;
		this.b2height = b2height;
		this.b2width = b2width;
		this.damage = damage;
		this.color = color;
		isCircle = false;
	}

	public Bullet(World world, float x, float y, float radians, PlayScreen screen, float damage, float speed,
			float lifeTime, float health, float width, float height, float b2width, float b2height, Color color,
			boolean isShotgun) {
		this.screen = screen;
		setRegion(AssetLoader.pistol1);
		this.world = world;
		defineRectBullet(x, y, radians);
		this.speed = speed / GnomeShooter.PPM;
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;

		b2body.setLinearVelocity(dx, dy);
		remove = false;
		lifeTimer = 0;
		this.lifeTime = lifeTime;
		this.health = health;
		this.height = height;
		this.width = width;
		this.b2height = b2height;
		this.b2width = b2width;
		this.damage = damage;
		this.color = color;
		isCircle = false;
		this.isShotgun = true;
	}

	public void defineCircleBullet(float x, float y, float radians) {
		BodyDef bdef = new BodyDef();
		bdef.position.set(x, y);
		bdef.type = BodyDef.BodyType.KinematicBody;
		b2body = world.createBody(bdef);
		b2body.setTransform(b2body.getWorldCenter(), radians);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(2 / GnomeShooter.PPM);
		fdef.shape = shape;
		fdef.filter.groupIndex = -1;
		b2body.createFixture(fdef).setUserData(this);

		MassData mdata = new MassData();
		mdata.mass = 1 / GnomeShooter.PPM;
		b2body.setMassData(mdata);
		b2body.setBullet(true);

	}

	public void defineRectBullet(float x, float y, float radians) {
		BodyDef bdef = new BodyDef();
		bdef.position.set(x, y);
		bdef.type = BodyDef.BodyType.KinematicBody;
		b2body = world.createBody(bdef);
		b2body.setTransform(b2body.getWorldCenter(), radians);

		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(b2width, b2height);
		fdef.shape = shape;
		fdef.filter.groupIndex = -1;
		b2body.createFixture(fdef).setUserData(this);

		MassData mdata = new MassData();
		mdata.mass = 1 / GnomeShooter.PPM;
		b2body.setMassData(mdata);
		b2body.setBullet(true);

	}

	public void kill() {
		this.b2body.destroyFixture(b2body.getFixtureList().first());

	}

	public void update(float delta) {
		if (isShotgun)
			damage -= delta * 35;
		if (health <= 0) {
			remove = true;
		}

		lifeTimer = lifeTimer + delta;
		if (lifeTimer > lifeTime) {
			remove = true;
		}
		this.setX(this.b2body.getPosition().x);
		this.setY(this.b2body.getPosition().y);
		this.setRotation(MathUtils.radiansToDegrees * this.b2body.getTransform().getRotation());
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void drawBullet(ShapeRenderer sr) {
		if (isCircle) {
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.DARK_GRAY);
			sr.circle(b2body.getPosition().x, b2body.getPosition().y, (float) radius / GnomeShooter.PPM, 100);
			sr.end();
		} else {
			sr.begin(ShapeType.Filled);
			sr.setColor(color);
			sr.rect(b2body.getPosition().x, b2body.getPosition().y, 0, 0, width / GnomeShooter.PPM,
					height / GnomeShooter.PPM, 1, 1, b2body.getTransform().getRotation() * MathUtils.radiansToDegrees);
			sr.end();
		}

	}
}
