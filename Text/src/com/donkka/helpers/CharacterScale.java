package com.donkka.helpers;

import java.util.Random;

public class CharacterScale {
	private static final int WEIGHT_SUM = 100;
	
	private static CharacterScale instance;
	
	private int[] weights;
	private Random rand;
	
	private CharacterScale(){
		rand = new Random();
		weights = new int[] {
				9, //a
				2, //b
				2, //c
				4, //d
				12, //e
				2, //f
				3, //g
				2, //h
				9, //i
				1, //j
				1, //k
				4, //l
				2, //m
				6, //n
				8, //o
				2, //p
				1, //q
				6, //r
				4, //s
				6, //t
				4, //u
				2, //v
				2, //w
				1, //x
				2, //y
				1  //z
		};
	}
	
	public static CharacterScale getInstance(){
		if(instance == null)
			instance = new CharacterScale();
		return instance;
	}
	
	public char getNextChar(){
		int w = rand.nextInt(WEIGHT_SUM) + 1;
		int sum = 0;
		for(int i = 0 ; i < weights.length ; i ++){
			if(sum + weights[i] > w)
				return (char) ('a' + i);
			sum += weights[i];
		}
		return 'a';
	}
}
