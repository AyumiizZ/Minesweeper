package com.myminesweeper.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndGameRenderer {
	private Texture youLose;
	private Texture youWin;
	private SpriteBatch batch;

	public EndGameRenderer(SpriteBatch batch) {
		this.batch = batch;
		setTexture();
	}

	public void end(boolean win, boolean die) {
		if (win) {
			winRender();
		}
		if (die) {
			loseRender();
		}
	}

	private void loseRender() {
		batch.draw(youLose, 240, 110);
	}

	private void winRender() {
		batch.draw(youWin, 280, 40);
	}

	private void setTexture() {
		youLose = new Texture("you_lose.png");
		youWin = new Texture("you_win.png");
	}
}
