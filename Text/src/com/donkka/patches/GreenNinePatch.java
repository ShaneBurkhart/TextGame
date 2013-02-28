package com.donkka.patches;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.donkka.art.Art;

public class GreenNinePatch extends NinePatch{

	private static GreenNinePatch instance = null;
	
	private GreenNinePatch(){
		super(Art.greenPatch, 20, 20, 70, 15);
	}
	
	public static GreenNinePatch getInstance(){
		if(instance == null)
			instance = new GreenNinePatch();
		return instance;
	}
}
