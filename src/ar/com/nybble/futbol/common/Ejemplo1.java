/**
 * 
 */
package ar.com.nybble.futbol.common;


import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.dataSource.repositorio.*;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.dataSource.util.HibernateUtil;
import ar.com.nybble.futbol.test.TipoDeDocumento;



/**
 * @author notarip
 *
 */
public final class Ejemplo1 {

	/**
	 * @param args
	 * @throws DataSourceException 
	 */
	public static void main(String[] args) throws DataSourceException {
		try {
			HibernateUtil.currentSession("hibernate.cfg.xml");
		} catch (DataSourceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HibernateUtil.openSession("hibernate.cfg.xml");
		
		//JugadorRepositorio jugRepo = new JugadorRepositorioImpl();
		//Jugador jug = new Jugador();
		ClubRepositorio clubRepo = new ClubRepositorioImpl();
		Club club = new Club("BOCA");
		
		try {
			clubRepo.create(club);
		} catch (DataSourceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
			
		try {
			HibernateUtil.closeSession("hibernate.cfg.xml");
			System.out.println("la cerro");
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
