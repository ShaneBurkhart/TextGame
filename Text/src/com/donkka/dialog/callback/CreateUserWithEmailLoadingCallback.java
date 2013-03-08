package com.donkka.dialog.callback;

import com.donkka.connection.ServerInterface;
import com.donkka.dialog.LoadingDialog;
import com.donkka.dialog.ShaneDialog;
import com.donkka.helpers.GameManager;
import com.donkka.player.UserCredentials;
import com.donkka.screens.ShaneScreen;

public class CreateUserWithEmailLoadingCallback implements LoadingCallback{
	
	private String email, username;
	
	public CreateUserWithEmailLoadingCallback(String email, String username){
		this.email = email;
		this.username = username;
	}

	@Override
	public boolean isDoneLoading() {
		return ServerInterface.getInstance().isFinished() || ServerInterface.getInstance().isFailed();
	}

	@Override
	public void onPreLoad() {
		ServerInterface.getInstance().createUserWithEmail(email, username);
	}

	@Override
	public void onPostLoad(ShaneScreen under) {
		if(UserCredentials.getInstance().getPassword() != ""){
			UserCredentials.getInstance().setUsername(username);
			UserCredentials.getInstance().setEmail(email);
			GameManager.getInstance().setScreen(new LoadingDialog(under, new FetchGameDataCallback()));
		}
	}

	@Override
	public void onFailedLoad(ShaneScreen under) {
		GameManager.getInstance().setScreen(ShaneDialog.getServerErrorDialog(under));
	}

}
