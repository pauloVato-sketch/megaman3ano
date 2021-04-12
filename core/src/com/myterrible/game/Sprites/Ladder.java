package com.myterrible.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.myterrible.game.screens.PlayScreen;

public class Ladder extends InteractiveTileObject{

	public Ladder(PlayScreen screen, Rectangle pontas) {
		super(screen, pontas);
		fixture.setUserData(this);
	}

}
