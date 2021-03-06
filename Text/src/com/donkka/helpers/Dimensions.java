package com.donkka.helpers;

import com.donkka.debug.DBug;

public class Dimensions {
	
	private static final float TARGET_WIDTH = 480f;
	private static final float TARGET_HEIGHT = 800f;
	
	private static float viewPortLeft = 0f;
	private static float viewPortRight = 0f;
	private static float viewPortTop = 0f;
	private static float viewPortWidth = 0f;
	private static float viewPortHeight = 0f;
	
	public static void resize(float width, float height){
		viewPortHeight = TARGET_HEIGHT;
		viewPortWidth = width / height * TARGET_HEIGHT;
		viewPortLeft = TARGET_WIDTH / 2 - viewPortWidth / 2;
		viewPortRight = TARGET_WIDTH / 2 + viewPortWidth / 2;		
		viewPortTop = TARGET_HEIGHT; 
		DBug.print("Resized Dimensions...");
	}
	
	public static float getLeft(){
		return viewPortLeft;
	}
	
	public static float getTop(){
		return viewPortTop;
	}
	
	public static float getRight(){
		return viewPortRight;
	}
	
	public static float getWidth(){
		return viewPortWidth;
	}
	
	public static float getHeight(){
		return viewPortHeight;
	}
	
	public static float getTargetWidth(){
		return TARGET_WIDTH;
	}
	
	public static float getTargetHeight(){
		return TARGET_HEIGHT;
	}
	
	public static class Floats{
		public static final int LEFT = 0x0;
		public static final int RIGHT = 0x1;
	}
}
