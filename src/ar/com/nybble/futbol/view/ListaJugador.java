package ar.com.nybble.futbol.view;

import java.util.Iterator;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer.AmbiguousBindingException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.services.AbmClubService;
import ar.com.nybble.futbol.services.AbmJugadorService;

/**
 * @author Administrador
 */
public class ListaJugador extends WebPage {
	
	/**
	 * Abstraer de esta clase
	 */
	ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
	AbmJugadorService abmJugador = (AbmJugadorService) context.getBean("AbmJugadorService");
	
	Form form = new Form("form1");
	RepeatingView repeating = new RepeatingView("detalle");
	
	
	public ListaJugador() {
		
		form.add(repeating);
		add(form);
		
		form.add(new Button("todos"){
			@Override
			public void onSubmit() {
				
				for (Iterator iterator = abmJugador.buscarJugadores(); iterator.hasNext();) {
					Jugador jugador = (Jugador) iterator.next();
					
					WebMarkupContainer item = new WebMarkupContainer(repeating.newChildId());
					repeating.add(item);
					item.add(new Label("id",String.valueOf(jugador.getId())));
					item.add(new Label("nombre", jugador.getNombre()));					
				}
			}
		});	
		form.add(new Button("porclub"){
			@Override
			public void onSubmit() {
				
				
//				for (Iterator iterator = abmJugador.buscarJugadores(); iterator.hasNext();) {
//					Jugador jugador = (Jugador) iterator.next();
//					
//					WebMarkupContainer item = new WebMarkupContainer(repeating.newChildId());
//					repeating.add(item);
//					item.add(new Label("id",String.valueOf(jugador.getId())));
//					item.add(new Label("nombre", jugador.getNombre()));					
//				}
			}
		});	
		
	}
	
	

}

