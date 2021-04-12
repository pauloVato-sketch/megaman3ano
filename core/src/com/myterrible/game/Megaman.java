package com.myterrible.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myterrible.game.screens.MenuScreen;
import com.myterrible.game.screens.PlayScreen;

public class Megaman extends Game {
	public SpriteBatch batch;
	
	public static final int V_WIDTH = 600;
	public static final int V_HEIGHT = 460;
	public static final float PPM =100;
	
	public static final short GROUND_BIT =1;
	public static final short ENEMY_BIT=62;
	public static final short MEGAMAN_BIT=2;
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
