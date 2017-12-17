package com.myminesweeper.game;

import com.badlogic.gdx.Gdx;

public class ClickingRenderer {
	private GameMapRenderer gameMapRenderer;
	private int tempX;
	private int tempY;
	private int tempRow;
	private int tempCol;
	private boolean die;
	private boolean win;
	private Clicker clicker;
	private boolean newGame;
	private boolean firstClick = true;
	private float sumTimeClick;
	private boolean dieAtFirstClick;

	public ClickingRenderer(GameMapRenderer gameMapRenderer) {
		this.gameMapRenderer = gameMapRenderer;
		this.clicker = new Clicker();
		Gdx.input.setInputProcessor(clicker);
	}

	public void updateClicking(float delta) {
		int MouseX = clicker.getX();
		int MouseY = clicker.getY();
		checkClicking(MouseX, MouseY);
		sumTimeClick += delta;
	}

	public void unluckyClicking(int MouseX, int MouseY) {
		checkClicking(MouseX, MouseY);
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

	public boolean isUnlucky() {
		return dieAtFirstClick;
	}

	public int dieAtX() {
		return tempX;
	}

	public int dieAtY() {
		return tempY;
	}

	private void checkClicking(int MouseX, int MouseY) {
		if (this.tempX != MouseX && this.tempY != MouseY) {
			if (MouseX >= 18 * gameMapRenderer.BLOCKSIZE + gameMapRenderer.XSTART && MouseX <= 18 * gameMapRenderer.BLOCKSIZE + gameMapRenderer.XSTART + 160 && MouseY <= 720 - 80
					&& MouseY >= 720 - 80 - 80) {
				newGameClick();
			}
			if (MouseX >= gameMapRenderer.XSTART && MouseX <= 16 * gameMapRenderer.BLOCKSIZE + gameMapRenderer.XSTART && MouseY >= gameMapRenderer.YSTART && MouseY <= 16 * gameMapRenderer.BLOCKSIZE + gameMapRenderer.YSTART) {
				leftClick((MouseX - gameMapRenderer.XSTART) / gameMapRenderer.BLOCKSIZE, 15 - (MouseY - gameMapRenderer.YSTART) / gameMapRenderer.BLOCKSIZE);
				if (die == true && firstClick) {
					this.dieAtFirstClick = true;
				}
				firstClick = false;
			} else if (MouseX >= gameMapRenderer.XSTART << 10 && MouseX <= (16 * gameMapRenderer.BLOCKSIZE + gameMapRenderer.XSTART) << 10 && MouseY >= gameMapRenderer.YSTART << 10
					&& MouseY <= (16 * gameMapRenderer.BLOCKSIZE + gameMapRenderer.YSTART) << 10) {
				rightClick(((MouseX >> 10) - gameMapRenderer.XSTART) / gameMapRenderer.BLOCKSIZE, 15 - ((MouseY >> 10) - gameMapRenderer.YSTART) / gameMapRenderer.BLOCKSIZE);
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
		if (die == false && win == false) {
			gameMapRenderer.setFlag(row, col);
			System.out.println("FLAG: " + gameMapRenderer.NumFlag());
			sumTimeClick = 0;
		}
	}

	private void leftClick(int row, int col) {
		if (die == false && win == false) {
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
