package com.myminesweeper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private MyMinesweeperGame myMinesweeperGame;
	private Texture[] revealed;
	private Texture notRevealed;
	private GameMap gameMap;
	private int MouseX;
	private int MouseY;
	private Clicker clicker;
	public GameScreen (MyMinesweeperGame myMinesweeperGame, GameMap gameMap) {
		this.myMinesweeperGame = myMinesweeperGame;
		this.gameMap = gameMap;
		setTexture();
		this.clicker = new Clicker();
		Gdx.input.setInputProcessor(clicker);


	}
	@Override
	public void render(float delta) {
		update(delta);
		SpriteBatch batch = myMinesweeperGame.batch;
		clear();
		batch.begin();
		updateMap(batch);
		batch.end();
	}
	public void update(float delta) {	
		this.MouseX = clicker.getX();
		this.MouseY = clicker.getY();

		if(MouseX >= 280 && MouseX <= 16*40+280 && MouseY >= 40 && MouseY <= 16*40+40 ) {
			System.out.println("X: "+MouseX+" Y: "+MouseY);
			gameMap.setReaveal((MouseX-280)/40, 15 - (MouseY-40)/40);
		}
	}
	public void updateMap(SpriteBatch batch) {
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				if(gameMap.IsReaveal(i, j))
				{
					batch.draw(revealed[gameMap.getNo(i, j)], i*40 + 280, j*40+40);
				}
				else
				{
					batch.draw(notRevealed, i*40 + 280, j*40+40);
				}
			}
		}
	}
	public void clear()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void setTexture()
	{
		revealed = new Texture[10];
		for(int i = 0; i < 9;i++)
			revealed[i] = new Texture(i+".png");
		revealed[9] = new Texture("unclicked_bomb.png");
		notRevealed = new Texture("not_reveal.png");
	}

}
