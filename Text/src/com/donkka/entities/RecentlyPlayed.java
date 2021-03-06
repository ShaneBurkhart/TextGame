package com.donkka.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.debug.DBug;
import com.donkka.patches.RecentlyPlayedNinePatch;
import com.donkka.sprites.DoubleFloatingNinePatch;

public class RecentlyPlayed{
	
	private static final float SCREEN_MARGIN_X = 10;
	private static final int NUM_ROWS = 5;
	private static final float VERTICAL_SPACING = 15;
	private static final float MARGIN = 20;

	private RecentlyPlayedListItem[] items;
	private DoubleFloatingNinePatch background;
	
	public RecentlyPlayed(float marginTop){
		float height = NUM_ROWS * Art.getTile('a').getHeight() + VERTICAL_SPACING * (NUM_ROWS - 1) + MARGIN * 2;
		background = new DoubleFloatingNinePatch(SCREEN_MARGIN_X, marginTop, height, RecentlyPlayedNinePatch.getInstance());
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
		DBug.print("Word Added: " + word.toUpperCase());
	}
	
	public class RecentlyPlayedListItem{
		
		private static final float ANIMATION_DURATION = .2f;
		private float ANIM_VEL = 0;
		private float ALPHA_VEL = 0;
		private float HEIGHT = 50;
		private float WIDTH = 50;
		private static final float HORIZONTAL_SPACING = 5;
		
		public String word;
		public Vector2 pos;
		private Vector2 targetPos;
		ShapeRenderer shapes = new ShapeRenderer();
		private boolean isDying = false;
		private float alpha = 0;
		
		public RecentlyPlayedListItem(String word){
			this.HEIGHT = this.WIDTH = Art.getTile('a').getWidth();
			this.word = word.toLowerCase();
			this.pos = new Vector2(background.getPos().x + MARGIN, background.getPos().y + MARGIN);
			this.targetPos = new Vector2(pos.x, pos.y);
			this.ANIM_VEL = (HEIGHT + VERTICAL_SPACING) / ANIMATION_DURATION;
			this.ALPHA_VEL = 1 / ANIMATION_DURATION;
		}
		
		public void render(SpriteBatch batch){
			char c;
			float x = pos.x;
			for(int i = 0 ; i < word.length() ; i ++){
				c = word.charAt(i);
				Art.getTile(c).setColor(1, 1, 1, alpha);
				Art.getTile(c).setPosition(x, pos.y);
				Art.getTile(c).setScale(1);
				Art.getTile(c).draw(batch);
				x += WIDTH + HORIZONTAL_SPACING;
			}
		}
		
		public void update(float delta){
			float y = Math.min(targetPos.y, pos.y + ANIM_VEL * delta);
			this.pos.set(background.getPos().x + MARGIN, y);
			if(isDying)
				alpha = Math.max(alpha - ALPHA_VEL * delta, 0);
			else
				alpha = Math.min(alpha + ALPHA_VEL * delta, 1);
		}
		
		public void animate(float y){
			this.targetPos.set(pos.x, y);
		}
		
		public void moveUp(){
			if(this.targetPos.y + HEIGHT + VERTICAL_SPACING > background.getPos().y + background.getDimensions().y - MARGIN)
				isDying = true;
			else
				this.animate(this.targetPos.y + HEIGHT + VERTICAL_SPACING);
		}
		
		public Vector2 getTargetPos(){
			return targetPos;
		}
		
		public boolean isDead(){
			return isDying && alpha == 0;
		}
	}
}
