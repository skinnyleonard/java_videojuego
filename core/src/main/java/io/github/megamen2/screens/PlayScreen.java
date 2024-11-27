package io.github.megamen2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.megamen2.Main;
import io.github.megamen2.scenes.HUD;
import io.github.megamen2.sprites.Men;
import io.github.megamen2.tools.B2WorldCreator;
import io.github.megamen2.tools.WorldContactListener;

public class PlayScreen implements Screen{
	private static Main game;
	private TextureAtlas atlas;
	private Texture bg;
	public static Sound run;
	private Sound jump;
	
	public OrthographicCamera gamecam;
	private Viewport gamePort;
	private HUD hud;
	private static int times = 1;
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	private World world;
//	private Box2DDebugRenderer b2dr;
	private static int jumpCounter;
	private Men player;
	
	public static Texture bgTex;
	public static Sprite bgSpr;
	private SpriteBatch sb;
	public static String theMap = "map1.tmx";
	
	public PlayScreen(Main game) {
		bgTex = new Texture("back.jpg");
		bgSpr = new Sprite(bgTex);
		
		atlas = new TextureAtlas("mencycle.atlas");
		bg = new Texture("bg.jfif");
		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(Main.V_WIDTH/Main.PPM, Main.V_HEIGHT/Main.PPM, gamecam);
		hud = new HUD(game.batch);
	
		mapLoader = new TmxMapLoader();
		map = mapLoader.load(theMap);
		renderer = new OrthogonalTiledMapRenderer(map, 1/Main.PPM);
		gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
		
		sb = new SpriteBatch();
		
		world = new World(new Vector2(0, -10), true);
//		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		this.jumpCounter = 1;
		player = new Men(world, this);
		
		world.setContactListener(new WorldContactListener());
		
		run = Gdx.audio.newSound(Gdx.files.internal("onplay.mp3"));
		run.play();
		
		jump = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	@Override
	public void show() {
		
	}
	
	private void handleInput(float dt) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && jumpCounter < 1) {
			player.b2body.applyLinearImpulse(new Vector2(0, 5.5f), player.b2body.getPosition(), true);
		    jumpCounter++;
		    jump.play();
		}
		if(player.b2body.getLinearVelocity().y == 0) {
			jumpCounter = 0;
		}
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) {
			player.b2body.applyLinearImpulse(new Vector2(1f, 0), player.b2body.getWorldCenter(), true);
		
		}
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2) {
			player.b2body.applyLinearImpulse(new Vector2(-1f, 0), player.b2body.getWorldCenter(), true);	
			
		}
	}
	
	public void update (float dt) {
		
		handleInput(dt);
		world.step(1/60f, 6, 2);
		
		gamecam.position.x = player.b2body.getPosition().x;
		
		player.update(dt);
		
		//actualiza el gamecam con las coordenadas correctas despues de cambiar
		gamecam.update();
		//le dice al renderizador dibujar solo lo que la camara ve en el mundo
		renderer.setView(gamecam);
	}
	
	public void renderBg() {
		bgSpr.draw(sb);
	}
	
	@Override
	public void render(float delta) {
		
		update(delta);
		
		sb.begin();
		renderBg();
		sb.end();
		
		Gdx.gl.glClearColor(22, 0, 36, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		renderer.render();
		
//		b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		game.batch.end();		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		
		
		
		hud.stage.draw();
		
	}
	
	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
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
		map.dispose();
		renderer.dispose();
		world.dispose();
//		b2dr.dispose();
		hud.dispose();
		run.dispose();
	}
	
	public static void isBonus(boolean check) {
		if(check = true) {
			jumpCounter = 0;
		}
	}

	public static void isDead(boolean b) {
		run.stop();
		if(b = true) {
			run.stop();
			game.setScreen(new PlayScreen((Main) game));
		}	
	}
	
	public static void isWinner(boolean b) {
		run.stop();
		if(b = true) {
			System.out.println("ganaste");
			run.stop();
			game.setScreen(new WinnerScreen((Main) game));
			run.play();
		}
	}
}
