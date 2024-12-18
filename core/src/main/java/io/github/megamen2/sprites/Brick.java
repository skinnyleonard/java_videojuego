package io.github.megamen2.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Brick extends InteractiveTileObject {
	 public Brick(World world, TiledMap map, Rectangle bounds){
		super(world, map, bounds);
		fixture.setUserData(this);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("brick", "collision");
	}
}
