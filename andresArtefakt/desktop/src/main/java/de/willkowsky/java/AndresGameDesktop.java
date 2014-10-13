package de.willkowsky.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.willkowsky.core.InvaderGame;

public class AndresGameDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new InvaderGame(), config);
//		new LwjglApplication(new Basic3DTest(), config);
	}
}
