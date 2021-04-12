package com.myterrible.game.scenarios;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myterrible.game.Megaman;

public class Hud implements Disposable{
	public Stage stage;
	private Viewport viewport;
	private Integer worldTimer;
	private float contaTempo;
	private int life=300;
	private static Integer pontuação;
	
	
	Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldLabel;
	Label lifeLabel;
	Label lifeQuantityLabel;
	static Label pontuaçãoLabel;
	public Hud(SpriteBatch sb) {
		worldTimer = 300;
		contaTempo = 0;
		life = 300;
		pontuação = 0;
		viewport = new FitViewport(Megaman.V_WIDTH, Megaman.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport,sb);
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		
		timeLabel=new Label(String.format("TIME"),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		countdownLabel=new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		lifeLabel=new Label(String.format("LIFE",life),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		lifeQuantityLabel=new Label(String.format("%03d",life),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		levelLabel=new Label(String.format("1-2"),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		worldLabel=new Label(String.format("LEVEL"),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		scoreLabel = new Label(String.format("Score"),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		pontuaçãoLabel = new Label(String.format("%06d",pontuação),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		table.add(lifeLabel).expandX().padTop(10);
		table.add(scoreLabel).expandX().padTop(10);
		table.add(worldLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		
		
		table.row();
		table.add(lifeQuantityLabel).expandX();
		table.add(pontuaçãoLabel).expandX();
		table.add(levelLabel).expandX();
		table.add(countdownLabel).expandX();
		
		stage.addActor(table);

	}
	
	public void update(float deltaTime) {
		contaTempo +=deltaTime;
		if(contaTempo >= 1) {
			worldTimer--;
			countdownLabel.setText(String.format("%03d",worldTimer));
			contaTempo =0;
		}
	}
	
	
	public static void addScore(int valor) {
		pontuação += valor;
		pontuaçãoLabel.setText(String.format("%06d", pontuação));
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

	public Integer getLife() {
		// TODO Auto-generated method stub
		return life;
	}

	public void setLife(int decrease) {
		// TODO Auto-generated method stub
		life = decrease;
		lifeQuantityLabel.setText(String.format("%03d", life));
	}
	
}
