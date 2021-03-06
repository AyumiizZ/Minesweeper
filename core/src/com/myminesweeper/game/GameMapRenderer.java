package com.myminesweeper.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMapRenderer {
	private GameMap gameMap;
	private Texture[] revealed;
	private Texture notRevealed;
	private Texture[] somethingElse;
	public int XSTART = 240;
	public int YSTART = 40;
	public int BLOCKSIZE = 40;

	public GameMapRenderer(GameMap gameMap) {
		this.gameMap = gameMap;
		setTexture();
	}

	public void setReveal(int row, int col) {
		if (row >= 0 && row <= 15 && col >= 0 && col <= 15 && !gameMap.isReveal(row, col)
				&& gameMap.isFlag(row, col) == 0) {
			gameMap.setReveal(row, col, true);
			if (gameMap.getNo(row, col) == 0) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (i + row >= 0 && i + row <= 15 && j + col >= 0 && j + col <= 15) {
							setReveal(i + row, j + col);
						}
					}
				}
			}
		}
	}

	public void doubleClickReveal(int row, int col) {
		int noFlag = 0;
		if (gameMap.isReveal(row, col) == true) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i + row >= 0 && i + row <= 15 && j + col >= 0 && j + col <= 15) {
						if (gameMap.isFlag(i + row, j + col) > 0) {
							noFlag++;
							// System.out.println("Flag at X: " + (row + i) + " Y: " + (col + j));
						}
					}
				}
			}
			// System.out.println("noFlag = " + noFlag);
			if (noFlag >= gameMap.getNo(row, col)) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (i + row >= 0 && i + row <= 15 && j + col >= 0 && j + col <= 15) {
							setReveal(i + row, j + col);
						}
					}
				}
			}
		}
	}

	public boolean haveBombRevealed() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (gameMap.haveBomb(i, j) && gameMap.isReveal(i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	public int NumFlag() {
		int noFlag = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (gameMap.isFlag(i, j) == 1) {
					noFlag++;
				}
			}
		}
		return noFlag;
	}

	public boolean NotBombRevealed() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (!gameMap.haveBomb(i, j) && !gameMap.isReveal(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	public void updateMap(SpriteBatch batch) {
		boolean die = haveBombRevealed();
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (gameMap.isReveal(i, j)) {
					batch.draw(revealed[gameMap.getNo(i, j)], i * BLOCKSIZE + XSTART, j * BLOCKSIZE + YSTART);
				} else if (gameMap.isFlag(i, j) > 0 && die && gameMap.haveBomb(i, j) == false) {
					batch.draw(somethingElse[2], i * BLOCKSIZE + XSTART, j * BLOCKSIZE + YSTART);
				} else if (gameMap.isFlag(i, j) > 0) {
					batch.draw(somethingElse[gameMap.isFlag(i, j) - 1], i * BLOCKSIZE + XSTART, j * BLOCKSIZE + YSTART);
				} else if (gameMap.haveBomb(i, j) && die) {
					batch.draw(revealed[10], i * BLOCKSIZE + XSTART, j * BLOCKSIZE + YSTART);
				} else {
					batch.draw(notRevealed, i * BLOCKSIZE + XSTART, j * BLOCKSIZE + YSTART);
				}
			}
		}
		batch.draw(somethingElse[0], 19 * BLOCKSIZE + XSTART, 14 * BLOCKSIZE + YSTART);
	}

	public void setFlag(int row, int col) {
		if (gameMap.isReveal(row, col) == false) {
			gameMap.setFlag(row, col);
		}
	}

	private void setTexture() {
		revealed = new Texture[11];
		for (int i = 0; i < 9; i++)
			revealed[i] = new Texture(i + ".png");
		revealed[9] = new Texture("clicked_bomb.png");
		revealed[10] = new Texture("unclicked_bomb.png");
		somethingElse = new Texture[3];
		somethingElse[0] = new Texture("flag.png");
		somethingElse[1] = new Texture("ques.png");
		somethingElse[2] = new Texture("wrong_flag.png");
		notRevealed = new Texture("not_reveal.png");
	}
}
