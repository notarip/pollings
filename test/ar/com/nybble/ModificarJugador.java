/**
 * 
 */
package ar.com.nybble;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author notarip
 *
 */
public class ModificarJugador {
	
	@Test
	public void IniciarActividadProfesional() {
		Jugador pablo = new Jugador();
		Fecha fecha = new Fecha("01/01/2000");
		pablo.iniciarActividadProfesional(fecha);
		assertTrue(pablo.enActivudad() == true);
	}

}
