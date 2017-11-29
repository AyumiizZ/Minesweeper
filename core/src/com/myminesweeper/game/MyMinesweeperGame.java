package com.myminesweeper.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyMinesweeperGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture[] img;
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture[16];
		img[0] = new Texture("0.png");
		img[1] = new Texture("1.png");
		img[2] = new Texture("2.png");
		img[3] = new Texture("3.png");
		img[4] = new Texture("4.png");
		img[5] = new Texture("5.png");
		img[6] = new Texture("6.png");
		img[7] = new Texture("7.png");
		img[8] = new Texture("8.png");
		img[9] = new Texture("flag.png");
		img[10] = new Texture("ques.png");
		img[11] = new Texture("not_reveal_game_not_end.png");
		img[12] = new Texture("clicked_bomb.png");
		img[13] = new Texture("unclicked_bomb.png");
		img[14] = new Texture("on_click.png");
		img[15] = new Texture("num_flag.png");
	}

	@Override
	public void render () {
		clear();
		boolean[] a = new boolean[10];
//		a[1] = false;
		batch.begin();
		for(int i = 0;i<16;i++)
			batch.draw(img[i], i*40, 0);
//		System.out.println(a[1]);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for(int i = 0;i<16;i++)
			img[i].dispose();
	}
	
	public void clear() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
//	public void genPic(){
//		
//	}
}
