package info.petershen.gnomeshooter.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetStorage {

	public static Texture gunTexture, astroTexture;

	public Texture flashTexture;

	public TextureRegion standing1, standing2, standing3, standing4, standing5, standing6, standing7, standing8,
			standing9, standing10, standing11, standing12, standing13, standing14, standing15, standing16, standing17,
			standing18, standing19, standing20, standing21, standing22;

	public TextureRegion running1, running2, running3, running4, running5, running6, running7, running8, running9,
			running10, running11, running12, running13, running14, running15, running16, running17, running18,
			running19, running20, running21, running22, running23, running24, running25, running26, running27;

	public TextureRegion jumping1, jumping2, jumping3, jumping4, jumping5;

	public TextureRegion falling1, falling2, falling3, falling4;

	public TextureRegion astronautWalk1, astronautWalk2, astronautWalk3, astronautWalk4, astronautWalk5, astronautWalk6,
			astronautWalk7;

	public TextureRegion astroJump1, astroJump2;

	public TextureRegion gnome1, gnome2, gnome3;

	public Animation<TextureRegion> standingAnimation, runningAnimation, jumpingAnimation, fallingAnimation,
			astroWalkFAnimation, astroWalkBAnimation, astroJumpAnimation;
	public Animation<TextureRegion> gnomeWalk;

	public TextureRegion pistol1, pistol1Cock, arm, muzzleFlash, enemy1, gnome, smg, ak, chain, deagle, barret, shotgun;

	public Sound pistolShot, bang, akShot, barretShot, revShot, smgShot, machineShot, shotgunShot, money, dryfire,
			loadshort, loadmed, loadlong;
	public Music future, wicked;

	private Texture gnomes;

	public BitmapFont size15, size20, size24, size27, size32, size38;

	private AssetManager manager;

	public AssetStorage() {
		loadAssets();
	}

	public void loadAssets() {

		manager = new AssetManager();

		manager.load("fonts/8bit15.fnt", BitmapFont.class);
		manager.load("fonts/8bit20.fnt", BitmapFont.class);
		manager.load("fonts/8bit24.fnt", BitmapFont.class);
		manager.load("fonts/8bit27.fnt", BitmapFont.class);
		manager.load("fonts/8bit32.fnt", BitmapFont.class);
		manager.load("fonts/8bit38.fnt", BitmapFont.class);

		manager.load("textures/gun.gif", Texture.class);
		manager.load("textures/astronaut.png", Texture.class);
		manager.load("textures/muzzle_flash.png", Texture.class);
		manager.load("textures/gnomes.png", Texture.class);

		manager.load("sound_effects/pistol_shot.wav", Sound.class);
		manager.load("sound_effects/ak.mp3", Sound.class);
		manager.load("sound_effects/barret.mp3", Sound.class);
		manager.load("sound_effects/revolver.mp3", Sound.class);
		manager.load("sound_effects/smg.mp3", Sound.class);
		manager.load("sound_effects/machine.mp3", Sound.class);
		manager.load("sound_effects/shotgun.mp3", Sound.class);
		manager.load("sound_effects/dryfire.mp3", Sound.class);
		manager.load("sound_effects/loadshort.mp3", Sound.class);
		manager.load("sound_effects/loadmed.mp3", Sound.class);
		manager.load("sound_effects/loadlong.mp3", Sound.class);
		manager.load("sound_effects/bang.wav", Sound.class);
		manager.load("sound_effects/money.mp3", Sound.class);

		manager.finishLoading();
		
		System.out.println("finished loading");

		size15 = manager.get("fonts/8bit15.fnt", BitmapFont.class);
		size20 = manager.get("fonts/8bit20.fnt", BitmapFont.class);
		size24 = manager.get("fonts/8bit24.fnt", BitmapFont.class);
		size27 = manager.get("fonts/8bit27.fnt", BitmapFont.class);
		size32 = manager.get("fonts/8bit32.fnt", BitmapFont.class);
		size38 = manager.get("fonts/8bit38.fnt", BitmapFont.class);

		gunTexture = manager.get("textures/gun.gif", Texture.class);
		astroTexture = manager.get("textures/astronaut.png", Texture.class);
		flashTexture = manager.get("textures/muzzle_flash.png", Texture.class);
		gnomes = manager.get("textures/gnomes.png", Texture.class);

		pistolShot = manager.get("sound_effects/pistol_shot.wav", Sound.class);
		akShot = manager.get("sound_effects/ak.mp3", Sound.class);
		barretShot = manager.get("sound_effects/barret.mp3", Sound.class);
		revShot = manager.get("sound_effects/revolver.mp3", Sound.class);
		smgShot = manager.get("sound_effects/smg.mp3", Sound.class);
		machineShot = manager.get("sound_effects/machine.mp3", Sound.class);
		shotgunShot = manager.get("sound_effects/shotgun.mp3", Sound.class);
		dryfire = manager.get("sound_effects/dryfire.mp3", Sound.class);
		loadshort = manager.get("sound_effects/loadshort.mp3", Sound.class);
		loadmed = manager.get("sound_effects/loadmed.mp3", Sound.class);
		loadlong = manager.get("sound_effects/loadlong.mp3", Sound.class);
		bang = manager.get("sound_effects/bang.wav", Sound.class);
		money = manager.get("sound_effects/money.mp3", Sound.class);
		
		gnome1 = new TextureRegion(gnomes, 35, 153, 71, 131);
		gnome2 = new TextureRegion(gnomes, 169, 153, 71, 131);
		gnome3 = new TextureRegion(gnomes, 303, 153, 71, 131);

		muzzleFlash = new TextureRegion(flashTexture, 190, 175, 315, 176);

		pistol1 = new TextureRegion(gunTexture, 16, 16, 50, 38);
		pistol1Cock = new TextureRegion(gunTexture, 13, 93, 35, 23);

		smg = new TextureRegion(gunTexture, 238, 14, 66, 58);
		ak = new TextureRegion(gunTexture, 230, 204, 100, 50);
		chain = new TextureRegion(gunTexture, 344, 4, 100, 64);
		deagle = new TextureRegion(gunTexture, 128, 16, 64, 38);
		barret = new TextureRegion(gunTexture, 344, 188, 128, 58);
		shotgun = new TextureRegion(gunTexture, 344, 78, 102, 40);

		astronautWalk1 = new TextureRegion(astroTexture, 5, 0, 17, 32);
		astronautWalk2 = new TextureRegion(astroTexture, 38, 0, 17, 32);
		astronautWalk3 = new TextureRegion(astroTexture, 71, 0, 17, 32);
		astronautWalk4 = new TextureRegion(astroTexture, 103, 0, 17, 32);
		astronautWalk5 = new TextureRegion(astroTexture, 7, 33, 17, 32);
		astronautWalk6 = new TextureRegion(astroTexture, 38, 33, 17, 32);
		astronautWalk7 = new TextureRegion(astroTexture, 69, 33, 17, 32);

		astroJump1 = new TextureRegion(astroTexture, 6, 84, 15, 32);
		astroJump2 = new TextureRegion(astroTexture, 30, 84, 15, 32);

		arm = new TextureRegion(astroTexture, 57, 98, 12, 5);

		TextureRegion[] astronautWalkingFrames = { astronautWalk1, astronautWalk2, astronautWalk3, astronautWalk4,
				astronautWalk5, astronautWalk6, astronautWalk7 };

		TextureRegion[] astroJumpFrames = { astroJump1, astroJump2 };

		TextureRegion[] runningFrames = { running1, running2, running3, running4, running5, running6, running7,
				running8, running9, running10, running11, running12, running13, running14, running15, running16,
				running17, running18, running19, running20, running21, running22, running23, running24, running25,
				running26, running27 };

		TextureRegion[] jumpingFrames = { jumping1, jumping2, jumping3, jumping4, jumping5 };

		TextureRegion[] standingFrames = { standing1, standing2, standing3, standing4, standing5, standing6, standing7,
				standing8, standing9, standing10, standing11, standing12, standing13, standing14, standing15,
				standing16, standing17, standing18, standing19, standing20, standing21, standing22 };

		TextureRegion[] fallingFrames = { falling1, falling2, falling3, falling4 };

		TextureRegion[] gnomeFrames = { gnome1, gnome2, gnome3 };

		standingAnimation = new Animation<TextureRegion>(0.02f, standingFrames);
		standingAnimation.setPlayMode(Animation.PlayMode.LOOP);

		runningAnimation = new Animation<TextureRegion>(0.03f, runningFrames);
		runningAnimation.setPlayMode(Animation.PlayMode.LOOP);

		fallingAnimation = new Animation<TextureRegion>(0.05f, fallingFrames);

		jumpingAnimation = new Animation<TextureRegion>(0.1f, jumpingFrames);

		astroWalkFAnimation = new Animation<TextureRegion>(0.05f, astronautWalkingFrames);
		astroWalkFAnimation.setPlayMode(Animation.PlayMode.LOOP);

		astroWalkBAnimation = astroWalkFAnimation;
		astroWalkBAnimation.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

		astroJumpAnimation = new Animation<TextureRegion>(0.1f, astroJumpFrames);

		gnomeWalk = new Animation<TextureRegion>(0.1f, gnomeFrames);
		gnomeWalk.setPlayMode(Animation.PlayMode.LOOP);
	}
	
	public void dispose() {
		manager.dispose();
		gunTexture.dispose();
		astroTexture.dispose();
		gnomes.dispose();
		flashTexture.dispose();
		size15.dispose();
		size20.dispose();
		size24.dispose();
		size27.dispose();
		size32.dispose();
		size38.dispose();
		
	}

}
