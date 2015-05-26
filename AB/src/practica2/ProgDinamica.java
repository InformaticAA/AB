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
		noVisitados.remove(actual);
		
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
				return gtab[actual][codifSets.get(noVisitados)];				
			}
			
			/* Calcula el coste por primera vez */
			else {
				double minDist = Double.POSITIVE_INFINITY;
				visitados.add(actual);
				
				/* Expande los nodos conectados con el vertice actual */
				for (Integer elem : noVisitados) {
					
					double distancia = matriz[actual][elem] + 
							progDinamica(matriz,gtab,
									codifSets,
									(HashSet<Integer>) noVisitados.clone(),
									(ArrayList<Integer>) visitados.clone(),
									elem,coste + matriz[actual][elem]);
					
					if (distancia < minDist) {
						minDist = distancia;
					}
				}
				
				/* Guarda el valor calculado para reutilizarlo */
				gtab[actual][codifSets.get(noVisitados)] = minDist;
				return minDist;
			}
		}
	}
}
