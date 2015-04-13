package practica1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Plan {
	
	private static final int MAX_INTERVAL_LEFT = 0;
	private static final int MAX_INTERVAL_LENGTH = 0;
	
	
	public static void main(String[] args){
		
		int numIntervalos = preguntarIntervalos();
	}
	
	private static int preguntarIntervalos(){
		Scanner teclado = new Scanner(System.in);
		int numIntervalos = teclado.nextInt();
		teclado.close();
		return numIntervalos;
	}
	
	private static List generarIntervalos(int numIntervalos){
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
	
	private static void calcularConflictos(ArrayList list){
		
		Iterator<Registro> i = list.iterator();
		Iterator<Registro> i2 = list.iterator();

		i2.next();
		
		while(i.hasNext()){
			while(i2.hasNext()){
				Registro a = i.next();
				Registro b = i2.next();
				if(!a.getIntervalo().compatibles(b.getIntervalo())){
					a.addConflicto();
					b.addConflicto();
				}
			}
			i2 = i;
		}
	}

}
