package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.donkka.dialog.LoadingDialog;
import com.donkka.dialog.callback.EmailExistsLoadingCallback;
import com.donkka.dialog.callback.LoadingCallback;
import com.donkka.helpers.GameManager;

public class EnterEmailButton extends ShaneButton{

	private TextField textField;
	
	public EnterEmailButton(Vector2 pos, Sprite sprite, TextField textField) {
		super(pos, sprite);
		this.textField = textField;
	}

	@Override
	public void onTouchDown(float x, float y) {
	}

	@Override
	public void onTouchDragged(float x, float y) {
	}

	@Override
	public void onTouchUp(float x, float y) {
		String email = textField.getText();
		LoadingCallback callback = new EmailExistsLoadingCallback(email);
		GameManager.getInstance().setScreen(new LoadingDialog(GameManager.getInstance().getGame().getScreen(), callback));
	}
	
}
