package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;

public class Ak extends WeaponBase {


	boolean circleBullets = false;

	public Ak(World world, PlayScreen screen) {
		super(world, screen);
		
		
		this.world = world;
		this.screen = screen;
		
		this.reloadSound = screen.game.assets.loadmed;

		this.originX = 0;
		this.originY = 2;
		this.posXOffset = -10;
		this.posYOffset = -14;
		this.gunWidth = 40;
		this.gunHeight = 18;
		this.fpRadius = (float) Math.sqrt(950);
		this.fpxright = -1;
		this.fpyright = 25;
		this.fpxleft = -10;
		this.fpyleft = 25;
		this.fireRate = 650;
		this.clip = 30;
		this.ammo = 120;
		this.clipsize = 30;
		this.reloadTime = 1.7f;
		this.minDeviant = -4;
		this.maxDeviant = 4;

		this.region = screen.game.assets.ak;
		setRegion(region);
		this.bulletOffRight = -4f;
		this.bulletOffLeft = 3.5f;
		this.bulletSpeed = 40;
		this.bulletDamage = 18;
		this.bulletLife = 5;
		this.bulletHealth = 3;
		this.radius = 0;
		this.bulletWidth = 5;
		this.bulletHeight = 3;
		this.b2radius = 0;
		this.b2width = 2;
		this.b2height = 1;

		this.circleBullets = false;
		this.color = Color.WHITE;
		
		this.isAuto = true;
		
		this.muzzleHeight = 15;
		this.muzzleWidth = 15;
		this.muzzleFlash = screen.game.assets.muzzleFlash;
		
		this.flashYLeft = 2;
		
		this.flash = new MuzzleFlash(muzzleFlash, muzzleWidth, muzzleHeight);
		this.shotSound = screen.game.assets.machineShot;
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
