package com.donkka.screens;

import com.donkka.art.Art;
import com.donkka.connection.GameData;
import com.donkka.debug.DBug;
import com.donkka.helpers.Dimensions;
import com.donkka.helpers.GameManager;

public class FetchingDataScreen extends ShaneScreen{

	private static final float ROTATE_VEL = 1.5f * 360;
	
	public FetchingDataScreen(){
		GameData.getInstance().callToServer();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		Art.getTile('l').setPosition(Dimensions.getWidth() / 2 - Art.getTile('l').getWidth() / 2, Dimensions.getHeight() / 2 - Art.getTile('l').getHeight() / 2);
		Art.getTile('l').rotate(ROTATE_VEL * delta);
		Art.getTile('l').draw(batch);
		batch.end();
		
		if(GameData.getInstance().isFinished()){
			GameManager.getInstance().setScreen(GameManager.getInstance().getMainMenu());
			DBug.print(GameData.getInstance().getData());
		}
		if(GameData.getInstance().isFailed()){
			GameManager.getInstance().setScreen(GameManager.getInstance().getMainMenu());
			DBug.print("Failed");
		}
	}
}
