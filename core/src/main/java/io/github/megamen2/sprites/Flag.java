package io.github.megamen2.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import io.github.megamen2.Main;

public class Flag extends InteractiveTileObject {
	private static TiledMapTileSet tileSet;
	private Men men;
	 public Flag(World world, TiledMap map, Rectangle bounds){
		super(world, map, bounds);
		tileSet = map.getTileSets().getTileSet("tiles");
		fixture.setUserData(this);
		setCategoryFilter(Main.BALLON_BIT);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("checkpoint", "collision");
		setCategoryFilter(Main.DESTROYED_BIT);
		getCell().setTile(tileSet.getTile(1));
	}
}
