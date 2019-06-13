package jugador;

import java.util.ArrayList;

import carta.Carta;

public class Mesa {

	private ArrayList<Carta> mano = new ArrayList<>();
	
	public void nuevaCarta(Carta carta) {
		mano.add(carta);
	}
	
	public String getMano() {
		int i = 1;
		String str = "";
		for (Carta c : mano) {
			str += "\n" + i++ + " - " + c;
		}
		return str;
	}

	public ArrayList<Carta> getManoArray() {
		return mano;
	}

	public Carta getCarta(int index) {
		return mano.get(index);
	}

	public Carta quitarCarta(Carta carta) {
		Carta c = null;
		if (mano.contains(carta)) {
			mano.remove(carta);
			c = carta;
		}
		return c;
	}
	
	public void nuevaMano() {
		mano = new ArrayList<>();
	}
}
