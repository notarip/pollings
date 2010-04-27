package ar.com.nybble.futbol.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
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
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
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
 * @author notarip
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

	
	List resultado = new LinkedList<Jugador>();
	
	Jugador jugaNauta;
	
	
	
	
	public ConsultaClubs() {
		
		formBusqueda.add(busqueda);
		formBusqueda.add(radioCriterioGroup);
		formBusqueda.add(resultadoL);
		formBusqueda.add(criterio);
		
		formBusqueda.add(new Button("buscar"){
			@Override
			public void onSubmit() {
				resultado.clear(); 
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
				jugaNauta = item.getModelObject();
				item.add(new Label("item", item.getModelObject().toString()));
				item.add(new PageLink<DetalleJugador>("link", new IPageLink(){

					@Override
					public Page getPage() {
						return new DetalleJugador(jugaNauta);
					}

					@Override
					public Class<? extends Page> getPageIdentity() {
						return DetalleJugador.class;
					}
				
				}));
				
//				item.add(new Link ("link") {
//					@Override
//					public void onClick() {
//						setResponsePage(new DetalleJugador());	
//					}
//				});
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

