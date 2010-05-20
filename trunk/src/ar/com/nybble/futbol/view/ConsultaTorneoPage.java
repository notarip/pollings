package ar.com.nybble.futbol.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.Torneo;
import ar.com.nybble.futbol.common.ContextFactory;
import ar.com.nybble.futbol.services.AbmClubService;
import ar.com.nybble.futbol.services.AbmTorneoService;


/**
 * @author notarip
 */
public class ConsultaTorneoPage extends TemplatePage {
	
	
	Form formBusqueda = new Form("formBusqueda");
	Form formResultados = new Form("formResultados");
	TextField busqueda = new TextField("busqueda", new Model(""));
	List resultado = new LinkedList<Jugador>();
	Club clubNauta;
	
	public ConsultaTorneoPage() {
		formBusqueda.add(busqueda);
		formBusqueda.add(new Button("buscar"){
			@Override
			public void onSubmit() {
				resultado.clear();
				formResultados.setVisible(true);
				String busquedaTxt = (String) busqueda.getModelObject();
				consultarYModelar(busquedaTxt);
			}
		});
		formResultados.setVisible(false);

		add(formBusqueda);
		add(formResultados);
		
		formResultados.add(new ListView<Club>("rows",resultado) {
			@Override
			protected void populateItem(ListItem<Club> item) {
				clubNauta = item.getModelObject();
				item.add(new Label("item", item.getModelObject().toString()));
				item.add(new Link ("link",item.getModel()) {
					@Override
					public void onClick() {
						Torneo torneo = (Torneo) getModelObject();
						setResponsePage(new DetalleTorneoPage(torneo));
					}
				});
			}
		});
	}

	private void consultarYModelar(String busquedaTxt) {
		AbmTorneoService abmTorneo = (AbmTorneoService) ContextFactory.getInstancia().getBean("AbmTorneoService");
			
		for (Iterator iterator = abmTorneo.buscarTorneoPorNombre(busquedaTxt); iterator.hasNext();) {
			Torneo torneo = (Torneo) iterator.next();
			resultado.add(torneo);
		}
	}
	
	

}

