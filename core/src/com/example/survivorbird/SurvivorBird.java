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

		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(bird, birdX, birdY - 100, Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/13);


		batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
