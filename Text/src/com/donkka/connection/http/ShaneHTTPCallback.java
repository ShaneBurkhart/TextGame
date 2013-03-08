package com.donkka.connection.http;

import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.donkka.connection.ServerInterface;

public abstract class ShaneHTTPCallback implements HttpResponseListener{
	private static final String ERROR_CODE = "-1";
	private String result = "";
	
	public ShaneHTTPCallback(){
		ServerInterface.getInstance().reset();
	}
	
	@Override
	public void handleHttpResponse(HttpResponse httpResponse) {
		result = httpResponse.getResultAsString(); 
		if(result.equals(ERROR_CODE)){
			ServerInterface.getInstance().setIsFailed(true);
			return;
		}
		onSuccessResponse(result);
		ServerInterface.getInstance().setIsFinished(true);
	}

	@Override
	public void failed(Throwable t) {
		ServerInterface.getInstance().setIsFailed(true);
	}
	
	public abstract void onSuccessResponse(String result);
}
