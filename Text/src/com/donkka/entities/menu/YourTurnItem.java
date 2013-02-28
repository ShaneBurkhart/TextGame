package com.donkka.entities.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.donkka.entities.GameInfo;

public class YourTurnItem extends MenuItem {
	
	private GameInfo info;
	
	public YourTurnItem(GameInfo info){
		this.info = info;
	}

	@Override
	public void render(SpriteBatch batch, float x, float y, float width) {
		super.render(batch, x, y, width);
		
	}

	@Override
	public void onTouch(float x, float y, int TouchEvent, int index) {
		
	}
}
