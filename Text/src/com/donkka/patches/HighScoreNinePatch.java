package com.donkka.patches;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.donkka.art.Art;

public class HighScoreNinePatch extends NinePatch{

	private static HighScoreNinePatch instance = null;
	
	private HighScoreNinePatch(){
		super(Art.highScorePatch, 20, 20, 20, 20);
	}
	
	public static HighScoreNinePatch getInstance(){
		if(instance == null)
			instance = new HighScoreNinePatch();
		return instance;
	}
}
