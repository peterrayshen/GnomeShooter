package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;

public class Shotgun extends WeaponBase {

	private int pellets;

	public Shotgun(World world, PlayScreen screen) {

		super(world, screen);

		this.initAmmo = 60;
		this.initClip = 12;

		this.world = world;
		this.screen = screen;

		this.reloadSound = screen.game.assets.loadmed;

		this.originX = 0;
		this.originY = 0;
		this.posXOffset = -10;
		this.posYOffset = -14;
		this.gunWidth = 40;
		this.gunHeight = 18;
		this.fpRadius = (float) Math.sqrt(900);
		this.fpxright = -2;
		this.fpyright = 45;
		this.fpxleft = -8;
		this.fpyleft = 30;
		this.fireRate = 60;
		this.clip = initClip;
		this.ammo = initAmmo;
		this.clipsize = 12;
		this.reloadTime = 1.7f;
		this.minDeviant = -11;
		this.maxDeviant = 11;

		this.region = screen.game.assets.shotgun;
		setRegion(region);
		this.bulletOffRight = -2f;
		this.bulletOffLeft = 3f;
		this.bulletSpeed = 40;
		this.bulletDamage = 18;
		this.bulletLife = 0.2f;
		this.bulletHealth = 3;
		this.radius = 2;
		this.bulletWidth = 5;
		this.bulletHeight = 3;
		this.b2radius = 2;
		this.b2width = 2;
		this.b2height = 1;
		this.pellets = 7;

		this.circleBullets = true;
		this.color = Color.WHITE;

		this.isAuto = false;

		this.muzzleHeight = 26;
		this.muzzleWidth = 26;
		this.muzzleFlash = screen.game.assets.muzzleFlash;

		this.flashYLeft = 5.5f;
		this.flashYRight = -4;

		this.flash = new MuzzleFlash(muzzleFlash, muzzleWidth, muzzleHeight);
		this.shotSound = screen.game.assets.shotgunShot;
		setBounds(screen.player.b2body.getWorldCenter().x + this.posXOffset / GnomeShooter.PPM,
				screen.player.b2body.getWorldCenter().y + this.posYOffset / GnomeShooter.PPM,
				gunWidth / GnomeShooter.PPM, gunHeight / GnomeShooter.PPM);

		setOrigin(
				(screen.arm.getX() * 150 + screen.arm.getOriginX() * 150 - this.getX() * 150) / GnomeShooter.PPM
						+ originX / GnomeShooter.PPM,
				(screen.arm.getY() * 150 + screen.arm.getOriginX() * 150 - this.getY() * 150) / GnomeShooter.PPM
						+ originY / GnomeShooter.PPM);

	}

	public void shoot() {

		if (!reloading) {

			if (fireTimer > 1 / (fireRate / 60f)) {
				isShooting = true;
				shotSound.play();
				for (int i = 0; i < pellets; i++) {
					deviant = MathUtils.random(minDeviant, maxDeviant);
					if (region.isFlipY())
						screen.bullets
								.add(new Bullet(world, firePoint.x, firePoint.y + bulletOffRight / GnomeShooter.PPM,
										this.getRotation() * MathUtils.degreesToRadians
												+ MathUtils.degreesToRadians * deviant,
										screen, bulletDamage, bulletSpeed, bulletLife, bulletHealth, bulletWidth,
										bulletHeight, b2width, b2height, color, true));
					else
						screen.bullets
								.add(new Bullet(world, firePoint.x, firePoint.y + bulletOffLeft / GnomeShooter.PPM,
										this.getRotation() * MathUtils.degreesToRadians
												+ MathUtils.degreesToRadians * deviant,
										screen, bulletDamage, bulletSpeed, bulletLife, bulletHealth, bulletWidth,
										bulletHeight, b2width, b2height, color, true));
				}
				fireTimer = 0;
				clip--;
				if (clip == 0)
					reloading = true;

			}

		}

	}

}
