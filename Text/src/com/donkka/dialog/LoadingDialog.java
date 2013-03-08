package com.donkka.dialog;

import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.donkka.art.Art;
import com.donkka.connection.ServerInterface;
import com.donkka.dialog.callback.LoadingCallback;
import com.donkka.helpers.Dimensions;
import com.donkka.screens.ShaneScreen;

public class LoadingDialog extends ShaneDialog{

	private LoadingCallback callback;
	private boolean calledPostLoad = false;
	
	public LoadingDialog(ShaneScreen under, LoadingCallback callback) {
		super(under, "", "", 0, 0);
		this.callback = callback;
	}

	@Override
	public void show() {
		super.show();
		this.callback.onPreLoad();
	}

	@Override
	public void render(float delta) {
		updateComponents();
		drawBackground(shapes);
		batch.begin();
		Art.calibri40.drawMultiLine(batch, "Loading...", 0, Dimensions.getHeight() / 2 + Art.calibri40.getCapHeight() / 2, Dimensions.getTargetWidth(), HAlignment.CENTER);
		batch.end();
		if(this.callback.isDoneLoading() && !calledPostLoad && isShowing){
			if(ServerInterface.getInstance().isFailed())
				this.callback.onFailedLoad(under);
			else
				this.callback.onPostLoad(under);
			calledPostLoad = true;
		}
	}
}
