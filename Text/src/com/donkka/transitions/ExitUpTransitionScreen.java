package com.donkka.transitions;

import com.donkka.helpers.Dimensions;
import com.donkka.helpers.GameManager;
import com.donkka.screens.ShaneScreen;

public class ExitUpTransitionScreen extends ShaneScreen{
	private float slideVelocity = Dimensions.getHeight() / TransitionSettings.DURATION;
	private ShaneScreen nextScreen, currentScreen;
	private float finalY;
	
	public ExitUpTransitionScreen(ShaneScreen current, ShaneScreen next){
		this.nextScreen = next;
		this.currentScreen = current;
		this.finalY = Dimensions.getTargetHeight() / 2;
		this.nextScreen.setCameraPosY(this.nextScreen.camera.position.y + this.nextScreen.camera.viewportHeight);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		if(currentScreen != null){
			currentScreen.setCameraPosY(Math.max(finalY - Dimensions.getTargetHeight(), currentScreen.camera.position.y - delta * slideVelocity));
			currentScreen.render(0);
		}
		
		if(nextScreen != null){
			nextScreen.setCameraPosY(Math.max(finalY, nextScreen.camera.position.y - delta * slideVelocity));
			nextScreen.render(0);
		}
		
		if(nextScreen.camera.position.y == finalY)
			GameManager.getInstance().setScreen(nextScreen);
	}
}
