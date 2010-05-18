/**
 * 
 */
package ar.com.nybble.futbol.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.TipoDeTorneo;
import ar.com.nybble.futbol.Torneo;
import ar.com.nybble.futbol.common.Fecha;
import ar.com.nybble.futbol.common.exceptions.PartidosYaGeneradosException;
import ar.com.nybble.futbol.common.exceptions.TorneoNoHabilitadoException;

/**
 * @author notarip
 *
 */

public class AltaDePartidoTorneo {
	
	
	String nombreTorneoAper = new String("APERTURA2010");
	Integer cantidadClubs = new Integer(2);
	TipoDeTorneo LIGA_SIMPLE = TipoDeTorneo.LIGA_SIMPLE;
	TipoDeTorneo LIGA_DOBLE = TipoDeTorneo.LIGA_DOBLE;
	Fecha fecha2 = new Fecha(2010,Calendar.JANUARY,1);
	Fecha fecha = new Fecha();
	Torneo apertura2010 = new Torneo(nombreTorneoAper, cantidadClubs, LIGA_SIMPLE ,fecha);
	Club club1 = new Club("BOCA");
	Club club2 = new Club("RIVER");
	Club club3 = new Club("VELEZ");
	
	@Before
	public void setUp() {
		apertura2010.cargarClub(club1);
		apertura2010.cargarClub(club2);
	}
	
	
	/**
	 * que no se puedan generar los partidos si el torneo no esta habilitado
	 */
	@Test (expected = TorneoNoHabilitadoException.class)
	public void noSePuedenGenerarPartidosSiElTorneoNoEstaHabilitado() {
		apertura2010.generarPartidos();
	}
	
	/**
	 * que se puedan generar los partidos si el torneo esta habilitado
	 */
	@Test
	public void sePuedanGenerarPartidosSiElTorneoEstaHabilitado() {
		apertura2010.habilitar(fecha);
		apertura2010.generarPartidos();
		assertTrue(apertura2010.tienePartidos());

	}
	
	/**
	 * Si el torneo es Liga Simple que la cantidad de partidos sea CantClubs(CantClubs - 1)/2
	 */
	@Test
	public void siElTorneoEsLigaSimpleLaCantidadDePartidosEsCantClubs() {
		apertura2010.habilitar(fecha);
		apertura2010.generarPartidos();
		assertEquals(new Integer(cantidadClubs*(cantidadClubs-1)/2),apertura2010.getCantidadDePartidos());

	}

	/**
	 * Si el torneo es Liga Doble que la cantidad de partidos sea CantClubs(CantClubs - 1)
	 */
	@Test
	public void siElTorneoEsLigaDobleLaCantidadDePartidosEsCantClubs() {
		apertura2010.setTipoDeTorneo(LIGA_DOBLE);
		apertura2010.habilitar(fecha);
		apertura2010.generarPartidos();
		assertEquals(new Integer(cantidadClubs*(cantidadClubs-1)),apertura2010.getCantidadDePartidos());
		
	}
	
	/**
	 * Si el torneo ya genero los partidos que no deje setear el Tipo de Torneo
	 */
//	@Test(expected = PartidosYaGeneradosException.class)
//	public void siElTorneoYaGeneroLosPartidosQueNoDejeSetearElTipodetorneo() {
//		apertura2010.setTipoDeTorneo(LIGA_DOBLE);
//		apertura2010.habilitar(fecha);
//		apertura2010.generarPartidos();
//		apertura2010.setTipoDeTorneo(LIGA_SIMPLE);
//
//	}
	
}






