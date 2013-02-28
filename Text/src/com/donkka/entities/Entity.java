package com.donkka.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
	public abstract void render(SpriteBatch batch, float delat);
	
	public abstract void onTouch(float x, float y, int TouchEvent);
}
