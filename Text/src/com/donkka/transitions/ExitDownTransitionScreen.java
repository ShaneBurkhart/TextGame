package com.donkka.transitions;

import com.donkka.helpers.GameManager;
import com.donkka.screens.ShaneScreen;

public class ExitDownTransitionScreen extends ShaneScreen{
	private final float DURATION = .4f;
	private float slideVelocity = 0;
	private ShaneScreen nextScreen, currentScreen;
	private float finalY;
	
	public ExitDownTransitionScreen(ShaneScreen current, ShaneScreen next){
		this.nextScreen = next;
		this.currentScreen = current;
		this.finalY = 240;
		this.nextScreen.camera.position.y = this.nextScreen.camera.position.y - this.nextScreen.camera.viewportHeight;
		this.slideVelocity = this.camera.viewportHeight / DURATION;
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		if(currentScreen != null){
			currentScreen.camera.position.y = Math.min(finalY + 480, currentScreen.camera.position.y + delta * slideVelocity);
			currentScreen.render(0);
		}
		
		if(nextScreen != null){
			nextScreen.camera.position.y = Math.min(finalY, nextScreen.camera.position.y + delta * slideVelocity);
			nextScreen.render(0);
		}
		
		if(nextScreen.camera.position.y == finalY)
			GameManager.getInstance().setScreen(nextScreen);
	}
}
