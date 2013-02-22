package com.donkka.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.helpers.Dimensions;
import com.donkka.text.TouchEvent;

public class TileInterface {
	
	private static final int NUM_CHARS = 6;
	private static final float BANK_Y = 80f;
	private static final float SELECTED_Y = 150f;
	
	private Tile[] bank, selected;
	
	public TileInterface(char[] chars){
		bank = new Tile[NUM_CHARS];
		selected = new Tile[NUM_CHARS];
		for(int i = 0 ; i < bank.length ; i ++){
			if(i > chars.length - 1)
				break;
			bank[i] = new Tile(i, Character.toLowerCase(chars[i]));
		}		
	}
	
	public void render(SpriteBatch batch, float delta){
		Tile t;
		for(int i = 0 ; i < NUM_CHARS ; i ++){
			for(int j = 0 ; j < 2 ; j ++){
				t = j == 0 ? bank[i] : selected[i];
				if(t == null)
					continue;
				t.update(delta);
				t.render(batch);
			}
		}
	}

	public void shuffle(){
		
	}
	
	public void recall(){
		
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
						else if(j == 1)
							selectedToBank(i);
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
	
	private class Tile{
		
		private static final float TILE_MOVE_DURATION = .07f;
		private static final float SCALE_VEL = 10f;
		private static final float INFLATE_SCALE = 1.2f;
		private static final float DEFLATE_SCALE = 1f;
		private static final float HORIZONTAL_SPACING = 10f;
		private float WIDTH = 0;
		private float LEFT_X = 0;
		private float INTERFACE_WIDTH = 0;
		private float TILE_VEL = 0;
		
		private Vector2 pos, targetPos;
		private char c;
		private float scale = 0f;
		
		public Tile(int index, char c){
			this.WIDTH = Art.getTile(c).getWidth();
			this.INTERFACE_WIDTH = WIDTH * NUM_CHARS + HORIZONTAL_SPACING * (NUM_CHARS - 1);
			this.LEFT_X = Dimensions.getTargetWidth() / 2 - INTERFACE_WIDTH / 2;
			pos = new Vector2(LEFT_X + index * (WIDTH + HORIZONTAL_SPACING), BANK_Y);
			targetPos = new Vector2(pos.x, pos.y);
			this.c = c;
		}
		
		public void render(SpriteBatch batch){
			Art.getTile(c).setPosition(pos.x, pos.y);
			Art.getTile(c).setColor(1, 1, 1, 1);
			Art.getTile(c).setScale(scale);
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
		
		public Vector2 getPos(){
			return pos;
		}
		
		public char getChar(){
			return c;
		}
		
		public boolean isTouched(float x, float y){
			return x > pos.x - HORIZONTAL_SPACING / 2 && x < pos.x + WIDTH + HORIZONTAL_SPACING / 2 && y > pos.y - HORIZONTAL_SPACING / 2 && y < pos.y + WIDTH + HORIZONTAL_SPACING / 2; 
		}
	}
}
