package com.donkka.entities.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.donkka.entities.Entity;
import com.donkka.patches.GreenNinePatch;
import com.donkka.patches.OrangeNinePatch;


public class GameMenuInterface implements Entity{
	
	public static final float HOR_MARGIN = 10;
	public static final float VERT_SPACING = 20;
	public static final float BOTTOM_PADDING_NO_ITEMS = 40;
	public static final float HEADING_HEIGHT = 65;
	public static final float TOP_MARGIN_TEXT = 15;
	public static final float MENU_SIDE_PADDING = 15;
	
	private TurnMenu yourMove;
	private TurnMenu theirMove;
	private float top;
	
	public GameMenuInterface(float top){
		this.top = top;
		yourMove = new TurnMenu(top, "Your Move", GreenNinePatch.getInstance());
		theirMove = new TurnMenu(top - yourMove.getHeight() - VERT_SPACING, "Their Move", OrangeNinePatch.getInstance());
	}
	
	public void render(SpriteBatch batch, float delta){
		yourMove.render(batch);
		theirMove.render(batch);
	}
	
	public void onTouch(float x, float y, int TouchEvent){
		yourMove.onTouch(x, y, TouchEvent);
		theirMove.onTouch(x, y, TouchEvent);
	}
	
	public float getTop(){
		return top;
	}
	
	public float getHeight(){
		return yourMove.getHeight() + VERT_SPACING + theirMove.getHeight();
	}
	
	public void addToYourMove(MenuItem item){
		yourMove.add(item);
		reposition();
	}
	
	private void reposition(){
		yourMove.setTop(top);
		theirMove.setTop(top - yourMove.getHeight() - GameMenuInterface.VERT_SPACING);
	}
	
	public void addToYourTheir(MenuItem item){
		theirMove.add(item);
		reposition();
	}
}
