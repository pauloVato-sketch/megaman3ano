package com.myterrible.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.myterrible.game.Megaman;
import com.myterrible.game.Sprites.Inimigos;
import com.myterrible.game.Sprites.MegaPlayer;
import com.myterrible.game.Sprites.Shot;
import com.myterrible.game.Sprites.Terrestres;
import com.myterrible.game.scenarios.Hud;
import com.myterrible.game.screens.GameOverScreen;
import com.myterrible.game.screens.PlayScreen;
import com.myterrible.game.screens.SecondPlayScreen;
import com.myterrible.game.screens.WinScreen;

public class WorldContactListener implements ContactListener{
	MegaPlayer p1;
	Megaman gameSave;
	Hud hudSave;
	Shot tiro;
	Inimigos inimigo;
	private World world;
	
	public WorldContactListener(final MegaPlayer player,final Megaman game,Hud hud,Inimigos inimigo,World world) {
		this.p1 = player;
		this.gameSave = game;
		this.hudSave = hud;
		this.tiro= player.tiro;
		this.inimigo = inimigo;
		this.world = world;
	}
	
	//conteudo da funcao
	public void contatoPlayerInimigo(Fixture A,Fixture B) {
		
		if(A.getBody().getLinearVelocity().x > 0) {
			A.getBody().applyLinearImpulse(new Vector2(-3,0), p1.b2body.getWorldCenter(), true);
		}else if(A.getBody().getLinearVelocity().x<0) {
			A.getBody().applyLinearImpulse(new Vector2(+3,0), p1.b2body.getWorldCenter(), true);
		}
		hudSave.setLife(hudSave.getLife()- 50);
		if(hudSave.getLife() <= 0) {
			gameSave.setScreen(new GameOverScreen(gameSave));
		}	
	}
	
	public void contatoPlayerMorte() {
		gameSave.setScreen(new GameOverScreen(gameSave));
	}
	
	public void trocaFase() {
		if(gameSave.getScreen() instanceof PlayScreen) {
			gameSave.setScreen(new SecondPlayScreen(gameSave));
			
		}else if(gameSave.getScreen() instanceof SecondPlayScreen) {
			gameSave.setScreen(new WinScreen(gameSave));
		}
	}
	
	public void contatoEnemyWall(Fixture A,Fixture B) {
		if(A.getBody().getLinearVelocity().x >= 0) {
			A.getBody().applyLinearImpulse(new Vector2(-0.5f,0), inimigo.b2enemy.getWorldCenter(), true);
			inimigo.reverseVelocity(true, false);
		
		}else if(A.getBody().getLinearVelocity().x<0) {
			inimigo.reverseVelocity(true, false);
			A.getBody().applyLinearImpulse(new Vector2(0.5f,0), inimigo.b2enemy.getWorldCenter(), true);
		}
	}
	@Override
	public void beginContact(Contact contact) {
		Fixture parteA = contact.getFixtureA();
		Fixture parteB = contact.getFixtureB();
		
		if((parteA.getUserData() == "player") && (parteB.getUserData() == "enemy")) {
			contatoPlayerInimigo(parteA,parteB);
		}else if((parteA.getUserData()=="enemy") && (parteB.getUserData()== "player")) {
			contatoPlayerInimigo(parteB,parteA);
		}
		
		
		
		
		if((parteA.getUserData()=="death") && (parteB.getUserData()=="player")){	
			contatoPlayerMorte();
		}else if((parteA.getUserData()=="player")&&(parteB.getUserData()=="death")) {
			contatoPlayerMorte();
		}
		
		
		if((parteA.getUserData()=="next_phase") && (parteB.getUserData()=="player")){
			trocaFase();
		}else if((parteA.getUserData()=="player")&&(parteB.getUserData()=="next_phase")) {
			trocaFase();
		}
		
		/*	
		if((parteA.getUserData() == "enemy") && (parteB.getUserData() == "Wall")) {
			contatoEnemyWall(parteA,parteB);
		}else if((parteA.getUserData()=="Wall") && (parteB.getUserData()== "enemy")) {
			contatoEnemyWall(parteB,parteA);
		}*/
		


		if((parteA.getUserData()=="tiro") && (parteB.getUserData()=="enemy")){
			tiro.onHitBullet();
			Hud.addScore(50);
			inimigo.setLife(inimigo.getLife()- 50);
			if(inimigo.getLife() <= 0) {
				inimigo.destroy = true;
			}
		
		}else if((parteA.getUserData()=="enemy")&&(parteB.getUserData()=="tiro")) {
			Hud.addScore(50);
			tiro.onHitBullet();
			inimigo.setLife(inimigo.getLife()- 50);
			if(inimigo.getLife() <= 0) {
				inimigo.destroy = true;

			}
			

		}


		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
