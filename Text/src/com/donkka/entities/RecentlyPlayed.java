package com.donkka.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.patches.RecentlyPlayedNinePatch;
import com.donkka.sprites.DoubleFloatingNinePatch;

public class RecentlyPlayed{
	
	private static final float MARGIN_X = 10;

	private RecentlyPlayedListItem[] items;
	private DoubleFloatingNinePatch background;
	
	public RecentlyPlayed(float marginTop, float height){
		background = new DoubleFloatingNinePatch(MARGIN_X, marginTop, height, RecentlyPlayedNinePatch.getInstance());
		items = new RecentlyPlayed.RecentlyPlayedListItem[20];
	}
	
	public void render(SpriteBatch batch){
		background.render(batch);
		for(int i = 0 ; i < items.length ; i ++){
			RecentlyPlayedListItem item = items[i];
			if(item != null){
				item.update(Gdx.graphics.getDeltaTime());
				item.render(batch);
				if(item.isDead())
					items[i] = null;
			}
		}
	}
	
	public void add(String word){
		for(RecentlyPlayedListItem i: items){
			if(i != null)
				i.moveUp();
		}
		//Shift up
		for(int i = items.length - 1 ; i > 0 ; i --){
			items[i] = items[i - 1];
		}
		items[0] = new RecentlyPlayedListItem(word);
	}
	
	public class RecentlyPlayedListItem{
		
		private static final float MARGIN = 20;
		private static final float ANIM_VEL = 500;
		private static final float HEIGHT = 50;
		private static final float WIDTH = 50;
		private static final float VERTICAL_SPACING = 15;
		private static final float HORIZONTAL_SPACING = 5;
		
		public String word;
		public Vector2 pos;
		private Vector2 targetPos;
		ShapeRenderer shapes = new ShapeRenderer();
		
		public RecentlyPlayedListItem(String word){
			this.word = word.toLowerCase();
			this.pos = new Vector2(background.getPos().x + MARGIN, background.getPos().y + MARGIN);
			this.targetPos = new Vector2(pos.x, pos.y);
		}
		
		public void render(SpriteBatch batch){
			char c;
			float x = pos.x;
			for(int i = 0 ; i < word.length() ; i ++){
				c = word.charAt(i);
				Art.getTile(c).setPosition(x, pos.y);
				Art.getTile(c).draw(batch);
				x += WIDTH + HORIZONTAL_SPACING;
			}
		}
		
		public void update(float delta){
			float y = Math.min(targetPos.y, pos.y + ANIM_VEL * delta);
			this.pos.set(background.getPos().x + MARGIN, y);
		}
		
		public void animate(float y){
			this.targetPos.set(pos.x, y);
		}
		
		public void moveUp(){
			this.animate(this.targetPos.y + HEIGHT + VERTICAL_SPACING);
		}
		
		public Vector2 getTargetPos(){
			return targetPos;
		}
		
		public boolean isDead(){
			return targetPos.y + HEIGHT > background.getPos().y + background.getDimensions().y - MARGIN;
		}
	}
}