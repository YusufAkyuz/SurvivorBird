package com.example.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
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

	Circle birdCircle;

	Circle[] enemyCircle1;
	Circle[] enemyCircle2;
	Circle[] enemyCircle3;

	ShapeRenderer shapeRenderer;

	int score = 0;
	int scoreEnemy = 0;

	BitmapFont font;
	BitmapFont fontGameOver;


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

		birdCircle = new Circle();
		enemyCircle1 = new Circle[numberOfEnemies];
		enemyCircle2 = new Circle[numberOfEnemies];
		enemyCircle3 = new Circle[numberOfEnemies];

		shapeRenderer = new ShapeRenderer();



		for (int i = 0; i < numberOfEnemies; i++) {

			enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

			enemyX[i] = Gdx.graphics.getWidth() - (i *  distance);

			enemyCircle1[i] = new Circle();
			enemyCircle2[i] = new Circle();
			enemyCircle3[i] = new Circle();
		}

		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(4);

		fontGameOver = new BitmapFont();
		fontGameOver.setColor(Color.BROWN);
		fontGameOver.getData().setScale(6);


	}

	@Override
	public void render () {

		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());



		if (gameState == 1) {

			if (enemyX[scoreEnemy] < birdX) {
				score++;
				if (scoreEnemy < numberOfEnemies -1) {
					scoreEnemy++;
				}else {
					scoreEnemy = 0;
				}
			}

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

				enemyCircle1[i] = new Circle(enemyX[i] + (Gdx.graphics.getWidth()/40),Gdx.graphics.getHeight()/2 + enemyOffSet1[i] + (Gdx.graphics.getHeight()/26), Gdx.graphics.getWidth()/40);
				enemyCircle2[i] = new Circle(enemyX[i] + (Gdx.graphics.getWidth()/40),Gdx.graphics.getHeight()/2 + enemyOffSet2[i] + (Gdx.graphics.getHeight()/26), Gdx.graphics.getWidth()/40);
				enemyCircle3[i] = new Circle(enemyX[i] + (Gdx.graphics.getWidth()/40),Gdx.graphics.getHeight()/2 + enemyOffSet3[i] + (Gdx.graphics.getHeight()/26), Gdx.graphics.getWidth()/40);




			}



			if (birdY > 0) {
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}else {
				gameState = 2;
			}


		}else if (gameState == 0){
			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		} else if (gameState ==2){

			fontGameOver.draw(batch, "Game Over Tap to Play Again" , Gdx.graphics.getWidth() /10, Gdx.graphics.getHeight()/2);


			if (Gdx.input.justTouched()) {
				gameState = 1;
				birdY = Gdx.graphics.getHeight() / 2;

				for (int i = 0; i < numberOfEnemies; i++) {

					enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

					enemyX[i] = Gdx.graphics.getWidth() - (i *  distance);

					enemyCircle1[i] = new Circle();
					enemyCircle2[i] = new Circle();
					enemyCircle3[i] = new Circle();
				}

				velocity = 0;
				scoreEnemy = 0;
				score = 0;

			}

		}

		batch.draw(bird, birdX, birdY , Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/13);

		font.draw(batch, String.valueOf(score), 50, 100);

		batch.end();

		birdCircle.set(birdX + (Gdx.graphics.getWidth()/40),birdY + (Gdx.graphics.getHeight()/26), (Gdx.graphics.getWidth()/20f) / 2f);
		/*

		Aşağıda yoruma alınan kısımlar circlelerın doğru eklenip eklenmediğini kontrol etmek için koyuldu

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);
		*/



		for (int i = 0; i < numberOfEnemies; i++) {
			/*
			shapeRenderer.circle(enemyX[i] + (Gdx.graphics.getWidth()/40),Gdx.graphics.getHeight()/2 + enemyOffSet1[i] + (Gdx.graphics.getHeight()/26), Gdx.graphics.getWidth()/40);
			shapeRenderer.circle(enemyX[i] + (Gdx.graphics.getWidth()/40),Gdx.graphics.getHeight()/2 + enemyOffSet2[i] + (Gdx.graphics.getHeight()/26), Gdx.graphics.getWidth()/40);
			shapeRenderer.circle(enemyX[i] + (Gdx.graphics.getWidth()/40),Gdx.graphics.getHeight()/2 + enemyOffSet3[i] + (Gdx.graphics.getHeight()/26), Gdx.graphics.getWidth()/40);
			*/

			if (Intersector.overlaps(birdCircle, enemyCircle1[i]) || Intersector.overlaps(birdCircle, enemyCircle2[i]) || Intersector.overlaps(birdCircle, enemyCircle3[i])) {

				//Oyun bittiğini gösterecek
				gameState = 2;
			}

		}

		//shapeRenderer.end();

	}

	@Override
	public void dispose () {

	}
}
