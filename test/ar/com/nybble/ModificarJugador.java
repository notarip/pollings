/**
 * 
 */
package ar.com.nybble;


import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author notarip
 *
 */

public class ModificarJugador {
	
	Jugador pablo = new Jugador();
	Date fecha = new Date();
	Club club1 = new Club();
	TipoDeLesion lesion = TipoDeLesion.DISTENSIÓN_FEMORAL_POSTERIOR;
	
	
	/**
	 * Se agrega un club al jugador y queda como vigente
	 */
	@Test
	public void agregaClubFederado() {
		pablo.agregarClub(club1);
		assertTrue(pablo.getClubVigente() == club1);
	}
	
	
	/**
	 * Si un jugador no tiene asignado club
	 */
	@Test
	public void juagdorSinClub() {
		assertTrue(pablo.getClubVigente() == null);
	}
	
	
	/**
	 * Poner a un jugador en actividad.
	 */
	@Test
	public void iniciarActividadrProfesional() {
		pablo.iniciarActividadProfesional(fecha);
		assertTrue(pablo.enActividad() == true);
	}
	
	/**
	 * Guardar la fecha de inicio de actividad
	 */
	@Test
	public void guardarLaFechaDeInicioDeActividad() {
		pablo.iniciarActividadProfesional(fecha);
		assertEquals(fecha, pablo.getFechaEstadoActual());
	}
	

	/**
	 * Poner a un jugador fuera de actividad.
	 */
	@Test
	public void lesionarJugador() {
		pablo.notificarLesion(fecha,lesion);
		System.out.println(pablo.getEstado());
		assertTrue(!pablo.enActividad());
	}
		
	/**
	 * Asignar una lesión a un jugador 
	 */
	@Test
	public void asignarUnaLesionAUnJugador() {
		pablo.notificarLesion(fecha, lesion);
		assertEquals(lesion,pablo.getLesion());
	}
	
	/**
	 *TODO Si no esta lesionado que el get lesion devuelva null 
	 */
	@Test
	 public void siNoEstaLesionadoGetLesionDevuelvaNull() {
		 assertEquals(null,pablo.getLesion());
	}
	
	/**
	 * Si se lesiono y se recupero que get lesion devuelva null
	 */
	@Test
	public void siSeLesionoYRecuperoGetLesionDevuelvaNull() {
		pablo.notificarLesion(fecha, lesion);
		pablo.recuperarActividad(fecha);
		assertEquals(null,pablo.getLesion());
	}


	/**
	 *Que cuando recupere la actividad este activo 
	 */
	@Test
	public void queCuandoRecupereLaActividadEsteActivo() {
		pablo.notificarLesion(fecha, lesion);
		pablo.recuperarActividad(fecha);
		assertEquals(EstadosJugador.ACTIVO,pablo.getEstado());

	}
	
	/**
	 * que se guarde la fecha de la vuelta de una lesion
	 */
	@Test
	public void queSeGuardeFechaDeRecuperarActividad() {
		pablo.notificarLesion(fecha, lesion);
		pablo.recuperarActividad(fecha);
		assertEquals(fecha,pablo.getFechaEstadoActual());
	}
	
	/**
	 *Que no aplique recuperar actividad si no esta lesionado
	 */
	@Test (expected=JugadorSinLesionException.class)
	public void queNoRecupereLaActividadSiNoEstaLesionado() {
		pablo.recuperarActividad(fecha);
	}

	/**
	 *TODO Que cuando de lesione el estado sea lesionado 
	 */
	@Test
	public void queCuandoSeLesioneEstadoSeaLesionado() {
		pablo.notificarLesion(fecha, lesion);
		assertEquals(EstadosJugador.LESIONADO,pablo.getEstado());
	}

	/**
	 *Que cuando se lesione se guarde la fecha 
	 */
	@Test
	public void queCuandoSeLesioneGuardeLaFecha() {
		pablo.notificarLesion(fecha, lesion);
		assertEquals(fecha,pablo.getFechaEstadoActual());

	}
		
	/**
	 * Que cuando pase a actvidad el estado se activo
	 */
	@Test
	public void queCuandoPaseAActvidadEstadoSeaActivo() {
		pablo.iniciarActividadProfesional(fecha);
		assertEquals(EstadosJugador.ACTIVO,pablo.getEstado());

	}
	
	/**
	 *Que cuando se retire el estado sea retirado 
	 */
	@Test
	public void queCuandoSeRetireEstadoSeaRetirado() {
		pablo.colgarLosGuantes(fecha);
		assertEquals( EstadosJugador.RETIRADO,pablo.getEstado());
	}

	/**
	 * Que cuando se retire se guarde la fecha
	 */
	@Test
	public void queCuandoSeRetireGuardeLaFecha() {
		pablo.colgarLosGuantes(fecha);
		assertEquals(fecha, pablo.getFechaEstadoActual());
	}
	
	/**
	 * Que cuando se quede sin club el estado sea sin club
	 */
	@Test
	public void queCuandoSeQuedeSinClubEstadoSeaSinClub() {
		pablo.desvincularClub(fecha);
		assertEquals(EstadosJugador.SIN_CLUB, pablo.getEstado());
	}

	/**
	 * que para desvincularce de un club tenga uno asignado
	 */
	@Test (expected = JugadorSinClubException.class)
	public void queParaDesvincularceDeUnClubTengaUnoAsignado() {
		pablo.desvincularClub(fecha);
		pablo.desvincularClub(fecha);
	}
	
	/**
	 * Que cuando se quede sin club se guarde la fecha
	 */
	@Test
	public void queCuandoSeQuedeSinClubGuardeLaFecha() {
		pablo.desvincularClub(fecha);
		assertEquals(fecha, pablo.getFechaEstadoActual());
	}

	/**
	 *que cuando se le asigne un club recupere la actividad anterior
	 *a quedarse sin club
	 */
	@Test
	public void queCuandoSeLeAsigneClubRecupereLaActividad() {
		pablo.agregarClub(club1);
		assertTrue(pablo.enActividad());

	}
	

	//TODO Que si el jugador esta sin club solo se le pueda asignar club
	//TODO Cuando vuelve de una lesion que vuelva al estado anterior
	//TODO que cuando se le asigne un club no recupere la actividad si esta lesionado
	//TODO Agregar una posicion la que jugo

}
