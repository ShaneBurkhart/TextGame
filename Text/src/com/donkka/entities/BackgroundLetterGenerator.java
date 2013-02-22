package com.donkka.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.donkka.art.Art;

public class BackgroundLetterGenerator {

	private final int MAX_TILES = 15;
	private final float RATE_TO_ADD = .5f;
	private final float MAX_VELOCITY = 400f;
	private final float MIN_VELOCITY = 200f;
	private final float MAX_SCALE = 1;
	private final float MIN_SCALE = .4f;
	
	MovingTile[] tiles;
	private float time = 0;
	private float width, height;
	private Random rand;
	
	public BackgroundLetterGenerator(){
		rand = new Random();
		tiles = new MovingTile[MAX_TILES];
	}
	
	public void render(SpriteBatch batch){
		for(MovingTile tile: tiles){
			if(tile != null)
				tile.render(batch);
		}
	}
	
	public void update(float delta, Camera camera){
		width = camera.viewportWidth;
		height = camera.viewportHeight;
		
		time += delta;
		for(MovingTile tile: tiles){
			if(tile != null)
				tile.update(delta);
		}
		
		checkForDead();
		
		while(time > RATE_TO_ADD){
			addTile();
			sort();
			time -= RATE_TO_ADD;
		}
		
		if(delta > .1f)
			System.out.println(delta);
	}
	
	private void addTile(){
		Sprite s = Art.getRandomLargeTile();
		int t = rand.nextInt(2);
		float x = 0;
		float y = rand.nextInt((int) (height + s.getHeight() / 2)) - s.getHeight()/2;
		float scale = MIN_SCALE + rand.nextFloat() * (MAX_SCALE - MIN_SCALE);
		float accel = MIN_VELOCITY + rand.nextFloat() * (MAX_VELOCITY - MIN_VELOCITY);
		if(t == 0){
			x = -(width - 480) / 2 - s.getWidth() / 2 - s.getWidth() * scale / 2;
		}else{
			x = 480 + (width - 480) / 2 - s.getWidth() / 2 + s.getWidth() * scale / 2;
			accel = -accel;
		}
		
		for(int i = 0; i < tiles.length; i++){
			if(tiles[i] == null){
				tiles[i] = new MovingTile(s, x, y, accel, scale);
				break;
			}
		}
	}
	
	private void checkForDead(){
		for(int i = 0;  i < tiles.length; i++){
			MovingTile t = tiles[i];
			if(t != null){
				if(t.x < -(width - 480) / 2 - t.sprite.getWidth() / 2 - t.sprite.getWidth() * t.scale / 2 || t.x > 480 + (width - 480) / 2 - t.sprite.getWidth() / 2 + t.sprite.getWidth() * t.scale / 2)
					tiles[i] = null;
			}
		}
	}
	
	private void sort(){
		int index = -1;
		float scale = 100;
		MovingTile[] tempArray = new MovingTile[tiles.length];
		for(int i = 0 ; i < tempArray.length ; i++)
			tempArray[i] = null;
		for(int i = 0 ; i < tiles.length ; i++){
			index = -1;
			scale = 100;
			for(int j = 0 ; j < tiles.length ; j ++){
				MovingTile tile = tiles[j];
				if(tile != null){
					if(scale > tile.scale){
						scale = tile.scale;
						index = j;
					}
				}
			}
			if(index != -1){
				tempArray[i] = tiles[index];
				tiles[index] = null;
			}
		}
		for(int i = 0 ; i < tiles.length ; i ++){
			tiles[i] = tempArray[i];
		}
	}
	
	private class MovingTile{
		
		private float accel, x, y, scale, vel;
		private Sprite sprite;
		
		public MovingTile(Sprite s, float x, float y, float accel, float scale){
			this.x = x;
			this.y = y;
			this.scale = scale;
			this.accel = accel;
			this.sprite = s;
			this.vel = accel * 1f;
		}
		
		public void render(SpriteBatch batch){
			sprite.setScale(scale);
			sprite.setPosition(x, y);
			sprite.draw(batch);
		}
		
		public void update(float delta){
			vel += delta * accel;
			x += vel * delta + .5f * accel * delta * delta;
		}
	}
}
