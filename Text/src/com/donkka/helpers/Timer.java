package com.donkka.helpers;

public class Timer {
	
	private static final long TIME_LIMIT_SEC = 60;
	
	private long startTime;
	private static Timer instance;
	private boolean isStarted = false;
	private Timer(){
	}
	
	public long getTimeInSecondsFromStart(){
		return ( System.nanoTime() - startTime ) / (1000000000);
	}
	
	public long getTimeRemaining(){
		return Math.max(TIME_LIMIT_SEC - getTimeInSecondsFromStart(), 0);
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
	
	public boolean isStarted(){
		return isStarted;
	}
	
	public void stop(){
		isStarted = false;
	}
	
	public void start(){
		startTime = System.nanoTime();
		isStarted = true;
	}
}
