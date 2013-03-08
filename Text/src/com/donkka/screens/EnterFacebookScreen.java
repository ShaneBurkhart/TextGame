package com.donkka.screens;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class EnterFacebookScreen extends ShaneScreen{

	public EnterFacebookScreen(){
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		
		batch.end();
		
		shapes.begin(ShapeType.Filled);
		shapes.rect(300, 100, 100, 100);
		shapes.end();
	}
}
