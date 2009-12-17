/**
 * 
 */
package ar.com.nybble.futbol;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author notarip
 *
 */
@Entity
public class Club {

	private String nombre;
	private long Id;

	public Club(String string) {
		this.nombre = string;
	}

	public void setId(long id) {
		Id = id;
	}
	
	@Id
	public long getId() {
		return Id;
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
