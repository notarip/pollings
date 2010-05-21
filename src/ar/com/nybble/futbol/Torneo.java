/**
 * 
 */
package ar.com.nybble.futbol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ar.com.nybble.futbol.common.Fecha;
import ar.com.nybble.futbol.common.exceptions.CantidadDeClubsErroneaException;
import ar.com.nybble.futbol.common.exceptions.ClubNoPerteneceAlTorneoException;
import ar.com.nybble.futbol.common.exceptions.ClubPertenecienteAlTorneoException;
import ar.com.nybble.futbol.common.exceptions.CupoDeClubsCompletoException;
import ar.com.nybble.futbol.common.exceptions.NoSeAgregaronTodosLosClubsException;
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
		this.setNombre(nombre);
		this.setCantidadClubs(cantidadClubs);
		this.setTipoDeTorneo(tipoDeTorneo);
		this.setFechaDeCreacion(fecha);
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
	
	@Temporal(TemporalType.DATE)
	public Date getFechaDeCreacion() {
		return this.fechaDeCreacion;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaDeHabilitacion() {
		return this.fechaDeHabilitacion;
	}
	
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "torneo", fetch = FetchType.LAZY)
	public Collection<Partido> getPartidos() {
		return partidos;
	}
	
	@ManyToMany (cascade = CascadeType.ALL, mappedBy = "torneos", fetch = FetchType.LAZY)
	public Collection<Club> getClubs() {
		return clubs;
	}
	

	public void setId(long id) {
		Id = id;
	}
	
	private void setClubs(Collection<Club> clubs) {
		this.clubs = clubs;
	}
	
	private void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}
	
	private void setFechaDeHabilitacion(Date fechaDeHabilitacion) {
		this.fechaDeHabilitacion = fechaDeHabilitacion;
	}
	
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private void setPartidos(Collection<Partido> partidos) {
		this.partidos = partidos;
	}
	
	

	public void cargarClub(Club club) {
		if (estaHabilitado())
			throw new TorneoHabilitadoException();
		if (cupoCompleto())
			throw new CupoDeClubsCompletoException();
		if (perteneceAlTorneo(club))
			throw new ClubPertenecienteAlTorneoException();
		clubs.add(club);
		club.agregarTorneo(this);
		
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
		this.setPartidos(new LicenciadoEnMatematicas().combinarPartidos(clubs, this.tipoDeTorneo, this));

		
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
//		if (this.getTipoDeTorneo() != null && tienePartidos())
//			throw new PartidosYaGeneradosException();
		this.tipoDeTorneo = tipoDeTorneo;
	}
	
	

	@Override
	public String toString() {
		return this.getNombre();
	}
	
/******************************************************************/
/*********************CLASE INTERNA*******************************/
	
	private static class LicenciadoEnMatematicas {
		
		
		
		private Club[] bolillero = null;
		private Collection<Partido> partidos = new ArrayList<Partido>();
		private Collection<Club> clubs = null;
		private int cantidad;
		private Torneo torneo;
		private LicenciadoEnMatematicas instancia;
	


		public LicenciadoEnMatematicas() {
			instancia = this;
		}
		
		public LicenciadoEnMatematicas getInstancia() {
			return instancia;
		}
		
		public Collection<Partido> combinarPartidos(Collection<Club> clubs, TipoDeTorneo tipoTorneo, Torneo torneo){
			cantidad = clubs.size();
			this.clubs = clubs;
			this.torneo = torneo;
			crearBolillero(cantidad);
			for (int i = 0; i < bolillero.length-1; i++) {
				for (int j = 0; j < bolillero.length/2; j++) {
					partidos.add(new Partido(bolillero[j],bolillero[cantidad -j-1],new Fecha(2010, 1, i+1), torneo));
				}
				
				for (int j = 0; j < bolillero.length-2; j++) {
					Club aux = bolillero[j];
					bolillero[j] = bolillero[j+1];
					bolillero[j+1] = aux;
				}
			}
			if (tipoTorneo == TipoDeTorneo.LIGA_DOBLE){
				Collection<Partido> partidos2 = new ArrayList<Partido>();
				for (Iterator iterator = partidos.iterator(); iterator.hasNext();) {
					Partido partido = (Partido) iterator.next();
					partidos2.add(new Partido(partido.getVisita(),partido.getLocal(), new Fecha (2010,1,cantidad-1), torneo));
				}
				for (Iterator iterator = partidos2.iterator(); iterator.hasNext();) {
					Partido partido = (Partido) iterator.next();
					partidos.add(partido);
				}
			}
				
			
			return partidos;
			
		}

		
		private void crearBolillero(int cantidad) {
			bolillero = new Club[cantidad];
			Random random = new Random();
			for (Iterator iterator = clubs.iterator(); iterator.hasNext();) {
				Club club = (Club) iterator.next();
				int posicion = random.nextInt(cantidad-1);
				if (bolillero[posicion] != null){
					posicion = 0; 
					while (bolillero[posicion] != null){
						posicion++;
					}
				}
				bolillero[posicion] = club;
			}
		}
	}
/*********************FIN CLASE INTERNA*******************************/	
/******************************************************************/
	
	
	
	
}
