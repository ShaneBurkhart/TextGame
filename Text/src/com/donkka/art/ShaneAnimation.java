package com.donkka.art;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ShaneAnimation {

	private Animation animation;
	private float time = 0;
	private Vector2 pos;
	
	public ShaneAnimation(Animation animation){
		this.animation = animation;
		this.pos = new Vector2(0, 0);
	}
	
	public void setPosition(float x, float y){
		this.pos.set(x, y);
	}
	
	public void render(SpriteBatch batch, float delta){
		time += delta;
		batch.draw(this.animation.getKeyFrame(time), pos.x, pos.y);
	}
}
