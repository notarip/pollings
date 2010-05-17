/**
 * 
 */
package ar.com.nybble.futbol.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.TipoDeTorneo;
import ar.com.nybble.futbol.Torneo;
import ar.com.nybble.futbol.common.exceptions.CantidadDeClubsErroneaException;
import ar.com.nybble.futbol.common.exceptions.ClubPertenecienteAlTorneoException;
import ar.com.nybble.futbol.common.exceptions.CupoDeClubsCompletoException;
import ar.com.nybble.futbol.common.exceptions.NoSeAgregaronTodosLosClubsException;
import ar.com.nybble.futbol.common.exceptions.TorneoHabilitadoException;

/**
 * @author notarip
 *
 */
public class AltaDeUnTorneo {
	
	private static final Integer CLUBS_MIN_MENOS2 = new Integer(Torneo.CLUBS_MINIMO - 2);
	private static final Integer CLUBS_MAX_MAS2 = new Integer(Torneo.CLUBS_MAXIMO + 2);
	String nombreTorneoAper = new String("APERTURA2010");
	Integer cantidadClubs = new Integer(2);
	TipoDeTorneo tipoDeTorneo = TipoDeTorneo.LIGA_SIMPLE;
	Date fecha = new Date();
	Torneo apertura2010 = new Torneo(nombreTorneoAper, cantidadClubs, tipoDeTorneo ,fecha);
	Club club1 = new Club("BOCA");
	Club club2 = new Club("RIVER");
	Club club3 = new Club("VELEZ");
	
	
//	@Before
//	public void setUp(){
//		apertura2010.cargarClub(club1);
//		apertura2010.cargarClub(club2);
//	}
	
	
	
	/**
	 * Dar de alta un torneo y que conserve el nombre
	 */
	@Test
	public void darDeAltaUnTorneoQueConserveElNombre() {
		assertTrue(apertura2010.getNombre()==nombreTorneoAper);
	}
	
	
	/**
	 * Dar de alta un torneo y la cantidad de clubs
	 */
	@Test
	public void darDeAltaUnTorneoLaCantidadDeClubs() {
		assertEquals(cantidadClubs,apertura2010.getCantidadClubs());		

	}
	
	/**
	 * Dar de alta un torneo y que conserve la fecha de alta
	 */
	@Test
	public void darDeAltaUnTorneoQueConserveLaFechaDeAlta() {
		assertEquals(fecha, apertura2010.getFechaDeCreacion());
	}
	

	/**
	 * Dar de alta un torneo y que conserve el tipo de torneo
	 */
	@Test
	public void darDeAltaUnTorneoQueConserveElTipoDeTorneo() {
		assertEquals(tipoDeTorneo, apertura2010.getTipoDeTorneo());
	}
	
		
	/**
	 * Que se puedan cargar los cubs
	 */
	@Test
	public void queSePuedanCargarLosCubs() {
		apertura2010.cargarClub(club1);
		assertTrue(apertura2010.perteneceAlTorneo(club1));
	
	}

	/**
	 * Que no se pueda cargar mas de una vez un club
	 */
	@Test (expected = ClubPertenecienteAlTorneoException.class)
	public void queNoSePuedaCargarMasDeUnaVezUnClub() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club1);
	}
	
	/**
	 * Que no se pueda agregar mas clubs de los que tiene el torneo
	 */
	@Test(expected = CupoDeClubsCompletoException.class)
	public void queNoSePuedaAgregarMasClubsDeLosQueTieneElTorneo() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
		apertura2010.cargarClub(club3);
	}
	
	/**
	 * Habilitar un torneo
	 */
	@Test
	public void habilitarUnTorneo() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
		apertura2010.habilitar(fecha);
		assertTrue(apertura2010.estaHabilitado());
	}

	/**
	 * Que no se pueda habilitar si ya esta habilitado
	 */
	@Test (expected = TorneoHabilitadoException.class)
	public void queNoSePuedaHabilitarSiYaEstaHabilitado() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
		apertura2010.habilitar(fecha);
		apertura2010.habilitar(fecha);
	}
	
	
	/**
	 * Que se guarde la fecha de habilitacion
	 */
	@Test
	public void queSeGuardeLaFechaDeHabilitacion() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
		apertura2010.habilitar(fecha);
		assertEquals(fecha, apertura2010.getFechaDeHabilitacion());
	}
	
	/**
	 * Que para habilitar un torneo la cantidad de clubs sea par
	 */
	@Test(expected = CantidadDeClubsErroneaException.class )
	public void queParaHabilitarUnTorneoLaCantidadDeClubsSeaPar() {
		apertura2010.setCantidadClubs(new Integer(5));
		apertura2010.habilitar(fecha);

	}

	/**
	 * Que para habilitar un torneo la cantidad de clubs sea
	 * menor al maximo permitido
	 */
	@Test(expected = CantidadDeClubsErroneaException.class )
	public void queParaHabilitarUnTorneoLaCantidadDeClubsSeaMenorAlMaximo() {
		apertura2010.setCantidadClubs(CLUBS_MAX_MAS2);
		apertura2010.habilitar(fecha);
	}

	/**
	 * Que para habilitar un torneo la cantidad de clubs sea
	 * mayor al minimo permitido
	 */
	@Test(expected = CantidadDeClubsErroneaException.class )
	public void queParaHabilitarUnTorneoLaCantidadDeClubsSeaMayorAlMinimo() {
		apertura2010.setCantidadClubs(CLUBS_MIN_MENOS2);
		apertura2010.habilitar(fecha);
	}

	/**
	 * Si estan cargados los clubs se pueda habilitar el torneo
	 */
	@Test
	public void siEstanCargadosLosClubsSePuedaHabilitarElTorneo() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
		apertura2010.habilitar(fecha);
		assertTrue(apertura2010.estaHabilitado());
	}

	/**
	 * Que para habilitar un torneo esten cargados todos los clubs
	 */
	@Test (expected = NoSeAgregaronTodosLosClubsException.class)
	public void queParaHabilitarUnTorneoEstenCargadosTodosLosClubs() {
		apertura2010.cargarClub(club1);
		apertura2010.habilitar(fecha);
	}
	
	/**
	 * Que no se puedan agregar clubs si el torneo esta habilitado
	 */
	@Test (expected = TorneoHabilitadoException.class)
	public void queNoSePuedanAgregarClubsSiElTorneoEstaHabilitado() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
		apertura2010.habilitar(fecha);
		apertura2010.cargarClub(club3);

	}
	
	/**
	 * Que se puedan sacar clubs del torneo si no esta habilitado
	 */
	@Test
	public void queSePuedanSacarClubsSiElTorneoNoEstaHabilitado() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
		apertura2010.sacarClub(club1);
		assertFalse(apertura2010.perteneceAlTorneo(club1));

	}
	
	/**
	 * Que no se pueda sacar un club si el torneo esta habilitado
	 */
	@Test (expected = TorneoHabilitadoException.class)
	public void queNoSePuedaSacarUnClubSiElTorneoEstaHabilitado() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
		apertura2010.habilitar(fecha);
		apertura2010.sacarClub(club1);
	}
	


}



