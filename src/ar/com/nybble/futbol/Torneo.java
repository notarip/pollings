/**
 * 
 */
package ar.com.nybble.futbol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.test.annotation.Timed;

import ar.com.nybble.futbol.common.LicenciadoEnMatematicas;
import ar.com.nybble.futbol.common.exceptions.CantidadDeClubsErroneaException;
import ar.com.nybble.futbol.common.exceptions.ClubNoPerteneceAlTorneoException;
import ar.com.nybble.futbol.common.exceptions.ClubPertenecienteAlTorneoException;
import ar.com.nybble.futbol.common.exceptions.CupoDeClubsCompletoException;
import ar.com.nybble.futbol.common.exceptions.NoSeAgregaronTodosLosClubsException;
import ar.com.nybble.futbol.common.exceptions.PartidosYaGeneradosException;
import ar.com.nybble.futbol.common.exceptions.TorneoHabilitadoException;
import ar.com.nybble.futbol.common.exceptions.TorneoNoHabilitadoException;

/**
 * @author notarip
 *
 */
@Entity
@Table(name = "Torneo") 
public class Torneo {
	
	private long Id;
	public static final int CLUBS_MAXIMO = 30;
	public static final int CLUBS_MINIMO = 2;
	private String nombre = null;
	private Integer cantidadClubs = null;
	private TipoDeTorneo tipoDeTorneo = null;
	private Date fechaDeCreacion = null;
	private Date fechaDeHabilitacion = null;
	private Collection<Club> clubs = new ArrayList<Club>();

	private Collection<Partido> partidos = null;
	
	
	public Torneo() {
		// TODO Auto-generated constructor stub
	}

	public Torneo(String nombre, Integer cantidadClubs,TipoDeTorneo tipoDeTorneo, Date fecha) {
		this.nombre = nombre;
		this.setCantidadClubs(cantidadClubs);
		this.tipoDeTorneo = tipoDeTorneo;
		this.fechaDeCreacion = fecha;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return Id;
	}
	
	@Column (name = "NOMBRE")
	public String getNombre() {
		return this.nombre;
	}

	@Column (name = "NCLUBS")
	public Integer getCantidadClubs() {
		return cantidadClubs;
	}	
	
	
	@Column (name = "TIPO")
	@Enumerated (EnumType.STRING)
	public TipoDeTorneo getTipoDeTorneo() {
		return this.tipoDeTorneo;
	}
	
	@Column (name = "CREACION")
	@Temporal(TemporalType.DATE)
	public Date getFechaDeCreacion() {
		return this.fechaDeCreacion;
	}

	@Column (name = "HABILITACION")
	@Temporal(TemporalType.DATE)
	public Date getFechaHabilitacion() {
		return this.fechaDeHabilitacion;
	}
	
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "torneo", fetch = FetchType.LAZY)
	public Collection<Partido> getPartidos() {
		return partidos;
	}
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Collection<Club> getClubs() {
		return clubs;
	}
	

	private void setId(long id) {
		Id = id;
	}

	public void cargarClub(Club club) {
		if (estaHabilitado())
			throw new TorneoHabilitadoException();
		if (cupoCompleto())
			throw new CupoDeClubsCompletoException();
		if (perteneceAlTorneo(club))
			throw new ClubPertenecienteAlTorneoException();
		clubs.add(club);
	}

	private boolean cupoCompleto() {
		return (clubs.size() == this.getCantidadClubs());
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
		if (!cantidadDeClubsValida(this.cantidadClubs))
			throw new CantidadDeClubsErroneaException();
		if (!cupoCompleto())
			throw new NoSeAgregaronTodosLosClubsException();
			
		this.fechaDeHabilitacion = fecha2;
		
		
	}

	private boolean cantidadDeClubsValida(Integer cantidadClubs2) {
		if (((cantidadClubs2 % 2) == 0) && ((cantidadClubs2 >= CLUBS_MINIMO) && (cantidadClubs2 <= CLUBS_MAXIMO)))
			return true;
		return false;
	}

	public boolean estaHabilitado() {
		if (fechaDeHabilitacion != null)
			return true;
		else
			return false;
		
	}


	public void setCantidadClubs(Integer cantidadClubs) {
		this.cantidadClubs = cantidadClubs;
	}


	public void sacarClub(Club club1) {
		if (estaHabilitado())
			throw new TorneoHabilitadoException();
		if (!perteneceAlTorneo(club1))
			throw new ClubNoPerteneceAlTorneoException();
		for (Iterator iterator = clubs.iterator(); iterator.hasNext();) {
			Club club = (Club) iterator.next();
			if (club.equals(club1))
				iterator.remove();
		}
	}

	public void generarPartidos() {
		if (!estaHabilitado())
			throw new TorneoNoHabilitadoException();
		partidos =  new LicenciadoEnMatematicas().combinarPartidos(clubs, this.tipoDeTorneo);

		
	}

	public boolean tienePartidos() {
		if  (partidos != null)
			return true;
		return false;
	}
	
	@Transient
	public Integer getCantidadDePartidos() {
		if (tienePartidos())
			return partidos.size();
		else
			return 0;
	}
	
	public void setTipoDeTorneo(TipoDeTorneo tipoDeTorneo) {
		if (tienePartidos())
			throw new PartidosYaGeneradosException();
		this.tipoDeTorneo = tipoDeTorneo;
	}
}
