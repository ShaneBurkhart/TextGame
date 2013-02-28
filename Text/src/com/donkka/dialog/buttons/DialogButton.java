package com.donkka.dialog.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.donkka.sprites.buttons.ShaneButton;

public abstract class DialogButton extends ShaneButton{

	public DialogButton(Sprite sprite) {
		super(new Vector2(), sprite);
	}
}
