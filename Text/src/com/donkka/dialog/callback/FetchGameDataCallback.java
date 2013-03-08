package com.donkka.dialog.callback;

import com.donkka.connection.ServerInterface;
import com.donkka.dialog.ShaneDialog;
import com.donkka.helpers.GameManager;
import com.donkka.player.UserCredentials;
import com.donkka.screens.MainMenu;
import com.donkka.screens.ShaneScreen;
import com.donkka.transitions.ExitLeftTransitionScreen;

public class FetchGameDataCallback implements LoadingCallback{

	@Override
	public boolean isDoneLoading() {
		return ServerInterface.getInstance().isFinished() || ServerInterface.getInstance().isFailed();
	}

	@Override
	public void onPreLoad() {
		ServerInterface.getInstance().getGameData(UserCredentials.getInstance().getUserID());
	}

	@Override
	public void onPostLoad(ShaneScreen under) {
		GameManager.getInstance().setScreen(new ExitLeftTransitionScreen(under, new MainMenu()));
	}

	@Override
	public void onFailedLoad(ShaneScreen under) {
		GameManager.getInstance().setScreen(ShaneDialog.getServerErrorDialog(under));
	}

}
