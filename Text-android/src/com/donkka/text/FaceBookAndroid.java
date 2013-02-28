package com.donkka.text;

import android.app.Activity;

import com.donkka.facebook.FaceBookInterface;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Session.StatusCallback;

public class FaceBookAndroid implements FaceBookInterface{
	
	Activity a;
	
	public FaceBookAndroid(Activity a){
		this.a = a;
	}
	
	@Override
	public void login() {
		 Session.openActiveSession(this.a, true, new StatusCallback() {
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				
			}
		});
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}

}
