package practica1;

public class Registro {
	
	private Intervalo intervalo;
	private int conflictos;
	
	public Registro(Intervalo inter){
		this.intervalo = inter;
		this.conflictos = 0;
	}
	
	public void setIntervalo(Intervalo inter){
		this.intervalo = inter;
	}
	
	public Intervalo getIntervalo(){
		return this.intervalo;
	}
	
	public void setConflictos(int conflictos){
		this.conflictos = conflictos;
	}
	
	public int getConflictos(){
		return this.conflictos;
	}
	
	public void addConflicto(){
		this.conflictos++;
	}
}
