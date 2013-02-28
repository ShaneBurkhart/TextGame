package com.donkka.screens;

import com.donkka.art.Art;
import com.donkka.constants.StringConstants;
import com.donkka.dialog.ShaneDialog;
import com.donkka.dialog.buttons.PreGameDialogButton;
import com.donkka.entities.ButtonPanel;
import com.donkka.entities.RecentlyPlayed;
import com.donkka.entities.TileInterface;
import com.donkka.helpers.GameManager;
import com.donkka.helpers.Timer;
import com.donkka.text.TouchEvent;

public class GameScreen extends ShaneScreen{
	
	RecentlyPlayed recentlyPlayed;
	TileInterface tileInterface;
	ButtonPanel buttonPanel;
	private boolean isReady = false;
	
	public GameScreen(){
		recentlyPlayed = new RecentlyPlayed(75);
		tileInterface = new TileInterface(recentlyPlayed);
		buttonPanel = new ButtonPanel(tileInterface);
	}
	
	@Override
	public void show() {
		super.show();
		if(!isReady){
			GameManager.getInstance().setScreen(new ShaneDialog(this, StringConstants.PREGAME_TITLE, StringConstants.PREGAME_MESSAGE, 400, 300)
				.addButton(new PreGameDialogButton(Art.letsgo, this)));
			isReady = true;
		}else{
			Timer.getInstance().start();
		}
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		recentlyPlayed.render(batch);
		tileInterface.render(batch, delta);
		buttonPanel.render(batch, delta);
		batch.end();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		tileInterface.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		buttonPanel.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		tileInterface.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		buttonPanel.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		tileInterface.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		buttonPanel.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		return false;
	}
}
