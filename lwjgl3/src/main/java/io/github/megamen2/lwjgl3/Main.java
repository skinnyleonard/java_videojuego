package io.github.megamen2.lwjgl3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class Main extends Game {
	
	public static Main INSTANCE;
	private int widthScreen, heightScreen;
	private OrthographicCamera orthographicCamera;
	
	public Main() {
		INSTANCE = this;
	}
	
	@Override
	public void create() {
		this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();
		this.orthographicCamera = new OrthographicCamera();
		this.orthographicCamera.setToOrtho(false, widthScreen, heightScreen);
		setScreen(new GameScreen(orthographicCamera));
	}
}