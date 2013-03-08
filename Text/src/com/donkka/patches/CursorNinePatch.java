package com.donkka.patches;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.donkka.art.Art;

public class CursorNinePatch extends NinePatch{

	private static CursorNinePatch instance = null;
	
	private CursorNinePatch(){
		super(Art.cursorPatch, 1, 1, 1, 1);
	}
	
	public static CursorNinePatch getInstance(){
		if(instance == null)
			instance = new CursorNinePatch();
		return instance;
	}
}
