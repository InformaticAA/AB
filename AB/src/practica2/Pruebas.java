package practica2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Pruebas {
	
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
			long inicio = System.currentTimeMillis();
			double coste = FuerzaBruta.fuerzaBruta(matriz,visitados,0,0,debug);
			long tiempo = System.currentTimeMillis() - inicio;
			tiempoFB = tiempoFB + tiempo;
			if(debug){
				System.out.println();
				System.out.println("El coste minimo obtenido es: " + coste);
				System.out.println();
				System.out.printf("Tiempo empleado: %f milisegundos%n",tiempo);
			}
			
			if(debug){
				System.out.println("Ejecutando por programacion dinamica...");
				System.out.println();
				System.out.println("Posibles caminos   -  Coste");
				System.out.println("===========================");
			}
			visitados = new ArrayList<Integer>();
			inicio = System.currentTimeMillis();
			coste = FuerzaBruta.fuerzaBruta(matriz,visitados,0,0,debug);				//Habra que cambiar a programacion dinamica
			tiempo = System.currentTimeMillis() - inicio;
			tiempoPD = tiempoPD + tiempo;
			if(debug){
				System.out.println();
				System.out.println("El coste minimo obtenido es: " + coste);
				System.out.println();
				System.out.printf("Tiempo empleado: %f milisegundos%n",tiempo);
			}
		}
		System.out.println();
		System.out.printf("Tiempo total fuerza bruta: %d milisegundos%n",tiempoFB);
		System.out.printf("Media fuerza bruta: %.2f milisegundos%n",((double)tiempoFB/numPruebas));
		System.out.println();
		System.out.printf("Tiempo total fuerza bruta: %d milisegundos%n",tiempoPD);
		System.out.printf("Media fuerza bruta: %.2f milisegundos%n",((double)tiempoPD/numPruebas));
		
		
	}
	
	/**
	 * Pide por teclado un numero (con el mensaje pasado como parametro 
	 * y lo devuelve
	 */
	private static int preguntar(String mensaje){
		System.out.printf(mensaje);
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
