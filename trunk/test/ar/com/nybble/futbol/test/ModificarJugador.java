/**
 * 
 */
package ar.com.nybble.futbol.test;


import java.util.Date;

import org.junit.Test;

import ar.com.nybble.futbol.*;
import ar.com.nybble.futbol.common.exceptions.JugadorSinClubException;
import ar.com.nybble.futbol.common.exceptions.JugadorSinLesionException;
import static org.junit.Assert.*;

/**
 * @author notarip
 *
 */

public class ModificarJugador {
	
	Documento doc = new Documento("29317973",TipoDeDocumento.DNI);
	Jugador pablo = new Jugador(doc);
	Date fecha = new Date();
	Club club1 = new Club("Milan");
	TipoDeLesion lesion = TipoDeLesion.DISTENSIÓN_FEMORAL_POSTERIOR;
	Nacionalidad nacionalidad = new Nacionalidad("PERUANO");
	TipoEstadosJugador retirado = TipoEstadosJugador.RETIRADO;
	TipoEstadosJugador activo = TipoEstadosJugador.ACTIVO;
	TipoEstadosJugador lesionado = TipoEstadosJugador.LESIONADO;
	TipoEstadosJugador sinclub = TipoEstadosJugador.SIN_CLUB;
	
	
	
	/**
	 * Se agrega un club al jugador y queda como vigente
	 */
	@Test
	public void agregaClubFederado() {
		pablo.agregarClub(club1, fecha);
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
	 * Que guarde la nacionalidad
	 */
	@Test
	public void queGuardeLaNacionalidad() {
		pablo.setNacionalidad(nacionalidad);
		assertEquals(nacionalidad, pablo.getNacionalidad());
		
	}
	
	
	/**
	 * Que guarde el documento 
	 */
	@Test
	public void queGuardeElDocumento() {
		pablo.setDocumento(doc);
		assertEquals(doc, pablo.getDocumento());
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
	 * Si no esta lesionado que el get lesion devuelva null 
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
		assertEquals(activo,pablo.getEstado());

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
	 * Que cuando de lesione el estado sea lesionado 
	 */
	@Test
	public void queCuandoSeLesioneEstadoSeaLesionado() {
		pablo.notificarLesion(fecha, lesion);
		assertEquals(lesionado,pablo.getEstado());
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
		assertEquals(activo,pablo.getEstado());

	}
	
	/**
	 *Que cuando se retire el estado sea retirado 
	 */
	@Test
	public void queCuandoSeRetireEstadoSeaRetirado() {
		pablo.colgarLosGuantes(fecha);
		assertEquals(retirado,pablo.getEstado());
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
		pablo.agregarClub(club1, fecha);
		pablo.desvincularClub(fecha);
		assertEquals(sinclub, pablo.getEstado());
	}

	/**
	 * que para desvincularce de un club tenga uno asignado
	 */
	@Test (expected = JugadorSinClubException.class)
	public void queParaDesvincularceDeUnClubTengaUnoAsignado() {
		pablo.agregarClub(club1, fecha);
		pablo.desvincularClub(fecha);
		pablo.desvincularClub(fecha);
	}
	
	/**
	 * que cuando se quede sin club se guarde la fecha
	 */
	@Test
	public void queCuandoSeQuedeSinClubGuardeLaFecha() {
		pablo.agregarClub(club1, fecha);
		pablo.desvincularClub(fecha);
		assertEquals(fecha, pablo.getFechaEstadoActual());
	}

	/**
	 * que cuando se le asigne un club recupere la actividad anterior
	 * a quedarse sin club
	 */
	@Test
	public void queCuandoSeLeAsigneClubRecupereLaActividad() {
		pablo.agregarClub(club1, fecha);
		assertTrue(pablo.enActividad());

	}
	
	/**
	 * cuando vuelve de una lesion que vuelva al estado anterior
	 * 02/03/10 Deshabilitado por poderce lesionar un jugador mas 
	 * de una vez seguida 
	 */
	
	public void cuandoVuelveDeUnaLesionVuelvaAlEstadoAnterior() {
		pablo.colgarLosGuantes(fecha);
		assertEquals(retirado, pablo.getEstado());
		pablo.notificarLesion(fecha, lesion);
		pablo.recuperarActividad(fecha);
		assertEquals(retirado, pablo.getEstado());
	}

	/**
	 * que cuando se le asigne un club no recupere la actividad si esta lesionado
	 */
	@Test
	public void queCuandoAsigneClubNoRecupereActividadSiEstaLesionado() {
		pablo.notificarLesion(fecha, lesion);
		pablo.agregarClub(club1, fecha);
		assertEquals(lesionado, pablo.getEstado());
	}
	
	/**
	 * que guarde la fecha cuando cambia de club
	 */
	@Test
	public void queGuardeLaFechaCuandoCambiaDeClub() {
		pablo.agregarClub(club1,fecha);
		assertEquals(fecha, pablo.getFechaDeInicioClubActual());
	}
	

	//TODO que no pueda agregar un club null
	//TODO Agregar una posicion la que jugo

}
