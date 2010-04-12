package ar.com.nybble.futbol.view.test;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;


/**
 * Homepage
 * 
 */
public class HomePage extends WebPage {

	private Label label;
	private TextField field;
	
	public HomePage() {
	Form form = new Form("form");
	field = new TextField("field", new Model(""));
	form.add(field);
	form.add(new Button("button") {
		@Override
		public void onSubmit() {
			String value = (String)field.getModelObject();
			label.setDefaultModelObject(value);
			field.setModelObject("");
		}
	});
	
	add(form);
	add(label = new Label("message", new Model("")));
	}
}
