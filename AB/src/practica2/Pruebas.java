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
				System.out.printf("Tiempo empleado: %d milisegundos%n",tiempo);
			}
		}
		System.out.println();
		System.out.printf("Tiempo total fuerza bruta: %d milisegundos%n",tiempoFB);
		System.out.printf("Media fuerza bruta: %d milisegundos%n",tiempoFB/numPruebas);
	}
	
	private static int preguntar(String mensaje){
		System.out.printf(mensaje);
		Scanner teclado = new Scanner(System.in);
		int numIntervalos = teclado.nextInt();
		return numIntervalos;
	}
	
	private static boolean preguntarDebug(){
		System.out.printf("¿Desea activar el modo debug? (Y/N) ");
		Scanner teclado = new Scanner(System.in);
		String debug = teclado.next();
		return debug.equalsIgnoreCase("Y");
	}
	
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
	
	private static int generarDatoRandom(){
		Random rand = new Random(); 
		int pit = rand.nextInt(15) + 1;
		return pit;
	}
}
