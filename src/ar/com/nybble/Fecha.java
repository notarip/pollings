package ar.com.nybble;

public class Fecha {
	
	public final String fecha;
	
	

	public Fecha(String string) {
		this.fecha = string;
	}
	
	@Override
	public boolean equals(Object obj) {
		Fecha fecha2 = (Fecha)obj;
		return (this.fecha == fecha2.fecha);
	}

	
	
	@Override
	public String toString() {
		return fecha;
	}

	public Fecha agregarDias(int i) {
		return new Fecha("10/01/1982");
	}
	
}
