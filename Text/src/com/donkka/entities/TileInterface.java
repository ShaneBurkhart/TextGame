package com.donkka.entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.debug.DBug;
import com.donkka.helpers.CharacterScale;
import com.donkka.helpers.Dictionary;
import com.donkka.helpers.Dimensions;
import com.donkka.text.TouchEvent;

public class TileInterface {
	
	private static final int NUM_CHARS = 6;
	private static final float BANK_Y = 90f;
	private static final float SELECTED_Y = 165f;
	private float INTERFACE_WIDTH = 0;
	private static final float HORIZONTAL_SPACING = 8f;
	private static final int NUM_SHUFFLES = 2;
	
	private Tile[] bank, selected;
	private Random rand;
	private RecentlyPlayed recentlyPlayed;
	
	public TileInterface(RecentlyPlayed recentlyPlayed){
		this.recentlyPlayed = recentlyPlayed;
		rand = new Random();
		bank = new Tile[NUM_CHARS];
		selected = new Tile[NUM_CHARS];
		fillChars();
	}
	
	public void render(SpriteBatch batch, float delta){
		Tile t;
		for(int i = 0 ; i < NUM_CHARS ; i ++){
			for(int j = 0 ; j < 2 ; j ++){
				t = j == 0 ? bank[i] : selected[i];
				renderEmtpy(j, i, batch);
				if(t == null)
					continue;
				t.update(delta);
				t.render(batch);
			}
		}
	}

	public void shuffle(){
		DBug.print("Shuffle");
		//Shuffle
		int j = 0;
		Tile temp;
		for(int k = 0 ; k < NUM_SHUFFLES ; k ++){
			for(int i = 0 ; i < bank.length ; i ++){
				j = rand.nextInt(bank.length);
				temp = bank[j];
				bank[j] = bank[i];
				bank[i] = temp;
			}
		}
		//Consolidate
		j = 0;
		for(int i = 0 ; i < bank.length ; i ++){
			if(bank[i] == null)
				continue;
			bank[j] = bank[i];
			if(i != j)
				bank[i] = null;
			j++;
		}
		//Update
		for(int i = 0 ; i < bank.length ; i ++){
			if(bank[i] == null)
				continue;
			bank[i].animate(i, BANK_Y);
		}
	}
	
	public void recall(){
		DBug.print("Recall");
		for(int i = 0 ; i < selected.length ; i ++){
			if(selected[i] == null)
				continue;
			selectedToBank(i);
		}
	}
	
	public void submit(){
		String word = getSelectedWord();
		if(Dictionary.getInstance().isWord(word)){
			DBug.print("Valid Word : " + word);
			removeChars();
			fillChars();
			recentlyPlayed.add(word);
		}else{
			DBug.print("Invalid Word : " + word);
			Gdx.input.vibrate(100);
		}
		recall();
	}
	
	private void fillChars(){
		int numToAdd = 0;
		for(Tile t : bank){
			if(t == null)
				numToAdd++;
		}
		String newChars = "";
		do{
			newChars = "";
			for(int i = 0 ; i < numToAdd ; i ++)
				newChars += CharacterScale.getInstance().getNextChar();
			DBug.print("Not " + newChars);
		}while(!Dictionary.getInstance().containsValidChars(getChars() + newChars));
		int j = 0;
		for(int i = 0 ; i < bank.length ; i ++){
			if(bank[i] != null)
				continue;
			bank[i] = new Tile(i, newChars.charAt(j++));
		}
	}
	
	private String getChars(){
		String chars = "";
		for(Tile t : bank){
			if(t == null)
				continue;
			chars += t.getChar();
		}
		return chars;
	}
	
	private void removeChars(){
		for(int i = 0 ; i < selected.length ; i ++)
			selected[i] = null;
	}
	
	private String getSelectedWord(){
		String word = "";
		for(Tile t : selected){
			if(t == null)
				continue;
			word += t.getChar();
		}
		return word;
	}
	
	public void onTouch(float x, float y, int touchEvent){
		Tile t;
		for(int i = 0 ; i < NUM_CHARS ; i ++){
			for(int j = 0 ; j < 2 ; j ++){
				t = j == 0 ? bank[i] : selected[i];
				if(t == null)
					continue;
				if(t.isTouched(x, y)){
					if(touchEvent == TouchEvent.TOUCH_UP){
						if(j == 0)
							bankToSelected(i);
						else if(j == 1){
							selectedToBank(i);
							shrink();
						}
						t.deflate();
						return;
					}else if(touchEvent == TouchEvent.TOUCH_DOWN){
						t.inflate();
						return;
					}else if(touchEvent == TouchEvent.TOUCH_DRAGGED){
						t.inflate();
					}
					continue;
				}
				t.deflate();
			}
		}
	}
	
	public void renderEmtpy(int j, int i, SpriteBatch batch){
		float y, x;
		if(j == 0)
			y = BANK_Y + Art.getTile('a').getHeight() / 2 - Art.empty.getHeight() / 2;
		else
			y = SELECTED_Y + Art.getTile('a').getHeight() / 2 - Art.empty.getHeight() / 2;
		x = getLeft() + i * (Art.getTile('a').getWidth() + HORIZONTAL_SPACING) + Art.getTile('a').getWidth() / 2 - Art.empty.getWidth() / 2;
		Art.empty.setScale(1);
		Art.empty.setPosition(x, y);
		Art.empty.draw(batch);
	}
	
	public void bankToSelected(int index){
		Tile t = bank[index];
		for(int i = 0 ; i < NUM_CHARS ; i ++){
			if(selected[i] == null){
				selected[i] = t;
				t.animate(i, SELECTED_Y);
				break;
			}
		}
		bank[index] = null;
	}
	
	public void selectedToBank(int index){
		Tile t = selected[index];
		for(int i = 0 ; i < NUM_CHARS ; i ++){
			if(bank[i] == null){
				bank[i] = t;
				t.animate(i, BANK_Y);
				break;
			}
		}
		selected[index] = null;
	}
	
	private void shrink(){
		int j = 0;
		for(int i = 0 ; i < selected.length ; i ++){
			if(selected[i] == null)
				continue;
			selected[j] = selected[i];
			if(i != j)
				selected[i] = null;
			selected[j].animate(j, SELECTED_Y);
			j++;
		}
	}
	
	public float getLeft(){
		return Dimensions.getTargetWidth() / 2 - INTERFACE_WIDTH / 2;
	}
	
	public float getWidth(){
		return INTERFACE_WIDTH;
	}
	
	private class Tile{
		
		private static final float TILE_MOVE_DURATION = .07f;
		private static final float SCALE_VEL = 10f;
		private static final float INFLATE_SCALE = 1.2f;
		private static final float DEFLATE_SCALE = 1f;
		private float WIDTH = 0;
		private float LEFT_X = 0;
		
		private float TILE_VEL = 0;
		
		private Vector2 pos, targetPos;
		private char c;
		private float scale = 0f;
		
		public Tile(int index, char c){
			this.WIDTH = Art.getTile(c).getWidth();
			INTERFACE_WIDTH = WIDTH * NUM_CHARS + HORIZONTAL_SPACING * (NUM_CHARS - 1);
			this.LEFT_X = Dimensions.getTargetWidth() / 2 - INTERFACE_WIDTH / 2;
			pos = new Vector2(LEFT_X + index * (WIDTH + HORIZONTAL_SPACING), BANK_Y);
			targetPos = new Vector2(pos.x, pos.y);
			this.c = c;
		}
		
		public void render(SpriteBatch batch){
			Art.getTile(c).setPosition(pos.x, pos.y);
			Art.getTile(c).setColor(1, 1, 1, 1);
			Art.getTile(c).setScale(scale);
			Art.getTile(c).setRotation(0);
			Art.getTile(c).draw(batch);
		}
		
		float dx, dy, dst, vx, vy;
		
		public void update(float delta){
			if(scale < 1)
				scale = Math.min(scale + SCALE_VEL * delta, 1);
			if(pos.x != targetPos.x || pos.y != targetPos.y){
				dx = targetPos.x - pos.x;
				dy = targetPos.y - pos.y;
				dst = (float) Math.sqrt(dx * dx + dy * dy);
				vx = TILE_VEL * dx / dst;
				vy = TILE_VEL * dy / dst;
				if(vx > 0)
					pos.x = Math.min(pos.x + vx * delta, targetPos.x);
				else
					pos.x = Math.max(pos.x + vx * delta, targetPos.x);
				if(vy > 0)
					pos.y = Math.min(pos.y + vy * delta, targetPos.y);
				else
					pos.y = Math.max(pos.y + vy * delta, targetPos.y);
			}
		}
		
		public void animate(int index, float y){
			this.animate(LEFT_X + index * (WIDTH + HORIZONTAL_SPACING), y);
		}
		
		public void animate(float x, float y){
			float dx = x - pos.x;
			float dy = y - pos.y;
			TILE_VEL = (float) Math.sqrt(dx * dx + dy *dy) / TILE_MOVE_DURATION;
			targetPos.set(x, y);
		}
		
		public void inflate(){
			scale = INFLATE_SCALE;
		}
		
		public void deflate(){
			scale =  DEFLATE_SCALE;
		}
		
		public char getChar(){
			return c;
		}
		
		public boolean isTouched(float x, float y){
			return x > pos.x - HORIZONTAL_SPACING / 2 && x < pos.x + WIDTH + HORIZONTAL_SPACING / 2 && y > pos.y - HORIZONTAL_SPACING / 2 && y < pos.y + WIDTH + HORIZONTAL_SPACING / 2; 
		}
	}
}
