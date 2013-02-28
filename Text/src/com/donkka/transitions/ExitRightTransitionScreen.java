package com.donkka.transitions;

import com.donkka.helpers.Dimensions;
import com.donkka.helpers.GameManager;
import com.donkka.screens.ShaneScreen;

public class ExitRightTransitionScreen extends ShaneScreen{
	private float slideVelocity = Dimensions.getWidth() / TransitionSettings.DURATION;
	private ShaneScreen nextScreen, currentScreen;
	private float finalX;
	
	public ExitRightTransitionScreen(ShaneScreen current, ShaneScreen next){
		this.nextScreen = next;
		this.currentScreen = current;
		this.finalX = camera.position.x;
		this.nextScreen.setCameraPosX(this.nextScreen.camera.position.x + this.nextScreen.camera.viewportWidth);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		if(currentScreen != null){
			currentScreen.setCameraPosX(Math.max(finalX - currentScreen.camera.viewportWidth, currentScreen.camera.position.x - delta * slideVelocity));
			currentScreen.render(0);
		}
		
		if(nextScreen != null){
			nextScreen.setCameraPosX(Math.max(finalX, nextScreen.camera.position.x - delta * slideVelocity));
			nextScreen.render(0);
		}
		
		if(nextScreen.camera.position.x == finalX)
			GameManager.getInstance().setScreen(nextScreen);
	}
}
