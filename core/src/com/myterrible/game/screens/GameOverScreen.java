package com.myterrible.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myterrible.game.Megaman;

public class GameOverScreen implements Screen {
	protected Megaman gameSave;
	protected Stage stage;
	private Viewport viewport;
	private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
	private Texture texture;
	private BitmapFont font;
	
	public GameOverScreen(final Megaman game) {
		gameSave = game;
        
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.V_WIDTH,game.V_HEIGHT, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, game.batch);   
        }

     
	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        Gdx.input.setInputProcessor(stage);
        mainTable.right();
        mainTable.bottom();
        texture = new Texture(Gdx.files.internal("img/gameover.jpg"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture,0,0,600,480);

        //Create buttons
        font = new BitmapFont();
        skin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("atlas/buttons.pack"));
        
        skin.addRegions(atlas);
    
        TextButtonStyle style = new TextButtonStyle();
        style.font = font;
   
        style.down = skin.getDrawable("buttondown");
   
        TextButton playButton = new TextButton("PLAY AGAIN?", style);
        TextButton exitButton = new TextButton("EXIT", style);
        
      
        mainTable.addActor(playButton);
        mainTable.addActor(exitButton);
        //Add listeners to buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(gameSave));
            }
        });
        exitButton.addListener(new ClickListener() {
        	@Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
    });
        mainTable.row();
        mainTable.row();
        mainTable.add(playButton);
        mainTable.row();
     
        mainTable.add(exitButton);
        
        stage.addActor(mainTable);
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(176, 224, 230, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        gameSave.batch.begin();
        gameSave.batch.draw(texture, 0, 0,600,460);
        gameSave.batch.end();
        
        stage.act();
        stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		 viewport.update(width, height);
	     camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);	
	     camera.update();
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

	}

}
