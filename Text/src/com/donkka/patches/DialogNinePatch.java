package com.donkka.patches;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.donkka.art.Art;

public class DialogNinePatch extends NinePatch{

	private static DialogNinePatch instance = null;
	
	private DialogNinePatch(){
		super(Art.dialogPatch, 10, 10, 180, 10);
	}
	
	public static DialogNinePatch getInstance(){
		if(instance == null)
			instance = new DialogNinePatch();
		return instance;
	}
}
