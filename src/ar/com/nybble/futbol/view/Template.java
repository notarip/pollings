package ar.com.nybble.futbol.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 * @author notarip
 */
public class Template extends WebPage {
	
	public Template() {
		add(new BookmarkablePageLink("link1", ListaJugador.class));
		add(new BookmarkablePageLink("link2",ListaJugador.class ));
		add(new BookmarkablePageLink("link3",ListaJugador.class ));
		
	}
}

