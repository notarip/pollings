package ar.com.nybble.futbol;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Pablo Notari
 * @category Value Object
 */

@Entity
public class Nacionalidad {
	
	
	public final String nacionalidad;

	public Nacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	@Id
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	
	@Override
	public boolean equals(Object otraNacionalidad) {
		Nacionalidad nacionalidad2 = (Nacionalidad)otraNacionalidad;
		return (this.nacionalidad == nacionalidad2.nacionalidad);
	}
	
	@Override
	public String toString() {
		return this.nacionalidad;
	}
	

}
