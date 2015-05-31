/*
 * Autores: Alejandro Marquez Ferrer - 566400
 * 			Alejandro Royo Amondarain - 560285
 * 
 * Descripcion: Este fichero contiene el codigo correspondiente a las
 * pruebas solicitadas por el guion de practicas. De esta forma, este
 * codigo permite ejecutar las siguientes pruebas:
 * 
 * 		- Algoritmo de fuerza bruta leyendo una matriz de fichero.
 * 		- Algoritmo de programacion dinamica leyendo una matriz de fichero.
 * 		- Pruebas automaticas con los dos algoritmos generando matrices aleatorias.
 */

package practica2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class tsp {

	static Registro[][] gtab;

	public static void main(String[] args){
		
		if ( args.length == 0 ) {
			Pruebas.main(args);
		}
		
		else if (args.length == 2) {
			
			long inicio = 0;
			long tiempo = 0;
			double coste = 0;
			Registro reg;
			
			String opcion = args[0];
			int[][] matriz = leerMatriz(args[1]);
			int numVert = matriz.length;
			int varNumVert = (int) Math.pow(2, numVert);
			gtab = new Registro[numVert][numVert * varNumVert];
			ArrayList<Integer> visitados = new ArrayList<Integer>();
			HashSet<Integer> noVisitados = new HashSet<Integer>();
			Hashtable<Set<Integer>,Integer> codifSets = new Hashtable<Set<Integer>,Integer>();
			
			/* Inicializacion de variables */
			for (int i = 0; i < numVert; i++) {
				noVisitados.add(i);
			}
			
			for (int i = 0; i < gtab.length; i++) {
				for (int j = 0; j < gtab[0].length; j++) {
					gtab[i][j] = new Registro(-1,"");
				}
			}
			
			
			if ( opcion.equals("-b") ) {
				
				/* Calcula los posibles caminos mediante fuerza bruta */
				System.out.println("Aplicando algoritmo de fuerza bruta...");
				System.out.println("Posibles caminos   -  Coste");
				System.out.println("===========================");
				FuerzaBruta.mejorCamino = "";
				FuerzaBruta.menorCoste = Double.POSITIVE_INFINITY;
				inicio = System.currentTimeMillis();
				coste = (int) FuerzaBruta.fuerzaBruta(matriz,visitados,0,0,true);
				tiempo = System.currentTimeMillis() - inicio;
				String camino = FuerzaBruta.mejorCamino;
				System.out.println();
				System.out.println("Camino mas corto: " + camino + "- Coste: " + coste);
				System.out.println();
				System.out.printf("Tiempo empleado: %d milisegundos%n",tiempo);
			}
			else if ( opcion.equals("-d") ) {
				
				/* Calcula los posibles caminos mediante fuerza bruta */
				System.out.println();
				System.out.println("Aplicando algoritmo de programacion dinamica...");
				System.out.println("Posibles caminos   -  Coste");
				System.out.println("===========================");
				visitados = new ArrayList<Integer>();
				ProgDinamica.contadorSet = 0;
				inicio = System.currentTimeMillis();
				reg = ProgDinamica.progDinamica(matriz,gtab,codifSets,noVisitados,visitados,0,0,true);
				tiempo = System.currentTimeMillis() - inicio;
				System.out.println();
				System.out.println("Camino mas corto: " + reg.getCamino() + " - Coste: " + reg.getCoste());
				System.out.println();
				System.out.printf("Tiempo empleado: %d milisegundos%n",tiempo);
			}
			else {
				System.out.println("Opcion " + args[0] + " invalida.");
				System.out.println("Formato correcto: tsp (-b|-d) <nombreFichero>");
			}
		}
		else {
			System.out.println("ERROR: numero de parametros incorrecto.");
			System.out.println("Formato correcto: tsp (-b|-d) <nombreFichero>");
		}
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
}
