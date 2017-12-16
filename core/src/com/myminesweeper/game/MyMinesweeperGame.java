package com.myminesweeper.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyMinesweeperGame extends Game {
	public SpriteBatch batch;
	private GameMap gameMap;
	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gameMap = new GameMap();
		setScreen(new GameScreen(this, gameMap));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
