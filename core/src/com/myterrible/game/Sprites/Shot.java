package com.myterrible.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.myterrible.game.Megaman;
import com.myterrible.game.screens.MapScreen;
import com.myterrible.game.screens.PlayScreen;


public class Shot extends Sprite{

	private int power = 0;
	private Body corpoTiro;
	private boolean correndoDireita = true;
	private World world;
	private Fixture fixture;
	private boolean destroyed;
	private MegaPlayer playerSave ;
	
	public Shot(World world,MapScreen screen,MegaPlayer player,boolean correndo) {
		super(screen.getAtlas().findRegion("mega_man_hd_sprites_"));
		this.world = world;
		this.playerSave = player;
		correndoDireita = correndo;
		TextureRegion shot = new TextureRegion(getTexture(), 464, 260, 132,132 );
		createBody();
		setBounds(0,16,16/Megaman.PPM,16/Megaman.PPM);
		setRegion(shot);
	}
	public void update(float deltaTime) {
		
		if(correndoDireita) {
			corpoTiro.setLinearVelocity(new Vector2(2,0));
		
			
		}else {
			
			corpoTiro.setLinearVelocity(new Vector2(-2,0));
		}
	}
	
	public void createBody() {
		
		BodyDef bdef = new BodyDef();
		
		if(playerSave.correndoDireita) {
			bdef.position.set((playerSave.b2body.getPosition().x - playerSave.getWidth() / 2) + 2f, (playerSave.b2body.getPosition().y - playerSave.getHeight() *4) + 2f);
		}else {
			bdef.position.set(playerSave.b2body.getPosition().x - playerSave.getWidth() / 2, (playerSave.b2body.getPosition().y - playerSave.getHeight() *4) + 2f);
		}
		bdef.type = BodyDef.BodyType.DynamicBody; 
		corpoTiro = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5 / Megaman.PPM);

		corpoTiro.setGravityScale(0);

		fdef.shape = shape;
		corpoTiro.createFixture(fdef).setUserData("tiro");
		
		
		corpoTiro.isBullet();
		
	}
	
	
	public void onHitBullet() {
		destroyed = true;
	}
	
	

}