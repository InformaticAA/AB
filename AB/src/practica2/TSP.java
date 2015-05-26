
package practica2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class TSP {

	public static void main(String[] args){
		int numVert = 4;
//		int varNumVert = combinaciones(numVert);
		int varNumVert = (int) Math.pow(2, numVert);
		
		int[][] matriz = new int[numVert][numVert];
		double[][] gtab = new double[numVert][varNumVert];
		ArrayList<Integer> visitados = new ArrayList<Integer>();
		HashSet<Integer> noVisitados = new HashSet<Integer>();
		Hashtable<Set<Integer>,Integer> codifSets = new Hashtable<Set<Integer>,Integer>();
		
		
		/* Inicializacion de variables */
		for (int i = 0; i < numVert; i++) {
			noVisitados.add(i);
		}
		
		for (int i = 0; i < gtab.length; i++) {
			for (int j = 0; j < gtab.length; j++) {
				gtab[i][j] = -1;
			}
		}
		
		matriz[0][0] = 0;
		matriz[0][1] = 10;
		matriz[0][2] = 15;
		matriz[0][3] = 20;
		
		matriz[1][0] = 5;
		matriz[1][1] = 0;
		matriz[1][2] = 9;
		matriz[1][3] = 10;
		
		matriz[2][0] = 6;
		matriz[2][1] = 13;
		matriz[2][2] = 0;
		matriz[2][3] = 12;
		
		matriz[3][0] = 8;
		matriz[3][1] = 8;
		matriz[3][2] = 9;
		matriz[3][3] = 0;
		
		/* Calcula los posibles caminos mediante fuerza bruta */
		System.out.println("Aplicando algoritmo de fuerza bruta...");
		System.out.println("Posibles caminos   -  Coste");
		System.out.println("===========================");
		double coste = FuerzaBruta.fuerzaBruta(matriz,visitados,0,0);
		System.out.println();
		System.out.println("El coste minimo obtenido es: " + coste);
		
		/* Calcula los posibles caminos mediante fuerza bruta */
		System.out.println();
		System.out.println("Aplicando algoritmo de programacion dinamica...");
		System.out.println("Posibles caminos   -  Coste");
		System.out.println("===========================");
		coste = ProgDinamica.progDinamica(matriz,gtab,codifSets,noVisitados,visitados,0,0);
		System.out.println();
		System.out.println("El coste minimo obtenido es: " + coste);
	}
	
//	/**
//	 * Devuelve el suamtorio de las combinaciones de numVert elementos
//	 * cogidos de n en n, siendo n = [1..numVert], sin repetir y el orden
//	 * no importa
//	 */
//	private static int combinaciones(int numVert) {
//		int sumatorio = 0;
//		
//		for (int i = 1; i <= numVert; i++) {
//			sumatorio += factorial(numVert) / ( factorial(i) * factorial(numVert - i) );
//		}
//		return sumatorio + 1;
//	}
//	
//	/**
//	 * Devuelve el factorial de un numero n
//	 */
//	private static int factorial(int n) {
//		if (n == 0) {
//			return 1;
//		} else {
//			return n * factorial(n-1);
//		}
//	}
}
