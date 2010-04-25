package ar.com.nybble.futbol.view;

import org.apache.wicket.protocol.http.WebApplication;

import ar.com.nybble.futbol.view.ConsultaClubs;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.Start1.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public WicketApplication()
	{
	}
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<ConsultaClubs> getHomePage()
	{
		return ConsultaClubs.class;
	}

}
