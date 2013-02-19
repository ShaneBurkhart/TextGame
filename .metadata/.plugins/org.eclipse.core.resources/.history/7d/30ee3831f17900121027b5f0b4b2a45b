package com.donkka.buttons;

import com.badlogic.gdx.math.Vector2;
import com.donkka.entities.HDSprite;
import com.donkka.entities.ShaneButton;
import com.donkka.helpers.GameManager;
import com.donkka.screens.MainMenu;
import com.donkka.transitions.FadeTransitionScreen;

public class PlayButton extends ShaneButton{

	public PlayButton(Vector2 pos, HDSprite sprite) {
		super(pos, sprite);
	}

	@Override
	public void onTouchDown(float x, float y) {
	}

	@Override
	public void onTouchDragged(float x, float y) {
	}

	@Override
	public void onTouchUp(float x, float y) {
		GameManager.getInstance().setScreen(new FadeTransitionScreen(GameManager.getInstance().getGame().getScreen(), new MainMenu()));
	}
	
}
