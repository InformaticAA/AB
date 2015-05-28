/*
 * Autores: Alejandro Marquez Ferrer - 566400
 * 			Alejandro Royo Amondarain - 560285
 * 
 * Descripcion: Este fichero contiene el codigo correspondiente al objeto Registro.
 * 	En el, se definen sus atributos, su constructor, asi como funciones utiles.
 * 		
 */

package practica1;

public class Registro {
	
	private Intervalo intervalo;
	private int conflictos;
	
	/**
	 * Constructor del objeto Registro
	 */
	public Registro(Intervalo inter){
		this.intervalo = inter;
		this.conflictos = 0;
	}
	
	/**
	 * Introduce un intervalo en el Registro
	 */
	public void setIntervalo(Intervalo inter){
		this.intervalo = inter;
	}

	/**
	 * Devuelve el intervalo del Registro
	 */
	public Intervalo getIntervalo(){
		return this.intervalo;
	}
	
	/**
	 * Establece el numero de conflictos del Registro
	 */
	public void setConflictos(int conflictos){
		this.conflictos = conflictos;
	}
	
	/**
	 * Devuelve el numero de intervalos del registro
	 */
	public int getConflictos(){
		return this.conflictos;
	}
	
	/**
	 * Suma 1 al numero de conflictos del intervalo
	 */
	public void addConflicto(){
		this.conflictos++;
	}
}
