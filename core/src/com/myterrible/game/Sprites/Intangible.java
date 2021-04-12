package com.myterrible.game.Sprites;

import com.badlogic.gdx.math.Rectangle;
import com.myterrible.game.screens.MapScreen;


public class Intangible extends InteractiveTileObject{
	
	public Intangible(MapScreen screen, Rectangle pontas) {
		super(screen, pontas);
		fixture.setUserData("death");
		
	}


}
