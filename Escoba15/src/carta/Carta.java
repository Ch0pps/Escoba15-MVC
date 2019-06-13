package carta;

public class Carta implements ICarta {
	
	private Palo palo;
	private int valor;
	private String figura;
	
	public Carta(Palo palo, int valor, String figura) {
		this.palo = palo;
		this.valor = valor;
		this.figura = figura;
	}
	
	public Palo getPalo() {
		return palo;
	}
	
	public int getValor() {
		return valor;
	}
	
	public String getFigura() {
		return figura;
	}
	
	public String toString() {
		return getFigura() + " de " + getPalo();
	}
}
