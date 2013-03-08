package com.donkka.connection.http;

import com.donkka.debug.DBug;
import com.donkka.player.UserCredentials;


public class DoesUsernameExistHTTPCallback extends ShaneHTTPCallback{
	
	@Override
	public void onSuccessResponse(String result) {
		DBug.print("Succesfully checked for username : " + result);
		if(result.equals("0")){
			UserCredentials.getInstance().setDoesUsernameExist(false);
		}else{
			UserCredentials.getInstance().setDoesUsernameExist(true);
		}
			
	}
}
