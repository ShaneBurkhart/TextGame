package com.donkka.entities.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Item {
	static final float ITEM_HEIGHT = 60;
	static final float ITEM_PADDING_VERT = 10;
	public float getHeight();
	public void render(SpriteBatch batch, float x, float y, float width);
	public void onTouch(float x, float y, int TouchEvent, int index);
}
