package com.myterrible.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.myterrible.game.Megaman;
import com.myterrible.game.screens.MapScreen;
import com.myterrible.game.screens.PlayScreen;

public class Terrestres extends Inimigos{
	
	private float tempoDeEstado;
	private Animation <TextureRegion> andando;
	private Array<TextureRegion> frames;
	private TextureRegion bitStanding;
	
	public Terrestres(MapScreen ps, float x, float y) {
		super(ps, x, y);
		frames = new Array<TextureRegion>();
		for(int i = 0;i <3;i++) {
			frames.add(new TextureRegion(ps.getAtlasTwo().findRegion("large_enemies"),i*20,240,21,24));
		}
		andando = new Animation(0.2f , frames);
		tempoDeEstado = 0;
		setBounds(0, 48, 32/Megaman.PPM, 32/Megaman.PPM);
	}
	
	public void update(float deltaTime) {
		tempoDeEstado += deltaTime;
		b2enemy.setLinearVelocity(velocity);
		setPosition(b2enemy.getPosition().x - getWidth()/2,b2enemy.getPosition().y - getHeight()/2);
		setRegion(andando.getKeyFrame(tempoDeEstado, true));
	}

	@Override
	protected void defineInimigo(float x,float y) {
		// TODO Auto-generated method stub
		BodyDef bdef = new BodyDef();
		bdef.position.set(x/Megaman.PPM,y/Megaman.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2enemy = world.createBody(bdef);
		
		FixtureDef fdefine = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(16/Megaman.PPM);
		fdefine.filter.categoryBits = Megaman.ENEMY_BIT;
		
		fdefine.shape = shape;
		b2enemy.createFixture(fdefine).setUserData("enemy");
	}
	
}
