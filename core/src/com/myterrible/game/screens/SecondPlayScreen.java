package com.myterrible.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myterrible.game.Megaman;
import com.myterrible.game.Sprites.MegaPlayer;
import com.myterrible.game.Sprites.MegaPlayer.Estados;
import com.myterrible.game.Sprites.Shot;
import com.myterrible.game.Sprites.Terrestres;
import com.myterrible.game.scenarios.Hud;
import com.myterrible.game.screens.MapScreen;
import com.myterrible.game.tools.B2WorldCreator;
import com.myterrible.game.tools.WorldContactListener;

public class SecondPlayScreen extends MapScreen implements Screen {
	protected Terrestres enemy;
	protected Terrestres enemy2;
	protected Terrestres enemy3;
	
	public SecondPlayScreen(Megaman game) {
		super(game);
		atlas = new TextureAtlas("spritesv2/megaman_only.pack");
		atlas2 = new TextureAtlas("spritesv2/megaman_and_enemies.pack");
		this.game = game;
		hud = new Hud(game.batch);
		gamecam = new OrthographicCamera();
		gamePort = new StretchViewport(Megaman.V_WIDTH/Megaman.PPM,Megaman.V_HEIGHT/Megaman.PPM,gamecam);
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("maps_itens/mapa1/FASE_2.tmx");
		renderer = new OrthogonalTiledMapRenderer(map,1/Megaman.PPM);
		gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
	
		world = new World(new Vector2(0,-10),true);
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(this);
		player = new MegaPlayer(this,32,1600);
		player.tiro = new Shot(world,this,player,player.correndoDireita);
		enemy = new Terrestres(this, 100, 1600);
		enemy2 = new Terrestres(this,1800,400);
		enemy3 = new Terrestres(this,2200,400);
		world.setContactListener(new WorldContactListener(player,game, hud, enemy,world));
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	
	public void update(float deltaTime) {
		handleInput(deltaTime);
		world.step(1/60f, 6, 2);
		
		player.update(deltaTime);
		player.tiro.update(deltaTime);
		enemy.update(deltaTime);
		enemy2.update(deltaTime);
		enemy3.update(deltaTime);
		hud.update(deltaTime);
		gamecam.position.x = player.b2body.getPosition().x;
		gamecam.position.y = player.b2body.getPosition().y;
		//atualiza camera com coordenadas
		gamecam.update();
		
		//desenhar apenas o que a camera ve
		renderer.setView(gamecam);
	}

	private void handleInput(float deltaTime) {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && (player.estadoAtual != Estados.PULANDO)){
			player.b2body.applyLinearImpulse(new Vector2(0,4.5f),player.b2body.getWorldCenter(),true);
			
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) {
			player.b2body.applyLinearImpulse(new Vector2(0.1f,0) , player.b2body.getWorldCenter(),true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2) {
			player.b2body.applyLinearImpulse(new Vector2(-0.1f,0) , player.b2body.getWorldCenter(),true);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			player.shoot();
		}
		
	
	}
	
	@Override
	public void render(float deltaTime) {
		update(deltaTime);
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gamecam.position.set(player.getX(), player.getY(), 0);
		renderer.render();
		
		b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		player.tiro.draw(game.batch);
		enemy.draw(game.batch);
		enemy2.draw(game.batch);
		enemy3.draw(game.batch);
		
		game.batch.end();
		hud.stage.draw();
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		
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
		// TODO Auto-generated method stub
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
	}

}
