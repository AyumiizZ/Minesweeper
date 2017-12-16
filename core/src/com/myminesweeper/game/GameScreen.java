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
	private Texture[] somethingElse;
	private Texture gameOver;
	private GameMap gameMap;
	private int MouseX;
	private int MouseY;
	private int tempX;
	private int tempY;
	private int dieAtX;
	private int dieAtY;
	private boolean die;
	private float sumTimeClick;
	private Clicker clicker;

	public GameScreen(MyMinesweeperGame myMinesweeperGame, GameMap gameMap) {
		init(myMinesweeperGame,gameMap);
	}

	public void init(MyMinesweeperGame myMinesweeperGame, GameMap gameMap) {
		this.myMinesweeperGame = myMinesweeperGame;
		this.gameMap = gameMap;
		setTexture();
		this.clicker = new Clicker();
		this.sumTimeClick = 0;
		Gdx.input.setInputProcessor(clicker);
	}
	@Override
	public void render(float delta) {
		if (!die) {
			update(delta);
		}
		else{
			init(myMinesweeperGame, gameMap);
		}
		SpriteBatch batch = myMinesweeperGame.batch;
		clear();
		batch.begin();
		updateMap(batch);
		batch.end();
	}

	public void update(float delta) {
		this.MouseX = clicker.getX();
		this.MouseY = clicker.getY();
		if (MouseX >= 280 && MouseX <= 16 * 40 + 280 && MouseY >= 40 && MouseY <= 16 * 40 + 40
				&& this.tempX != this.MouseX && this.tempY != this.MouseY) {
			System.out.println("Left Click at X: " + MouseX + " Y: " + MouseY + " time: " + sumTimeClick);
			sumTimeClick = 0;
			int row = (MouseX - 280) / 40;
			int col = 15 - (MouseY - 40) / 40;
			if(gameMap.IsFlag(row, col) == 0)
				die = gameMap.HaveBomb(row,col);
			gameMap.setReveal(row,col);
			if (die) {
				dieAtX = (MouseX - 280) / 40;
				dieAtY = 15 - (MouseY - 40) / 40;
			}

		} else if (MouseX >= 280 << 10 && MouseX <= (16 * 40 + 280) << 10 && MouseY >= 40 << 10
				&& MouseY <= (16 * 40 + 40) << 10 && this.tempX != this.MouseX && this.tempY != this.MouseY) {
			System.out.println(
					"Right Click at X: " + (MouseX >> 10) + " Y: " + (MouseY >> 10) + " time: " + sumTimeClick);
			sumTimeClick = 0;
			gameMap.setFlag(((MouseX >> 10) - 280) / 40, 15 - ((MouseY >> 10) - 40) / 40);
		}
		if ((this.tempX == 0 && this.tempY == 0) || (this.tempX != this.MouseX && this.tempY != this.MouseY)) {
			this.tempX = this.MouseX;
			this.tempY = this.MouseY;
		}
		sumTimeClick += delta;
	}

	public void updateMap(SpriteBatch batch) {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (gameMap.IsReaveal(i, j)) {
					batch.draw(revealed[gameMap.getNo(i, j)], i * 40 + 280, j * 40 + 40);
				} else if (gameMap.IsFlag(i, j) > 0) {
					batch.draw(somethingElse[gameMap.IsFlag(i, j) - 1], i * 40 + 280, j * 40 + 40);
				} else if (gameMap.HaveBomb(i, j) && die) {
					batch.draw(revealed[10], i * 40 + 280, j * 40 + 40);
				} else {
					batch.draw(notRevealed, i * 40 + 280, j * 40 + 40);
				}
			}
		}
		if(die)
			batch.draw(gameOver,200,200);
	}

	public void clear() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void setTexture() {
		revealed = new Texture[11];
		for (int i = 0; i < 9; i++)
			revealed[i] = new Texture(i + ".png");
		revealed[9] = new Texture("clicked_bomb.png");
		revealed[10] = new Texture("unclicked_bomb.png");
		somethingElse = new Texture[2];
		somethingElse[0] = new Texture("flag.png");
		somethingElse[1] = new Texture("ques.png");
		gameOver = new Texture("badlogic.jpg");
		notRevealed = new Texture("not_reveal.png");
	}

}
