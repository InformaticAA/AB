/*
 * Autores: Alejandro Marquez Ferrer - 566400
 * 			Alejandro Royo Amondarain - 560285
 * 
 * Descripcion: Este fichero contiene el codigo correspondiente al objeto intervalo.
 * 	En el, se definen sus atributos, su constructor, asi como funciones utiles.
 * 		
 */
package practica1;

public class Intervalo {
	
	private int inicio;
	private int fin;
	private int length;
	
	/**
	 * Metodo constructor
	 */
	public Intervalo(int inicio, int fin){
		this.inicio = inicio;
		this.fin = fin;
		this.length = fin - inicio;
	}

	/**
	 * Devuelve el inicio del intervalo
	 */
	public int getInicio() {
		return inicio;
	}

	/**
	 * Establece el inicio del intervalo al pasado por parametro
	 */
	public void setInicio(int inicio) {
		this.inicio = inicio;
		this.length = this.fin - this.inicio;
	}

	/**
	 * Devuelve el fin del intervalo
	 */
	public int getFin() {
		return fin;
	}

	/**
	 * Establece el fin del intervalo al pasado por parametro
	 */
	public void setFin(int fin) {
		this.fin = fin;
		this.length = this.fin - this.inicio;
	}

	/**
	 * Devuelve la longitud del intervalo
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Devuelve el intervalo formateado correctamente como String
	 */
	public String toString() {
		return "[" + inicio + ", " + fin + "]";
	}
	
	/**
	 * Devuelve true si y solo si el intervalo pasado como parametro 
	 * y este no generan conflicto
	 */
	public boolean compatibles(Intervalo i2){
		return (this.inicio >= i2.getFin() || i2.getInicio() >= this.getFin());
	}
}
