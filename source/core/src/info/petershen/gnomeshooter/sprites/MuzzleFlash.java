package info.petershen.gnomeshooter.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.tools.AssetLoader;

public class MuzzleFlash extends Sprite{
	
	private TextureRegion region;
	
	public MuzzleFlash(TextureRegion region, float width, float height) {
		
		this.setRegion(region);
		this.setSize(width / GnomeShooter.PPM, height / GnomeShooter.PPM);
	}
	
	public void update(SpriteBatch batch) {
		this.draw(batch);
	}

}
