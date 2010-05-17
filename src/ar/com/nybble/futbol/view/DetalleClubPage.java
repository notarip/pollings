/**
 * 
 */
package ar.com.nybble.futbol.view;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

import ar.com.nybble.futbol.Club;

/**
 * @author notarip
 *
 */
public class DetalleClubPage extends TemplatePage {
	
	Form form = new Form("form");
	Label nombre = new Label("nombre", new Model(""));
	Club club = null;
	
		
	public DetalleClubPage() {
		construccionEnComun();
		nombre.setDefaultModelObject("club nulo");
	}
	
	public DetalleClubPage(Club club) {
		construccionEnComun();
		if (club == null)
			nombre.setDefaultModelObject("club nulo");
		else{
			this.club = club;
			nombre.setDefaultModelObject(club.toString());
		}
	}
	
	private void construccionEnComun() {
		this.form.add(nombre);
		add(form);
		
	}
	

}
