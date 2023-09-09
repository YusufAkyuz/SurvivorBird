package com.example.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background;
	Texture bird;
	float birdX = 0;
	float birdY = 0;
	int gameState = 0;		//Oyunun başlayıp başlamadığını kontrol edecek
	float velocity;
	float gravity = 0.41f;


	@Override
	public void create () {

		batch = new SpriteBatch();
		background = new Texture("background.png");
		bird = new Texture("bird.png");

		birdX = Gdx.graphics.getWidth() / 3;
		birdY = Gdx.graphics.getHeight() / 2;


	}

	@Override
	public void render () {


		if (gameState == 1) {

			if (Gdx.input.justTouched()) {
				velocity = -11.8f;

			}

			if (birdY > 0 || velocity < 0) {
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}


		}else {
			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		}

		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(bird, birdX, birdY , Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/13);


		batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
