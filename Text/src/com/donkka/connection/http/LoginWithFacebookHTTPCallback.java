package com.donkka.connection.http;

import com.donkka.debug.DBug;
import com.donkka.player.UserCredentials;


public class LoginWithFacebookHTTPCallback extends ShaneHTTPCallback{

	@Override
	public void onSuccessResponse(String result) {
		int userID = Integer.parseInt(result);
		DBug.print("Logged In With Facebook : " + userID);
		if(userID > 0){
			UserCredentials.getInstance().setIsLoggedIn(true);
			UserCredentials.getInstance().setUserID(userID);
		}else
			UserCredentials.getInstance().setIsLoggedIn(false);
	}
}
