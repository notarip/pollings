package ar.com.nybble.futbol;

import javax.persistence.Entity;
import javax.persistence.Id;

import ar.com.nybble.futbol.test.TipoDeDocumento;

/**
 * @author notarip
 *
 */
@Entity
public class Documento {

	
	
	
	private String numero;
	private TipoDeDocumento tipo;

	
	public Documento() {
		// TODO Auto-generated constructor stub
	}
	
	public Documento(String numero, TipoDeDocumento tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}

	@Id
	public String getNumero() {
		return numero;
	}
	
	public void setNumero (String numero){
		this.numero = numero;
	}

	public TipoDeDocumento getTipoDeDocumento() {
		return tipo;
	}
	
	public void setTipoDeDocumento (TipoDeDocumento tipo){
		this.tipo = tipo;
	}
	
	
	
	@Override
	public boolean equals(Object otroDoc) {
		if (this.getNumero().equals(((Documento) otroDoc).getNumero())	&& 
				this.getTipoDeDocumento().equals(((Documento) otroDoc).getTipoDeDocumento()))
			return true;
		else
			return false;
	}

	
	


}
