package com.donkka.connection.http;

import com.donkka.debug.DBug;
import com.donkka.player.UserCredentials;


public class DoesEmailExistHTTPCallback extends ShaneHTTPCallback{
	
	@Override
	public void onSuccessResponse(String result) {
		DBug.print("Succesfully checked for email : " + result);
		if(result.equals("0")){
			UserCredentials.getInstance().setDoesEmailExist(false);
		}else{
			UserCredentials.getInstance().setDoesEmailExist(true);
		}
			
	}
}
