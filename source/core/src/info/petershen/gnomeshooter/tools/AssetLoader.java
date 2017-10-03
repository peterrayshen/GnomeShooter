package info.petershen.gnomeshooter.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture gunTexture, astroTexture;
	
	public static Texture flashTexture;
	

	public static TextureRegion standing1, standing2, standing3, standing4, standing5, standing6, standing7, standing8,
			standing9, standing10, standing11, standing12, standing13, standing14, standing15, standing16, standing17,
			standing18, standing19, standing20, standing21, standing22;

	public static TextureRegion running1, running2, running3, running4, running5, running6, running7, running8,
			running9, running10, running11, running12, running13, running14, running15, running16, running17, running18,
			running19, running20, running21, running22, running23, running24, running25, running26, running27;

	public static TextureRegion jumping1, jumping2, jumping3, jumping4, jumping5;

	public static TextureRegion falling1, falling2, falling3, falling4;

	public static TextureRegion astronautWalk1, astronautWalk2, astronautWalk3, astronautWalk4, astronautWalk5,
			astronautWalk6, astronautWalk7;
	
	public static TextureRegion astroJump1, astroJump2;
	
	public static TextureRegion gnome1, gnome2, gnome3;

	public static Animation<TextureRegion> standingAnimation, runningAnimation, jumpingAnimation, fallingAnimation, astroWalkFAnimation, astroWalkBAnimation, astroJumpAnimation;
	public static Animation<TextureRegion> gnomeWalk;
	
	public static TextureRegion pistol1, pistol1Cock, arm, muzzleFlash, enemy1, gnome, smg, ak, chain, deagle, barret, shotgun;
	
	public static Sound pistolShot, bang, akShot, barretShot, revShot, smgShot, machineShot,shotgunShot,  money, dryfire, loadshort, loadmed, loadlong;
	public static Music future, wicked;
	
	private static Texture gnomes;
	
	public static BitmapFont size15, size20, size24, size27, size32, size38;
	
	

	public static void load() {
		
		size15 = new BitmapFont(Gdx.files.internal("fonts/8bit15.fnt"));
		size20 = new BitmapFont(Gdx.files.internal("fonts/8bit20.fnt"));
		size24 = new BitmapFont(Gdx.files.internal("fonts/8bit24.fnt"));
		size27 = new BitmapFont(Gdx.files.internal("fonts/8bit27.fnt"));
		size32 = new BitmapFont(Gdx.files.internal("fonts/8bit32.fnt"));
		size38 = new BitmapFont(Gdx.files.internal("fonts/8bit38.fnt"));

		gunTexture = new Texture(Gdx.files.internal("textures/gun.gif"));
		astroTexture = new Texture(Gdx.files.internal("textures/astronaut.png"));
		flashTexture = new Texture(Gdx.files.internal("textures/muzzle_flash.png"));
		gnomes = new Texture(Gdx.files.internal("textures/gnomes.png"));
		
		gnome1 = new TextureRegion(gnomes, 35, 153, 71, 131);
		gnome2 = new TextureRegion(gnomes, 169, 153, 71, 131);
		gnome3 = new TextureRegion(gnomes, 303, 153, 71, 131);
		

		
		pistolShot = Gdx.audio.newSound(Gdx.files.internal("sound_effects/pistol_shot.wav"));
		akShot = Gdx.audio.newSound(Gdx.files.internal("sound_effects/ak.mp3"));
		barretShot = Gdx.audio.newSound(Gdx.files.internal("sound_effects/barret.mp3"));
		revShot = Gdx.audio.newSound(Gdx.files.internal("sound_effects/revolver.mp3"));
		smgShot = Gdx.audio.newSound(Gdx.files.internal("sound_effects/smg.mp3"));
		machineShot = Gdx.audio.newSound(Gdx.files.internal("sound_effects/machine.mp3"));
		shotgunShot = Gdx.audio.newSound(Gdx.files.internal("sound_effects/shotgun.mp3"));
		dryfire = Gdx.audio.newSound(Gdx.files.internal("sound_effects/dryfire.mp3"));
		loadshort = Gdx.audio.newSound(Gdx.files.internal("sound_effects/loadshort.mp3"));
		loadmed = Gdx.audio.newSound(Gdx.files.internal("sound_effects/loadmed.mp3"));
		loadlong = Gdx.audio.newSound(Gdx.files.internal("sound_effects/loadlong.mp3"));
		
		
		
		
		bang = Gdx.audio.newSound(Gdx.files.internal("sound_effects/bang.wav"));
		
		money = Gdx.audio.newSound(Gdx.files.internal("sound_effects/money.mp3"));
		
		muzzleFlash = new TextureRegion(flashTexture, 190, 175, 315, 176);

		
		pistol1 = new TextureRegion(gunTexture, 16, 16, 50, 38);
		pistol1Cock = new TextureRegion(gunTexture, 13, 93, 35, 23);
		
		smg = new TextureRegion(gunTexture,238, 14, 66, 58);
		ak = new TextureRegion(gunTexture,230, 204, 100, 50);
		chain = new TextureRegion(gunTexture,344, 4, 100, 64);
		deagle = new TextureRegion(gunTexture,128, 16, 64, 38);
		barret = new TextureRegion(gunTexture,344, 188, 128, 58);
		shotgun = new TextureRegion(gunTexture,344, 78, 102, 40);
		
		
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
		
		TextureRegion[] astronautWalkingFrames = {astronautWalk1, astronautWalk2, astronautWalk3, astronautWalk4, astronautWalk5,
				astronautWalk6, astronautWalk7};
		
		TextureRegion[] astroJumpFrames = {astroJump1, astroJump2};
		
		TextureRegion[] runningFrames = {running1, running2, running3, running4, running5, running6, running7, running8,
				running9, running10, running11, running12, running13, running14, running15, running16, running17, running18,
				running19, running20, running21, running22, running23, running24, running25, running26, running27};
		
		TextureRegion[] jumpingFrames = {jumping1, jumping2, jumping3, jumping4, jumping5};
		

		TextureRegion[] standingFrames = { standing1, standing2, standing3, standing4, standing5, standing6, standing7,
				standing8, standing9, standing10, standing11, standing12, standing13, standing14, standing15,
				standing16, standing17, standing18, standing19, standing20, standing21, standing22 };
		
		TextureRegion[] fallingFrames = {falling1, falling2, falling3, falling4 };
		
		TextureRegion[] gnomeFrames = {gnome1, gnome2, gnome3};
		
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

	public static void dispose() {
		gunTexture.dispose();
		astroTexture.dispose();
		size15.dispose();
		size20.dispose();
		size24.dispose();
		size27.dispose();
		size32.dispose();
		size38.dispose();
	}

}
