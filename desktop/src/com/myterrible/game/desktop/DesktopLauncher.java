package com.myterrible.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myterrible.game.Megaman;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Megaman.V_WIDTH;
		config.height = Megaman.V_HEIGHT;
		new LwjglApplication(new Megaman(), config);
	}
}
