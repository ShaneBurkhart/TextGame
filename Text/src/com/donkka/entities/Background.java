package com.donkka.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.donkka.art.Art;

public class Background implements Screen{
	
	SpriteBatch batch;
	OrthographicCamera camera;
	
	public Background(){
		camera = new OrthographicCamera(480, 800);
		camera.position.set(240, 400, 0);
		camera.update();
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		camera.update();
		camera.apply(Gdx.gl10);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		Art.background.setPosition(0, 400 - 500 + 3);
		Art.background.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		float w = width;
		float h = height;
		camera.viewportWidth = 480;
		camera.viewportHeight = h / w * 480f;
		camera.position.set(240, 400, 0);
	}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}
}
