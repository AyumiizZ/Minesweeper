package com.myminesweeper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter{
	private MyMinesweeperGame myMinesweeperGame;
	private Texture[] revealed;
	private GameMap gameMap;
	public GameScreen (MyMinesweeperGame myMinesweeperGame, GameMap gameMap) {
		this.myMinesweeperGame = myMinesweeperGame;
		this.gameMap = gameMap;
		revealed = new Texture[10];
		revealed[0] = new Texture("0.png");
		revealed[1] = new Texture("1.png");
		revealed[2] = new Texture("2.png");
		revealed[3] = new Texture("3.png");
		revealed[4] = new Texture("4.png");
		revealed[5] = new Texture("5.png");
		revealed[6] = new Texture("6.png");
		revealed[7] = new Texture("7.png");
		revealed[8] = new Texture("8.png");
		revealed[9] = new Texture("unclicked_bomb.png");
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
				batch.draw(revealed[gameMap.getNo(i, j)], i*40 + 280, j*40+40);
			}
		}
		batch.end();
	}

	public void clear()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
