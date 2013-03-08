package com.donkka.connection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.donkka.connection.http.CreateUserWithEmailHTTPCallback;
import com.donkka.connection.http.DoesEmailExistHTTPCallback;
import com.donkka.connection.http.DoesUsernameExistHTTPCallback;
import com.donkka.connection.http.GetGameDataHTTPCallback;
import com.donkka.connection.http.LoginWithEmailAndPasswordHTTPCallback;
import com.donkka.connection.http.LoginWithFacebookHTTPCallback;

public class ServerInterface {

	private static ServerInterface instance;
	private boolean isFailed = false;
	private boolean isFinished = false;
	
	public static ServerInterface getInstance(){
		if(instance == null)
			instance = new ServerInterface();
		return instance;
	}
	
	private ServerInterface(){}
	
	public void loginWithFaceBook(String email, String username){
		String url = ServerURLs.getLoginWithFacebookURL(email, username);
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, new LoginWithFacebookHTTPCallback());
	}
	
	public void loginWithEmailAndPassword(String email, String password){
		String url = ServerURLs.getLoginWithEmailAndPasswordURL(email, password);
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, new LoginWithEmailAndPasswordHTTPCallback());
	}
	
	public void createUserWithEmail(String email, String username){
		reset();
		String url = ServerURLs.getCreateUserWithEmailURL(email, username);
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, new CreateUserWithEmailHTTPCallback());
	}
	
	public void doesEmailExist(String email){
		String url = ServerURLs.getDoesEmailExistURL(email);
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, new DoesEmailExistHTTPCallback());
	}
	
	public void doesUsernameExist(String username){
		String url = ServerURLs.getDoesUsernameExistURL(username);
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, new DoesUsernameExistHTTPCallback());
	}
	
	public void getGameData(int userID){
		String url = ServerURLs.getGameDataURL(userID);
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, new GetGameDataHTTPCallback());
	}
	
	public void submitScore(){
		
	}
	
	public void logout(){
		
	}
	
	public void reset(){
		isFinished = false;
		isFailed = false;
	}
	
	public boolean isFailed(){
		return isFailed;
	}
	
	public boolean isFinished(){
		return isFinished;
	}
	
	public void setIsFinished(boolean bool){
		this.isFinished = bool;
	}
	
	public void setIsFailed(boolean bool){
		this.isFailed = bool;
	}
}
