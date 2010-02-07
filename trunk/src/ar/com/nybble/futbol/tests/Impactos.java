package ar.com.nybble.futbol.tests;

import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.dataSource.util.HibernateUtil;

public class Impactos {

	/**
	 * @param args
	 */
	
	static String conf = new String("hibernate.cfg.xml");
	public static void main(String[] args) {
		try {
			HibernateUtil.currentSession(conf);
		} catch (DataSourceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HibernateUtil.openSession(conf);
		
			
		try {
			HibernateUtil.closeSession(conf);
			System.out.println("IMPACTOS SATIFACTORIOS");
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	}


