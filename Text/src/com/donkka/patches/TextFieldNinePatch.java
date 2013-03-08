package com.donkka.patches;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.donkka.art.Art;

public class TextFieldNinePatch extends NinePatch{

	private static TextFieldNinePatch instance = null;
	
	private TextFieldNinePatch(){
		super(Art.textFieldPatch, 10, 10, 10, 10);
	}
	
	public static TextFieldNinePatch getInstance(){
		if(instance == null)
			instance = new TextFieldNinePatch();
		return instance;
	}
}
