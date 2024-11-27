package io.github.megamen2.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.megamen2.Main;

public class WelcomeScreen implements Screen{
	private static Game game;
	
	public OrthographicCamera gamecam;
	private Viewport viewport;
	private Stage stage;
	
	public static Texture bgTex;
	public static Sprite bgSpr;
	private SpriteBatch sb;
	
	public TextureAtlas butonAtlas;

	public TextButton newGameButton;
	public Skin skin;
	private Sound intro;
	
	public WelcomeScreen(Game game) {
		intro = Gdx.audio.newSound(Gdx.files.internal("intro.mp3"));
		
		bgTex = new Texture("bg.png");
		bgSpr = new Sprite(bgTex);
		
		this.game = game;
		viewport = new FitViewport(1366, 768, new OrthographicCamera());
        sb = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		Table root = new Table();
		root.setFillParent(true);;
		stage.addActor(root);
		
		skin = new Skin(Gdx.files.internal("skin.json"));
		
		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
		Label playAgainLabel = new Label("Presiona la tecla 1 o 2 para elegir el nivel 1 o 2, luego dale play!", font);
		
		Button button = new Button(skin);
		root.add(button).expand().center().width(500).height(166);
		root.row();
        root.add(playAgainLabel).expandX().padTop(10f);
		intro.play();
		button.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				intro.stop();
				game.setScreen(new PlayScreen((Main) game));
			}
		});
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
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			PlayScreen.theMap = "map1.tmx";
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			PlayScreen.theMap = "Nivel2.tmx";
			Main.RESPAWN_X = 1024;
		}
		
		sb.begin();
			renderBg();
		sb.end();
		stage.act();
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
