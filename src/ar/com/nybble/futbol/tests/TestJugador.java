package ar.com.nybble.futbol.tests;

import java.util.Date;

import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.TipoDeLesion;

public class TestJugador {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Jugador pablo = new Jugador(); 
		
		
		System.out.println(pablo.getEstado());
		System.out.println(pablo.getEstado().hashCode());
		
		pablo.notificarLesion(new Date(), TipoDeLesion.DISTENSION_FEMORAL_POSTERIOR);
		
			
		System.out.println(pablo.getEstado());
		System.out.println(pablo.getEstado().hashCode());
		
		pablo.recuperarActividad(new Date());
		
		System.out.println(pablo.getEstado());
		System.out.println(pablo.getEstado().hashCode());
		
		pablo.notificarLesion(new Date(), TipoDeLesion.DISTENSION_FEMORAL_POSTERIOR);
		
		System.out.println(pablo.getEstado());
		System.out.println(pablo.getEstado().hashCode());

	}

}
