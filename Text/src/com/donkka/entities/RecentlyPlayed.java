package com.donkka.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.donkka.patches.RecentlyPlayedNinePatch;
import com.donkka.sprites.DoubleFloatingNinePatch;

public class RecentlyPlayed{
	
	private static final float MARGIN_X = 10;

	private ArrayList<RecentlyPlayedListItem> items;
	private DoubleFloatingNinePatch background;
	
	public RecentlyPlayed(float marginTop, float height){
		background = new DoubleFloatingNinePatch(MARGIN_X, marginTop, height, RecentlyPlayedNinePatch.getInstance());
		items = new ArrayList<RecentlyPlayed.RecentlyPlayedListItem>();
	}
	
	public void render(SpriteBatch batch){
		background.render(batch);
		for(RecentlyPlayedListItem i: items){
			if(i != null){
				i.update(Gdx.graphics.getDeltaTime());
				i.render(batch);
			}
		}
	}
	
	public void add(String word){
		for(RecentlyPlayedListItem i: items)
			i.moveUp();
		items.add(new RecentlyPlayedListItem(word));
	}
	
	public class RecentlyPlayedListItem{
		
		private static final float MARGIN = 20;
		private static final float ANIM_VEL = 100;
		private static final float HEIGHT = 40;
		private static final float MARGIN_TOP = 5;
		
		public String word;
		public Vector2 pos;
		private Vector2 targetPos;
		ShapeRenderer shapes = new ShapeRenderer();
		
		public RecentlyPlayedListItem(String word){
			this.word = word;
			this.pos = new Vector2(background.getPos().x + MARGIN, background.getPos().y + MARGIN);
			this.targetPos = new Vector2(pos.x, pos.y);
		}
		
		public void render(SpriteBatch batch){
			batch.end();
			shapes.setProjectionMatrix(batch.getProjectionMatrix());
			shapes.begin(ShapeType.Filled);
			shapes.setColor(Color.BLACK);
			shapes.rect(pos.x, pos.y, 200, HEIGHT);
			shapes.end();
			batch.begin();
		}
		
		public void update(float delta){
			float y = Math.min(targetPos.y, pos.y + ANIM_VEL * delta);
			this.pos.set(background.getPos().x + MARGIN, y);
		}
		
		public void animate(float y){
			this.targetPos.set(pos.x, y);
		}
		
		public void moveUp(){
			this.animate(this.targetPos.y + HEIGHT + MARGIN_TOP);
		}
		
		public Vector2 getTargetPos(){
			return targetPos;
		}
	}
}
