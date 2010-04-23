package ar.com.nybble.futbol.view.test;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 * @author Administrador
 */
public class ConsultaClubs extends WebPage {

	private static final List<String> CRITERIOS = Arrays.asList(new String[] { "Por Club",
    "Por Jugador"});
	Form formBusqueda = new Form("formBusqueda");
	TextField busqueda = new TextField("busqueda", new Model(""));
	RadioChoice<String> radioCriterioGroup = new RadioChoice<String>("radioChoise", CRITERIOS);
//	Radio porJugador = new Radio("porJugador", new Model(""));
//	Radio porClub = new Radio("porClub", new Model(""));
	
	Label resultado = new Label("resultado", new Model(""));
	Label criterio = new Label("criterio", new Model(""));
	
	public ConsultaClubs() {
		
		formBusqueda.add(busqueda);
//		radioCriterioGroup.add(porJugador);
//		radioCriterioGroup.add(porClub);
		formBusqueda.add(radioCriterioGroup);
		formBusqueda.add(resultado);
		formBusqueda.add(criterio);
		
		formBusqueda.add(new Button("buscar"){
			@Override
			public void onSubmit() {
				String busquedaTxt = (String) busqueda.getModelObject();
				//String criterioTxt = radioCriterioGroup.;
				resultado.setDefaultModelObject(busquedaTxt);
				//criterio.setDefaultModelObject(criterioTxt);
				
			}
		});
		add(formBusqueda);
		
		
		
	}

}

