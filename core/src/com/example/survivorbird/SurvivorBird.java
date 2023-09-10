package com.example.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background;
	Texture bird;
	Texture bee1;
	Texture bee2;
	Texture bee3;
	float birdX = 0;
	float birdY = 0;
	int gameState = 0;		//Oyunun başlayıp başlamadığını kontrol edecek
	float velocity;
	float gravity = 0.7f;




	int numberOfEnemies = 4;
	float [] enemyX = new float[numberOfEnemies];
	float [] enemyOffSet1 = new float[numberOfEnemies];
	float [] enemyOffSet2 = new float[numberOfEnemies];
	float [] enemyOffSet3 = new float[numberOfEnemies];

	Random random;
	float distance = 0;

	float enemyVelocity = 5;

	@Override
	public void create () {

		batch = new SpriteBatch();
		background = new Texture("background.png");
		bird = new Texture("bird.png");
		bee1 = new Texture("bee.png");
		bee2 = new Texture("bee.png");
		bee3 = new Texture("bee.png");

		distance = Gdx.graphics.getWidth() / 2;

		random = new Random();

		birdX = Gdx.graphics.getWidth() / 3;
		birdY = Gdx.graphics.getHeight() / 2;

		for (int i = 0; i < numberOfEnemies; i++) {

			enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

			enemyX[i] = Gdx.graphics.getWidth() - (i *  distance);
		}


	}

	@Override
	public void render () {

		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		
		
		if (gameState == 1) {
			
			if (Gdx.input.justTouched()) {
			velocity = -13.5f;
			}

			for (int i = 0; i < enemyX.length; i++) {

				if (enemyX[i] < -Gdx.graphics.getWidth()/20) {
					enemyX[i] = enemyX[i] + numberOfEnemies * distance;

					enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

				}else {
					enemyX[i] = enemyX[i] - enemyVelocity;
				}

			
				batch.draw(bee1, enemyX[i], Gdx.graphics.getHeight()/2 + enemyOffSet1[i], Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/13);
				batch.draw(bee1, enemyX[i], Gdx.graphics.getHeight()/2 + enemyOffSet2[i], Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/13);
				batch.draw(bee1, enemyX[i], Gdx.graphics.getHeight()/2 + enemyOffSet3[i], Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/13);

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

		batch.draw(bird, birdX, birdY , Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/13);

		batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
