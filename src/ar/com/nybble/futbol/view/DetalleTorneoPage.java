package ar.com.nybble.futbol.view;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

import ar.com.nybble.futbol.Torneo;

/**
 * @author notarip
 */
public class DetalleTorneoPage extends TemplatePage {
	
	Form form = new Form("form");
	Label nombre = new Label("nombre", new Model(""));
	Torneo torneo = null;
	
		
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
		}
	}
	
	private void construccionEnComun() {
		this.form.add(nombre);
		add(form);
		
	}

}

