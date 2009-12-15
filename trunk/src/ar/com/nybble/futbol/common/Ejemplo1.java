/**
 * 
 */
package ar.com.nybble.futbol.common;


import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.dataSource.util.HibernateUtil;



/**
 * @author notarip
 *
 */
public final class Ejemplo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HibernateUtil session = new HibernateUtil();
		session.openSession("1");
		try {
			session.closeSession("1");
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
