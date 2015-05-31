/*
 * Autores: Alejandro Marquez Ferrer - 566400
 * 			Alejandro Royo Amondarain - 560285
 * 
 * Descripcion: Este fichero contiene el codigo correspondiente a la
 * implementacion del algoritmo de fuerza bruta para resolver el
 * problema del viajante de comercio.
 */

package practica2;

import java.util.ArrayList;

public class FuerzaBruta {

	public static double menorCoste = Double.POSITIVE_INFINITY;
	public static String mejorCamino = "";
	
	@SuppressWarnings("unchecked")
	public static double fuerzaBruta(int[][] matriz, ArrayList<Integer> visitados,
			int actual, int coste, boolean debug){
		
		double numVert = matriz.length;

		if ( (visitados.size() > 0)
				&& (visitados.get(0) == actual)
				&& (visitados.size() == numVert) ) {
			
			visitados.add(0);
			
			String camino = "";
			for (int i = 0; i < visitados.size(); i++) {
				camino = camino + "[" + visitados.get(i) + "] ";
			}
			if (coste < menorCoste) {
				menorCoste = coste;
				mejorCamino = camino;
			}
			
			if(debug){
				System.out.printf(camino);
				System.out.println("   -    " + coste);
			}
			return 0;
		}
		
		else if ( visitados.contains(actual) ) {
			
			/* Vertice actual ya visitado */
			return Double.POSITIVE_INFINITY;
		}
		else {
			
			/* Quedan vertices por visitar */
			double minDist = Double.POSITIVE_INFINITY;
			visitados.add(actual);
			
			/* Expande los nodos conectados con el vertice inicial */
			for (int i = 0; i < matriz[actual].length; i++) {
				double distancia = matriz[actual][i] + 
						fuerzaBruta(matriz,(ArrayList<Integer>) visitados.clone(),
								i,coste + matriz[actual][i],debug);
				
				if (distancia < minDist) {
					minDist = distancia;
				}
			}
			return minDist;
		}
	}
}
