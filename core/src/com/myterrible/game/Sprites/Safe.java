package com.myterrible.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.myterrible.game.Megaman;
import com.myterrible.game.screens.MapScreen;
import com.myterrible.game.screens.PlayScreen;

public class Safe extends InteractiveTileObject{

	public Safe(MapScreen screen, Rectangle pontas) {
		super(screen, pontas);
		fixture.setUserData("next_phase");
		
	}

	

}
