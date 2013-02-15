package com.donkka.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.donkka.helpers.GameManager;
import com.donkka.screens.ShaneScreen;

public class FadeTransitionScreen extends ShaneScreen{
	private final float FADE_VELOCITY = 6f;
	private ShaneScreen nextScreen, currentScreen;
	private float alpha = 0;
	private boolean isFading = true;
	
	public FadeTransitionScreen(ShaneScreen current, ShaneScreen next){
		this.nextScreen = next;
		this.currentScreen = current;
		this.isFading = true;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		if(isFading){
			if(currentScreen != null){
				currentScreen.render(0);
				alpha = Math.min(1, alpha + delta * FADE_VELOCITY);
			}else{
				alpha = 1;
			}
		}else{
			if(nextScreen != null){
				nextScreen.render(0);
				alpha = Math.max(0, alpha - delta * FADE_VELOCITY);
			}else{
				alpha = 0;
				isFading = false;
			}
		}
		
		if(alpha == 1){
			isFading = false;	
		}
		
		Gdx.gl.glEnable(GL10.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	    
		shapes.begin(ShapeType.Filled);
		shapes.setColor(new Color(0, 0, 0, alpha));
		shapes.rect(camera.position.x - camera.viewportWidth/2, camera.position.y - camera.viewportHeight/2, camera.viewportWidth, camera.viewportHeight);
		shapes.end();
		
		Gdx.gl.glDisable(GL10.GL_BLEND);
		
		if(!isFading && alpha == 0)
			GameManager.getInstance().setScreen(nextScreen);
	}
}
