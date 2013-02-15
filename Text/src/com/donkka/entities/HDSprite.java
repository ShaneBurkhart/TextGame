package com.donkka.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.donkka.art.Art;

public class HDSprite extends Sprite{

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x - super.getWidth() / 2 + this.getWidth(), y - super.getHeight() / 2 + this.getHeight());
	}

	public HDSprite(Sprite sprite){
		super(sprite);
	}
	
	@Override
	public void setScale(float scaleXY) {
		super.setScale(scaleXY * Art.HD_SCALE);
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

	@Override
	public float getX() {
		return super.getX();
	}

	@Override
	public float getY() {
		return super.getY() - super.getHeight() / 2 + this.getHeight();
	}

	@Override
	public float getWidth() {
		return super.getWidth() * Art.HD_SCALE;
	}

	@Override
	public float getHeight() {
		return super.getHeight() * Art.HD_SCALE;
	}

}
