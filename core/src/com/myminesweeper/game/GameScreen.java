package com.myminesweeper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private MyMinesweeperGame myMinesweeperGame;
	private GameMapRenderer gameMapRenderer;
	private EndGameRenderer endGameRenderer;
	private ClickingRenderer clickingRenderer;
	private Texture background;
	private Texture button;
	private GameMap gameMap;
	private BitmapFont font;
	private boolean newGame;

	public GameScreen(MyMinesweeperGame myMinesweeperGame, GameMap gameMap) {
		this.myMinesweeperGame = myMinesweeperGame;
		this.gameMap = gameMap;
		this.gameMapRenderer = new GameMapRenderer(gameMap);
		this.endGameRenderer = new EndGameRenderer(myMinesweeperGame.batch);
		this.clickingRenderer = new ClickingRenderer(gameMapRenderer);
		this.font = new BitmapFont();
		setTexture();
	}

	@Override
	public void render(float delta) {
		SpriteBatch batch = myMinesweeperGame.batch;
		batch.begin();

		if (clickingRenderer.isUnlucky()) {
			int tempX = clickingRenderer.dieAtX();
			int tempY = clickingRenderer.dieAtY();
			createNewGame();
			clickingRenderer.unluckyClicking(tempX, tempY);
		} else {
			clear();
			batch.draw(background, 0, 0);
			batch.draw(button, 18 * 40 +240, 80);
			gameMapRenderer.updateMap(batch);
			if(gameMapRenderer.NumFlag()<10)
				font.draw(batch,gameMapRenderer.NumFlag()+"/40",1006,590);
			else
				font.draw(batch,gameMapRenderer.NumFlag()+"/40",1001,590);
		}
		if (newGame == true) {
			createNewGame();
		}
		update(delta);
		batch.end();

	}

	private void createNewGame() {
		this.gameMap = new GameMap();
		this.gameMapRenderer = new GameMapRenderer(gameMap);
		this.clickingRenderer = new ClickingRenderer(gameMapRenderer);
	}

	public void update(float delta) {
		clickingRenderer.updateClicking(delta);
		boolean die = clickingRenderer.getDie();
		boolean win = clickingRenderer.getWin();
		newGame = clickingRenderer.wannaNewGame();
		if (clickingRenderer.isUnlucky() == false)
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
