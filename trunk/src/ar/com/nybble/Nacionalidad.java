package ar.com.nybble;

/**
 * @author Pablo Notari
 * @category Value Object
 */
public class Nacionalidad {
	
	
	public final String nacionalidad;

	public Nacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	
	
	@Override
	public boolean equals(Object otraNacionalidad) {
		Nacionalidad nacionalidad2 = (Nacionalidad)otraNacionalidad;
		return (this.nacionalidad == nacionalidad2.nacionalidad);
	}
	
	

}
