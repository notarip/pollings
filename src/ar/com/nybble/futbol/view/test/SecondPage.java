package ar.com.nybble.futbol.view.test;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


/**
 * @author notarip
 */
public class SecondPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
	public SecondPage(final PageParameters parameters)  {
		IModel messageModel = new Model("Hello World!");
		
		add(new Label("message1", messageModel));
		add(new Label("message2", messageModel));
		add(new MessageForm("messageInputForm", messageModel));
		
	}	

	private final class MessageForm extends Form{
		
		public MessageForm(String id, IModel model){
			super(id);
			add(new TextField("messageInput", model));
		}

		protected void onSubmit(){
			//nothing to do here as the model is automatically updated
		}
		
		
}

}

