package com.donkka.debug;

public class DBug {

	private static final boolean DEBUG = true;
	
	public static void print(String s){
		if(DEBUG)
			System.out.println(s);
	}
}
