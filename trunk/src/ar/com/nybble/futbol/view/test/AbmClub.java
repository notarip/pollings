package ar.com.nybble.futbol.view.test;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.RepeatingView;

import ar.com.nybble.futbol.CambioDeClub;
import ar.com.nybble.futbol.Club;

/**
 * @author Administrador
 */
public class AbmClub extends WebPage {
	Form form = new Form("form1");
	RepeatingView repeating = new RepeatingView("detalle");
	
	public AbmClub() {
		form.add(repeating);
		
		form.add(new Button("alta"){
			@Override
			public void onSubmit() {
				
			}
		});
		form.add(new Button("baja"){
			@Override
			public void onSubmit() {
				
			}
		});
		form.add(new Button("modificacion"){
			@Override
			public void onSubmit() {
				
			}
		});

		
		LinkedList<Club> clubs = new LinkedList<Club>();
		clubs.add(new Club("Boca"));
		clubs.add(new Club("River"));
		clubs.add(new Club("San Lorenzo"));
		
		for (Iterator iterator = clubs.iterator(); iterator.hasNext();) {
			Club club = (Club) iterator.next();
			WebMarkupContainer item = new WebMarkupContainer(repeating.newChildId());
			repeating.add(item);
			item.add(new Label("id","555"));
			item.add(new Label("nombre", club.getNombre()));
		}
		//dentro del ciclo

				
		add(form);
	
	}
	

}

