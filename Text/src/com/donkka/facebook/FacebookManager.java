package com.donkka.facebook;


public class FacebookManager {

	private static FacebookManager instance;
	private FaceBookInterface fbInterface;
	
	private FacebookManager(){
	}
	
	public static FacebookManager getInstance(){
		if(instance == null)
			instance = new FacebookManager();
		return instance;
	}
	
	public void init(FaceBookInterface fbInterface){
		this.fbInterface = fbInterface;
	}
	
	public FaceBookInterface getFacebookInterface(){
		return fbInterface;
	}
}
