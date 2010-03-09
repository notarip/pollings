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
		
		pablo.notificarLesion(new Date("08/01/2010"), TipoDeLesion.DISTENSIÓN_FEMORAL_POSTERIOR);
		
			
		System.out.println(pablo.getEstado());
		
		pablo.recuperarActividad(new Date("09/02/2010"));
		
		System.out.println(pablo.getEstado());

	}

}
