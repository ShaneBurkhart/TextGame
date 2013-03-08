package com.donkka.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.donkka.art.Art;
import com.donkka.patches.CursorNinePatch;
import com.donkka.patches.SelectionNinePatch;
import com.donkka.patches.TextFieldNinePatch;

public class ShaneTextFieldStyle extends TextFieldStyle {
	
	Color color;
	
	public ShaneTextFieldStyle(){
		color = Color.BLACK;
		font = Art.calibri22;
		fontColor = color;
		cursor = new NinePatchDrawable(CursorNinePatch.getInstance());
		background = new NinePatchDrawable(TextFieldNinePatch.getInstance());
		selection = new NinePatchDrawable(SelectionNinePatch.getInstance());
	}
}
