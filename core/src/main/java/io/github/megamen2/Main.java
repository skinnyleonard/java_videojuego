package io.github.megamen2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.megamen2.screens.PlayScreen;
//import io.github.megamen2.screens.WelcomeScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
	public static final int V_WIDTH = 1920;
	public static final int V_HEIGHT = 1080;
	public static final float PPM = 100;
	
	public static final short DEFAULT_BIT = 1;
	public static final short MEN_BIT = 2;
	public static final short BALLON_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
