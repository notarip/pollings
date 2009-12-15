
package ar.com.nybble.futbol;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * @author notarip
 *
 */
@Entity
@Table(name = "Jugador")
public class Jugador implements Persona {

	
	private long Id;
	private String nombre;
	private Date fechaNacimiento;
	private Documento documento;
	private Collection<Club> clubs = new LinkedList<Club>();
	private Collection<Date> fechasDeCambioClub = new LinkedList<Date>();
	private Collection<EstadosJugador> estados = new LinkedList<EstadosJugador>();
	private Collection<Date> fechasDeCambioEstados = new LinkedList<Date>();
	private TipoDeLesion tipoDeLesion;
	private Nacionalidad nacionalidad;
	
	
	
	public Jugador() {
		getEstados().add(EstadosJugador.ACTIVO);
		getFechasDeCambioEstados().add(new Date());
	}
	
	private void setId(long id) {
		Id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return Id;
	}
	
	@Column (name = "NOMBRE")
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	@Override
	public void setFechaNacimiento(Date fecha1) {
		this.fechaNacimiento = fecha1;
	}

	@Temporal(value = TemporalType.DATE)
	public Date getFechaNacimieto() {
		return fechaNacimiento;
	}

	public void agregarClub(Club club1, Date fecha2) {
		getClubs().add(club1);
		getFechasDeCambioClub().add(fecha2);
		if (tipoDeLesion == null)
			this.getEstados().add(EstadosJugador.ACTIVO);
		else
			getEstados().add(EstadosJugador.LESIONADO);
	}
	
	
	@Transient
	public Club getClubVigente() {
		Iterator<Club> lista = getClubs().iterator();
		if (!lista.hasNext()) {
			return null;
		}
		return lista.next();
	}

	public void setClubs(LinkedList<Club> clubs) {
		this.clubs = clubs;
	}

	@ManyToMany 
	public Collection<Club> getClubs() {
		return  clubs;
	}

	public void setFechasDeCambioClub(LinkedList<Date> fechasDeCambioClub) {
		this.fechasDeCambioClub = fechasDeCambioClub;
	}

	@OneToMany
	public Collection<Date> getFechasDeCambioClub() {
		return fechasDeCambioClub;
	}

	public void iniciarActividadProfesional(Date fecha2) {
		getFechasDeCambioEstados().add(fecha2);
		getEstados().add(EstadosJugador.ACTIVO);
	}

	@Transient
	public boolean enActividad() {
		if (getEstado() == EstadosJugador.ACTIVO){
			return true;
		}
		return false;
	}

	public void notificarLesion(Date fecha2, TipoDeLesion lesion) {
		getFechasDeCambioEstados().add(fecha2);
		getEstados().add(EstadosJugador.LESIONADO);
		tipoDeLesion = lesion;
	}


	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@OneToOne
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	@Enumerated (EnumType.STRING)
	public Object getLesion() {
		return tipoDeLesion;
	}

	public void recuperarActividad(Date fecha2) {
		if (getEstado() != EstadosJugador.LESIONADO)
			throw new JugadorSinLesionException();
		else{
			this.tipoDeLesion  = null;
			this.getFechasDeCambioEstados().add(fecha2);
			this.getEstados().add(((LinkedList<EstadosJugador>) getEstados()).get(getEstados().size()-2));
		}
	}

	@Transient
	public EstadosJugador getEstado() {
		return ((LinkedList<EstadosJugador>) getEstados()).peekLast();
	}

	public void setEstados(LinkedList<EstadosJugador> estados) {
		this.estados = estados;
	}

	@OneToMany
	public Collection<EstadosJugador> getEstados() {
		return estados;
	}

	public void colgarLosGuantes(Date fecha2) {
		this.getEstados().add(EstadosJugador.RETIRADO);
		this.getFechasDeCambioEstados().add(fecha2);
	}

	public void desvincularClub(Date fecha2) throws JugadorSinClubException {
		if (getEstado() == EstadosJugador.SIN_CLUB)
			throw new JugadorSinClubException();
		else{
			this.getEstados().add(EstadosJugador.SIN_CLUB);
			this.getFechasDeCambioEstados().add(fecha2);
		}
	}

	@Transient
	public Object getFechaEstadoActual() {
		return ((LinkedList<Date>) getFechasDeCambioEstados()).getLast();
	}

	public void setFechasDeCambioEstados(LinkedList<Date> fechasDeCambioEstados) {
		this.fechasDeCambioEstados = fechasDeCambioEstados;
	}
	
	@OneToMany
	public Collection<Date> getFechasDeCambioEstados() {
		return fechasDeCambioEstados;
	}

	@Transient
	public Object getFechaDeInicioClubActual() {
		return ((LinkedList<Date>) getFechasDeCambioClub()).peekLast();
	}

	@OneToOne
	public Documento getDocumeto() {
		return this.documento;
	}

	@Override
	public void setDocumento(Documento documento) {
		this.documento = documento;
		
	}

	
}
