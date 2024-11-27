package io.github.megamen2.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import io.github.megamen2.Main;
import io.github.megamen2.screens.PlayScreen;

public class WinFlag extends InteractiveTileObject{
	private Main game;
	public WinFlag(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
        fixture.setUserData(this);
	}

	@Override
	public void onHeadHit() {
//		Gdx.app.log("spike", "collision");
		PlayScreen.isWinner(true);
		PlayScreen.run.stop();
	}
}
