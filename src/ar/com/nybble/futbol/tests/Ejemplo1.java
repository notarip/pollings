/**
 * 
 */
package ar.com.nybble.futbol.tests;


import java.util.Date;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.Nacionalidad;
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
		
		JugadorRepositorioImpl jugaRepo = new JugadorRepositorioImpl();
		Jugador pablo = new Jugador(new Documento("29317973", TipoDeDocumento.DNI));
		pablo.agregarClub(new Club("BOCA JUNIORS"), new Date());
		pablo.setNombre("Pablo E. Notari");
		//pablo.setNacionalidad(new Nacionalidad("ARGENTINO"));
		
		
		try {
			HibernateUtil.beginTransaction();
			jugaRepo.create(pablo);
		} catch (DataSourceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession("hibernate.cfg.xml");
			System.out.println("la cerro");
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
