package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.donkka.dialog.LoadingDialog;
import com.donkka.dialog.callback.UsernameExistsLoadingCallback;
import com.donkka.helpers.GameManager;
import com.donkka.screens.ShaneScreen;

public class EnterUsernameButton extends ShaneButton{

	private TextField textField;
	private ShaneScreen under;
	
	public EnterUsernameButton(Vector2 pos, Sprite sprite, TextField textField, ShaneScreen under) {
		super(pos, sprite);
		this.textField = textField;
		this.under = under;
	}

	@Override
	public void onTouchDown(float x, float y) {
	}

	@Override
	public void onTouchDragged(float x, float y) {
	}

	@Override
	public void onTouchUp(float x, float y) {
		String username = textField.getText();
		GameManager.getInstance().setScreen(new LoadingDialog(under, new UsernameExistsLoadingCallback(username)));
	}
	
}
