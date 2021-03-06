package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.donkka.text.TouchEvent;

public abstract class ShaneButton {
	public static final float TOUCH_SCALE = 1.1f;
	protected Sprite sprite;
	protected Vector2 pos;
	protected float scale = 1;
	
	public ShaneButton(Vector2 pos, Sprite sprite){
		this.sprite = sprite;
		this.pos = pos;
	}
	
	public float getWidth(){
		return sprite.getWidth();
	}
	
	public float getHeight(){
		return sprite.getHeight();
	}
	
	public void setPosition(Vector2 pos){
		this.pos = pos;
	}
	
	public void onTouch(float x, float y, int touchEvent){
		float left = pos.x + sprite.getWidth() / 2 * (1 - scale);
		float bottom = pos.y + sprite.getHeight() / 2 * (1 - scale);
		float width = sprite.getWidth() * scale;
		float height = sprite.getHeight() * scale;
		
		if(x > left && x < left + width && y > bottom && y < bottom + height){
			if(touchEvent == TouchEvent.TOUCH_DOWN){
				onTouchDown(x, y);
				scale = TOUCH_SCALE;
				return;
			}else if(touchEvent == TouchEvent.TOUCH_DRAGGED){
				onTouchDragged(x, y);
				scale = TOUCH_SCALE;
				return;
			}else if(touchEvent == TouchEvent.TOUCH_UP){
				onTouchUp(x, y);
				scale = 1;
				return;
			}
		}
		scale = 1;
	}
	
	public abstract void onTouchDown(float x, float y);
	public abstract void onTouchDragged(float x, float y);
	public abstract void onTouchUp(float x, float y);
	
	public void render(SpriteBatch batch){
		sprite.setPosition(pos.x, pos.y);
		sprite.setScale(scale);
		sprite.draw(batch);
	}
}
