package com.donkka.helpers;

public class Score {
	
	private static Score instance;
	
	private int score;
	
	private Score(){
		score = 0;
	}
	
	public void reset(){
		score = 0;
	}
	
	public int getScore(){
		return score;
	}

	public void add(int toAdd){
		score += toAdd;
	}
	
	public static Score getInstance(){
		if(instance == null)
			instance = new Score();
		return instance;
	}
}
