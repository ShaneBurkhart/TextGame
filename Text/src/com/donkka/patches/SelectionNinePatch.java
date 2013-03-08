package com.donkka.patches;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.donkka.art.Art;

public class SelectionNinePatch extends NinePatch{

	private static SelectionNinePatch instance = null;
	
	private SelectionNinePatch(){
		super(Art.selectionPatch, 1, 1, 1, 1);
	}
	
	public static SelectionNinePatch getInstance(){
		if(instance == null)
			instance = new SelectionNinePatch();
		return instance;
	}
}
