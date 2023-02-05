package com.eroguro.spiral;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	boolean[][] filled;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		filled = new boolean[50][];
		for(int i = 0; i < 50; i++){
			filled[i] = new boolean[50];
			for(int j = 0; j < 50; j++){
				filled[i][j] = false;
			}
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		for(int x = 0; x < 50; x++){
			for(int y = 0; y < 50; y++){
				filled[x][y] = false;
			}
		}
		int dir = 3;
		int x = 25;
		int y = 25;
		batch.begin();
		drawNumber(x,y,"-");
		filled[x][y] = true;
		for(int i = 1;i < 10; i++)
		{
			switch(dir) {
				case 0: // up
					if(!filled[x][y+1]){
						y++;
						if(!filled[x-1][y]){
							drawNumber(x+1,y,"┐");
						}else {
							drawNumber(x, y, "|");
						}
						dir = 2;
						filled[x][y] = true;
						break;
					}
					x++;
					drawNumber(x, y, "-");
					break;
				case 1: // down
					if(!filled[x][y-1]){
						y--;
						drawNumber(x, y, "|");
						dir = 3;
						filled[x][y] = true;
						break;
					}
					x--;
					drawNumber(x, y, "-");
					break;
				case 2: // left
					if(!filled[x-1][y]){
						x--;
						drawNumber(x, y, "-");
						dir = 1;
						filled[x][y] = true;
						break;
					}
					y++;
					drawNumber(x, y, "|");
					break;
				case 3: // right
					if(!filled[x+1][y]){
						x++;
						drawNumber(x, y, "-");
						dir = 0;
						filled[x][y] = true;
						break;
					}
					y--;
					drawNumber(x, y, "|");
					break;
			}


		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}

	private void drawNumber(int x, int y, Integer num){
		font.draw(batch, num.toString(), ((x)*8), ((y)*8));
	}

	private void drawNumber(int x, int y, String num){
		font.draw(batch, num, x*16, y*16);
	}
}
