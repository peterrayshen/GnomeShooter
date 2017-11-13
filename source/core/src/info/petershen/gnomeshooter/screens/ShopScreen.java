package info.petershen.gnomeshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import info.petershen.gnomeshooter.GnomeShooter;

public class ShopScreen implements Screen {

	public GnomeShooter game;
	private PlayScreen screen;
	public Skin skin;
	public Stage stage;
	public SpriteBatch batch;
	public Sprite bg;

	private TextButton nextRound;


	public ShopScreen(GnomeShooter game) {
		this.game = game;
		this.screen = game.playScreen;
		batch = new SpriteBatch();
		stage = new Stage();

		skin = new Skin();

		Pixmap pixmap = new Pixmap(400, 24, Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();

		skin.add("white", new Texture(pixmap));
		
		TextButtonStyle nextRoundStyle = new TextButtonStyle();
		nextRoundStyle.up = skin.newDrawable("white", Color.BLACK);
		nextRoundStyle.down = skin.newDrawable("white", Color.GRAY);
		nextRoundStyle.font = game.assets.size32;

		nextRound = new TextButton("next round", nextRoundStyle);
		nextRound.setBounds(stage.getCamera().viewportWidth / 2 - nextRound.getWidth() / 2, 225, 325, 40);
		nextRound.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				newRound();
			}
		});

		stage.addActor(nextRound);


		

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

	public void newRound() {
		game.setScreen(game.playScreen);
		screen.roundController.startRound();
	}

}
