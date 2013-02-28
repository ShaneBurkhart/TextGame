package com.donkka.connection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;


public class GameData {
	
	private static GameData instance;
	private boolean failed = false;
	private boolean isFinished = false;
	
	public static GameData getInstance(){
		if(instance == null)
			instance = new GameData();
		return instance;
	}
	
	private GameData(){
	}
	
	public void callToServer(){
		isFinished = false;
		failed = false;
		HttpRequest request = new Net.HttpRequest(HttpMethods.GET);
		request.setUrl("https://www.google.com/");
		Gdx.net.sendHttpRequest(request, new ResponseListener());
	}
	
	public boolean isFailed(){
		return failed;
	}
	
	public boolean isFinished(){
		return isFinished;
	}
	
	private String data = "";
	
	public String getData(){
		return data;
	}
	
	private class ResponseListener implements Net.HttpResponseListener{

		@Override
		public void handleHttpResponse(HttpResponse httpResponse) {
			data = httpResponse.getResultAsString();
			isFinished = true;
		}

		@Override
		public void failed(Throwable t) {
			failed = true;
		}
	}
}
