package com.donkka.helpers;

public class Timer {
	
	private static final long TIME_LIMIT_SEC = 60;
	
	private long startTime;
	private static Timer instance;
	private Timer(){
	}
	
	public long getTimeInSecondsFromStart(){
		return ( System.nanoTime() - startTime ) / (1000000000);
	}
	
	public long getTimeRemaining(){
		return TIME_LIMIT_SEC - getTimeInSecondsFromStart();
	}
	
	public boolean isTimeUp(){
		return getTimeRemaining() < 0;
	}
	
	public long getTimeLimit(){
		return TIME_LIMIT_SEC;
	}
	
	public static Timer getInstance(){
		if(instance == null)
			instance = new Timer();
		return instance;
	}
	
	public void start(){
		startTime = System.nanoTime();
	}
}
