package ar.com.nybble.futbol.view.test;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.RepeatingView;


/**
 * @author notarip
 *
 */
public class AbmClub extends WebPage {
	Form form = new Form("form1");
	RepeatingView repeating = new RepeatingView("detalle");
	
	
	public AbmClub() {
		form.add(repeating);
		add(form);
		
		form.add(new Button("alta"){
			@Override
			public void onSubmit() {
				WebMarkupContainer item = new WebMarkupContainer(repeating.newChildId());
				repeating.add(item);
				item.add(new Label("id","INS"));
				item.add(new Label("nombre", "Alta"));				
				
			}
		});
		form.add(new Button("baja"){
			@Override
			public void onSubmit() {
				WebMarkupContainer item = new WebMarkupContainer(repeating.newChildId());
				repeating.add(item);
				item.add(new Label("id","DEL"));
				item.add(new Label("nombre", "Baja"));				
				
			}
		});
		form.add(new Button("modificacion"){
			@Override
			public void onSubmit() {
				WebMarkupContainer item = new WebMarkupContainer(repeating.newChildId());
				repeating.add(item);
				repeating.add(item);
				item.add(new Label("id","MOD"));
				item.add(new Label("nombre", "Modificar"));
				
			}
		});


	}
	

}

