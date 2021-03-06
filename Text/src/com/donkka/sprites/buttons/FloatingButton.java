package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.donkka.helpers.Dimensions;
import com.donkka.sprites.FloatingSprite;
import com.donkka.text.TouchEvent;

public abstract class FloatingButton extends FloatingSprite {
	
	public FloatingButton(float xOffset, float yOffset, int dimensionFloat, Sprite sprite){
		super(xOffset, yOffset, dimensionFloat, sprite);
	}
	
	public FloatingButton(Sprite sprite){
		super(0, 0, Dimensions.Floats.LEFT, sprite);
	}
	
	public FloatingButton(float xOffset, Sprite sprite){
		super(xOffset, 0, Dimensions.Floats.LEFT, sprite);
	}
	
	public FloatingButton(float xOffset, int dimensionFloat, Sprite sprite){
		super(xOffset, 0, dimensionFloat, sprite);
	}

	public static final float TOUCH_SCALE = 1.2f;
	
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
}
