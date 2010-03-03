package ar.com.nybble.futbol.view.test;

import java.text.MessageFormat;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.WebPage;


/**
 * Homepage
 * @param <T>
 */
public class HomePage<T> extends WebPage {

	private static final long serialVersionUID = 1L;
	private TextField<T> campo = new TextField<T>("input");
	private Label label = new Label("mensaje");

	
	public HomePage() {
		add(campo);
		add(label);
		add(new Button("boton"){
			@Override
			public void onSubmit() {
				label.setDefaultModelObject(campo.getDefaultModelObject());
				
			}
		});
	}
	
	
	

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
//    public HomePage(final PageParameters parameters) {
//
//		// Add the simplest type of label
//		// add(new Label("msg", "Si ud puede ver esto es que wicket se configuro exitosamente."));
//		//Button bt1 = new Button("msg");
//		//add(bt1);
//    	//add(new Field("usr"));
//        
//    }
}
