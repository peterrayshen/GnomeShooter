package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import info.petershen.gnomeshooter.GnomeShooter;

public class MuzzleFlash extends Sprite {

	public MuzzleFlash(TextureRegion region, float width, float height) {

		this.setRegion(region);
		this.setSize(width / GnomeShooter.PPM, height / GnomeShooter.PPM);
	}

	public void update(SpriteBatch batch) {
		this.draw(batch);
	}

}
