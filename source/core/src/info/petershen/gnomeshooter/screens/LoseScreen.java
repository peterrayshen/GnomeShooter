package info.petershen.gnomeshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoseScreen implements Screen{
	
	//this screen is displayed whenever the player loses
	
	public Stage stage;
	private Viewport viewport;
	
	private Label messageLabel, playAgain;
	
	private PlayScreen playScreen;
	
	public LoseScreen(SpriteBatch sb, PlayScreen playScreen) {
		
		
		//imports a font and brings it into the game
		BitmapFont font = playScreen.game.assets.size27;
		
		//sets the viewport
		viewport = new FitViewport(1280, 720, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		//creates the text that says YOU LOSE
		messageLabel = new Label("GAME OVER",
				new Label.LabelStyle(font, Color.WHITE));
		messageLabel.setBounds(640, 410, 10, 10);
		messageLabel.setAlignment(Align.center);
		
		playAgain = new Label("PRESS SPACE TO PLAY AGAIN",
				new Label.LabelStyle(font, Color.WHITE));
		playAgain.setBounds(640, 350, 10, 10);
		playAgain.setAlignment(Align.center);
		
		//adds the text to the screen
		stage.addActor(messageLabel);
		stage.addActor(playAgain);
		
		this.playScreen = playScreen;
		
	}
	
	//disposes of ressources
	public void dispose() {
		stage.dispose();


	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	//method that's called many times every second
	@Override
	public void render(float delta) {
		//clears the screen and sets the background colour to grey
		Gdx.gl.glClearColor(150 / 255f, 220 / 255f, 190 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
			restartGame();
		
		stage.act(delta);
		stage.draw();
		
	}
	
	public void restartGame() {
		playScreen.restartGame();
		playScreen.game.setScreen(playScreen.game.playScreen);
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
		// TODO Auto-generated method stub
		
	}

}
