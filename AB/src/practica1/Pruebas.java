package practica1;

import java.util.ArrayList;

public class Pruebas {
	
	public static void main(String[] args){
		
		System.out.println("Ejecutando pruebas...");
		String mensaje = "Introduzca el número de intervalos deseado: ";
		int numIntervalos = Plan.preguntar(mensaje);
		mensaje = "Introduzca el numero de pruebas que desee ejecutar";
		int numPruebas = Plan.preguntar(mensaje);
		int[] vecesSuperado = new int[4];
		for (int i = 0; i < numPruebas; i++) {
			int empezar,terminar,longitud,conflictos;
			ArrayList<Registro> l = Plan.generarIntervalos(numIntervalos);
			
			/* Muestra la lista original */
			
			/* Muestra los intervalos seleccionados siguiendo el criterio de
			 * que primero vaya la tarea que empiece primero */
			System.out.println("---Primero la tarea que empiece primero");
			System.out.println();
			ArrayList<Registro> l_ordenado = Mergesort.mergesort(l,"izquierda");
			ArrayList<Registro> sol = Plan.seleccionVoraz(l_ordenado);
			empezar = sol.size();
			
			/* Muestra los intervalos seleccionados siguiendo el criterio de
			 * que primero vaya la tarea que termine primero */
			System.out.println("---Primero la tarea que termine primero");
			System.out.println();
			l_ordenado = Mergesort.mergesort(l,"derecha");
			sol = Plan.seleccionVoraz(l_ordenado);
			terminar = sol.size();

			
			/* Muestra los intervalos seleccionados siguiendo el criterio de
			 * que primero vaya la tarea mas corta */
			System.out.println("---Primero la tarea mas corta");
			System.out.println();
			l_ordenado = Mergesort.mergesort(l,"longitud");
			sol = Plan.seleccionVoraz(l_ordenado);
			longitud = sol.size();

			
			/* Muestra los intervalos seleccionados siguiendo el criterio de
			 * que primero va la tarea menos conflictiva */
			System.out.println("---Primero la tarea menos conflictiva");
			System.out.println();
			l_ordenado = Mergesort.mergesort(l,"conflictos");
			sol = Plan.seleccionVoraz(l_ordenado);
			conflictos = sol.size();
			
			
		}
	}
}
