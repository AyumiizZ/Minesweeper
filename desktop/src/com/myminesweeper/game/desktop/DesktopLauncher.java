package com.myminesweeper.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myminesweeper.game.MyMinesweeperGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MyMinesweeperGame.WIDTH;
		config.height = MyMinesweeperGame.HEIGHT;
		new LwjglApplication(new MyMinesweeperGame(), config);
	}
}
