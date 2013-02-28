package com.donkka.entities.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.donkka.patches.DividerNinePatch;

public abstract class MenuItem implements Item{

	@Override
	public float getHeight() {
		return ITEM_PADDING_VERT + ITEM_HEIGHT + ITEM_PADDING_VERT;
	}

	@Override
	public void render(SpriteBatch batch, float x, float y, float width) {
		DividerNinePatch.getInstance().draw(batch, x, y + getHeight() - 1, width, 1);
	}
}
