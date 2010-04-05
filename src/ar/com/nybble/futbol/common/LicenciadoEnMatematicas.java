/**
 * 
 */
package ar.com.nybble.futbol.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Partido;
import ar.com.nybble.futbol.TipoDeTorneo;

/**
 * @author notarip
 *
 */
public class LicenciadoEnMatematicas {
	
	
	
	private Club[] bolillero = null;
	private Collection<Partido> partidos = new ArrayList<Partido>();
	private Collection<Club> clubs = null;
	private int cantidad;
	
	
	public LicenciadoEnMatematicas() {
		
	}
	
	
	public Collection<Partido> combinarPartidos(Collection<Club> clubs, TipoDeTorneo tipoTorneo){
		cantidad = clubs.size();
		this.clubs = clubs;
		crearBolillero(cantidad);
		for (int i = 0; i < bolillero.length-1; i++) {
			for (int j = 0; j < bolillero.length/2; j++) {
				partidos.add(new Partido(bolillero[j],bolillero[cantidad -j-1],i+1));
			}
			
			for (int j = 0; j < bolillero.length-2; j++) {
				Club aux = bolillero[j];
				bolillero[j] = bolillero[j+1];
				bolillero[j+1] = aux;
			}
		}
		if (tipoTorneo == TipoDeTorneo.LIGA_DOBLE){
			Collection<Partido> partidos2 = new ArrayList<Partido>();
			for (Iterator iterator = partidos.iterator(); iterator.hasNext();) {
				Partido partido = (Partido) iterator.next();
				partidos2.add(new Partido(partido.getVisita(),partido.getLocal(),partido.getFecha()+cantidad-1));
			}
			for (Iterator iterator = partidos2.iterator(); iterator.hasNext();) {
				Partido partido = (Partido) iterator.next();
				partidos.add(partido);
			}
		}
			
		
		return partidos;
		
	}

	
	private void crearBolillero(int cantidad) {
		bolillero = new Club[cantidad];
		Random random = new Random();
		for (Iterator iterator = clubs.iterator(); iterator.hasNext();) {
			Club club = (Club) iterator.next();
			int posicion = random.nextInt(cantidad-1);
			if (bolillero[posicion] != null){
				posicion = 0; 
				while (bolillero[posicion] != null){
					posicion++;
				}
			}
			bolillero[posicion] = club;
		}
	}
}
