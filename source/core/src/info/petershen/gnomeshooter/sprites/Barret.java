package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;
import info.petershen.gnomeshooter.tools.AssetLoader;

public class Barret extends WeaponBase {

	public Barret(World world, PlayScreen screen) {
		super(world, screen);
		this.world = world;
		this.screen = screen;

		this.reloadSound = AssetLoader.loadlong;

		this.originX = 0;
		this.originY = 5;
		this.posXOffset = -10;
		this.posYOffset = -14;
		this.gunWidth = 46;
		this.gunHeight = 25;
		this.fpRadius = (float) Math.sqrt(1200);
		this.fpxright = -6;
		this.fpyright = 45;
		this.fpxleft = -6;
		this.fpyleft = 30;
		this.fireRate = 350;
		this.clip = 8;
		this.ammo = 32;
		this.clipsize = 8;
		this.reloadTime = 2.4f;
		this.minDeviant = -1f;
		this.maxDeviant = 1;

		this.region = AssetLoader.barret;
		setRegion(region);
		this.bulletOffRight = -4;
		this.bulletOffLeft = 4;
		this.bulletSpeed = 40;
		this.bulletDamage = 50;
		this.bulletLife = 5;
		this.bulletHealth = 5;
		this.radius = 0;
		this.bulletWidth = 8;
		this.bulletHeight = 5;
		this.b2radius = 0;
		this.b2width = 8;
		this.b2height = 5;
		this.shotSound = AssetLoader.barretShot;
		this.circleBullets = false;
		this.color = Color.WHITE;

		this.muzzleHeight = 25;
		this.muzzleWidth = 35;
		this.muzzleFlash = AssetLoader.muzzleFlash;

		this.flashYLeft = 9;
		this.flashYRight = -9;

		this.flash = new MuzzleFlash(muzzleFlash, muzzleWidth, muzzleHeight);

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
