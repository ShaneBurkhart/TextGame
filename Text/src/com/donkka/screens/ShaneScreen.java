package com.donkka.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public abstract class ShaneScreen implements Screen, InputProcessor{
	
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public ShapeRenderer shapes;
	public boolean isTransition = false;
	protected Vector3 touchPos;
	
	public ShaneScreen(){
		touchPos = new Vector3();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		shapes = new ShapeRenderer();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera.viewportWidth = 960;
		camera.viewportHeight = h / w * 960f;
		camera.position.set(240, 400, 0);
		camera.update();
	}

	@Override
	public void render(float delta) {
		camera.update();
		camera.apply(Gdx.gl10);
		batch.setProjectionMatrix(camera.combined);
		shapes.setProjectionMatrix(camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		//Resize to stretch camera to show more but not scale sprites
		float w = width;
		float h = height;
		camera.viewportWidth = 480;
		camera.viewportHeight = h / w * 480f;
		camera.position.set(240, 400, 0);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		batch.dispose();
		shapes.dispose();
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
