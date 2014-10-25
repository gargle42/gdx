package de.willkowsky.html;

import de.willkowsky.core.Exotenangriff;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class ExotenangriffHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Exotenangriff();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
