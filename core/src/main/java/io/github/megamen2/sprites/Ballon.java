package io.github.megamen2.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import io.github.megamen2.Main;
import io.github.megamen2.screens.PlayScreen;

public class Ballon extends InteractiveTileObject{
//	private PlayState men;
	private Main game;
	public Ballon(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Main.BALLON_BIT);
        
	}

	@Override
	public void onHeadHit() {
		//Gdx.app.log("ballon", "collision");
		PlayScreen.isBonus(true);
		setCategoryFilter(Main.DESTROYED_BIT);
		getCell().setTile(null);
		PlayScreen.isBonus(true);
	}
}
