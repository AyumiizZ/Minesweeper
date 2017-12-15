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
	private int tempX;
	private int tempY;
	private int dieAtX;
	private int dieAtY;
	private boolean die;
	private Clicker clicker;

	public GameScreen(MyMinesweeperGame myMinesweeperGame, GameMap gameMap) {
		this.myMinesweeperGame = myMinesweeperGame;
		this.gameMap = gameMap;
		setTexture();
		this.clicker = new Clicker();
		Gdx.input.setInputProcessor(clicker);

	}

	@Override
	public void render(float delta) {
		if (!die) {
			update(delta);
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
			System.out.println("X: " + MouseX + " Y: " + MouseY);
			die = gameMap.setReveal((MouseX - 280) / 40, 15 - (MouseY - 40) / 40);
			if (die) {
				dieAtX = (MouseX - 280) / 40;
				dieAtY = 15 - (MouseY - 40) / 40;
			}

		}
		if ((this.tempX == 0 && this.tempY == 0) || (this.tempX != this.MouseX && this.tempY != this.MouseY)) {
			this.tempX = this.MouseX;
			this.tempY = this.MouseY;
		}
	}

	public void updateMap(SpriteBatch batch) {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (gameMap.IsReaveal(i, j)) {
					batch.draw(revealed[gameMap.getNo(i, j)], i * 40 + 280, j * 40 + 40);
				} else if (gameMap.HaveBomb(i, j) && die) {
					batch.draw(revealed[10], i * 40 + 280, j * 40 + 40);
				} else {
					batch.draw(notRevealed, i * 40 + 280, j * 40 + 40);
				}
			}
		}
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
		notRevealed = new Texture("not_reveal.png");
	}

}
