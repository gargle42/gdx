package de.willkowsky.html;

import de.willkowsky.core.AndresGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class AndresGameHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new AndresGame();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
