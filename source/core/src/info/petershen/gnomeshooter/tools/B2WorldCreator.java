package info.petershen.gnomeshooter.tools;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;

public class B2WorldCreator {

	private static float rectX1 = 0.0f;
	private static float rectY1 = 0.0f;
	private static float rectWidth1 = 2880.0f;
	private static float rectHeight1 = 32.0f;

	private static float rectX2 = 512.0f;
	private static float rectY2 = 32.0f;
	private static float rectWidth2 = 32.0f;
	private static float rectHeight2 = 384.0f;

	private static float rectX3 = 2432.0f;
	private static float rectY3 = 32.0f;
	private static float rectWidth3 = 32.0f;
	private static float rectHeight3 = 384.0f;

	private static float rectX4 = 544.0f;
	private static float rectY4 = 384.0f;
	private static float rectWidth4 = 1888.0f;
	private static float rectHeight4 = 32.0f;
	
	public ArrayList<Sprite> ground;
	private PlayScreen screen;

	public B2WorldCreator(World world, PlayScreen screen) {
		
		ground = new ArrayList<Sprite>();
	
		this.screen = screen;
		defineBody(world);
		createGround();

	}

	private void defineBody(World world) {
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((rectX1 + rectWidth1 / 2) / GnomeShooter.PPM,
				(rectY1 + rectHeight1 / 2) / GnomeShooter.PPM);

		body = world.createBody(bdef);
		shape.setAsBox((rectWidth1 / 2) / GnomeShooter.PPM, (rectHeight1 / 2) / GnomeShooter.PPM);
		fdef.shape = shape;
		fdef.filter.groupIndex = GnomeShooter.GROUND_INDEX;
		body.createFixture(fdef).setUserData("ground");


		
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((rectX2 + rectWidth2 / 2) / GnomeShooter.PPM,
				(rectY2 + rectHeight2 / 2) / GnomeShooter.PPM);

		body = world.createBody(bdef);
		shape.setAsBox((rectWidth2 / 2) / GnomeShooter.PPM, (rectHeight2 / 2) / GnomeShooter.PPM);
		fdef.shape = shape;
		fdef.filter.groupIndex = GnomeShooter.BOUNDARY_INDEX;
		body.createFixture(fdef).setUserData("boundary");

		
	
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((rectX3 + rectWidth3 / 2) / GnomeShooter.PPM,
				(rectY3 + rectHeight3 / 2) / GnomeShooter.PPM);

		body = world.createBody(bdef);
		shape.setAsBox((rectWidth3 / 2) / GnomeShooter.PPM, (rectHeight3 / 2) / GnomeShooter.PPM);
		fdef.shape = shape;
		fdef.filter.groupIndex = GnomeShooter.BOUNDARY_INDEX;
		body.createFixture(fdef).setUserData("boundary");


		
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((rectX4 + rectWidth4 / 2) / GnomeShooter.PPM,
				(rectY4 + rectHeight4 / 2) / GnomeShooter.PPM);

		body = world.createBody(bdef);
		shape.setAsBox((rectWidth4 / 2) / GnomeShooter.PPM, (rectHeight4 / 2) / GnomeShooter.PPM);
		fdef.shape = shape;
		fdef.filter.groupIndex = GnomeShooter.BOUNDARY_INDEX;
		body.createFixture(fdef).setUserData("boundary");

	}
	
	private void createGround() { 
		
		for (int i = 0; i < 5; i++) {
			
			Sprite sprite = new Sprite();
			sprite.setRegion(screen.game.assets.ground);
			sprite.setBounds((250 + 500 * i) / GnomeShooter.PPM , -280 / GnomeShooter.PPM, 500 / GnomeShooter.PPM, 500 / GnomeShooter.PPM);
			System.out.println((250 + 500 * i) / GnomeShooter.PPM );
			
			ground.add(sprite);
		}
		
	}

}
