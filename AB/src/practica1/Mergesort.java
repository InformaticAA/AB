package practica1;

import java.util.ArrayList;

public class Mergesort {
	
	public enum Estrategia {
		IZQUIERDA,DERECHA,LONGITUD,CONFLICTOS
	}

	public static ArrayList<Registro> mergesort(ArrayList<Registro> lista, String criterio) {
		ArrayList<Registro> izquierda = new ArrayList<Registro>();
		ArrayList<Registro> derecha = new ArrayList<Registro>();
		ArrayList<Registro> resultado = new ArrayList<Registro>();
		
		/* Ordenacion respecto a los inicios de intervalo */
		if (lista.size() <= 1) {
			return lista;
		}
		else {
			
			/* Separa el vector lista en dos mitades */
			int puntoMedio = lista.size()/2;

			for (int i = 0; i < puntoMedio; i++) {
				izquierda.add(lista.get(i));
			}
			
			for (int i = puntoMedio; i < lista.size(); i++) {
				derecha.add(lista.get(i));
			}
			
			izquierda = mergesort(izquierda,criterio);
			derecha = mergesort(derecha,criterio);
			
			/* Distinto para cada estrategia */
			Estrategia estrategia = Estrategia.valueOf(criterio);
			switch(estrategia) {
				
			case IZQUIERDA:
				
				if (izquierda.get(izquierda.size()-1).getIntervalo().getInicio() <=
					derecha.get(0).getIntervalo().getInicio() ) {
				
					izquierda.addAll(derecha);
					return izquierda;
				}
				break;
			
			case DERECHA:
				
				if (izquierda.get(izquierda.size()-1).getIntervalo().getFin() <=
					derecha.get(0).getIntervalo().getFin() ) {
				
					izquierda.addAll(derecha);
					return izquierda;
				}
				break;
				
			case LONGITUD:
				
				if (izquierda.get(izquierda.size()-1).getIntervalo().getLength() <=
					derecha.get(0).getIntervalo().getLength() ) {
				
					izquierda.addAll(derecha);
					return izquierda;
				}
				break;
			
			case CONFLICTOS:
				
				if (izquierda.get(izquierda.size()-1).getConflictos() <=
					derecha.get(0).getConflictos() ) {
				
					izquierda.addAll(derecha);
					return izquierda;
				}
				break;

			default:
				break;
			}
			
			
			resultado = merge(izquierda,derecha,criterio);
			return resultado;
		}
	}
		
	public static ArrayList<Registro> merge(ArrayList<Registro> izquierda, ArrayList<Registro> derecha, String criterio) {
		ArrayList<Registro> resultado = new ArrayList<Registro>();
	
		while(izquierda.size() > 0 && derecha.size() > 0) {
			
			/* Distinto para cada estrategia */
			Estrategia estrategia = Estrategia.valueOf(criterio);
			switch(estrategia) {
			
			case IZQUIERDA:
				
				if (izquierda.get(0).getIntervalo().getInicio() <=
						derecha.get(0).getIntervalo().getInicio() ) {
					
					/* Lo incorpora al resultado y o borra de su vector */
					resultado.add(izquierda.get(0));
					izquierda.remove(0);
				} else {
					
					resultado.add(derecha.get(0));
					derecha.remove(0);
				}
				break;
				
			case DERECHA:
				
				if (izquierda.get(0).getIntervalo().getFin() <=
						derecha.get(0).getIntervalo().getFin() ) {
			
					/* Lo incorpora al resultado y o borra de su vector */
					resultado.add(izquierda.get(0));
					izquierda.remove(0);
				} else {
					
					resultado.add(derecha.get(0));
					derecha.remove(0);
				}
				break;

			case LONGITUD:
				
				if (izquierda.get(0).getIntervalo().getLength() <=
						derecha.get(0).getIntervalo().getLength() ) {
					
					/* Lo incorpora al resultado y o borra de su vector */
					resultado.add(izquierda.get(0));
					izquierda.remove(0);
				} else {
					
					resultado.add(derecha.get(0));
					derecha.remove(0);
				}
				break;
				
				
			case CONFLICTOS:
				
				if (izquierda.get(0).getConflictos() <=
						derecha.get(0).getConflictos() ) {
					
					/* Lo incorpora al resultado y o borra de su vector */
					resultado.add(izquierda.get(0));
					izquierda.remove(0);
				} else {
					
					resultado.add(derecha.get(0));
					derecha.remove(0);
		}

				
			default:
				break;
			}
		}
		
		if (izquierda.size() > 0) {
			resultado.addAll(izquierda);
		}
		if (derecha.size() > 0) {
			resultado.addAll(derecha);
		}
		return resultado;
	}
}
