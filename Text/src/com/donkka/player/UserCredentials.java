package com.donkka.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.donkka.debug.DBug;

public class UserCredentials {

	private static final String PREFS_NAME = "userCredentials";
	private static final String EMAIL_TAG = "email";
	private static final String PASSWORD_TAG = "password";
	private static final String FACEBOOK_TAG = "facebook";
	
	private static UserCredentials instance;
	
	private String password, email, username;
	private boolean isFacebook;
	private int userID;
	private boolean isLoggedIn = false;
	private boolean doesEmailExist = false;
	private boolean doesUsernameExist = false;

	private UserCredentials(){
		loadCredentials();
	}
	
	public static UserCredentials getInstance(){
		if(instance == null)
			instance = new UserCredentials();
		return instance;
	}
	
	public void setDoesUsernameExist(boolean exists){
		this.doesUsernameExist = exists;
	}
	
	public boolean doesUsernameExist(){
		return doesUsernameExist;
	}
	
	public void setDoesEmailExist(boolean exists){
		this.doesEmailExist = exists;
	}
	
	public boolean doesEmailExist(){
		return doesEmailExist;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public int getUserID(){
		return userID;
	}
	
	public void setUserID(int userID){
		this.userID = userID;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public boolean isFacebook(){
		return isFacebook;
	}
	
	public void setIsFacebook(boolean isFacebook){
		this.isFacebook = isFacebook;
	}
	
	public boolean isLoggedIn(){
		return isLoggedIn;
	}
	
	public void setIsLoggedIn(boolean isLoggedIn){
		this.isLoggedIn = isLoggedIn;
	}
	
	public void clear(){
		setEmail("");
		setPassword("");
		setIsFacebook(false);
		getPreferences().clear();
		DBug.print("User Credentials Cleared");
	}
	
	public void saveCredentials(){
		DBug.print("Saving Credentials");
		getPreferences().putBoolean(FACEBOOK_TAG, isFacebook());
		getPreferences().putString(PASSWORD_TAG, getPassword());
		getPreferences().putString(EMAIL_TAG, getEmail());
		getPreferences().flush();
	}
	
	public void loadCredentials(){
		DBug.print("Loading Credentials");
		if(doesPrefExist(EMAIL_TAG)){
			setEmail(getPreferences().getString(EMAIL_TAG));
			DBug.print("Email: " + getEmail());
		}
		if(doesPrefExist(PASSWORD_TAG)){
			setPassword(getPreferences().getString(PASSWORD_TAG));
			DBug.print("Password: " + getPassword());
		}
		if(doesPrefExist(FACEBOOK_TAG)){
			setIsFacebook(getPreferences().getBoolean(FACEBOOK_TAG));
			DBug.print("IsFacebook: " + isFacebook());
		}
	}
	
	private Preferences getPreferences(){
		return Gdx.app.getPreferences(PREFS_NAME);
	}
	
	private boolean doesPrefExist(String tag){
		return Gdx.app.getPreferences(PREFS_NAME).contains(tag);
	}
}
