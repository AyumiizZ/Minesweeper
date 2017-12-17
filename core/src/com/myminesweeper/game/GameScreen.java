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
	private ClickingRenderer clickingRenderer;
	private boolean newGame;
	private Texture background;
	private Texture button;
	private GameMap gameMap;
	private boolean die;
	private boolean win;

	public GameScreen(MyMinesweeperGame myMinesweeperGame, GameMap gameMap) {
		this.myMinesweeperGame = myMinesweeperGame;
		this.gameMap = gameMap;
		this.gameMapRenderer = new GameMapRenderer(myMinesweeperGame.batch, gameMap);
		this.endGameRenderer = new EndGameRenderer(myMinesweeperGame.batch);
		this.clickingRenderer = new ClickingRenderer(this, gameMapRenderer);
		setTexture();
	}

	@Override
	public void render(float delta) {
		SpriteBatch batch = myMinesweeperGame.batch;
		batch.begin();
		clear();
		batch.draw(background, 0, 0);
		gameMapRenderer.updateMap(batch, die);
		if(newGame == true) {
			createNewGame();
		}
		update(delta);
		batch.draw(button, 18 * 40 + 280, 80);
		batch.end();
		
	}
	private void createNewGame() {
		this.gameMap = new GameMap();
		this.gameMapRenderer = new GameMapRenderer(myMinesweeperGame.batch, gameMap);
		this.clickingRenderer = new ClickingRenderer(this, gameMapRenderer);
	}
	public void update(float delta) {
		clickingRenderer.updateClicking(delta);
		die = clickingRenderer.getDie();
		win = clickingRenderer.getWin();
		newGame = clickingRenderer.wannaNewGame();
		endGameRenderer.end(win, die);
	}

	public void clear() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void setTexture() {
		background = new Texture("background.png");
		button = new Texture("button.png");
	}

}
