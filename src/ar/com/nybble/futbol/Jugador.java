
package ar.com.nybble.futbol;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author notarip
 *
 */
public class Jugador {

	private String nombre;
	private Date fecha;
	private LinkedList<Club> clubs = new LinkedList<Club>();
	private LinkedList<Date> fechasDeCambioActividad = new LinkedList<Date>();
	private LinkedList<EstadosJugador> estados = new LinkedList<EstadosJugador>();
	private LinkedList<Date> fechasDeCambioClub = new LinkedList<Date>();
	private TipoDeLesion tipoDeLesion;
	private Nacionalidad nacionalidad;
	
	
	public Jugador() {
		estados.add(EstadosJugador.ACTIVO);
		fechasDeCambioActividad.add(new Date());
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	public void setNacimiento(Date fecha1) {
		this.fecha = fecha1;
	}

	public Date getFecha() {
		return fecha;
	}

	public void agregarClub(Club club1, Date fecha2) {
		clubs.add(club1);
		fechasDeCambioClub.add(fecha2);
		if (tipoDeLesion == null)
			this.estados.add(EstadosJugador.ACTIVO);
		else
			estados.add(EstadosJugador.LESIONADO);
	}

	public Club getClubVigente() {
		Iterator<Club> lista = clubs.iterator();
		if (!lista.hasNext()) {
			return null;
		}
		return lista.next();
	}

	public void iniciarActividadProfesional(Date fecha2) {
		fechasDeCambioActividad.add(fecha2);
		estados.add(EstadosJugador.ACTIVO);
	}

	public boolean enActividad() {
		if (getEstado() == EstadosJugador.ACTIVO){
			return true;
		}
		return false;
	}

	public void notificarLesion(Date fecha2, TipoDeLesion lesion) {
		fechasDeCambioActividad.add(fecha2);
		estados.add(EstadosJugador.LESIONADO);
		tipoDeLesion = lesion;
	}


	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public Object getLesion() {
		return tipoDeLesion;
	}

	public void recuperarActividad(Date fecha2) {
		if (getEstado() != EstadosJugador.LESIONADO)
			throw new JugadorSinLesionException();
		else{
			this.tipoDeLesion  = null;
			this.fechasDeCambioActividad.add(fecha2);
			this.estados.add(estados.get(estados.size()-2));
		}
	}

	public EstadosJugador getEstado() {
		return estados.peekLast();
	}

	public void colgarLosGuantes(Date fecha2) {
		this.estados.add(EstadosJugador.RETIRADO);
		this.fechasDeCambioActividad.add(fecha2);
	}

	public void desvincularClub(Date fecha2) throws JugadorSinClubException {
		if (getEstado() == EstadosJugador.SIN_CLUB)
			throw new JugadorSinClubException();
		else{
			this.estados.add(EstadosJugador.SIN_CLUB);
			this.fechasDeCambioActividad.add(fecha2);
		}
	}

	public Object getFechaEstadoActual() {
		return fechasDeCambioActividad.getLast();
	}

	public Object getFechaDeInicioClubActual() {
		return fechasDeCambioClub.peekLast();
	}
	
	
	
	
}
