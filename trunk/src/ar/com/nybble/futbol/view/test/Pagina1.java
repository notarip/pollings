package ar.com.nybble.futbol.view.test;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author notarip
 */
public class Pagina1 extends WebPage {
	
	public Pagina1() {
		add(new Label("message","Pablo usa Wicket!"));
	}

}

