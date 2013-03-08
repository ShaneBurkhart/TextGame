package com.donkka.dialog.callback;

import com.donkka.art.Art;
import com.donkka.connection.ServerInterface;
import com.donkka.dialog.LoadingDialog;
import com.donkka.dialog.ShaneDialog;
import com.donkka.dialog.buttons.OkButton;
import com.donkka.helpers.GameManager;
import com.donkka.player.UserCredentials;
import com.donkka.screens.ShaneScreen;

public class UsernameExistsLoadingCallback implements LoadingCallback{
	
	private String username;
	
	public UsernameExistsLoadingCallback(String username){
		this.username = username;
	}

	@Override
	public boolean isDoneLoading() {
		return ServerInterface.getInstance().isFinished() || ServerInterface.getInstance().isFailed();
	}

	@Override
	public void onPreLoad() {
		ServerInterface.getInstance().doesUsernameExist(username);
	}

	@Override
	public void onPostLoad(ShaneScreen under) {
		if(UserCredentials.getInstance().doesUsernameExist()){
			ShaneDialog dialog = new ShaneDialog(under, "Username Exists!", "The username you have entered already exists.  Please enter a new one.", 400, 400);
			dialog.addButton(new OkButton(Art.letsgo, under));
			GameManager.getInstance().setScreen(dialog);
		}else{
			if(UserCredentials.getInstance().isFacebook())
				;
			else
				GameManager.getInstance().setScreen(new LoadingDialog(under, new CreateUserWithEmailLoadingCallback(UserCredentials.getInstance().getEmail(), username)));
		}
	}

	@Override
	public void onFailedLoad(ShaneScreen under) {
		GameManager.getInstance().setScreen(ShaneDialog.getServerErrorDialog(under));
	}

}
