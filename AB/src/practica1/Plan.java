package practica1;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Plan {
	
	private static final int MAX_INTERVAL_LEFT = 100;
	private static final int MAX_INTERVAL_LENGTH = 1000;
	
	
	public static void main(String[] args){
		
		int numIntervalos = preguntarIntervalos();
		String criterio = preguntarCriterio();
		ArrayList<Registro> l = generarIntervalos(numIntervalos);
		calcularConflictos(l);
		System.out.println("Mostrando normal...");
		for (Registro registro : l) {
			System.out.println(registro.getIntervalo().toString());
		}
		System.out.println("Mostrando ordenado...");
		ArrayList<Registro> l_ordenado = Mergesort.mergesort(l,criterio);
		for (Registro registro : l_ordenado) {
			System.out.println(registro.getIntervalo().toString() + " -> " + registro.getConflictos());
		}
	}
	
	private static int preguntarIntervalos(){
		System.out.printf("Introduzca el número de intervalos deseado: ");
		Scanner teclado = new Scanner(System.in);
		int numIntervalos = teclado.nextInt();
		return numIntervalos;
	}
	
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
	
	private static ArrayList<Registro> generarIntervalos(int numIntervalos){
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
	
	private static void calcularConflictos(ArrayList<Registro> list){
		
		ListIterator<Registro> i1 = list.listIterator();
		
		while (i1.hasNext()) {
			
			Registro a = i1.next();
			ListIterator<Registro> i2 = list.listIterator(i1.previousIndex());
			i2.next();
					
			while(i2.hasNext()) {
				Registro b = i2.next();
				if(!a.getIntervalo().compatibles(b.getIntervalo())){
					
					System.out.printf("---" + a.getIntervalo().toString());
					System.out.println(" -> " + b.getIntervalo().toString());
					
					a.addConflicto();
					b.addConflicto();
				}
			}
		}
	}

}
