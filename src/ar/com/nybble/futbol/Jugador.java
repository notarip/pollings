
package ar.com.nybble.futbol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.collection.PersistentBag;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.collection.PersistentList;



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
	private List<CambioDeClub> clubs = new LinkedList<CambioDeClub>();
	private List<CambioDeEstado> estados = new LinkedList<CambioDeEstado>();
	private TipoDeLesion tipoDeLesion;
	private Nacionalidad nacionalidad;
	
	
	
	public Jugador() {
		getEstados().add(new CambioDeEstado(TipoEstadosJugador.ACTIVO,new Date(), this));
	}
	
	public Jugador(Documento documento) {
		getEstados().add(new CambioDeEstado(TipoEstadosJugador.ACTIVO,new Date(), this));
		this.documento = documento;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void agregarClub(Club club1, Date fecha2) {
		CambioDeClub cambio = new CambioDeClub(club1,fecha2,this);
		this.getCambiosDeClub().add(cambio);
		if (tipoDeLesion == null)
			getEstados().add(new CambioDeEstado(TipoEstadosJugador.ACTIVO,fecha2, this));
		else
			getEstados().add(new CambioDeEstado(TipoEstadosJugador.LESIONADO,fecha2, this));
	}
	
	
	@Transient
	public Club getClubVigente() {
		List<CambioDeClub> cambios = getCambiosDeClub(); 
		if (!cambios.isEmpty())
			return ((List<CambioDeClub>)cambios).get(cambios.size()-1).getClub();
		else
			return null;
	}


	
	public void iniciarActividadProfesional(Date fecha2) {
		getEstados().add(new CambioDeEstado(TipoEstadosJugador.ACTIVO,fecha2, this));
	}

	@Transient
	public boolean enActividad() {
		if (getEstado() == TipoEstadosJugador.ACTIVO){
			return true;
		}
		return false;
	}

	public void notificarLesion(Date fecha2, TipoDeLesion lesion) {
		getEstados().add(new CambioDeEstado(TipoEstadosJugador.LESIONADO,fecha2, this));
		tipoDeLesion = lesion;
	}


	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@ManyToOne
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	@Enumerated (EnumType.STRING)
	public TipoDeLesion getLesion() {
		return tipoDeLesion;
	}

	public void setLesion (TipoDeLesion lesion){
		this.tipoDeLesion = lesion;
	}
	
	public void recuperarActividad(Date fecha2) {
		if (getEstado() != TipoEstadosJugador.LESIONADO)
			throw new JugadorSinLesionException();
		else{
			this.tipoDeLesion  = null;
			CambioDeEstado cambio =((List<CambioDeEstado>)this.getEstados()).get(getEstados().size()-2);
			CambioDeEstado cambio2 = new CambioDeEstado(cambio.getEstado(),fecha2,this);
			getEstados().add(cambio2);
			}
	}

	@Transient
	public TipoEstadosJugador getEstado() {
		List<CambioDeEstado> cambios = (List<CambioDeEstado>) getEstados();
		return cambios.get(cambios.size()-1).getEstado();
	}


	public void colgarLosGuantes(Date fecha2) {
		CambioDeEstado cambio = new CambioDeEstado(TipoEstadosJugador.RETIRADO,fecha2,this);
		getEstados().add(cambio);
	}

	public void desvincularClub(Date fecha2) throws JugadorSinClubException {
		if (getEstado() == TipoEstadosJugador.SIN_CLUB)
			throw new JugadorSinClubException();
		else{
			CambioDeEstado cambio = new CambioDeEstado(TipoEstadosJugador.SIN_CLUB,fecha2,this);
			getEstados().add(cambio);
		}
	}

	@Transient
	public Date getFechaEstadoActual() {
		List<CambioDeEstado> estados = getEstados();
		return estados.get(estados.size()-1).getFecha();
	}


	@Transient
	public Object getFechaDeInicioClubActual() {
		List<CambioDeClub> cambios = (List<CambioDeClub>)getCambiosDeClub(); 
		if (!cambios.isEmpty())
			return cambios.get(cambios.size()-1).getFecha();
		else
			return null;
	}

	@OneToOne
	public Documento getDocumento() {
		return this.documento;
	}

	@Override
	public void setDocumento(Documento documento) {
		this.documento = documento;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.documento.equals(((Jugador) obj).getDocumento()))
			return true;
		return false;
	}
	
	
	public void setCambiosDeClub(List<CambioDeClub> cambiosDeClub) {
		this.clubs = cambiosDeClub;
	}
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "jugador")
	public List<CambioDeClub> getCambiosDeClub() {
		return clubs;
	}


	public void setEstados(List<CambioDeEstado> estados) {
		this.estados = estados;
	}

	@OneToMany (cascade = CascadeType.ALL, mappedBy = "jugador")
	public List<CambioDeEstado> getEstados() {
		return estados;
	}

	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
