package io.github.megamen2.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.megamen2.Main;

public class HUD implements Disposable{
	public Stage stage;
	private Viewport viewport;
	
	private Integer score;
	
	Label scoreLabel;
	Label levelLabel;
	Label menLabel;
	Label timeLabel;
	
	public HUD(SpriteBatch sb) {
		score = 0;
		
		viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); 
		timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE)); 
		levelLabel = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		menLabel = new Label("Oscar", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(menLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		table.row();
		table.add(scoreLabel).expandX();
		table.add(levelLabel).expandX();
		
		stage.addActor(table);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
