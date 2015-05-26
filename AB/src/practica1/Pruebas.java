package practica1;

import java.util.ArrayList;

public class Pruebas {
	
	public static void main(String[] args){
		
		System.out.println("Ejecutando pruebas...");
		String mensaje = "Introduzca el numero de intervalos deseado: ";
		int numIntervalos = Plan.preguntar(mensaje);
		mensaje = "Introduzca el numero de pruebas que desee ejecutar: ";
		int numPruebas = Plan.preguntar(mensaje);
		boolean debug = Plan.preguntarDebug();
		
		int[] vecesOptimo = new int[4];
		for (int i = 0; i < numPruebas; i++) {
			int empezar,terminar,longitud,conflictos;
			ArrayList<Registro> l = Plan.generarIntervalos(numIntervalos);
			Plan.calcularConflictos(l,true);
			
			if(debug){
				System.out.println();
				System.out.println("Prueba " + (i+1) + ": Mostrando intervalos generados");
				System.out.println("===========================================");
				/* Muestra la lista original */
				Plan.mostrarIntervalos(l);
			}
			
			/* Muestra los intervalos seleccionados siguiendo el criterio de
			 * que primero vaya la tarea que empiece primero */
			ArrayList<Registro> l_ordenado = Mergesort.mergesort(l,"IZQUIERDA");
			ArrayList<Registro> sol = Plan.seleccionVoraz(l_ordenado);
			empezar = sol.size();
			
			if(debug){
				System.out.println();
				System.out.println("---Lista 1: Primero la tarea que empiece primero");
				System.out.println();
				Plan.mostrarIntervalos(sol);
				System.out.printf("%nNum intervalos = %d%n",empezar);
				System.out.printf("Utilizacion total = %d%n%n",Plan.calcularUtilizacion(sol));
			}
			
			/* Muestra los intervalos seleccionados siguiendo el criterio de
			 * que primero vaya la tarea que termine primero */
			l_ordenado = Mergesort.mergesort(l,"DERECHA");
			sol = Plan.seleccionVoraz(l_ordenado);
			terminar = sol.size();
			
			if(debug){
				System.out.println("---Lista 2: Primero la tarea que termine primero");
				System.out.println();
				Plan.mostrarIntervalos(sol);
				System.out.printf("%nNum intervalos = %d%n",terminar);
				System.out.printf("Utilizacion total = %d%n%n",Plan.calcularUtilizacion(sol));
			}
			/* Muestra los intervalos seleccionados siguiendo el criterio de
			 * que primero vaya la tarea mas corta */
			l_ordenado = Mergesort.mergesort(l,"LONGITUD");
			sol = Plan.seleccionVoraz(l_ordenado);
			longitud = sol.size();
			
			if(debug){
				System.out.println("---Lista 3: Primero la tarea mas corta");
				System.out.println();
				Plan.mostrarIntervalos(sol);
				System.out.printf("%nNum intervalos = %d%n",longitud);
				System.out.printf("Utilizacion total = %d%n%n",Plan.calcularUtilizacion(sol));
			}

			/* Muestra los intervalos seleccionados siguiendo el criterio de
			 * que primero va la tarea menos conflictiva */
			l_ordenado = Mergesort.mergesort(l,"CONFLICTOS");
			sol = Plan.seleccionVoraz(l_ordenado);
			conflictos = sol.size();

			if(debug){
				System.out.println("---Lista 4: Primero la tarea menos conflictiva");
				System.out.println();
				Plan.mostrarIntervalos(sol);
				System.out.printf("%nNum intervalos = %d%n",conflictos);
				System.out.printf("Utilizacion total = %d%n%n",Plan.calcularUtilizacion(sol));
			}
			
			int[] resultados = new int[4];
			resultados[0] = empezar;
			resultados[1] = terminar;
			resultados[2] = longitud;
			resultados[3] = conflictos;
			
			/* Eleccion del indice con mayor numero de intervalos */
			int imax = 0;
			for (int j = 1; j < resultados.length; j++) {
				if(resultados[imax] < resultados[j]){
					imax = j;
				}
			}
			
			/* Actualizar la tabla de veces que ha sido optimo */
			for (int j = 0; j < resultados.length; j++) {
				if(resultados[j] == resultados[imax]){
					vecesOptimo[j]++;
				}
			}

		}
		
		/* Mostrar las veces que ha sido optimo cada estrategia */
		System.out.println();
		System.out.println("Veces que ha sido optima cada estrategia");
		System.out.println("========================================");
		System.out.printf("Primero la tarea que empieza primero: %d/%d",vecesOptimo[0],numPruebas);
		if(vecesOptimo[0] == numPruebas){
			System.out.println(" -> OPTIMO ");
		}
		else{
			System.out.println();
		}
		System.out.printf("Primero la tarea que termina primero: %d/%d",vecesOptimo[1],numPruebas);
		if(vecesOptimo[1] == numPruebas){
			System.out.println(" -> OPTIMO ");
		}
		else{
			System.out.println();
		}
		System.out.printf("Primero la tarea mas corta: %d/%d",vecesOptimo[2],numPruebas);
		if(vecesOptimo[2] == numPruebas){
			System.out.println(" -> OPTIMO ");
		}
		else{
			System.out.println();
		}
		System.out.printf("Primero la tarea menos conflictiva: %d/%d",vecesOptimo[3],numPruebas);
		if(vecesOptimo[3] == numPruebas){
			System.out.println(" -> OPTIMO ");
		}
		else{
			System.out.println();
		}

		
	}
}
