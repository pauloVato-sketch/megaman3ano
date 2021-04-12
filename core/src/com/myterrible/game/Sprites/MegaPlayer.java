package com.myterrible.game.Sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.myterrible.game.Megaman;
import com.myterrible.game.screens.MapScreen;
import com.myterrible.game.screens.PlayScreen;

public class MegaPlayer extends Sprite{
	
	public enum Estados {CAINDO,PULANDO,PARADO,CORRENDO};
	
	
	public Estados estadoAtual;
	public Estados estadoAnterior;
	public World world;
	public Body b2body;
	public boolean correndoDireita;
	public Shot tiro;
	private TextureRegion megamanStand;
	private Animation<TextureRegion> megamanRun;
	private Animation<TextureRegion> megamanJump;
	private float tempoEstado;
	private MapScreen ps;
	private float posx;
	private float posy;
	
	public MegaPlayer(MapScreen screen,float x,float y) {
		super(screen.getAtlas().findRegion("mega_man_hd_sprites_"));
		posx = x;
		posy=y;
		this.world = screen.getWorld();
		ps = screen;
		estadoAtual = Estados.PARADO;
		estadoAnterior = Estados.PARADO;
		tempoEstado = 0;
		correndoDireita = true;
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i = 0; i<3;i++) {
			frames.add(new TextureRegion(getTexture(),i*124,264,116,128));
		}
		megamanRun = new Animation<TextureRegion>(0.1f,frames);
		frames.clear();
		
		for(int i = 2; i<4;i++) {
			frames.add(new TextureRegion(getTexture(),i*124,140,116,128));
		}
		megamanJump = new Animation<TextureRegion>(0.1f,frames);

		defineMegaPlayer();
		megamanStand = new TextureRegion(getTexture(), 0, 28, 130, 132);
		setBounds(0,48,48/Megaman.PPM,48/Megaman.PPM);
		setRegion(megamanStand);
	}
	
	
	
	public void update(float deltaTime) {
		setPosition(b2body.getPosition().x - getWidth()/2,b2body.getPosition().y - getHeight()/2);
		setRegion(getFrame(deltaTime));
	}
	
	
	private TextureRegion getFrame(float deltaTime) {
		estadoAtual = getEstado();
		
		TextureRegion region;
		
		switch(estadoAtual) {
			case PULANDO:
				region = (TextureRegion) megamanJump.getKeyFrame(tempoEstado);
				break;
			case CORRENDO:
				region = (TextureRegion) megamanRun.getKeyFrame(tempoEstado,true);
				break;
			case CAINDO:
			case PARADO:
			default:
				region=megamanStand;
				break;
		}
		if((b2body.getLinearVelocity().x < 0 || !correndoDireita)&& !region.isFlipX()) {
			region.flip(true, false);
			correndoDireita = false;
		}else if((b2body.getLinearVelocity().x>0|| correndoDireita) && region.isFlipX()) {
			region.flip(true, false);
			correndoDireita =true;
		}
		if(estadoAtual == estadoAnterior) {
				tempoEstado = tempoEstado + deltaTime ;
			}else{
				tempoEstado=0;
		};
		estadoAnterior = estadoAtual;
		return region;
	}
	
	public Estados getEstado() {
		if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y <0 && estadoAnterior == Estados.PULANDO)) {
			return Estados.PULANDO;
		}else if(b2body.getLinearVelocity().y<0) {
			return Estados.CAINDO;
		}else if(b2body.getLinearVelocity().x !=0) {
			return Estados.CORRENDO;
		}else {
			return Estados.PARADO;
		}
	}

	public void defineMegaPlayer() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(posx/Megaman.PPM,posy/Megaman.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdefine = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(17/Megaman.PPM);
	
		
		fdefine.shape = shape;
		b2body.createFixture(fdefine).setUserData("player");
	
	}

	public void shoot() {
		tiro = new Shot(world, ps, this, correndoDireita);
	}

	
}
