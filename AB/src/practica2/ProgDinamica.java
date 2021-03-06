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
	public static Registro progDinamica(int[][] matriz, Registro[][] gtab,
				Hashtable<Set<Integer>,Integer> codifSets,
				HashSet<Integer> noVisitados,
				ArrayList<Integer> visitados,
				int actual, int coste,boolean debug){
		
		if (noVisitados.isEmpty()) {
		
			visitados.add(0);

			String camino = "[" + visitados.get(visitados.size() - 2) + "] " + "[" + actual + "] ";
			
			return new Registro(matriz[actual][0],camino);
		}
		else {
			noVisitados.remove(actual);
			
			/* Genera una codificacion para un nuevo set de noVisitados */
			if (!codifSets.containsKey(noVisitados)) {
				codifSets.put(noVisitados, contadorSet);
				contadorSet++;
			}
			
			/* Si el resultado ya esta calculado lo reutiliza */
			if ( gtab[actual][codifSets.get(noVisitados)].getCoste() >= 0 ) {
				return gtab[actual][codifSets.get(noVisitados)];
			}
			
			/* Calcula el coste por primera vez */
			else {
				double minDist = Double.POSITIVE_INFINITY;
				String minCamino = "";
				visitados.add(actual);

				if (!noVisitados.isEmpty()) {
					
					/* Expande los nodos conectados con el vertice actual */
					for (Integer elem : noVisitados) {
						
						Registro reg = progDinamica(matriz,gtab,
								codifSets,
								(HashSet<Integer>) noVisitados.clone(),
								(ArrayList<Integer>) visitados.clone(),
								elem,coste + matriz[actual][elem],debug);
						double distancia = matriz[actual][elem] + reg.getCoste();
						String camino = "[" + actual + "] " + reg.getCamino();
						
						if (distancia < minDist) {
							minDist = distancia;
							minCamino = camino;
						}
					}
					
					/* Guarda el valor calculado para reutilizarlo */
					Registro nuevoReg = new Registro((int) minDist,minCamino);
					gtab[actual][codifSets.get(noVisitados)] = nuevoReg;

					return nuevoReg;
				}
				else {
					
					/* No quedan nodos sin visitar */
					Registro reg = progDinamica(matriz,gtab,
							codifSets,
							(HashSet<Integer>) noVisitados.clone(),
							(ArrayList<Integer>) visitados.clone(),
							0,coste + matriz[actual][0],debug);
					
					reg.setCoste(reg.getCoste() + matriz[actual][0]);
					return reg;
				}
			}
		}
	}
}
