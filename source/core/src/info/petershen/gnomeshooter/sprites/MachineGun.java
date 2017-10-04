package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;

public class MachineGun extends WeaponBase {

	public MachineGun(World world, PlayScreen screen) {

		super(world, screen);
		this.world = world;
		this.screen = screen;

		this.initAmmo = 300;
		this.initClip = 100;

		this.reloadSound = screen.game.assets.loadlong;

		this.originX = 0;
		this.originY = 3.5f;
		this.posXOffset = -10;
		this.posYOffset = -14;
		this.gunWidth = 40;
		this.gunHeight = 23;
		this.fpRadius = (float) Math.sqrt(800);
		this.fpxright = -3;
		this.fpyright = 25;
		this.fpxleft = -2;
		this.fpyleft = 7;
		this.fireRate = 600;
		this.clip = initClip;
		this.ammo = initAmmo;
		this.reloadTime = 2.4f;
		this.clipsize = 100;
		this.minDeviant = -4;
		this.maxDeviant = 4;

		this.region = screen.game.assets.chain;
		setRegion(region);
		this.bulletOffRight = -4;
		this.bulletOffLeft = 4;
		this.bulletSpeed = 40;
		this.bulletDamage = 25;
		this.bulletLife = 3;
		this.bulletHealth = 2;
		this.radius = 0;
		this.bulletWidth = 4.5f;
		this.bulletHeight = 3.5f;
		this.b2radius = 0;
		this.b2width = 4.5f;
		this.b2height = 3.5f;

		this.circleBullets = false;
		this.color = Color.DARK_GRAY;

		this.muzzleHeight = 22;
		this.muzzleWidth = 22;
		this.muzzleFlash = screen.game.assets.muzzleFlash;

		this.flashYLeft = 6;
		this.flashYRight = -2;

		this.flash = new MuzzleFlash(muzzleFlash, muzzleWidth, muzzleHeight);

		this.isAuto = true;

		this.shotSound = screen.game.assets.akShot;

		setBounds(screen.player.b2body.getWorldCenter().x + this.posXOffset / GnomeShooter.PPM,
				screen.player.b2body.getWorldCenter().y + this.posYOffset / GnomeShooter.PPM,
				gunWidth / GnomeShooter.PPM, gunHeight / GnomeShooter.PPM);

		setOrigin(
				(screen.arm.getX() * 150 + screen.arm.getOriginX() * 150 - this.getX() * 150) / GnomeShooter.PPM
						+ originX / GnomeShooter.PPM,
				(screen.arm.getY() * 150 + screen.arm.getOriginX() * 150 - this.getY() * 150) / GnomeShooter.PPM
						+ originY / GnomeShooter.PPM);

	}
}
