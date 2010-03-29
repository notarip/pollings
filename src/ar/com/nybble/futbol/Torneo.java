/**
 * 
 */
package ar.com.nybble.futbol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import ar.com.nybble.futbol.common.exceptions.ClubPertenecienteAlTorneoException;
import ar.com.nybble.futbol.common.exceptions.CupoDeClubsCompletoException;
import ar.com.nybble.futbol.common.exceptions.TorneoHabilitadoException;

/**
 * @author notarip
 *
 */
public class Torneo {
	
	private String nombre = null;
	private Integer cantidadClubs = null;
	private TipoDeTorneo tipoDeTorneo = null;
	private Date fechaDeCreacion = null;
	private Collection<Club> clubs = new ArrayList<Club>();
	private Date fechaDeHabilitacion = null;
	
	public Torneo() {
		// TODO Auto-generated constructor stub
	}

	public Torneo(String nombre, Integer cantidadClubs,TipoDeTorneo tipoDeTorneo, Date fecha) {
		this.nombre = nombre;
		this.cantidadClubs = cantidadClubs;
		this.tipoDeTorneo = tipoDeTorneo;
		this.fechaDeCreacion = fecha;
	}



	public String getNombre() {
		return this.nombre;
	}

	public Object getCantitdadClubs() {
		return this.cantidadClubs;
	}

	public Date getFecha() {
		return this.fechaDeCreacion;
	}

	public TipoDeTorneo getTipoDeTorneo() {
		return this.tipoDeTorneo;
	}

	public void cargarClub(Club club) {
		if (clubs.size() == this.cantidadClubs)
			throw new CupoDeClubsCompletoException();
		if (perteneceAlTorneo(club))
			throw new ClubPertenecienteAlTorneoException();
		clubs.add(club);
	}

	public boolean perteneceAlTorneo(Club club) {
		for (Iterator iterator = clubs.iterator(); iterator.hasNext();) {
			Club clubVar = (Club) iterator.next();
			if (clubVar.equals(club))
				return true;
		}
		return false;
	}

	public void habilitar(Date fecha2) {
		if (estaHabilitado())
			throw new TorneoHabilitadoException();
		this.fechaDeHabilitacion = fecha2;
	}

	public boolean estaHabilitado() {
		if (fechaDeHabilitacion != null)
			return true;
		else
			return false;
		
	}
}
