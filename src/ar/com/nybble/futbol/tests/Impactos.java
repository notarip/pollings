package ar.com.nybble.futbol.tests;

import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.dataSource.util.HibernateUtil;

public class Impactos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HibernateUtil.currentSession("hibernate.cfg.xml");
		} catch (DataSourceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HibernateUtil.openSession("hibernate.cfg.xml");
		
			
		try {
			HibernateUtil.closeSession("hibernate.cfg.xml");
			System.out.println("IMPACTOS SATIFACTORIOS");
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	}


