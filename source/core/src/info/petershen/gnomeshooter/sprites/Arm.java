package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;
import info.petershen.gnomeshooter.tools.AssetLoader;

public class Arm extends Sprite {

	private float rotation;
	private PlayScreen screen;

	public Arm(World world, PlayScreen screen) {
		this.screen = screen;
		setBounds(screen.player.b2body.getWorldCenter().x - 4 / GnomeShooter.PPM,
				screen.player.b2body.getWorldCenter().y - 10 / GnomeShooter.PPM, 16 / GnomeShooter.PPM,
				13 / GnomeShooter.PPM);
		setRegion(AssetLoader.arm);
		setOrigin(4 / GnomeShooter.PPM, 5 / GnomeShooter.PPM);

	}

	public void update(float delta) {

		setPosition(screen.player.b2body.getWorldCenter().x - 4 / GnomeShooter.PPM,
				screen.player.b2body.getWorldCenter().y - 10 / GnomeShooter.PPM);
		rotation = MathUtils.radiansToDegrees
				* MathUtils.atan2(screen.mouse.y - this.getY(), screen.mouse.x - this.getX());
		setRotation(rotation);

	}

}
