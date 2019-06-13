package jugador;

import carta.ICarta;

public interface IJugador {
	String getNombre();
	ICarta[] getMano();
	ICarta[] getCartas();
	String toString();
}
