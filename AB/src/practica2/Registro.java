/*
 * Autores: Alejandro Marquez Ferrer - 566400
 * 			Alejandro Royo Amondarain - 560285
 * 
 * Descripcion: Este fichero contiene el codigo correspondiente al objeto Registro.
 * 	En el, se definen sus atributos, su constructor, asi como funciones utiles.
 * 		
 */

package practica2;

public class Registro {
	
	private int coste;
	private String camino;
	
	/**
	 * Constructor del objeto Registro
	 */
	public Registro(int coste,String camino){
		this.coste = coste;
		this.camino= camino;
	}
	
	/**
	 * Introduce un coste en el Registro
	 */
	public void setCoste(int coste){
		this.coste = coste;
	}

	/**
	 * Devuelve el coste del Registro
	 */
	public int getCoste(){
		return this.coste;
	}
	
	/**
	 * Establece el camino del Registro
	 */
	public void setCamino(String camino){
		this.camino = camino;
	}
	
	/**
	 * Devuelve el camino del Registro
	 */
	public String getCamino(){
		return this.camino;
	}
}
