package com.myminesweeper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private MyMinesweeperGame myMinesweeperGame;
	private GameMapRenderer gameMapRenderer;
	private EndGameRenderer endGameRenderer;
	private Texture background;
	private Texture button;
	private GameMap gameMap;
	private int MouseX;
	private int MouseY;
	private int tempX;
	private int tempY;
	private int tempRow;
	private int tempCol;
	private boolean die;
	private boolean win;
	private float sumTimeClick;
	private Clicker clicker;

	public GameScreen(MyMinesweeperGame myMinesweeperGame, GameMap gameMap) {
		this.myMinesweeperGame = myMinesweeperGame;
		this.gameMap = gameMap;
		this.gameMapRenderer = new GameMapRenderer(myMinesweeperGame.batch, gameMap);
		this.endGameRenderer = new EndGameRenderer(myMinesweeperGame.batch);
		setTexture();
		this.clicker = new Clicker();
		this.sumTimeClick = 0;
		Gdx.input.setInputProcessor(clicker);
	}

	@Override
	public void render(float delta) {
		SpriteBatch batch = myMinesweeperGame.batch;
		batch.begin();
		clear();
		batch.draw(background, 0, 0);
		gameMapRenderer.updateMap(batch, die);
		batch.draw(button, 18 * 40 + 280, 80);
		if (!die && !win) {
			update(delta);
		} else {
			endGameRenderer.end(win, die);
		}
		batch.end();
	}

	public void update(float delta) {
		this.MouseX = clicker.getX();
		this.MouseY = clicker.getY();
		if (MouseX >= 18 * 40 + 280 && MouseX <= 18 * 40 + 280 + 160 && MouseY <= 720 - 80 && MouseY >= 720 - 80 - 80) {

			System.out.println("Left Click at X: " + MouseX + " Y: " + MouseY + " time: " + sumTimeClick);
			this.gameMap = new GameMap();
		}
		if (MouseX >= 280 && MouseX <= 16 * 40 + 280 && MouseY >= 40 && MouseY <= 16 * 40 + 40
				&& this.tempX != this.MouseX && this.tempY != this.MouseY) {
			System.out.println("Left Click at X: " + MouseX + " Y: " + MouseY + " time: " + sumTimeClick);

			int row = (MouseX - 280) / 40;
			int col = 15 - (MouseY - 40) / 40;
			gameMapRenderer.setReveal(row, col);
			if (tempRow == row && tempCol == col && sumTimeClick < 0.35) {
				gameMapRenderer.doubleClickReveal(row, col);
			}
			die = gameMapRenderer.haveBombRevealed();
			win = gameMapRenderer.NotBombRevealed();
			sumTimeClick = 0;
			tempRow = row;
			tempCol = col;

		} else if (MouseX >= 280 << 10 && MouseX <= (16 * 40 + 280) << 10 && MouseY >= 40 << 10
				&& MouseY <= (16 * 40 + 40) << 10 && this.tempX != this.MouseX && this.tempY != this.MouseY) {
			System.out.println(
					"Right Click at X: " + (MouseX >> 10) + " Y: " + (MouseY >> 10) + " time: " + sumTimeClick);
			sumTimeClick = 0;
			gameMap.setFlag(((MouseX >> 10) - 280) / 40, 15 - ((MouseY >> 10) - 40) / 40);

			System.out.println("FLAG: " + gameMapRenderer.NumFlag());
		}
		if ((this.tempX == 0 && this.tempY == 0) || (this.tempX != this.MouseX && this.tempY != this.MouseY)) {
			this.tempX = this.MouseX;
			this.tempY = this.MouseY;
		}
		sumTimeClick += delta;
	}

	public void clear() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void setTexture() {
		// youLose = new Texture("you_lose.png");
		background = new Texture("background.png");
		// youWin = new Texture("you_win.png");
		button = new Texture("button.png");
	}

}
