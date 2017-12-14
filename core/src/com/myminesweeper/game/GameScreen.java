package com.myminesweeper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter{
	private MyMinesweeperGame myMinesweeperGame;
	private Texture[] revealed;
	private Texture notRevealed;
	private GameMap gameMap;
	public GameScreen (MyMinesweeperGame myMinesweeperGame, GameMap gameMap) {
		this.myMinesweeperGame = myMinesweeperGame;
		this.gameMap = gameMap;
		setTexture();

	}
	@Override
	public void render(float delta) {
		SpriteBatch batch = myMinesweeperGame.batch;
		clear();
		batch.begin();
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
		batch.end();
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
