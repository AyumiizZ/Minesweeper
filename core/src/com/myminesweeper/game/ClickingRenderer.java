package com.myminesweeper.game;

import com.badlogic.gdx.Gdx;

public class ClickingRenderer {
	private GameScreen gameScreen;
	private GameMapRenderer gameMapRenderer;
	private int tempX;
	private int tempY;
	private int tempRow;
	private int tempCol;
	private boolean die;
	private boolean win;
	private Clicker clicker;
	private boolean newGame;

	private float sumTimeClick;

	public ClickingRenderer(GameScreen gameScreen, GameMapRenderer gameMapRenderer) {
		this.gameScreen = gameScreen;
		this.gameMapRenderer = gameMapRenderer;
		this.clicker = new Clicker();
		Gdx.input.setInputProcessor(clicker);
	}

	public void updateClicking(float delta) {
		checkClicking(clicker.getX(),clicker.getY());
		sumTimeClick += delta;
	}

	public boolean getDie() {
		return die;
	}

	public boolean getWin() {
		return win;
	}
	public boolean wannaNewGame() {
		return newGame;
	}
//	public void newGame() {
//		newGameClick(false);
////		System.out.println(newGame);
//		this.die = false;
//		this.win = false;
//	}
	private void checkClicking(int MouseX, int MouseY) {
		if (this.tempX != MouseX && this.tempY != MouseY) {
			if (MouseX >= 18 * 40 + 280 && MouseX <= 18 * 40 + 280 + 160 && MouseY <= 720 - 80
					&& MouseY >= 720 - 80 - 80) {
//				System.out.println("A");
				newGameClick();
			}
			if (MouseX >= 280 && MouseX <= 16 * 40 + 280 && MouseY >= 40 && MouseY <= 16 * 40 + 40) {
				leftClick((MouseX - 280) / 40, 15 - (MouseY - 40) / 40);
			} else if (MouseX >= 280 << 10 && MouseX <= (16 * 40 + 280) << 10 && MouseY >= 40 << 10
					&& MouseY <= (16 * 40 + 40) << 10) {
				rightClick(((MouseX >> 10) - 280) / 40, 15 - ((MouseY >> 10) - 40) / 40);
			}
			if ((this.tempX == 0 && this.tempY == 0) || (this.tempX != MouseX && this.tempY != MouseY)) {
				this.tempX = MouseX;
				this.tempY = MouseY;
			}
		}
	}
	public void newGameClick() {
		this.newGame = true;
	}
	private void rightClick(int row, int col) {
		if(die==false && win==false)
		{
		gameMapRenderer.setFlag(row, col);
		System.out.println("FLAG: " + gameMapRenderer.NumFlag());
		sumTimeClick = 0;
		}
	}

	private void leftClick(int row, int col) {
		if(die==false && win==false)
		{
		gameMapRenderer.setReveal(row, col);
		if (tempRow == row && tempCol == col && sumTimeClick < 0.35) {
			gameMapRenderer.doubleClickReveal(row, col);
		}
		die = gameMapRenderer.haveBombRevealed();
		win = gameMapRenderer.NotBombRevealed();
		sumTimeClick = 0;
		tempRow = row;
		tempCol = col;
		}
	}
}
