package com.myminesweeper.game;

import java.util.Random;

public class GameMap {
	Random rand = new Random();
	private int[][] MAP = new int[16][16];
	private int[][] isFlag = new int[16][16];
	public GameMap() {
		genMines();
		genNum();
	}
	
	private void genMines()
	{
		for(int i = 0; i < 16;i++)
		{
			for(int j = 0; j < 16; j++)
			{
				int x = rand.nextInt(16);
				int y = rand.nextInt(16);
				while (MAP[x][y] == -1)
				{
					x = rand.nextInt(16);
					y = rand.nextInt(16);
				}
				MAP[x][y] = -1;
			}
		}
	}
	private void genNum()
	{
		for(int i = 0; i < 16; i++)
		{
			for(int j =0; j < 16; j++)
			{
				if(MAP[i][j] == -1)
				{
					for(int x = -1;x <= 1;x++)
					{
						for(int y = -1; y <= 1; y++)
						{
							if(i+x >= 0 && i+x <= 15 && j+y >= 0 && j+y <= 15)
							{
								if(MAP[i+x][j+y] != -1) // 0,0 continue here
								{
									MAP[i+x][j+y]++;
								}
							}
						}
					}
				}
			}
		}
	}
	public boolean HaveBomb(int row,int col)
	{
		return MAP[row][col] == -1;
	}
	public int getIsFlag(int row,int col)
	{
		return isFlag[row][col];
	}
	
}
