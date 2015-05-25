package practica2;

import java.util.ArrayList;

public class TSP {

	public static void main(String[] args){
		int numVert = 0;
		int[][] matriz = new int[numVert][numVert];
		ArrayList<Integer> visitados = new ArrayList<Integer>();
		
		fuerzaBruta(matriz,visitados,0);
		
	}
}
