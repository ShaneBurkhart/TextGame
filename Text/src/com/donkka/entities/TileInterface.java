package com.donkka.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.debug.DBug;
import com.donkka.text.TouchEvent;

public class TileInterface {
	
	private static final int NUM_CHARS = 7;
	
	private char[] bank, selected;
	private Tile[] tiles;
	
	public TileInterface(char[] chars){
		tiles = new Tile[NUM_CHARS];
		bank = new char[NUM_CHARS];
		selected = new char[NUM_CHARS];
		for(int i = 0 ; i < bank.length ; i ++){
			if(i > chars.length - 1)
				break;
			bank[i] = Character.toLowerCase(chars[i]);
			tiles[i] = new Tile(100, 100, bank[i]);
		}		
	}
	
	public void render(SpriteBatch batch){
		for(Tile t: tiles){
			if(t == null)
				continue;
			t.update(Gdx.graphics.getDeltaTime());
			t.render(batch);
		}
	}

	public void shuffle(){
		
	}
	
	public void recall(){
		
	}
	
	public void onTouch(float x, float y, int touchEvent){
		for(Tile t: tiles){
			if(t == null)
				continue;
			if(x > t.getPos().x && x < t.getPos().x + Art.getTile(t.getChar()).getWidth() && y > t.getPos().y && y < t.getPos().y + Art.getTile(t.getChar()).getHeight()){
				if(touchEvent == TouchEvent.TOUCH_UP){
					t.deflate();
				}else if(touchEvent == TouchEvent.TOUCH_DOWN){
					t.inflate();
				}else if(touchEvent == TouchEvent.TOUCH_DRAGGED){
					t.inflate();
				}
				continue;
			}
			t.deflate();
		}
	}
	
	private class Tile{
		
		private static final float TILE_VEL = 1000f;
		private static final float INFLATE_SCALE = 1.2f;
		private static final float DEFLATE_SCALE = 1f;
		
		private Vector2 pos, targetPos;
		private char c;
		private float scale = 1f;
		
		public Tile(float x, float y, char c){
			pos = new Vector2(x, y);
			targetPos = new Vector2(x, y);
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
		
		public void animate(float x, float y){
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
	}
}