package com.donkka.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.sprites.buttons.RecallButton;
import com.donkka.sprites.buttons.ShuffleButton;
import com.donkka.sprites.buttons.SubmitButton;

public class ButtonPanel implements Entity{
	
	private static final float BUTTON_OVERHANG = 22;
	private static final float BUTTON_BOTTOM = 20;
	
	TileInterface tileInterface;
	SubmitButton submit;
	RecallButton recall;
	ShuffleButton shuffle;
	
	public ButtonPanel(TileInterface tileInterface){
		this.tileInterface = tileInterface;
		this.shuffle = new ShuffleButton(new Vector2(tileInterface.getLeft() - BUTTON_OVERHANG, BUTTON_BOTTOM), Art.shuffle, this);
		this.recall = new RecallButton(new Vector2(tileInterface.getLeft() + tileInterface.getWidth() / 2  - Art.recall.getWidth() / 2, BUTTON_BOTTOM), Art.recall, this);
		this.submit = new SubmitButton(new Vector2(tileInterface.getLeft() + tileInterface.getWidth() - Art.submit.getWidth() + BUTTON_OVERHANG, BUTTON_BOTTOM), Art.submit, this);
	}

	@Override
	public void render(SpriteBatch batch, float delat) {
		this.shuffle.render(batch);
		this.recall.render(batch);
		this.submit.render(batch);
	}

	@Override
	public void onTouch(float x, float y, int TouchEvent) {
		this.shuffle.onTouch(x, y, TouchEvent);
		this.recall.onTouch(x, y, TouchEvent);
		this.submit.onTouch(x, y, TouchEvent);
	}
	
	public TileInterface getTileInterface(){
		return tileInterface;
	}
}
