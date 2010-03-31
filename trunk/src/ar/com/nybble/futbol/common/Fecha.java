/**
 * 
 */
package ar.com.nybble.futbol.common;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author notarip
 *
 */
public class Fecha extends Date{
	
	public Fecha(int year, int month, int day) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(year, month, day);
		this.setTime(calendario.getTimeInMillis());
		
	}
	
	public Fecha() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		DateFormat formateador = DateFormat.getInstance();
		return formateador.format(this);
		
	}


}
