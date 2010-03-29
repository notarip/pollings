/**
 * 
 */
package ar.com.nybble.futbol.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.TipoDeTorneo;
import ar.com.nybble.futbol.Torneo;
import ar.com.nybble.futbol.common.exceptions.ClubPertenecienteAlTorneoException;
import ar.com.nybble.futbol.common.exceptions.CupoDeClubsCompletoException;
import ar.com.nybble.futbol.common.exceptions.TorneoHabilitadoException;

/**
 * @author notarip
 *
 */
public class AltaDeUnTorneo {
	
	String nombreTorneo = new String("APERTURA2010");
	Integer cantidadClubs = new Integer(2);
	TipoDeTorneo tipoDeTorneo = TipoDeTorneo.LIGA_SIMPLE;
	Date fecha = new Date();
	Torneo apertura2010 = new Torneo(nombreTorneo, cantidadClubs, tipoDeTorneo ,fecha);
	Club club1 = new Club("BOCA");
	Club club2 = new Club("RIVER");
	Club club3 = new Club("VELEZ");
	
	

	/**
	 * Dar de alta un torneo y que conserve el nombre
	 */
	@Test
	public void DarDeAltaUnTorneoQueConserveElNombre() {
		assertTrue(apertura2010.getNombre()==nombreTorneo);
	}
	
	
	/**
	 * Dar de alta un torneo y la cantidad de clubs
	 */
	@Test
	public void DarDeAltaUnTorneoLaCantidadDeClubs() {
		assertEquals(cantidadClubs,apertura2010.getCantitdadClubs());		

	}
	
	/**
	 * Dar de alta un torneo y que conserve la fecha de alta
	 */
	@Test
	public void DarDeAltaUnTorneoQueConserveLaFechaDeAlta() {
		assertEquals(fecha, apertura2010.getFecha());
	}
	

	/**
	 * Dar de alta un torneo y que conserve el tipo de torneo
	 */
	@Test
	public void DarDeAltaUnTorneoQueConserveElTipoDeTorneo() {
		assertEquals(tipoDeTorneo, apertura2010.getTipoDeTorneo());
	}
	
		
	/**
	 * Que se puedan cargar los cubs
	 */
	@Test
	public void QueSePuedanCargarLosCubs() {
		apertura2010.cargarClub(club1);
		assertTrue(apertura2010.perteneceAlTorneo(club1));
	
	}

	/**
	 * Que no se pueda cargar mas de una vez un club
	 */
	@Test (expected = ClubPertenecienteAlTorneoException.class)
	public void QueNoSePuedaCargarMasDeUnaVezUnClub() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club1);
	}
	
	/**
	 * Que no se pueda agregar mas clubs de los que tiene el torneo
	 */
	@Test(expected = CupoDeClubsCompletoException.class)
	public void QueNoSePuedaAgregarMasClubsDeLosQueTieneElTorneo() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
		apertura2010.cargarClub(club3);
	}
	
	/**
	 * Habilitar un torneo
	 */
	@Test
	public void HabilitarUnTorneo() {
		apertura2010.habilitar(fecha);
		assertTrue(apertura2010.estaHabilitado());
	}

	/**
	 * Que no se pueda habilitar si ya esta habilitado
	 */
	@Test (expected = TorneoHabilitadoException.class)
	public void QueNoSePuedaHabilitarSiYaEstaHabilitado() {
		apertura2010.habilitar(fecha);
		apertura2010.habilitar(fecha);
	}

}



//TODO: Que se guarde la fecha de habilitacion
//TODO: Que para habilitar un torneo esten cargados todos los clubs
//TODO: Que para habilitar un torneo la cantidad de clubs sea par
//TODO: Que para habilitar un torneo tenga 2 o mas clubs
