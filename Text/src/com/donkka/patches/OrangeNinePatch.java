package com.donkka.patches;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.donkka.art.Art;

public class OrangeNinePatch extends NinePatch{

	private static OrangeNinePatch instance = null;
	
	private OrangeNinePatch(){
		super(Art.orangePatch, 20, 20, 70, 15);
	}
	
	public static OrangeNinePatch getInstance(){
		if(instance == null)
			instance = new OrangeNinePatch();
		return instance;
	}
}
