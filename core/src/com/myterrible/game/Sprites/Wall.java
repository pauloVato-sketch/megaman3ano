package com.myterrible.game.Sprites;

import com.badlogic.gdx.math.Rectangle;
import com.myterrible.game.screens.MapScreen;

public class Wall extends InteractiveTileObject{

	public Wall(MapScreen screen,Rectangle pontas) {
		super(screen,pontas);
		fixture.setUserData("Wall");
	}
}
