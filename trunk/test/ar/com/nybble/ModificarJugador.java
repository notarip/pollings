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
	
	Jugador pablo = new Jugador();
	Fecha fecha = new Fecha("01/01/2000");
	
	
	/**
	 * Poner a un jugador en actividad.
	 */
	@Test
	public void IniciarActividadProfesional() {
		pablo.iniciarActividadProfesional(fecha);
		assertTrue(pablo.enActividad() == true);
	}
	/**
	 * Poner a un jugador fuera de actividad.
	 */
	@Test
	public void LesionarJugador() {
		pablo.notificarLesion(fecha,TipoDeLesion.FRACTURA_PERONE);
		assertTrue(!pablo.enActividad());
	}
	
	//Asignar una fecha de inicio de actividad a un jugador
	@Test
	public void AsignarFechaDeInicioDeActividad() {
		pablo.iniciarActividadProfesional(fecha);
		assertTrue(pablo.getFechaDeInicioDeActividad() == fecha);

	}
	
	//TODO Asignar una lesión a un jugador
	//TODO Asignar una fecha de lesión a un jugador
	//TODO Asignar una fecha de retiro a un jugador
	//TODO Asignar la vuelta de la lesión
	//TODO Asignar la fecha de la vuelta de la lesión

}
