package com.myterrible.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myterrible.game.Megaman;
import com.myterrible.game.Sprites.MegaPlayer;
import com.myterrible.game.Sprites.Terrestres;
import com.myterrible.game.Sprites.MegaPlayer.Estados;
import com.myterrible.game.scenarios.Hud;
import com.myterrible.game.tools.B2WorldCreator;
import com.myterrible.game.tools.WorldContactListener;

public class MapScreen implements Screen {
	protected Megaman game;
	protected Hud hud;
	protected OrthographicCamera gamecam;
	protected Viewport gamePort;
	protected MegaPlayer player;
	protected TextureAtlas atlas;
	
	//VARIAVEIS TILED
	protected TmxMapLoader mapLoader;
	protected TiledMap map;
	protected OrthogonalTiledMapRenderer renderer;
	
	//BOX2d
	protected World world;
	protected Box2DDebugRenderer b2dr;
	
	protected TextureAtlas atlas2;
	
	
	public MapScreen(Megaman game) {
		
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	public TextureAtlas getAtlasTwo() {
		return atlas2;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void render(float deltaTime) {
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		gamePort.update(width, height);
	}
	
	public TiledMap getMap() {
		return map;
	}
	
	public World getWorld() {
		return world;	
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
	
	}

}
