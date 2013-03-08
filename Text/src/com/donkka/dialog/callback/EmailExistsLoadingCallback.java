package com.donkka.dialog.callback;

import com.donkka.connection.ServerInterface;
import com.donkka.dialog.ShaneDialog;
import com.donkka.helpers.GameManager;
import com.donkka.player.UserCredentials;
import com.donkka.screens.EnterEmailPasswordScreen;
import com.donkka.screens.EnterUsernameScreen;
import com.donkka.screens.ShaneScreen;
import com.donkka.transitions.ExitLeftTransitionScreen;

public class EmailExistsLoadingCallback implements LoadingCallback{
	
	private String email;
	
	public EmailExistsLoadingCallback(String email){
		this.email = email;
	}

	@Override
	public boolean isDoneLoading() {
		return ServerInterface.getInstance().isFinished() || ServerInterface.getInstance().isFailed();
	}

	@Override
	public void onPreLoad() {
		ServerInterface.getInstance().doesEmailExist(email);
	}

	@Override
	public void onPostLoad(ShaneScreen under) {
		UserCredentials.getInstance().setEmail(email);
		if(UserCredentials.getInstance().doesEmailExist()){
			GameManager.getInstance().setScreen(new ExitLeftTransitionScreen(under, new EnterEmailPasswordScreen(email)));
		}else{
			GameManager.getInstance().setScreen(new ExitLeftTransitionScreen(under, new EnterUsernameScreen(email)));
		}
	}

	@Override
	public void onFailedLoad(ShaneScreen under) {
		GameManager.getInstance().setScreen(ShaneDialog.getServerErrorDialog(under));
	}

}
