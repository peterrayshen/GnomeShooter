package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;
import info.petershen.gnomeshooter.tools.AssetLoader;

public class Pistol extends WeaponBase {

	public Pistol(World world, PlayScreen screen) {
		super(world, screen);
		this.world = world;
		this.screen = screen;

		this.reloadSound = AssetLoader.loadshort;

		this.originX = 1.5f;
		this.originY = 0;
		this.posXOffset = 10;
		this.posYOffset = -11;
		this.gunWidth = 16;
		this.gunHeight = 13;
		this.fpRadius = (float) Math.sqrt(700);
		this.fpxright = 0;
		this.fpyright = 25;
		this.fpxleft = -7;
		this.fpyleft = 25;
		this.fireRate = 575;
		this.clip = 12;
		this.ammo = 48;
		this.clipsize = 12;
		this.reloadTime = 1;
		this.minDeviant = -5;
		this.maxDeviant = 5;
		this.cost = 1000;

		this.region = AssetLoader.pistol1;
		setRegion(region);
		this.bulletOffRight = 4;
		this.bulletOffLeft = -4;
		this.bulletSpeed = 40;
		this.bulletDamage = 14;
		this.bulletLife = 5;
		this.bulletHealth = 1;
		this.radius = 2;
		this.bulletWidth = 2;
		this.bulletHeight = 1;
		this.b2radius = 2;
		this.b2width = 2;
		this.b2height = 1;

		this.circleBullets = true;
		this.color = Color.DARK_GRAY;

		this.isAuto = false;

		setBounds(screen.player.b2body.getWorldCenter().x + this.posXOffset / GnomeShooter.PPM,
				screen.player.b2body.getWorldCenter().y + this.posYOffset / GnomeShooter.PPM, gunWidth / GnomeShooter.PPM,
				gunHeight / GnomeShooter.PPM);

		setOrigin(
				(screen.arm.getX() * 150 + screen.arm.getOriginX() * 150 - this.getX() * 150) / GnomeShooter.PPM
						+ originX / GnomeShooter.PPM,
				(screen.arm.getY() * 150 + screen.arm.getOriginX() * 150 - this.getY() * 150) / GnomeShooter.PPM
						+ originY / GnomeShooter.PPM);

	}

	
}
