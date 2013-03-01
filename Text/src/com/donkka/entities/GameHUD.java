package com.donkka.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.donkka.art.Art;
import com.donkka.helpers.Dimensions;
import com.donkka.helpers.Score;
import com.donkka.helpers.Timer;

public class GameHUD{
	
	private static final float SCREEN_MARGIN_X = 10;
	private float marginTop = 0;
	
	public GameHUD(float marginTop){
		this.marginTop = marginTop;
	}
	
	public void render(SpriteBatch batch){
		String time;
		if(Timer.getInstance().isStarted()){
			int seconds = (int) Timer.getInstance().getTimeRemaining();
			int mins = seconds / 60;
			seconds = seconds % 60;
			time = mins + ":";
			time += seconds > 9 ? seconds : "0" + seconds;
		}else{
			time = "1:00";
		}
		Art.calibri22.draw(batch, time, Dimensions.getLeft() + SCREEN_MARGIN_X, Dimensions.getHeight() - marginTop);
		Art.calibri22.drawMultiLine(batch, Score.getInstance().getScore() + "", Dimensions.getLeft(), Dimensions.getHeight() - marginTop, Dimensions.getWidth(), HAlignment.CENTER);
	}
}
