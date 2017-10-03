package info.petershen.gnomeshooter;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import info.petershen.gnomeshooter.screens.PlayScreen;
import info.petershen.gnomeshooter.screens.ShopScreen;
import info.petershen.gnomeshooter.tools.AssetStorage;

public class GnomeShooter extends Game {
	
	
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 240;
	
	public static final float PPM = 150;
	
	public static final short PLAYER_INDEX = -3;
	public static final short GROUND_INDEX = -4;
	public static final short ENEMY_INDEX = -2;
	public static final short BOUNDARY_INDEX = -1;
	
	public AssetStorage assets;
	
	
	public PlayScreen playScreen;
	public ShopScreen shopScreen;
	
	public SpriteBatch batch;
	Texture img;

	@Override
	public void create() {
		
		batch = new SpriteBatch();
		
		assets = new AssetStorage();
		
		playScreen = new PlayScreen(this);
		shopScreen = new ShopScreen(this);
		
	
		
		setScreen(playScreen);
		
	}


	@Override
	public void render() {
		super.render();
	}
}
