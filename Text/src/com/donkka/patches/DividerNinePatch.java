package com.donkka.patches;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.donkka.art.Art;

public class DividerNinePatch extends NinePatch{

	private static DividerNinePatch instance = null;
	
	private DividerNinePatch(){
		super(Art.dividerPatch, 1, 0, 1, 1);
	}
	
	public static DividerNinePatch getInstance(){
		if(instance == null)
			instance = new DividerNinePatch();
		return instance;
	}
}
