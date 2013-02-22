package com.donkka.loading;

import com.donkka.art.Art;
import com.donkka.helpers.Dictionary;

public class Loader implements Runnable{
	
	@Override
	public void run() {
		
		//Init Dictionary
		Dictionary.getInstance();
		
		//Init Graphics.  Later will do this in an asynchronous task so not to slow down
		Art.init();
		
		setLoaded(true);
	}
	
	private static boolean isLoaded = false;
	
	public static synchronized boolean isLoaded(){
		return isLoaded;
	}
	
	private static synchronized void setLoaded(boolean bool){
		isLoaded = bool;
	}
}