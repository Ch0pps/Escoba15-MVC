package jugador;

import java.util.ArrayList;

import carta.Carta;
import carta.ICarta;

public class Jugador implements IJugador {

	private ArrayList<Carta> mano = new ArrayList<>();
	private ArrayList<Carta> misCartas = new ArrayList<>();
	private String nombre;

	public Jugador(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void nuevaCarta(Carta carta) {
		mano.add(carta);
	}
	
	public ICarta[] getMano() {
		ICarta[] cartas = new ICarta[mano.size()];
		int i = 0;
		for (Carta c : mano) {
			cartas[i++] = (ICarta) c;
		}
		return cartas;
	}
	
	public Carta[] getManoCarta() {
		Carta[] cartas = new Carta[mano.size()];
		int i = 0;
		for (Carta c : mano) {
			cartas[i++] = c;
		}
		return cartas;
	}
	
	public ICarta[] getCartas() {
		ICarta[] cartas = new ICarta[misCartas.size()];
		int i = 0;
		for (Carta c : misCartas) {
			cartas[i++] = (ICarta) c;
		}
		return cartas;
	}

	public Carta getCarta(int index) {
		return mano.get(index);
	}

	public Carta tirarCarta(Carta carta) {
		mano.remove(carta);
		return carta;
	}

	public int armarJuego(ArrayList<Carta> cartas) {
		int suma = 0;
		for (Carta carta : cartas) {
			suma += carta.getValor();
		}
		if (suma == 15) {
			for (Carta carta : cartas) {
				misCartas.add(carta);
			}
		}
		return suma;
	}

	public void nuevaMano() {
		mano = new ArrayList<>();
	}

	public String toString() {
		return getNombre();
	}

	public int cantidadCartas() {
		return mano.size();
	}
}
