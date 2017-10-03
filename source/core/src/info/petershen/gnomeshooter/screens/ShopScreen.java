package info.petershen.gnomeshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.tools.AssetLoader;

public class ShopScreen implements Screen {

	public GnomeShooter game;
	private PlayScreen screen;
	public Skin skin;
	public Stage stage;
	public SpriteBatch batch;
	public Sprite bg;
	private boolean boughtSmg, boughtRev, boughtShotgun, boughtSniper, boughtAk, boughtLmg;

	private Label shopLabel, moneyLabel, moneyMessage, healthLabel, speedLabel, jumpLabel, pistolLabel, smgLabel,
			revLabel, shotgunLabel, akLabel, lmgLabel, sniperLabel;
	private ProgressBar healthBar, speedBar, jumpBar, pistolBar, smgBar, revBar, shotgunBar, akBar, lmgBar, sniperBar;
	private TextButton health, speed, jump, pistol, rev, smg, shotgun, ak, lmg, sniper, nextRound;
	private BitmapFont font, fontbig, fontsmall, fontmed;

	public ShopScreen(GnomeShooter game) {
		this.game = game;
		this.screen = game.playScreen;
		batch = new SpriteBatch();
		stage = new Stage();

		font = AssetLoader.size24;
		fontbig = AssetLoader.size38;
		fontsmall = AssetLoader.size15;
		fontmed = AssetLoader.size32;

		skin = new Skin();

		Pixmap pixmap = new Pixmap(400, 24, Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();

		Pixmap pixmap1 = new Pixmap(400, 24, Format.RGB888);
		pixmap1.setColor(Color.PINK);
		pixmap1.fill();

		Pixmap pixmap2 = new Pixmap(400, 24, Format.RGB888);
		pixmap1.setColor(Color.GOLDENROD);
		pixmap1.fill();

		Pixmap pixmap3 = new Pixmap(400, 24, Format.RGB888);
		pixmap3.setColor(Color.GREEN);
		pixmap3.fill();

		Pixmap pixmap4 = new Pixmap(400, 24, Format.RGB888);
		pixmap4.setColor(Color.ROYAL);
		pixmap4.fill();

		Pixmap pixmap5 = new Pixmap(400, 24, Format.RGB888);
		pixmap4.setColor(Color.BLACK);
		pixmap4.fill();

		skin.add("white", new Texture(pixmap));
		skin.add("pink", new Texture(pixmap1));
		skin.add("gold", new Texture(pixmap2));
		skin.add("green", new Texture(pixmap3));
		skin.add("royal", new Texture(pixmap4));
		skin.add("black", new Texture(pixmap5));

		BitmapFont fontdefault = new BitmapFont();

		skin.add("default", fontdefault);

		TextButtonStyle playerTextStyle = new TextButtonStyle();
		playerTextStyle.up = skin.newDrawable(skin.getDrawable("royal"));
		playerTextStyle.down = skin.newDrawable(skin.getDrawable("green"));
		playerTextStyle.font = fontsmall;
		playerTextStyle.fontColor = Color.GOLDENROD;

		TextButtonStyle weaponTextStyle = new TextButtonStyle();
		weaponTextStyle.up = skin.newDrawable("white", Color.WHITE);
		weaponTextStyle.down = skin.newDrawable("white", Color.GREEN);
		weaponTextStyle.font = font;

		TextButtonStyle nextRoundStyle = new TextButtonStyle();
		nextRoundStyle.up = skin.newDrawable("white", Color.BLACK);
		nextRoundStyle.down = skin.newDrawable("white", Color.GRAY);
		nextRoundStyle.font = fontmed;

		ProgressBarStyle playerStyle = new ProgressBarStyle();
		playerStyle.background = skin.newDrawable("pink");
		playerStyle.knobAfter = skin.newDrawable("white");

		ProgressBarStyle weaponStyle = new ProgressBarStyle();
		weaponStyle.background = skin.newDrawable("gold");
		weaponStyle.knobAfter = skin.newDrawable("white");

		ProgressBar bar = new ProgressBar(0, 100, 5, false, playerStyle);

		health = new TextButton("upgrade", playerTextStyle);
		health.setBounds(490, 380, 170, 24);
		health.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopHealth();
			}
		});

		speed = new TextButton("upgrade", playerTextStyle);
		speed.setBounds(490, 350, 170, 24);
		speed.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopSpeed();
			}
		});

		jump = new TextButton("upgrade", playerTextStyle);
		jump.setBounds(490, 320, 170, 24);
		jump.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopJump();
			}
		});

		pistol = new TextButton("upgrade", playerTextStyle);
		pistol.setBounds(490, 270, 170, 24);
		pistol.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopPistol();
			}
		});

		smg = new TextButton("buy", playerTextStyle);
		smg.setBounds(490, 240, 170, 24);
		smg.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopSmg();
			}
		});

		rev = new TextButton("buy", playerTextStyle);
		rev.setBounds(490, 210, 170, 24);
		rev.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopRevolver();
			}
		});

		shotgun = new TextButton("buy", playerTextStyle);
		shotgun.setBounds(490, 180, 170, 24);
		shotgun.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopShotgun();
			}
		});

		ak = new TextButton("buy", playerTextStyle);
		ak.setBounds(490, 150, 170, 24);
		ak.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopAk();
			}
		});

		lmg = new TextButton("buy", playerTextStyle);
		lmg.setBounds(490, 120, 170, 24);
		lmg.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopLmg();
			}
		});

		sniper = new TextButton("buy", playerTextStyle);
		sniper.setBounds(490, 90, 170, 24);
		sniper.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shopSniper();
			}
		});

		nextRound = new TextButton("next round", nextRoundStyle);
		nextRound.setBounds(stage.getCamera().viewportWidth / 2 - nextRound.getWidth() / 2, 30, 325, 40);
		nextRound.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				newRound();
			}
		});

		healthBar = new ProgressBar(0, 5, 0.01f, false, playerStyle);
		healthBar.setBounds(175, 380, 300, 24);
		healthBar.setValue(0.1f / 3 * 5);

		speedBar = new ProgressBar(0, 3, 0.1f, false, playerStyle);
		speedBar.setBounds(175, 350, 300, 24);
		speedBar.setValue(0.1f);

		jumpBar = new ProgressBar(0, 3, 0.1f, false, playerStyle);
		jumpBar.setBounds(175, 320, 300, 24);
		jumpBar.setValue(0.1f);

		pistolBar = new ProgressBar(0, 3, 0.1f, false, playerStyle);
		pistolBar.setBounds(175, 270, 300, 24);
		pistolBar.setValue(0.1f);

		smgBar = new ProgressBar(0, 3, 0.1f, false, playerStyle);
		smgBar.setBounds(175, 240, 300, 24);
		pistolBar.setValue(0.1f);

		revBar = new ProgressBar(0, 3, 0.1f, false, playerStyle);
		revBar.setBounds(175, 210, 300, 24);

		shotgunBar = new ProgressBar(0, 3, 0.1f, false, playerStyle);
		shotgunBar.setBounds(175, 180, 300, 24);

		akBar = new ProgressBar(0, 3, 0.1f, false, playerStyle);
		akBar.setBounds(175, 150, 300, 24);

		lmgBar = new ProgressBar(0, 3, 0.1f, false, playerStyle);
		lmgBar.setBounds(175, 120, 300, 24);

		sniperBar = new ProgressBar(0, 3, 0.1f, false, playerStyle);
		sniperBar.setBounds(175, 90, 300, 24);

		shopLabel = new Label("Shop", new Label.LabelStyle(fontbig, Color.WHITE));
		shopLabel.setPosition(20, 430);

		moneyLabel = new Label(Integer.toString(game.playScreen.money), new Label.LabelStyle(fontbig, Color.GOLD));
		moneyLabel.setAlignment(Align.left);
		moneyLabel.setPosition(640, 430);

		moneyMessage = new Label("Money", new Label.LabelStyle(fontbig, Color.GOLD));
		moneyMessage.setAlignment(Align.right);
		moneyMessage.setPosition(400, 430);

		healthLabel = new Label("Health", new Label.LabelStyle(fontsmall, Color.PINK));
		healthLabel.setPosition(20, 380);

		speedLabel = new Label("speed", new Label.LabelStyle(fontsmall, Color.PINK));
		speedLabel.setPosition(20, 350);

		jumpLabel = new Label("jump", new Label.LabelStyle(fontsmall, Color.PINK));
		jumpLabel.setPosition(20, 320);

		pistolLabel = new Label("pistol", new Label.LabelStyle(fontsmall, Color.GOLDENROD));
		pistolLabel.setPosition(20, 270);

		smgLabel = new Label("smg", new Label.LabelStyle(fontsmall, Color.BLACK));
		smgLabel.setPosition(20, 240);

		revLabel = new Label("revolver", new Label.LabelStyle(fontsmall, Color.BLACK));
		revLabel.setPosition(20, 210);

		shotgunLabel = new Label("shotgun", new Label.LabelStyle(fontsmall, Color.BLACK));
		shotgunLabel.setPosition(20, 180);

		akLabel = new Label("ak 47", new Label.LabelStyle(fontsmall, Color.BLACK));
		akLabel.setPosition(20, 150);

		lmgLabel = new Label("lmg", new Label.LabelStyle(fontsmall, Color.BLACK));
		lmgLabel.setPosition(20, 120);

		sniperLabel = new Label("sniper", new Label.LabelStyle(fontsmall, Color.BLACK));
		sniperLabel.setPosition(20, 90);

	
		bar.setValue(40);
		bar.setPosition(200, 200);
		bar.setBounds(200, 200, 200, 10);
		// stage.addActor(bar);
		stage.addActor(shopLabel);
		stage.addActor(moneyLabel);
		stage.addActor(moneyMessage);
		stage.addActor(healthLabel);
		stage.addActor(speedLabel);
		stage.addActor(jumpLabel);
		stage.addActor(pistolLabel);
		stage.addActor(smgLabel);
		stage.addActor(revLabel);
		stage.addActor(shotgunLabel);
		stage.addActor(akLabel);
		stage.addActor(lmgLabel);
		stage.addActor(sniperLabel);

		stage.addActor(healthBar);
		stage.addActor(speedBar);
		stage.addActor(jumpBar);

		stage.addActor(pistolBar);
		stage.addActor(smgBar);
		stage.addActor(revBar);
		stage.addActor(shotgunBar);
		stage.addActor(akBar);
		stage.addActor(lmgBar);
		stage.addActor(sniperBar);

		stage.addActor(health);
		stage.addActor(speed);
		stage.addActor(jump);
		stage.addActor(pistol);
		stage.addActor(smg);
		stage.addActor(rev);
		stage.addActor(shotgun);
		stage.addActor(ak);
		stage.addActor(nextRound);

		stage.addActor(lmg);
		stage.addActor(sniper);

		

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(110 / 255f, 110 / 255f, 110 / 255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();

		moneyLabel.setText(Integer.toString(game.playScreen.money));

		game.batch.begin();
		game.batch.end();

	
		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
			game.setScreen(game.playScreen);
			Gdx.input.setInputProcessor(null);
		}

		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void shopPistol() {

		AssetLoader.money.play();
		screen.pistol.upgrade();
		pistolBar.setValue(screen.pistol.level);

	}

	public void shopSmg() {
		if (screen.money >= screen.smg.cost) {
			screen.money -= screen.smg.cost;

			if (!boughtSmg) {

				AssetLoader.money.play();
				screen.weaponList.add(screen.smg);
				screen.currentWeapon = screen.weaponList.get(screen.weaponList.size() - 1);
				screen.money -= screen.smg.cost;
				boughtSmg = true;
				smgLabel = new Label("smg", new Label.LabelStyle(fontsmall, Color.GOLDENROD));
				smgLabel.setPosition(20, 240);
				stage.addActor(smgLabel);
				smg.setText("upgrade");
				smgBar.setValue(0.1f);

			} else {

				AssetLoader.money.play();
				screen.smg.upgrade();
				smgBar.setValue(screen.smg.level);

			}
		}
	}

	public void shopRevolver() {
		if (screen.money >= screen.revolver.cost) {
			screen.money -= screen.revolver.cost;

			if (!boughtRev) {
				AssetLoader.money.play();
				screen.weaponList.add(screen.revolver);
				screen.currentWeapon = screen.weaponList.get(screen.weaponList.size() - 1);
				screen.money -= screen.revolver.cost;
				boughtRev = true;
				revLabel = new Label("revolver", new Label.LabelStyle(fontsmall, Color.GOLDENROD));
				revLabel.setPosition(20, 210);
				stage.addActor(revLabel);
				rev.setText("upgrade");
				revBar.setValue(0.1f);

			} else {
				AssetLoader.money.play();
				screen.revolver.upgrade();
				revBar.setValue(screen.revolver.level);

			}
		}

	}

	public void shopShotgun() {
		if (screen.money >= screen.shotgun.cost) {
			screen.money -= screen.shotgun.cost;
			if (!boughtShotgun) {
				AssetLoader.money.play();
				screen.weaponList.add(screen.shotgun);
				screen.currentWeapon = screen.weaponList.get(screen.weaponList.size() - 1);
				screen.money -= screen.shotgun.cost;
				boughtShotgun = true;
				shotgunLabel = new Label("shotgun", new Label.LabelStyle(fontsmall, Color.GOLDENROD));
				shotgunLabel.setPosition(20, 180);
				stage.addActor(shotgunLabel);
				shotgun.setText("upgrade");
				shotgunBar.setValue(0.1f);
			} else {
				AssetLoader.money.play();
				screen.shotgun.upgrade();
				shotgunBar.setValue(screen.shotgun.level);
			}

		}

	}

	public void shopAk() {
		if (screen.money >= screen.ak.cost) {
			screen.money -= screen.ak.cost;
			if (!boughtAk) {
				AssetLoader.money.play();
				screen.weaponList.add(screen.ak);
				screen.currentWeapon = screen.weaponList.get(screen.weaponList.size() - 1);
				screen.money -= screen.ak.cost;
				boughtAk = true;
				akLabel = new Label("ak 47", new Label.LabelStyle(fontsmall, Color.GOLDENROD));
				akLabel.setPosition(20, 150);
				stage.addActor(akLabel);
				ak.setText("upgrade");
				akBar.setValue(0.1f);

			} else {
				AssetLoader.money.play();
				screen.ak.upgrade();
				akBar.setValue(screen.ak.level);

			}
		}

	}

	public void shopLmg() {
		if (screen.money >= screen.machine.cost) {
			screen.money -= screen.machine.cost;

			if (!boughtLmg) {
				AssetLoader.money.play();
				screen.weaponList.add(screen.machine);
				screen.currentWeapon = screen.weaponList.get(screen.weaponList.size() - 1);
				screen.money -= screen.machine.cost;
				boughtLmg = true;
				lmgLabel = new Label("lmg", new Label.LabelStyle(fontsmall, Color.GOLDENROD));
				lmgLabel.setPosition(20, 120);
				stage.addActor(lmgLabel);
				lmg.setText("upgrade");
				lmgBar.setValue(0.1f);

			} else {
				AssetLoader.money.play();
				screen.machine.upgrade();
				lmgBar.setValue(screen.machine.level);

			}
		}

	}

	public void shopSniper() {
		if (screen.money >= screen.barret.cost) {
			screen.money -= screen.barret.cost;

			if (!boughtSniper) {
				AssetLoader.money.play();
				screen.weaponList.add(screen.barret);
				screen.currentWeapon = screen.weaponList.get(screen.weaponList.size() - 1);
				screen.money -= screen.barret.cost;
				boughtSniper = true;
				sniperLabel = new Label("sniper", new Label.LabelStyle(fontsmall, Color.GOLDENROD));
				sniperLabel.setPosition(20, 90);
				stage.addActor(sniperLabel);
				sniper.setText("upgrade");
				sniperBar.setValue(0.1f);

			} else {
				AssetLoader.money.play();
				screen.barret.upgrade();
				sniperBar.setValue(screen.barret.level);
			}
		}

	}

	public void shopHealth() {
		screen.player.upgradeHealth();
		healthBar.setValue(screen.player.healthLevel);
	}

	public void shopSpeed() {
		screen.player.upgradeSpeed();
		speedBar.setValue(screen.player.speedLevel);
	}

	public void shopJump() {
		screen.player.upgradeJump();
		jumpBar.setValue(screen.player.jumpLevel);
	}

	public void newRound() {
		game.setScreen(game.playScreen);
		screen.roundController.startRound();
	}

}
