package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;

public class WeaponBase extends Sprite {

	public TextureRegion region, muzzleFlash;
	public PlayScreen screen;
	public Vector2 firePoint;
	public World world;
	public MuzzleFlash flash;
	public boolean isShooting;
	public float fireTimer, muzzleWidth, muzzleHeight, flashXLeft, flashYLeft, flashXRight, flashYRight;;

	public int cost, clipsize, level;
	public Sound shotSound;
	public Sound reloadSound;
	public float originX, originY, posXOffset, posYOffset, gunWidth, gunHeight, fpRadius, fpxright, fpyright, fpxleft,
			fpyleft, fireRate, bulletOffRight, bulletOffLeft;

	public float bulletDamage, bulletSpeed, bulletLife, bulletHealth, radius, bulletWidth, bulletHeight, b2radius,
			b2width, b2height, reloadTime, reloadTimer;

	public boolean circleBullets, isAuto, reloading;
	public float minDeviant, maxDeviant;
	public int clip, ammo;
	public float deviant;
	public Color color;

	public WeaponBase(World world, PlayScreen screen) {
		
		shotSound = screen.game.assets.pistolShot; 
		reloadSound = screen.game.assets.loadmed;
		
		fireTimer = 0;
		isShooting = false;
		setBounds(screen.player.b2body.getWorldCenter().x + this.posXOffset / GnomeShooter.PPM,
				screen.player.b2body.getWorldCenter().y + this.posYOffset / GnomeShooter.PPM, gunWidth / GnomeShooter.PPM,
				gunHeight / GnomeShooter.PPM);

		setOrigin(
				(screen.arm.getX() * 150 + screen.arm.getOriginX() * 150 - this.getX() * 150) / GnomeShooter.PPM
						+ originX / GnomeShooter.PPM,
				(screen.arm.getY() * 150 + screen.arm.getOriginX() * 150 - this.getY() * 150) / GnomeShooter.PPM
						+ originY / GnomeShooter.PPM);
		this.flash = new MuzzleFlash(screen.game.assets.muzzleFlash, 10, 10);
	}

	public void shoot() {
		deviant = MathUtils.random(minDeviant, maxDeviant);
		if (!reloading && clip != 0) {
			if (circleBullets) {
				if (fireTimer > 1 / (fireRate / 60f)) {
					isShooting = true;
					shotSound.play();
					if (region.isFlipY())
						screen.bullets.add(new Bullet(world, firePoint.x, firePoint.y + bulletOffLeft / GnomeShooter.PPM,
								this.getRotation() * MathUtils.degreesToRadians + MathUtils.degreesToRadians * deviant,
								screen, bulletDamage, bulletSpeed, bulletLife, bulletHealth, radius, b2radius, color));
					else
						screen.bullets
								.add(new Bullet(world, firePoint.x, firePoint.y + bulletOffRight / GnomeShooter.PPM,
										this.getRotation() * MathUtils.degreesToRadians
												+ MathUtils.degreesToRadians * deviant,
										screen, bulletDamage, bulletSpeed, bulletLife, bulletHealth, radius, b2radius,
										color));
					fireTimer = 0;
					clip--;
					if (clip == 0 && ammo > 0)
						reload();

				}
			}

			else {
				if (fireTimer > 1 / (fireRate / 60f)) {
					isShooting = true;
					shotSound.play();
					if (region.isFlipY())
						screen.bullets
								.add(new Bullet(world, firePoint.x, firePoint.y + bulletOffRight / GnomeShooter.PPM,
										this.getRotation() * MathUtils.degreesToRadians
												+ MathUtils.degreesToRadians * deviant,
										screen, bulletDamage, bulletSpeed, bulletLife, bulletHealth, bulletWidth,
										bulletHeight, b2width, b2height, color));
					else
						screen.bullets.add(new Bullet(world, firePoint.x, firePoint.y + bulletOffLeft / GnomeShooter.PPM,
								this.getRotation() * MathUtils.degreesToRadians + MathUtils.degreesToRadians * deviant,
								screen, bulletDamage, bulletSpeed, bulletLife, bulletHealth, bulletWidth, bulletHeight,
								b2width, b2height, color));
					fireTimer = 0;
					if (clip > 0)
						clip--;
					else
						clip = 0;
					if (clip == 0 && ammo > 0)
						reload();
				}

			}
		}
		else if (clip == 0 && !reloading) {
			this.isAuto = false;
			screen.game.assets.dryfire.play();
		}

	}

	public void reset() {
		isShooting = false;
	}

	public void update(float delta) {

		if (reloading) {
			reloadTimer += delta;
			if (reloadTimer >= reloadTime) {
				reloading = false;
				int prevAmmo = ammo;
				
					ammo -= clipsize - clip;
				if (ammo < 0)
					ammo = 0;
				if (prevAmmo >= clipsize)
					clip = clipsize;
				else
					clip = prevAmmo;
				reloadTimer = 0;
			}
		}

		fireTimer += delta;

		setPosition(screen.player.b2body.getWorldCenter().x + posXOffset / GnomeShooter.PPM,
				screen.player.b2body.getWorldCenter().y + posYOffset / GnomeShooter.PPM);

		setRotation(screen.arm.getRotation());

		if (screen.mouse.x / GnomeShooter.PPM < (screen.player.getX() + screen.player.getWidth() / 2) / GnomeShooter.PPM
				&& !region.isFlipY()) {
			region.flip(false, true);
			setRegion(region);
		} else if (screen.mouse.x / GnomeShooter.PPM > (screen.player.getX() + screen.player.getWidth() / 2)
				/ GnomeShooter.PPM && region.isFlipY()) {
			region.flip(false, true);
			setRegion(region);

		}
		if (!region.isFlipY()) {

			firePoint = new Vector2(
					getX() + getOriginX()
							+ ((float) (fpRadius * MathUtils.cos((MathUtils.degreesToRadians * this.getRotation())
									+ MathUtils.atan2(fpxright, fpyright))) / GnomeShooter.PPM),
					getY() + getOriginY() + ((float) (fpRadius) * MathUtils.sin(
							(this.getRotation() * MathUtils.degreesToRadians) + MathUtils.atan2(fpxright, fpyright))
							/ GnomeShooter.PPM));
			flash.setPosition(firePoint.x + flashXRight / GnomeShooter.PPM, firePoint.y + flashYRight / GnomeShooter.PPM);
			flash.setRotation(this.getRotation());

		} else {

			firePoint = new Vector2(
					getX() + getOriginX()
							+ ((float) (fpRadius * MathUtils.cos((MathUtils.degreesToRadians * this.getRotation())
									+ MathUtils.atan2(fpxleft, fpyleft))) / GnomeShooter.PPM),
					getY() + getOriginY() + ((float) (fpRadius * MathUtils
							.sin((this.getRotation() * MathUtils.degreesToRadians) + MathUtils.atan2(fpxleft, fpyleft)))
							/ GnomeShooter.PPM));
			flash.setPosition(firePoint.x + flashXLeft / GnomeShooter.PPM, firePoint.y + flashYLeft / GnomeShooter.PPM);
			flash.setRotation(this.getRotation());

		}

	}

	public void upgrade() {
		level++;
		cost += cost * 2;
	}

	public void reload() {
		if (ammo > 0 && !reloading) {
			reloadSound.play();
			reloading = true;
			System.out.println("reload");
		}

	}
}
