package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;
import info.petershen.gnomeshooter.tools.AssetLoader;

public class Revolver extends WeaponBase {



	public Revolver(World world, PlayScreen screen) {
		super(world, screen);
		this.world = world;
		this.screen = screen;


		
		this.reloadSound = AssetLoader.loadmed;

		this.originX = 0;
		this.originY = 3;
		this.posXOffset = 10;
		this.posYOffset = -11;
		this.gunWidth = 20;
		this.gunHeight = 16;
		this.fpRadius = (float) Math.sqrt(1000);
		this.fpxright = -1;
		this.fpyright = 25;
		this.fpxleft = -8;
		this.fpyleft = 25;
		this.fireRate = 400;
		this.clip = 6;
		this.ammo = 36;
		this.reloadTime = 1.7f;
		this.clipsize = 6;
		this.minDeviant = -5;
		this.maxDeviant = 5;

		this.region = AssetLoader.deagle;
		setRegion(region);
		this.bulletOffRight = 4;
		this.bulletOffLeft = -4;

		this.bulletSpeed = 40;
		this.bulletDamage = 78;
		this.bulletLife = 5;
		this.bulletHealth = 2;
		this.radius = 3;
		this.bulletWidth = 2;
		this.bulletHeight = 1;
		this.b2radius = 3.5f;
		this.b2width = 2;
		this.b2height = 1;

		this.circleBullets = true;
		this.color = Color.LIGHT_GRAY;
		
		this.shotSound = AssetLoader.revShot;

		this.flashYRight = -3;
		this.flashYLeft = 3.5f;
		this.muzzleHeight = 20;
		this.muzzleWidth = 20;
		this.muzzleFlash = AssetLoader.muzzleFlash;
		
		this.isAuto = false;
		
		this.flash = new MuzzleFlash(muzzleFlash, muzzleWidth, muzzleHeight);
		
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
