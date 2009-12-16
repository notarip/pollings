/**
 * 
 */
package ar.com.nybble.futbol;

/**
 * @author notarip
 *
 */
public class Club {

	private String nombre;

	public Club(String string) {
		this.nombre = string;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.nombre == obj.toString())
			return true;
		return false;
	}

}
