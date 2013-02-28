package com.donkka.text;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Text";
		cfg.useGL20 = false;
		cfg.width = 360;
		cfg.height = 600;
		
		new LwjglApplication(new TextGame(new FaceBookDesktop()), cfg);
	}
}
