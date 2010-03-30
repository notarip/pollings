/**
 * 
 */
package ar.com.nybble.futbol.common.exceptions;

/**
 * @author notarip
 * Se lanza cuando se intenta habilitar un Torneo y la cantidad de clubs
 * no es la correcta. n = cantidad de clubs ==> 2<= n <= 30 con n par.
 *
 */
public class CantidadDeClubsErroneaException extends RuntimeException {

}
