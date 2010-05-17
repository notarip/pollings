package ar.com.nybble.futbol.tests;

import java.util.ArrayList;
import java.util.Iterator;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Partido;
import ar.com.nybble.futbol.TipoDeTorneo;
import ar.com.nybble.futbol.common.LicenciadoEnMatematicas;

public class PruebaMatematico {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LicenciadoEnMatematicas paenza = new LicenciadoEnMatematicas();
		ArrayList<Club> clubs = new ArrayList<Club>();
		clubs.add(new Club("club1"));
		clubs.add(new Club("club2"));
		clubs.add(new Club("club3"));
		clubs.add(new Club("club4"));
		clubs.add(new Club("club5"));
		clubs.add(new Club("club6"));
		
		
/*		ArrayList<Partido> pares = (ArrayList<Partido>) paenza.combinarPartidos(clubs,TipoDeTorneo.LIGA_DOBLE);
		for (Iterator iterator = pares.iterator(); iterator.hasNext();) {
			Partido par = (Partido) iterator.next();
			System.out.println(par);
		}
*/
	}

}
