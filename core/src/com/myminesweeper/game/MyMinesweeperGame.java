package com.myminesweeper.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyMinesweeperGame extends Game {
	public SpriteBatch batch;
	private GameMap gameMap;
//	Texture img;
	public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
	@Override
    public void create () {
        batch = new SpriteBatch();
        gameMap = new GameMap();
        setScreen(new GameScreen(this, gameMap));
    }

	@Override
	public void render () {
		super.render();
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
////		batch.draw(img, 0, 0);
//		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
	}
	
}
