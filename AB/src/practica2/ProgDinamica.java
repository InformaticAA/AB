package practica2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class ProgDinamica {

	private static int contadorSet = 0;
	
	@SuppressWarnings("unchecked")
	public static double progDinamica(int[][] matriz, double[][] gtab,
				Hashtable<Set<Integer>,Integer> codifSets,
				HashSet<Integer> noVisitados,
				ArrayList<Integer> visitados,
				int actual, int coste){
		
//		System.out.println("VISITADOS SIZE: " + visitados.size());
//		System.out.println("NO_VISITADOS SIZE: " + noVisitados.size());
		
		if (noVisitados.isEmpty()) {
			
			/* El vertice actual es el inicial y ya se han visitado todos los nodos */
			for (int i = 0; i < visitados.size(); i++) {
				System.out.printf("[" + visitados.get(i) + "] " );
			}
			System.out.println("   -    " + coste);
			return matriz[actual][0];
		}
		else {
			
			/* Genera una codificacion para un nuevo set de noVisitados */
			if (!codifSets.contains(noVisitados)) {
				codifSets.put(noVisitados, contadorSet);
				contadorSet++;
			}
			
			/* Si el resultado ya esta calculado lo reutiliza */
			if ( gtab[actual][codifSets.get(noVisitados)] >= 0 ) {
				
				double ind = codifSets.get(noVisitados);
				System.out.println("Aprovechando calculo... i = " + actual + ", j) = " + ind);
				System.out.println("Calculo aprovechado = " + gtab[actual][codifSets.get(noVisitados)]);
				
				return gtab[actual][codifSets.get(noVisitados)];				
			}
			
			/* Calcula el coste por primera vez */
			else {
				double minDist = Double.POSITIVE_INFINITY;
				visitados.add(actual);
				
				/* Expande los nodos conectados con el vertice actual */
				for (Integer elem : noVisitados) {
					
					/* Cuenta como visitado el nodo actual */
					HashSet<Integer> noVisitadosCopia = (HashSet<Integer>) noVisitados.clone();
					noVisitadosCopia.remove(actual);
					
					double distancia = matriz[actual][elem] + 
							progDinamica(matriz,gtab,
									codifSets,
									noVisitadosCopia,
									(ArrayList<Integer>) visitados.clone(),
									elem,coste + matriz[actual][elem]);
					
					if (distancia < minDist) {
						minDist = distancia;
					}
				}
				
				/* Guarda el valor calculado para reutilizarlo */
				System.out.println("Guardando calculo... (i = " + actual + ", j = " + codifSets.get(noVisitados) + 
						") -> " + minDist);
				gtab[actual][codifSets.get(noVisitados)] = minDist;
				return minDist;
			}
		}
	}
}
