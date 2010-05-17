package ar.com.nybble.futbol.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.common.ContextFactory;
import ar.com.nybble.futbol.services.AbmClubService;
import ar.com.nybble.futbol.services.AbmJugadorService;


/**
 * @author notarip
 * Las unicas palabras que merecen existir son aquellas mejores que el silencio.
 */
public class ConsultaJugadoresPage extends TemplatePage {
	
	
	private static final String POR_CLUB = new String("Por Club");
	private static final String POR_JUGADOR = new String("Por Jugador");
	private static final List<String> CRITERIOS = Arrays.asList(new String[] {POR_CLUB,
    POR_JUGADOR});
	
	Form formBusqueda = new Form("formBusqueda");
	TextField busqueda = new TextField("busqueda", new Model(""));
	RadioChoice<String> radioCriterioGroup = new RadioChoice<String>("radioChoise",new Model("") ,CRITERIOS);
	
	Label resultadoL = new Label("resultado", new Model(""));
	Label criterio = new Label("criterio", new Model(""));
	
	Form formResultados = new Form("formResultados");

	
	List resultado = new LinkedList<Jugador>();
	
	Jugador jugaNauta;
	
	
	
	
	public ConsultaJugadoresPage() {
		
		formBusqueda.add(busqueda);
		formBusqueda.add(radioCriterioGroup);
//		formBusqueda.add(resultadoL);
//		formBusqueda.add(criterio);
		
		formBusqueda.add(new Button("buscar"){
			@Override
			public void onSubmit() {
				resultado.clear();
				formResultados.setVisible(true);
				String busquedaTxt = (String) busqueda.getModelObject();
				String criterioTxt = radioCriterioGroup.getDefaultModelObjectAsString();
				consultarYModelar(busquedaTxt,criterioTxt);
//				resultadoL.setDefaultModelObject(busquedaTxt);
//				criterio.setDefaultModelObject(criterioTxt);
			}
		});
		
		formResultados.setVisible(false);

		add(formBusqueda);
		add(formResultados);
		
		
		formResultados.add(new ListView<Jugador>("rows",resultado) {
			@Override
			protected void populateItem(ListItem<Jugador> item) {
				jugaNauta = item.getModelObject();
				item.add(new Label("item", item.getModelObject().toString()));
				item.add(new Link ("link",item.getModel()) {
					
					@Override
					public void onClick() {
						Jugador jugador = (Jugador) getModelObject();
						setResponsePage(new DetalleJugadorPage(jugador));
					}
				});
			}
		});
		
		
	}
	
	private void consultarYModelar(String busquedaTxt, String criterioTxt) {
		AbmClubService abmClub = (AbmClubService) ContextFactory.getInstancia().getBean("AbmClubService");
		AbmJugadorService abmJugador = (AbmJugadorService) ContextFactory.getInstancia().getBean("AbmJugadorService");
		
		
		if (criterioTxt.equals(POR_CLUB)){
			for (Iterator iterator = abmClub.buscarClubsPorNombre(busquedaTxt); iterator.hasNext();) {
				Club club = (Club) iterator.next();
				for (Iterator iterator2 = abmJugador.buscarJugadoresPorClub(club.getId()); iterator2.hasNext();) {
					Jugador jugador = (Jugador) iterator2.next();
					resultado.add(jugador);
				}
			}
		}else{
			for (Iterator iterator2 = abmJugador.buscarJugadoresPorNombre(busquedaTxt); iterator2.hasNext();) {
				Jugador jugador = (Jugador) iterator2.next();
				resultado.add(jugador);
			}
		}
	
	}
}

