package com.donkka.dialog.callback;

import com.donkka.art.Art;
import com.donkka.connection.ServerInterface;
import com.donkka.dialog.LoadingDialog;
import com.donkka.dialog.ShaneDialog;
import com.donkka.dialog.buttons.OkButton;
import com.donkka.helpers.GameManager;
import com.donkka.player.UserCredentials;
import com.donkka.screens.EnterEmailPasswordScreen;
import com.donkka.screens.ShaneScreen;

public class EmailLoginWithPasswordLoadingCallback implements LoadingCallback{
	
	private String email, password;
	
	public EmailLoginWithPasswordLoadingCallback(String email, String password){
		this.email = email;
		this.password = password;
	}

	@Override
	public boolean isDoneLoading() {
		return ServerInterface.getInstance().isFinished() || ServerInterface.getInstance().isFailed();
	}

	@Override
	public void onPreLoad() {
		ServerInterface.getInstance().loginWithEmailAndPassword(email, password);
	}

	@Override
	public void onPostLoad(ShaneScreen under) {
		if(UserCredentials.getInstance().getUserID() != 0){
			UserCredentials.getInstance().setPassword(password);
			UserCredentials.getInstance().setIsFacebook(false);
			GameManager.getInstance().setScreen(new LoadingDialog(under, new FetchGameDataCallback()));
		}else{
			EnterEmailPasswordScreen screen = new EnterEmailPasswordScreen(email);
			ShaneDialog dialog = new ShaneDialog(screen, "Sorry!", "The password doesnt match the email entered.", 400, 400);
			dialog.addButton(new OkButton(Art.letsgo, screen));
			GameManager.getInstance().setScreen(dialog);
		}
	}

	@Override
	public void onFailedLoad(ShaneScreen under) {
		GameManager.getInstance().setScreen(ShaneDialog.getServerErrorDialog(under));
	}

}
