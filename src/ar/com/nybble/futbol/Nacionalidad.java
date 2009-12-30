package ar.com.nybble.futbol;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Pablo Notari
 * @category Value Object
 */

@Entity
public class Nacionalidad {
	
	
	private String nacionalidad;
	private long id;
	

	public Nacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Nacionalidad() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void setId(long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
		
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	
	public void setNacionalidad(String nacionalidad){
		this.nacionalidad = nacionalidad;
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
