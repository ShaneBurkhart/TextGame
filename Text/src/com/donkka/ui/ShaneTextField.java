package com.donkka.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class ShaneTextField extends TextField{

	public ShaneTextField(String text, TextFieldStyle style, Vector2 pos, float width) {
		super("", style);
		setMessageText(text);
		setPosition(pos.x, pos.y);
		setSize(width, getHeight());
	}

}
