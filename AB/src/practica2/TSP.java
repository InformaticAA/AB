
package practica2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class TSP {

	static double[][] gtab;
	private static int contadorSet;

	public static void main(String[] args){
		
		int[][] matriz = leerMatriz("5.txt");				//CAMBIAR POR ARGS[1] PARA SOMETER
		int numVert = matriz.length;
		int varNumVert = (int) Math.pow(2, numVert);
		gtab = new double[numVert][numVert * varNumVert];
		ArrayList<Integer> visitados = new ArrayList<Integer>();
		HashSet<Integer> noVisitados = new HashSet<Integer>();
		Hashtable<Set<Integer>,Integer> codifSets = new Hashtable<Set<Integer>,Integer>();
		
		/* Inicializacion de variables */
		for (int i = 0; i < numVert; i++) {
			noVisitados.add(i);
		}
		
		for (int i = 0; i < gtab.length; i++) {
			for (int j = 0; j < gtab[0].length; j++) {
				gtab[i][j] = -1;
			}
		}
		
		/* Calcula los posibles caminos mediante fuerza bruta */
		System.out.println("Aplicando algoritmo de fuerza bruta...");
		System.out.println("Posibles caminos   -  Coste");
		System.out.println("===========================");
		long inicio = System.currentTimeMillis();
		double coste = FuerzaBruta.fuerzaBruta(matriz,visitados,0,0,true);
		long tiempo = System.currentTimeMillis() - inicio;
		System.out.println();
		System.out.println("El coste minimo obtenido es: " + coste);
		System.out.println();
		System.out.printf("Tiempo empleado: %d milisegundos%n",tiempo);
		
		/* Calcula los posibles caminos mediante fuerza bruta */
		System.out.println();
		System.out.println("Aplicando algoritmo de programacion dinamica...");
		System.out.println("Posibles caminos   -  Coste");
		System.out.println("===========================");
		visitados = new ArrayList<Integer>();
		ProgDinamica.contadorSet = 0;
		inicio = System.currentTimeMillis();
		coste = ProgDinamica.progDinamica(matriz,gtab,codifSets,noVisitados,visitados,0,0,true);
		tiempo = System.currentTimeMillis() - inicio;
		System.out.println();
		System.out.println("El coste minimo obtenido es: " + coste);
		System.out.println();
		System.out.printf("Tiempo empleado: %d milisegundos%n",tiempo);

	}
	
	
	/**
	 * Lee del fichero con nombre pasado como parametro la matriz contenida en el.
	 * Para ello, en la primera linea debera aparecer el numero de vertices, y en
	 * las siguientes las distintas filas de la matriz separadas por un salto de
	 * linea (siguiendo el ejemplo de la matriz del guion de practicas)
	 */
	private static int[][] leerMatriz(String fichero){
		int[][] matriz = null;
		
		try{
			Scanner s = new Scanner(new File(fichero));
			int numVert = s.nextInt();
			matriz = new int[numVert][numVert];
			
			for (int i = 0; i < matriz.length; i++) {
				s.nextLine();
				for (int j = 0; j < matriz[0].length; j++) {
					matriz[i][j] = s.nextInt();
				}
			}
			s.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return matriz;
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
