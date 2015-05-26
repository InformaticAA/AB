package practica2;

import java.util.ArrayList;

public class FuerzaBruta {

	public static double fuerzaBruta(int[][] matriz, ArrayList<Integer> visitados, int actual, int coste){
		
		double numVert = matriz.length;

		if ( (visitados.size() > 0)
				&& (visitados.get(0) == actual)
				&& (visitados.size() == numVert) ) {
			
			/* El vertice actual es el inicial y ya se han visitado todos los nodos */
			for (int i = 0; i < visitados.size(); i++) {
				System.out.printf("[" + visitados.get(i) + "] " );
			}
			System.out.println("   -    " + coste);
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
						fuerzaBruta(matriz,(ArrayList<Integer>) visitados.clone(),i,coste + matriz[actual][i]);
				
				if (distancia < minDist) {
					minDist = distancia;
				}
			}
			return minDist;
		}
	}
}
