package com.donkka.sprites;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.donkka.helpers.Dimensions;

public class DoubleFloatingNinePatch {
	protected NinePatch nine;
	protected Vector2 pos;
	protected Vector2 offset;
	protected Vector2 dimensions;
	
	public DoubleFloatingNinePatch(float xPadding, float yOffset, float height, NinePatch nine){
		this.nine = nine;
		this.offset = new Vector2(xPadding, yOffset);
		this.dimensions = new Vector2(Dimensions.getWidth() - xPadding * 2, height);
		pos = new Vector2(Dimensions.getLeft() + offset.x, Dimensions.getTop() - offset.y - dimensions.y);
	}
	
	public void updatePosition(){
		pos.set(Dimensions.getLeft() + offset.x, Dimensions.getTop() - offset.y - dimensions.y);
		dimensions.x = Dimensions.getWidth() - offset.x * 2;
	}
	
	public void render(SpriteBatch batch){
		updatePosition();
		nine.draw(batch, pos.x, pos.y, dimensions.x, dimensions.y);
	}
	
	public Vector2 getPos(){
		return pos;
	}
}
