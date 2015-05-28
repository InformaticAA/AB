/*
 * Autores: Alejandro Marquez Ferrer - 566400
 * 			Alejandro Royo Amondarain - 560285
 * 
 * Descripcion: Este fichero contiene el codigo correspondiente al metodo principal
 * 		de la practica 1. En el se pide al usuario un numero de intervalos a generar y
 * 		un criterio de ordenacion para dichos intervalos. A continuacion, muestra la 
 * 		lista de intervalos generada, la lista una vez ordenada, y la seleccion resultante
 * 		
 */

package practica1;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Plan {
	
	private static final int MAX_INTERVAL_LEFT = 100;
	private static final int MAX_INTERVAL_LENGTH = 1000;
	
	
	public static void main(String[] args){
		
		/* Pregunta por el numero de intervalos */
		String mensaje = "Introduzca el numero de intervalos deseado: ";
		int numIntervalos = preguntar(mensaje);
		
		/* Pregunta por el criterio mediante el cual hacer la seleccion */
		String criterio = preguntarCriterio();
		ArrayList<Registro> l = generarIntervalos(numIntervalos);
		calcularConflictos(l,true);
		System.out.println();
		
		/* Muestra la lista de intervalos generados tal cual */
		System.out.println("Mostrando normal...");
		for (Registro registro : l) {
			System.out.println(registro.getIntervalo().toString());
		}
		System.out.println();
		
		/* Muestra la lista de intervalos ordenada */
		System.out.println("Mostrando ordenado segun el criterio " + criterio + "...");
		ArrayList<Registro> l_ordenado = Mergesort.mergesort(l,criterio);
		for (Registro registro : l_ordenado) {
			System.out.println(registro.getIntervalo().toString());
		}
		System.out.println();
		
		/* Muestra la lista de intervalos escogida */
		System.out.println("Aplicando seleccion voraz...");
		ArrayList<Registro> sel_voraz = seleccionVoraz(l_ordenado);
		for (Registro registro : sel_voraz) {
			System.out.println(registro.getIntervalo().toString());
		}
		System.out.println();
		System.out.println("Numero de intervalos tomados: " + sel_voraz.size());
	}
	
	/**
	 * El metodo muestra el mensaje pasado como parametro, y a continuacion lee
	 * por teclado el entero introducido por el usuario y lo devuelve
	 */
	public static int preguntar(String mensaje){
		System.out.printf(mensaje);
		Scanner teclado = new Scanner(System.in);
		int numIntervalos = teclado.nextInt();
		return numIntervalos;
	}
	
	/**
	 * El metodo muestra el menu de criterios disponibles, y a continuacion lee
	 * por teclado la cadena introducida por el usuario y la devuelve (en mayusculas)
	 */
	private static String preguntarCriterio(){
		System.out.println();
		System.out.println("CRITERIOS DISPONIBLES:");
		System.out.println("=========================================");
		System.out.println("-Inicio de tareas => (izquierda)");
		System.out.println("-Fin de tareas => (derecha)");
		System.out.println("-Tamaño de tareas => (longitud)");
		System.out.println("-Conflictividad de tareas => (conflictos)");
		System.out.println("=========================================");
		System.out.println();
		System.out.print("Introduzca un criterio: ");
		Scanner teclado = new Scanner(System.in);
		String criterio = teclado.next();
		teclado.close();
		return criterio.toUpperCase();
	}
	
	/**
	 * Genera una lista de tantos intervalos aleatorios como se indique por parametro,
	 *  con un rango determinado.
	 */
	public static ArrayList<Registro> generarIntervalos(int numIntervalos){
		Random rand = new Random();
		
		ArrayList<Registro> list = new ArrayList<Registro>();
		
		for (int i = 0; i < numIntervalos; i++) {
			int inicio = rand.nextInt(MAX_INTERVAL_LEFT + 1);
			int fin = inicio + rand.nextInt(MAX_INTERVAL_LENGTH) + 1;
			Intervalo elem = new Intervalo(inicio, fin);
			Registro reg = new Registro(elem);
			list.add(reg);
		}
		
		return list;
	}
	
	/**
	 * A partir de una lista de Registros pasada como parametro, calcula el numero de
	 * conflictos totales de dichos registros (es decir, intervalos que coincidan), asi
	 * como actualiza el numero de conflictos a nivel particular para cada Registro.
	 */
	public static int calcularConflictos(ArrayList<Registro> list, boolean actualizar){
		
		ListIterator<Registro> i1 = list.listIterator();
		int numConflictos = 0;
		
		while (i1.hasNext()) {
			
			Registro a = i1.next();
			ListIterator<Registro> i2 = list.listIterator(i1.previousIndex());
			i2.next();
					
			while(i2.hasNext()) {
				Registro b = i2.next();
				if(!a.getIntervalo().compatibles(b.getIntervalo())){
					if(actualizar){
						a.addConflicto();
						b.addConflicto();
					}
					numConflictos = numConflictos + 2;
				}
			}
		}
		return numConflictos;
	}
	
	/**
	 * 
	 * Imprime por pantalla los intervalos de una lista, acompañados del numero
	 * de conflictos de cada uno.
	 */
	public static void mostrarIntervalos(ArrayList<Registro> list) {
		
		int interPorLinea = 5;
		int cuenta = 0;
		ListIterator<Registro> i1 = list.listIterator();
		
		while (i1.hasNext()) {
			Registro reg = i1.next();
			int numConflictos = reg.getConflictos();
			Intervalo inter = reg.getIntervalo();

			System.out.printf(inter + "(" + numConflictos + ")");
			cuenta++;
			
			if ( cuenta % interPorLinea == 0) {
				System.out.println();
			} else {
				System.out.printf(" ");
			}
		}
	}
	
	/**
	 * Algoritmo de seleccion voraz.
	 */
	public static ArrayList<Registro> seleccionVoraz(ArrayList<Registro> list) {
		ArrayList<Registro> solucion = new ArrayList<Registro>();
		ListIterator<Registro> i1 = list.listIterator();
		
		/* 
		 * Recorre la lista de intervalos ordenada e introduce un intervalo a la
		 * solucion solo si es compatible con los intervalos elegidos hasta el 
		 * momento 
		 */
		while (i1.hasNext()) {
			Registro nuevoReg = i1.next();
			solucion.add(nuevoReg);
			
			if ( calcularConflictos(solucion,false) > 0) {
				
				/* Si el nuevo intervalo ha generado algun conflicto, se elimina de la solucion */
				solucion.remove(nuevoReg);
				
			}
			
		}
		return solucion;
	}
	
	/**
	 * Calcula el espacio temporal total utilizado por los intervalos pasados como
	 * parametro.
	 */
	public static int calcularUtilizacion(ArrayList<Registro> list) {
		ListIterator<Registro> i1 = list.listIterator();
		int longTotal = 0;
		while (i1.hasNext()){
			Registro reg = i1.next();
			longTotal = longTotal + reg.getIntervalo().getLength();
		}
		return longTotal;
	}
	
	/**
	 * Pregunta al usuario si desea activar el modo debug. Si la respuesta es que si,
	 * devuelve true. En caso contrario, devuelve false.
	 */
	public static boolean preguntarDebug(){
		System.out.printf("¿Desea activar el modo debug? (Y/N) ");
		Scanner teclado = new Scanner(System.in);
		String debug = teclado.next();
		return debug.equalsIgnoreCase("Y");
	}
}
