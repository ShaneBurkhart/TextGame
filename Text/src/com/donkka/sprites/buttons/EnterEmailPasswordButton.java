package com.donkka.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.donkka.dialog.LoadingDialog;
import com.donkka.dialog.callback.EmailLoginWithPasswordLoadingCallback;
import com.donkka.dialog.callback.LoadingCallback;
import com.donkka.helpers.GameManager;

public class EnterEmailPasswordButton extends ShaneButton{

	private TextField textField;
	private String email;
	
	public EnterEmailPasswordButton(Vector2 pos, Sprite sprite, TextField textField, String email) {
		super(pos, sprite);
		this.textField = textField;
		this.email = email;
	}

	@Override
	public void onTouchDown(float x, float y) {
	}

	@Override
	public void onTouchDragged(float x, float y) {
	}

	@Override
	public void onTouchUp(float x, float y) {
		String password = textField.getText();
		LoadingCallback callback = new EmailLoginWithPasswordLoadingCallback(email, password);
		GameManager.getInstance().setScreen(new LoadingDialog(GameManager.getInstance().getGame().getScreen(), callback));
	}
	
}
