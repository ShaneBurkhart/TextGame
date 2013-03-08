package com.donkka.dialog;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.donkka.art.Art;
import com.donkka.helpers.Dimensions;
import com.donkka.patches.DialogNinePatch;
import com.donkka.screens.ShaneScreen;
import com.donkka.sprites.buttons.ShaneButton;
import com.donkka.text.TouchEvent;

public class ShaneDialog extends ShaneScreen{
	
	public static ShaneDialog getServerErrorDialog(ShaneScreen under){
		return new ShaneDialog(under, "A Problem With The Server", "We are sorry but there is a problem with the server.  Please try again later.  Sorry for your inconvienence!", 400, 400);
	}
	
	@Override
	public void setCameraPosY(float y) {
		under.setCameraPosY(y);
		super.setCameraPosY(y);
	}

	@Override
	public void setCameraPosX(float x) {
		under.setCameraPosX(x);
		super.setCameraPosX(x);
	}

	protected static final float DIALOG_MARGIN = 20;
	protected static final float TITLE_MESSAGE_SPACING = 25;
	protected static final float BACKGROUND_ALPHA = .6f;
	protected static final float SCALE_VEL = 8;
	protected static final float START_SCALE = .3f;
	protected static final float VERT_CENTER_OFFSET = 50f;
	protected static final float BUTTON_SPACING = 15f;
	protected float MAX_WIDTH = 0;
	protected float MAX_HEIGHT = 0;

	protected ShaneScreen under;
	protected float width = 0;
	protected float height = 0;
	protected float scale = START_SCALE;
	protected Vector2 rectCenter;
	protected String title, message;
	protected ArrayList<ShaneButton> buttons;
	
	
	public ShaneDialog(ShaneScreen under, String title, String message, float width, float height){
		this.under = under;
		this.title = title;
		this.message = message;
		this.MAX_WIDTH = width;
		this.MAX_HEIGHT = height;
		this.rectCenter = new Vector2(Dimensions.getTargetWidth() / 2, Dimensions.getTargetHeight() / 2 + VERT_CENTER_OFFSET);
		this.buttons = new ArrayList<ShaneButton>();
	}
	
	public ShaneDialog addButton(ShaneButton button){
		buttons.add(button);
		return this;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		for(ShaneButton b : buttons){
			if(b != null)
				b.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DOWN);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		for(ShaneButton b : buttons){
			if(b != null)
				b.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_UP);
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(touchPos.set(screenX, screenY, 0));
		for(ShaneButton b : buttons){
			if(b != null)
				b.onTouch(touchPos.x, touchPos.y, TouchEvent.TOUCH_DRAGGED);
		}
		return false;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		scale = Math.min(scale + delta * SCALE_VEL, 1);
		width = MAX_WIDTH * scale;
		height = MAX_HEIGHT * scale;
		
		drawBackground(shapes);
		
		batch.begin();
		DialogNinePatch.getInstance().draw(batch, rectCenter.x - width / 2, rectCenter.y - height / 2, width, height);
		
		if(scale == 1){
			drawUI(batch);
		}
		
		batch.end();
	}
	
	protected void drawBackground(ShapeRenderer shapes){
		under.render(0);
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		shapes.begin(ShapeType.Filled);
		shapes.setColor(0, 0, 0, BACKGROUND_ALPHA);
		shapes.rect(Dimensions.getLeft(), 0, Dimensions.getWidth(), Dimensions.getHeight());
		shapes.end();
		Gdx.gl.glDisable(GL10.GL_BLEND);
	}
	
	private void drawUI(SpriteBatch batch){
		Art.calibri22.setColor(Color.WHITE);
		Art.calibri40.setColor(Color.WHITE);
		Art.calibri40.drawMultiLine(batch, title, rectCenter.x - MAX_WIDTH / 2 + DIALOG_MARGIN, rectCenter.y + MAX_HEIGHT / 2 - DIALOG_MARGIN, MAX_WIDTH - DIALOG_MARGIN * 2, HAlignment.CENTER);
		Art.calibri22.drawWrapped(batch, message, rectCenter.x - MAX_WIDTH / 2 + DIALOG_MARGIN, rectCenter.y + MAX_HEIGHT / 2 - DIALOG_MARGIN - Art.calibri40.getCapHeight() - TITLE_MESSAGE_SPACING, MAX_WIDTH - DIALOG_MARGIN * 2, HAlignment.CENTER);
		float tot = 0;
		float x = 0;
		for(ShaneButton b : buttons)
			tot += b.getWidth();
		tot += (buttons.size() - 1) * BUTTON_SPACING;
		x = rectCenter.x - tot / 2;
		for(ShaneButton b : buttons){
			b.setPosition(new Vector2(x, rectCenter.y - MAX_HEIGHT / 2 + DIALOG_MARGIN));
			b.render(batch);
			x += b.getWidth() + BUTTON_SPACING;
		}
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		under.resize(width, height);
	}
}
