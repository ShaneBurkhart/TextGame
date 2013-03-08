package com.donkka.entities.menu;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.donkka.art.Art;
import com.donkka.helpers.Dimensions;

public class TurnMenu{
	
	private float top;
	private float height = GameMenuInterface.HEADING_HEIGHT + GameMenuInterface.BOTTOM_PADDING_NO_ITEMS;
	ArrayList<MenuItem> items;
	private String title;
	private NinePatch patch;
	
	public TurnMenu(float top, String title, NinePatch patch){
		this.top = top;
		this.title = title;
		this.patch = patch;
		this.items = new ArrayList<MenuItem>();
	}
	
	float tempY = 0;
	
	public void render(SpriteBatch batch){
		patch.draw(batch, Dimensions.getLeft() + GameMenuInterface.HOR_MARGIN, top - height, Dimensions.getWidth() - 2 * GameMenuInterface.HOR_MARGIN, height);
		Art.arial40.draw(batch, title, Dimensions.getLeft() + GameMenuInterface.HOR_MARGIN + GameMenuInterface.MENU_SIDE_PADDING, top - GameMenuInterface.TOP_MARGIN_TEXT);
		tempY = top - GameMenuInterface.HEADING_HEIGHT;
		for(int i = 0 ; i < items.size() ; i ++){
			items.get(i).render(batch, Dimensions.getLeft() + GameMenuInterface.HOR_MARGIN + GameMenuInterface.MENU_SIDE_PADDING, tempY - items.get(i).getHeight(), getWidth());
			tempY -= items.get(i).getHeight();
		}
	}
	
	public void onTouch(float x, float y, int TouchEvent){
		float left = Dimensions.getLeft() + GameMenuInterface.MENU_SIDE_PADDING;
		if(x > left && x < left + getWidth() && y < top - 65 && y > top - getHeight()){
			int p =  (int) (top - GameMenuInterface.HEADING_HEIGHT - y) / (int) (Item.ITEM_HEIGHT + Item.ITEM_PADDING_VERT * 2);
			if(p < items.size())
				items.get(p).onTouch(x, y, TouchEvent, p);
		}
	}
	
	public void setTop(float top){
		this.top = top;
	}
	
	public void add(MenuItem item){
		if(items.size() == 0)
			height = GameMenuInterface.HEADING_HEIGHT + 5;
		this.height += item.getHeight();
		this.items.add(item);
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getWidth(){
		return Dimensions.getWidth() - GameMenuInterface.HOR_MARGIN * 2 - GameMenuInterface.MENU_SIDE_PADDING * 2;
	}
}