package com.donkka.patches;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.donkka.art.Art;

public class RecentlyPlayedNinePatch extends NinePatch{

	private static RecentlyPlayedNinePatch instance = null;
	
	private RecentlyPlayedNinePatch(){
		super(Art.recentlyPlayedPatch, 20, 20, 20, 20);
	}
	
	public static RecentlyPlayedNinePatch getInstance(){
		if(instance == null)
			instance = new RecentlyPlayedNinePatch();
		return instance;
	}
}
