package com.donkka.entities;

public class GameInfo {
	
	public int id;
	public int opponentScore, myScore;
	public String opponentName, myName;
	
	public GameInfo(int id, int opponentScore, int myScore, String opponentName, String myName){
		this.id = id;
		this.opponentScore = opponentScore;
		this.myScore = myScore;
		this.opponentName = opponentName;
		this.myName = myName;
	}
}
