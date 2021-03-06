package com.donkka.sprites;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.donkka.helpers.Dimensions;

public class FloatingNinePatch {
	protected NinePatch nine;
	protected Vector2 pos;
	protected float scale = 1;
	protected int dimensionFloat = Dimensions.Floats.LEFT;
	protected Vector2 offset;
	protected Vector2 dimensions;
	
	public FloatingNinePatch(float xOffset, float yOffset, float width, float height, int dimensionFloat, NinePatch nine){
		this.nine = nine;
		this.dimensionFloat = dimensionFloat;
		this.offset = new Vector2(xOffset, yOffset);
		this.dimensions = new Vector2(width, height);
		if(dimensionFloat == Dimensions.Floats.LEFT)
			pos = new Vector2(Dimensions.getLeft() + offset.x, Dimensions.getTop() - offset.y - dimensions.y);
		else
			pos = new Vector2(Dimensions.getRight() - dimensions.x, Dimensions.getTop() - offset.y - dimensions.y);
	}
	
	public void updatePosition(){
		if(dimensionFloat == Dimensions.Floats.LEFT)
			pos.set(Dimensions.getLeft() + offset.x, Dimensions.getTop() - offset.y - dimensions.y);
		else
			pos.set(Dimensions.getRight() - offset.x - dimensions.x, Dimensions.getTop() - offset.y - dimensions.y);
	}
	
	public void render(SpriteBatch batch){
		updatePosition();
		nine.draw(batch, pos.x, pos.y, dimensions.x, dimensions.y);
	}
}
