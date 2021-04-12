package com.myterrible.game.Sprites;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.myterrible.game.screens.MapScreen;
import com.myterrible.game.screens.PlayScreen;

public abstract class Inimigos extends Sprite{
	
	protected MapScreen screen;
	protected World world;
	public Body b2enemy;
	public Vector2 velocity;
	private Integer life;
	public boolean destroy;
	
	public Inimigos(MapScreen ps,float x,float y) {
		super(ps.getAtlas().findRegion("mega_man_hd_sprites_"));
		this.world = ps.getWorld();
		this.screen = ps;
		life = 100;
		destroy = false;
		setPosition(x, y);
		defineInimigo(x,y);
		velocity = new Vector2(-1,0);
	}
	protected abstract void defineInimigo(float x,float y) ;

	public void reverseVelocity(boolean x,boolean y) {
		if(x) {
			velocity.x = -velocity.x;
			this.flip(true, false);

		}
		if(y) {
			velocity.y = -velocity.y;
		}
	}
	public Integer getLife() {
		// TODO Auto-generated method stub
		return life;
	}
	public void setLife(Integer life) {
		// TODO Auto-generated method stub
		this.life = life;
	}
	
	
}
