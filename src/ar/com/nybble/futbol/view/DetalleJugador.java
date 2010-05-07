package ar.com.nybble.futbol.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;


import ar.com.nybble.futbol.Jugador;

/**
 * @author notarip
 */
public class DetalleJugador extends TemplatePage {
	Form form = new Form("form");
	Label nombre = new Label("nombre", new Model(""));
	Label club = new Label("club", new Model(""));
	Label fecha = new Label("fecha", new Model(""));
	Label nacionalidad = new Label("nacionalidad", new Model(""));
	Label estado = new Label("estado", new Model(""));
	
	
	
	public DetalleJugador() {
		construccionEnComun();
		nombre.setDefaultModelObject("jugador nulo");
	}
	
	public DetalleJugador(Jugador jugador) {
		construccionEnComun();
		if (jugador == null)
			nombre.setDefaultModelObject("jugador nulo");
		else{
			nombre.setDefaultModelObject(jugador.toString());
			club.setDefaultModelObject(jugador.getClub().toString());
			fecha.setDefaultModelObject(jugador.getFechaNacimiento().toString());
			nacionalidad.setDefaultModelObject(jugador.getNacionalidad().toString());
			estado.setDefaultModelObject(jugador.getEstado().toString());
		}	
	}

	private void construccionEnComun(){
		form.add(new Link("home") {
			@Override
			public void onClick() {
				setResponsePage(new ConsultaClubs());
			}
		});
		form.add(nombre);
		form.add(club);
		form.add(fecha);
		form.add(nacionalidad);
		form.add(estado);
		add(form);
		
	}
	
	

}

