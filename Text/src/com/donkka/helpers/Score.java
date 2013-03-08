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
	
	public static int getWordPointValue(String word){
		int points = 0;
		for(char c : word.toCharArray())
			points += PointValues.POINT_VALUES[Character.toLowerCase(c) - 'a'];
		return points;
	}
	
	public static class PointValues{
		public static final int[] POINT_VALUES = new int[] {
			1, //a
			3, //b
			3, //c
			2, //d
			1, //e
			4, //f
			2, //g
			4, //h
			1, //i
			8, //j
			5, //k
			1, //l
			3, //m
			1, //n
			1, //o
			3, //p
			10, //q
			1, //r
			1, //s
			1, //t
			1, //u
			4, //v
			4, //w
			8, //x
			4, //y
			10  //z
		};		 
	}
}
