/**
 * 
 */
package ar.com.nybble.futbol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ar.com.nybble.futbol.common.exceptions.JugadorNoEsDelClubException;
import ar.com.nybble.futbol.common.exceptions.JugadorYaFichadoPorElClubException;

/**
 * @author notarip
 *
 */
@Entity
public class Club {

	private String nombre;
	private long Id;
	private Collection<Jugador> jugadores = new ArrayList<Jugador>();
	private Collection<Torneo> torneos = new ArrayList<Torneo>();

	
	public Club() {
		// TODO Auto-generated constructor stub
	}
	
	public Club(String string) {
		this.setNombre(string);
	}

	public void setId(long id) {
		Id = id;
	}
	
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	public long getId() {
		return Id;
	}
	
	@ManyToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	//se ignora el mappedBy por que lo resuelve el otro lado de la relacion
	public Collection<Torneo> getTorneos() {
		return torneos;
	}
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "club", fetch = FetchType.LAZY)
	public Collection<Jugador> getJugadores() {
		return jugadores;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getNombre() == obj.toString())
			return true;
		return false;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void ficharJugador(Jugador jugador) {
		if (this.perteneceAlClub(jugador))
			throw new JugadorYaFichadoPorElClubException();
		jugadores.add(jugador);
	}
 

	public boolean perteneceAlClub(Jugador jugain) {
		for (Iterator iterator = jugadores.iterator(); iterator.hasNext();) {
			Jugador juga = (Jugador) iterator.next();
			if (jugain.equals(juga))
				return true;
		}
		return false;
	}

	public void rescindirContratoJugador(Jugador pablo) {
		if (!perteneceAlClub(pablo))
			throw new JugadorNoEsDelClubException();
		jugadores.remove(pablo);
	}

	
	public void setJugadores(Collection<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void agregarTorneo(Torneo torneo){
		this.torneos.add(torneo);
	}
	
	
	
	public void setTorneos(Collection<Torneo> torneos) {
		this.torneos = torneos;
	}
}
