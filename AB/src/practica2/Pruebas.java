/*
 * Autores: Alejandro Marquez Ferrer - 566400
 * 			Alejandro Royo Amondarain - 560285
 * 
 * Descripcion: Este fichero contiene el codigo correspondiente a la bateria de
 * pruebas para comparar la eficiencia entre dos soluciones diferentes al
 * problema del viajante de comercio. Estas son:
 * 
 * 		- Fuerza bruta.
 * 		- Programacion dinamica.
 * 
 * El programa pregunta al usuario tres opciones:
 * 		- Numero de pruebas a realizar (se obtiene la media en tiempo).
 * 		- Numero de vertices de la matriz a generar.
 * 		- Opcion de debug para mostrar las soluciones por pantalla.
 */

package practica2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Pruebas {
	
	static Registro[][] gtab;
	
	public static void main(String[] args){
		int numPruebas = preguntar("Numero de pruebas a ejecutar: ");
		int dimension = preguntar("Numero de vertices: ");
		boolean debug = preguntarDebug();
		long tiempoFB = 0;
		long tiempoPD = 0;
		
		System.out.println("Ejecutando pruebas...");
		for (int i = 0; i < numPruebas; i++) {
			
			if(debug){
				System.out.println();
				System.out.println("Generando Matriz aleatoria...");
				System.out.println("Matriz generada.");
				System.out.println("=============================");
				System.out.println();
			}
			int[][] matriz = generarMatrizRandom(dimension,debug);
			if(debug){
				System.out.println("Ejecutando por fuerza bruta...");
				System.out.println();
				System.out.println("Posibles caminos   -  Coste");
				System.out.println("===========================");
			}
			ArrayList<Integer> visitados = new ArrayList<Integer>();
			FuerzaBruta.mejorCamino = "";
			FuerzaBruta.menorCoste = Double.POSITIVE_INFINITY;
			long inicio = System.currentTimeMillis();
			double coste = FuerzaBruta.fuerzaBruta(matriz,visitados,0,0,debug);
			String camino = FuerzaBruta.mejorCamino;
			long tiempo = System.currentTimeMillis() - inicio;
			tiempoFB = tiempoFB + tiempo;
			if(debug){
				System.out.println();
				System.out.println("Camino más corto: " + camino + "- Coste: " + coste);
				System.out.println();
				System.out.printf("Tiempo empleado: %d milisegundos%n",tiempo);
			}
			
			if(debug){
				System.out.println("Ejecutando por programacion dinamica...");
				System.out.println();
				System.out.println("Posibles caminos   -  Coste");
				System.out.println("===========================");
			}
			visitados = new ArrayList<Integer>();
			gtab = new Registro[dimension][(int)Math.pow(2,dimension)];
			ProgDinamica.contadorSet = 0;
			HashSet<Integer> noVisitados = new HashSet<Integer>();
			Hashtable<Set<Integer>,Integer> codifSets = new Hashtable<Set<Integer>,Integer>();
			
			/* Inicializacion de variables */
			for (int j = 0; j < dimension; j++) {
				noVisitados.add(j);
			}
			
			for (int j = 0; j < gtab.length; j++) {
				for (int z = 0; z< gtab[0].length; z++) {
					gtab[j][z] = new Registro(-1,"");
				}
			}
			inicio = System.currentTimeMillis();
			Registro reg = ProgDinamica.progDinamica(matriz, gtab, codifSets, noVisitados, visitados, 0, 0,debug);
			tiempo = System.currentTimeMillis() - inicio;
			tiempoPD = tiempoPD + tiempo;
			if(debug){
				System.out.println();
				System.out.println("Camino más corto: " + reg.getCamino() + " - Coste: " + reg.getCoste());
				System.out.println();
				System.out.printf("Tiempo empleado: %d milisegundos%n",tiempo);
			}
		}
		System.out.println();
		System.out.println("===========================================");
		System.out.println();
		System.out.printf("Tiempo total fuerza bruta: %d milisegundos%n",tiempoFB);
		System.out.printf("Media: %.2f milisegundos%n",((double)tiempoFB/numPruebas));
		System.out.println();
		System.out.printf("Tiempo total programacion dinamica: %d milisegundos%n",tiempoPD);
		System.out.printf("Media: %.2f milisegundos%n",((double)tiempoPD/numPruebas));
		
		
	}
	
	/**
	 * Pide por teclado un numero (con el mensaje pasado como parametro 
	 * y lo devuelve
	 */
	private static int preguntar(String mensaje){
		System.out.printf(mensaje);
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		int numIntervalos = teclado.nextInt();
		return numIntervalos;
	}
	
	/**
	 * Pide al usuario si quiere el modo debug. En caso de ser la respuesta "Y",
	 * devuelve true. En caso contrario, false
	 */
	private static boolean preguntarDebug(){
		System.out.printf("¿Desea activar el modo debug? (Y/N): ");
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		String debug = teclado.next();
		return debug.equalsIgnoreCase("Y");
	}
	
	
	/**
	 * Genera una matriz cuadrada con la dimension pasada por parametro, diagonal 0
	 * y resto de elementos entre 1 y 15. En caso de estar el debug activado, imprime
	 * por pantalla el contenido de la matriz
	 */
	private static int[][] generarMatrizRandom(int dimension,boolean debug){
		int[][] matriz = new int[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if(i == j){
					matriz[i][j] = 0;
				}
				else{
					matriz[i][j] = generarDatoRandom();
				}
				if(debug){
					System.out.printf(matriz[i][j] + " ");
				}
			}
			if(debug){
				System.out.println();
			}
		}
		return matriz;
	}
	
	/**
	 * Genera un entero entre 1 y 15
	 */
	private static int generarDatoRandom(){
		Random rand = new Random(); 
		int pit = rand.nextInt(15) + 1;
		return pit;
	}
}
