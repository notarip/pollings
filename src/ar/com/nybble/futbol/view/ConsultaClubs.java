package ar.com.nybble.futbol.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.common.ContextFactory;
import ar.com.nybble.futbol.services.AbmClubService;
import ar.com.nybble.futbol.services.AbmJugadorService;


/**
 * @author Administrador
 */
public class ConsultaClubs extends WebPage {
	
	
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
//	RepeatingView repeating = new RepeatingView("itemResultado");
	
	List resultado = new LinkedList<Jugador>();
	
	
	
	
	public ConsultaClubs() {
		
		formBusqueda.add(busqueda);
		formBusqueda.add(radioCriterioGroup);
		formBusqueda.add(resultadoL);
		formBusqueda.add(criterio);
		//formResultados.add(repeating);
		
		formBusqueda.add(new Button("buscar"){
			@Override
			public void onSubmit() {
				 
				String busquedaTxt = (String) busqueda.getModelObject();
				String criterioTxt = radioCriterioGroup.getDefaultModelObjectAsString();
				consultarYModelar(busquedaTxt,criterioTxt);
				resultadoL.setDefaultModelObject(busquedaTxt);
				criterio.setDefaultModelObject(criterioTxt);
			}
		});
		add(formBusqueda);
		add(formResultados);
		
		formResultados.add(new ListView<Jugador>("rows",resultado) {
			@Override
			protected void populateItem(ListItem<Jugador> item) {
				Jugador jugador = item.getModelObject();
				item.add(new Label("item", jugador.toString()));
			}
		});
		
		
	}
	
	private void consultarYModelar(String busquedaTxt, String criterioTxt) {
		AbmClubService abmClub = (AbmClubService) ContextFactory.getInstancia().getBean("AbmClubService");
		AbmJugadorService abmJugador = (AbmJugadorService) ContextFactory.getInstancia().getBean("AbmJugadorService");
		
		
		if (criterioTxt.equals(POR_CLUB)){
			for (Iterator iterator = abmClub.buscarClubsPorNombre(busquedaTxt); iterator.hasNext();) {
				Club club = (Club) iterator.next();
				System.out.println(club);
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
			
		

		
//		for (Iterator iterator = resultado.iterator(); iterator.hasNext();) {
//			Jugador jugador = (Jugador) iterator.next();
//			WebMarkupContainer item = new WebMarkupContainer(repeating.newChildId());
//			repeating.add(item);
//			item.add(new Label("item", jugador.toString()));
//			System.out.println(jugador.toString());
//		}
		
		
	}
	
	
	
	
	
}

