package ar.com.nybble.futbol.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.Partido;
import ar.com.nybble.futbol.Torneo;

/**
 * @author notarip
 */
public class DetalleTorneoPage extends TemplatePage {
	
	Form form = new Form("form");
	Form formPartidos = new Form("formPartidos");
	Label nombre = new Label("nombre", new Model(""));
	Label fechaIni = new Label("fechaIni", new Model(""));
	Label cantClubs = new Label("cantClubs", new Model(""));
	Torneo torneo = null;
	List resultado = new LinkedList<Partido>();
	Partido partidoNauta;
		
	public DetalleTorneoPage() {
		construccionEnComun();
		nombre.setDefaultModelObject("torneo nulo");
	}
	
	public DetalleTorneoPage(Torneo torneo) {
		construccionEnComun();
		if (torneo == null)
			nombre.setDefaultModelObject("torneo nulo");
		else{
			this.torneo = torneo;
			nombre.setDefaultModelObject(torneo.toString());
			fechaIni.setDefaultModelObject(torneo.getFechaDeHabilitacion());
			cantClubs.setDefaultModelObject(torneo.getCantidadClubs());
						
			
			for (Iterator iterator = torneo.getPartidos().iterator(); iterator.hasNext();) {
				Partido Partido = (Partido) iterator.next();
				resultado.add(Partido);
				
			}
		}
	}
	
	private void construccionEnComun() {
		
		formPartidos.add(new ListView<Partido>("rows",resultado) {
			@SuppressWarnings("unchecked")
			@Override
			protected void populateItem(ListItem<Partido> item) {
				item.add(new Link ("lnkPartido",item.getModel()) {
					@Override
					public void onClick() {
						Partido Partido = (Partido) getModelObject();
						//setResponsePage(new DetallePartidoPage(Partido));
					}
				});
		
				Link lnkLocal = new Link ("lnkLocal",item.getModel()){
					
					@Override
					public void onClick() {
						Club clubNauta = ((Partido)getModelObject()).getLocal();
						setResponsePage(new DetalleClubPage(clubNauta));
					}
				}; 	
				
				Link lnkVisita = new Link ("lnkVisita",item.getModel()){
					
					@Override
					public void onClick() {
						Club clubNauta = ((Partido)getModelObject()).getVisita();
						setResponsePage(new DetalleClubPage(clubNauta));
					}
				};
				lnkLocal.add(new Label("local",new PropertyModel<String>(((Partido)item.getModelObject()).getLocal(), "nombre")));
				lnkVisita.add(new Label("visita",new PropertyModel<String>(((Partido)item.getModelObject()).getVisita(),"nombre")));
				item.add(lnkLocal);
				item.add(lnkVisita);
			}
		});
		
		this.form.add(nombre);
		this.form.add(fechaIni);
		this.form.add(cantClubs);
		add(formPartidos);
		add(form);
	}

}

