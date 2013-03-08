package com.donkka.connection.http;

import com.donkka.connection.ServerInterface;
import com.donkka.debug.DBug;
import com.donkka.player.UserCredentials;


public class CreateUserWithEmailHTTPCallback extends ShaneHTTPCallback{

	@Override
	public void onSuccessResponse(String result) {
		if(result.equals("0")){
			ServerInterface.getInstance().setIsFailed(true);
			return;
		}
		DBug.print("Created User With Email : " + result);
		UserCredentials.getInstance().setPassword(result);
	}
}
