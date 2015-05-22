package practica1;

public class Intervalo {
	
	private int inicio;
	private int fin;
	private int length;
	
	public Intervalo(int inicio, int fin){
		this.inicio = inicio;
		this.fin = fin;
		this.length = fin - inicio;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
		this.length = this.fin - this.inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
		this.length = this.fin - this.inicio;
	}

	public int getLength() {
		return length;
	}
	
	public String toString() {
		return "[" + inicio + ", " + fin + "]";
	}
	
	public boolean compatibles(Intervalo i2){
		return (this.inicio >= i2.getFin() || i2.getInicio() >= this.getFin());
	}
}
