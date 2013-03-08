package com.donkka.dialog.callback;

import com.donkka.screens.ShaneScreen;

public interface LoadingCallback {

	public boolean isDoneLoading();
	public void onPreLoad();
	public void onPostLoad(ShaneScreen under);
	public void onFailedLoad(ShaneScreen under);
}
