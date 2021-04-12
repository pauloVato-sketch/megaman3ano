package com.myterrible.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.myterrible.game.Megaman;
import com.myterrible.game.screens.MapScreen;
import com.myterrible.game.screens.PlayScreen;

public abstract class InteractiveTileObject {
	protected World world;
	protected TiledMap map;
	protected TiledMapTile bloco;
	protected Rectangle pontas;
	protected Body body;
	protected Fixture fixture;
	
	public InteractiveTileObject(MapScreen screen,Rectangle pontas) {
		this.world = screen.getWorld();
		this.map=screen.getMap();
		this.pontas=pontas;
		
		BodyDef BDef = new BodyDef();
		FixtureDef FDef = new FixtureDef();
		PolygonShape Shape = new PolygonShape();
		
		BDef.type = BodyDef.BodyType.StaticBody;
		BDef.position.set((pontas.getX()+pontas.getWidth()/2)/Megaman.PPM,(pontas.getY()+pontas.getHeight()/2)/Megaman.PPM);
		body = world.createBody(BDef);
		
		Shape.setAsBox((pontas.getWidth()/2)/Megaman.PPM, (pontas.getHeight()/2)/Megaman.PPM );
		FDef.shape = Shape;
		fixture=body.createFixture(FDef);
		
	}
	

	

}
