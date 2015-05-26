package practica2;

import java.util.ArrayList;

public class TSP {

	public static void main(String[] args){
		int numVert = 4;
		int[][] matriz = new int[numVert][numVert];
		ArrayList<Integer> visitados = new ArrayList<Integer>();
		
		matriz[0][0] = 0;
		matriz[0][1] = 10;
		matriz[0][2] = 15;
		matriz[0][3] = 20;
		
		matriz[1][0] = 5;
		matriz[1][1] = 0;
		matriz[1][2] = 9;
		matriz[1][3] = 10;
		
		matriz[2][0] = 6;
		matriz[2][1] = 13;
		matriz[2][2] = 0;
		matriz[2][3] = 12;
		
		matriz[3][0] = 8;
		matriz[3][1] = 8;
		matriz[3][2] = 9;
		matriz[3][3] = 0;
		
		System.out.println("Posibles caminos   -  Coste");
		System.out.println("===========================");
		double coste = FuerzaBruta.fuerzaBruta(matriz,visitados,0,0);
		System.out.println();
		System.out.println("El coste minimo obtenido es: " + coste);
	}
}
