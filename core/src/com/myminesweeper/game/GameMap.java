package com.myminesweeper.game;

import java.util.Random;

public class GameMap {
	Random rand = new Random();
	private int[][] MAP = new int[16][16];
	private int[][] FLAG = new int[16][16];
	private boolean[][] REVEAL = new boolean[16][16];

	public GameMap() {
		genMines();
		genNum();
		debug();
	}

	private void debug() {
		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				System.out.print(MAP[i][15 - j] + " ");
			}
			System.out.println("");
		}

	}

	private void genMines() {
		for (int i = 0; i < 40; i++) {
			int x = rand.nextInt(16);
			int y = rand.nextInt(16);
			while (MAP[x][y] == 9) {
				x = rand.nextInt(16);
				y = rand.nextInt(16);
			}
			MAP[x][y] = 9;
		}
	}

	private void genNum() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (MAP[i][j] == 9) {
					for (int x = -1; x <= 1; x++) {
						for (int y = -1; y <= 1; y++) {
							if (i + x >= 0 && i + x <= 15 && j + y >= 0 && j + y <= 15) {
								if (MAP[i + x][j + y] != 9) // 0,0 continue here
								{
									MAP[i + x][j + y]++;
								}
							}
						}
					}
				}
			}
		}
	}

	public boolean haveBomb(int row, int col) {
		return MAP[row][col] == 9;
	}

	public int isFlag(int row, int col) {
		return FLAG[row][col];
	}

	public boolean isReveal(int row, int col) {
		return REVEAL[row][col];
	}

	public void setReveal(int row, int col, boolean input) {
		REVEAL[row][col] = input;
	}

	public int getNo(int row, int col) {
		return MAP[row][col];
	}

	public void setFlag(int row, int col) {
		FLAG[row][col] = (FLAG[row][col] + 1) % 3;
	}
}