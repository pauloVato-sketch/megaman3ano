package com.myterrible.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.myterrible.game.Megaman;
import com.myterrible.game.Sprites.Ladder;
import com.myterrible.game.Sprites.Safe;
import com.myterrible.game.Sprites.Wall;
import com.myterrible.game.Sprites.Intangible;
import com.myterrible.game.screens.MapScreen;
import com.myterrible.game.screens.PlayScreen;

public class B2WorldCreator {
	
	public B2WorldCreator(MapScreen screen) {
		//ALTERAR DEPOIS AQUI 
		World world = screen.getWorld();
		TiledMap map = screen.getMap();
				BodyDef bdef = new BodyDef();
				PolygonShape shape = new PolygonShape();
				FixtureDef fxdef = new FixtureDef();
				Body body;
				
				//chao
				for(MapObject obj:map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
					Rectangle rect = ((RectangleMapObject) obj).getRectangle();
					bdef.type = BodyDef.BodyType.StaticBody;
					bdef.position.set((rect.getX()+rect.getWidth()/2)/Megaman.PPM,(rect.getY()+rect.getHeight()/2)/Megaman.PPM);
					body = world.createBody(bdef);
					
					shape.setAsBox( (rect.getWidth()/2)/Megaman.PPM, (rect.getHeight()/2)/Megaman.PPM );
					fxdef.shape = shape;
					body.createFixture(fxdef);
					map.getLayers().get(2).setVisible(false);
				}
			
				
				
				for(MapObject obj:map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
					Rectangle rect = ((RectangleMapObject) obj).getRectangle();
					new Intangible(screen,rect);
				}
				for(MapObject obj:map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
					Rectangle rect = ((RectangleMapObject) obj).getRectangle();
					new Safe(screen,rect);
				}
				for(MapObject obj:map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
					Rectangle rect = ((RectangleMapObject) obj).getRectangle();
					new Wall(screen,rect);
				}
	}
}
