package io.github.megamen2.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.megamen2.Main;

public class WinnerScreen implements Screen{
	private static Game game;
	
	public OrthographicCamera gamecam;
	private Viewport viewport;
	private Stage stage;
	
	public static Texture bgTex;
	public static Sprite bgSpr;
	private SpriteBatch sb;
	private Sound finale;
	
	public WinnerScreen(Game game) {
		this.game = game;
		
		bgTex = new Texture("ganaste.png");
		bgSpr = new Sprite(bgTex);
		
		viewport = new FitViewport(1366, 768, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        
        Label winnerLabel = new Label("Ganaste!", font);
        Label playAgainLabel = new Label("Click para volver al menu principal", font);
        
        table.add(winnerLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);
        
        sb = new SpriteBatch();
        PlayScreen.run.stop();
        finale = Gdx.audio.newSound(Gdx.files.internal("final.mp3"));
        finale.play();
        
        stage.addActor(table);
	}
	
	@Override
	public void show() {
		
	}
	
	public void update (float dt) {
		
	}
	
	public void renderBg() {
		bgSpr.draw(sb);
	}
	
	@Override
	public void render(float delta) {
		if(Gdx.input.justTouched()) {
			game.setScreen(new WelcomeScreen((Main) game));
			finale.stop();
			dispose();
		}
		
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
			renderBg();
		sb.end();
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
