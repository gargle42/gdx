package de.willkowsky.html;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import de.willkowsky.core.invaders.Game;

public class AndresGameHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Game();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
