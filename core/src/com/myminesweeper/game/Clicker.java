package com.myminesweeper.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Clicker implements InputProcessor {

	private int MouseX;
	private int MouseY;

	public int getX() {
		return MouseX;
	}

	public int getY() {
		return MouseY;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			MouseX = screenX;
			MouseY = screenY;
		} else if (button == Input.Buttons.RIGHT) {
			MouseX = screenX << 10;
			MouseY = screenY << 10;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT || button == Input.Buttons.RIGHT) {
			MouseX = 0;
			MouseY = 0;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
