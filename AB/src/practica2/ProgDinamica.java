/*
 * Autores: Alejandro Marquez Ferrer - 566400
 * 			Alejandro Royo Amondarain - 560285
 * 
 * Descripcion: Este fichero contiene el codigo correspondiente a la
 * implementacion del algoritmo de programacion dinamica para resolver
 * el problema del viajante de comercio.
 */

package practica2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class ProgDinamica {

	public static int contadorSet = 0;
	@SuppressWarnings("unchecked")
	public static double progDinamica(int[][] matriz, double[][] gtab,
				Hashtable<Set<Integer>,Integer> codifSets,
				HashSet<Integer> noVisitados,
				ArrayList<Integer> visitados,
				int actual, int coste,boolean debug){
		
		if (noVisitados.isEmpty()) {
		
			visitados.add(0);
			
			if(debug){
				/* El vertice actual es el inicial y ya se han visitado todos los nodos */
				for (int i = 0; i < visitados.size(); i++) {
					System.out.printf("[" + visitados.get(i) + "] " );
				}
			
				System.out.println("   -    " + coste);
			}
			return matriz[actual][0];
		}
		else {
			noVisitados.remove(actual);
			
			/* Genera una codificacion para un nuevo set de noVisitados */
			if (!codifSets.containsKey(noVisitados)) {
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

				if (!noVisitados.isEmpty()) {
					
					/* Expande los nodos conectados con el vertice actual */
					for (Integer elem : noVisitados) {
						
						double distancia = matriz[actual][elem] + 
								progDinamica(matriz,gtab,
										codifSets,
										(HashSet<Integer>) noVisitados.clone(),
										(ArrayList<Integer>) visitados.clone(),
										elem,coste + matriz[actual][elem],debug);
						
						if (distancia < minDist) {
							minDist = distancia;
						}
					}
					
					/* Guarda el valor calculado para reutilizarlo */
					gtab[actual][codifSets.get(noVisitados)] = minDist;
					return minDist;
				}
				else {
					
					/* No quedan nodos sin visitar */
					double distancia = matriz[actual][0] + 
							progDinamica(matriz,gtab,
									codifSets,
									(HashSet<Integer>) noVisitados.clone(),
									(ArrayList<Integer>) visitados.clone(),
									0,coste + matriz[actual][0],debug); 
					
					return distancia;
				}
			}
		}
	}
}
