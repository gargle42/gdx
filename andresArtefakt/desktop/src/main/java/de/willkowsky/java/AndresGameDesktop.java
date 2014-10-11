package de.willkowsky.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.willkowsky.core.AndresGame;
import de.willkowsky.core.Basic3DTest;

public class AndresGameDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AndresGame(), config);
//		new LwjglApplication(new Basic3DTest(), config);
	}
}
