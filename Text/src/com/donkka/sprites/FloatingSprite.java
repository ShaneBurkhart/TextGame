package com.donkka.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.donkka.helpers.Dimensions;

public class FloatingSprite {
	protected Sprite sprite;
	protected Vector2 pos;
	protected float scale = 1;
	protected int dimensionFloat = Dimensions.Floats.LEFT;
	protected Vector2 offset;
	
	public FloatingSprite(float xOffset, float yOffset, int dimensionFloat, Sprite sprite){
		this.sprite = sprite;
		this.dimensionFloat = dimensionFloat;
		this.offset = new Vector2(xOffset, yOffset);
		if(dimensionFloat == Dimensions.Floats.LEFT)
			pos = new Vector2(Dimensions.getLeft() + offset.x, Dimensions.getTop() - offset.y - sprite.getHeight());
		else
			pos = new Vector2(Dimensions.getRight() - offset.x - sprite.getWidth(), Dimensions.getTop() - offset.y - sprite.getHeight());
	}
	
	public void updatePosition(){
		if(dimensionFloat == Dimensions.Floats.LEFT)
			pos.set(Dimensions.getLeft() + offset.x, Dimensions.getTop() - offset.y - sprite.getHeight());
		else
			pos.set(Dimensions.getRight() - offset.x - sprite.getWidth(), Dimensions.getTop() - offset.y - sprite.getHeight());
	}
	
	public void render(SpriteBatch batch){
		updatePosition();
		sprite.setPosition(pos.x, pos.y);
		sprite.setScale(scale);
		sprite.draw(batch);
	}
}
