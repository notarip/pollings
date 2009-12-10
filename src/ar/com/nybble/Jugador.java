
package ar.com.nybble;

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
	private TipoDeLesion tipoDeLesion;
	
	
	
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

	public void agregarClub(Club club1) {
		clubs.add(club1);
		this.estados.add(EstadosJugador.ACTIVO);
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
		if (estados.peekLast() == EstadosJugador.ACTIVO){
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
		// TODO Auto-generated method stub
		
	}

	public Nacionalidad getNacionalidad() {
		return new Nacionalidad("Nigeriano");
	}

	public Object getLesion() {
		return tipoDeLesion;
	}

	public void recuperarActividad(Date fecha2) {
		if (estados.peekLast() != EstadosJugador.LESIONADO)
			throw new JugadorSinLesionException();
		else{
			this.tipoDeLesion  = null;
			this.fechasDeCambioActividad.add(fecha2);
			this.estados.add(EstadosJugador.ACTIVO);
		}
	}

	public Object getEstado() {
		return estados.peekLast();
	}

	public void colgarLosGuantes(Date fecha2) {
		this.estados.add(EstadosJugador.RETIRADO);
		this.fechasDeCambioActividad.add(fecha2);
	}

	public void desvincularClub(Date fecha2) throws JugadorSinClubException {
		if (estados.peekLast() == EstadosJugador.SIN_CLUB)
			throw new JugadorSinClubException();
		else{
			this.estados.add(EstadosJugador.SIN_CLUB);
			this.fechasDeCambioActividad.add(fecha2);
		}
	}

	public Object getFechaEstadoActual() {
		return fechasDeCambioActividad.pop();
	}
}
