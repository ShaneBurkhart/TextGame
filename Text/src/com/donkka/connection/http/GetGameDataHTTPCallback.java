package com.donkka.connection.http;

import com.donkka.connection.GameData;
import com.donkka.debug.DBug;


public class GetGameDataHTTPCallback extends ShaneHTTPCallback{

	@Override
	public void onSuccessResponse(String result) {
		GameData.getInstance().setData(result);
		DBug.print("Game Data : " + result);
	}
}
