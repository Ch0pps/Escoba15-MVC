package vista;

import modelo.EstadoJuego;

public interface IVista {

	void cambioEnJugador();
	void cambioDeTurno();
	void cambioDeOpciones();
	void hayCambioDeEstado(EstadoJuego estadoJuego);
	void hayError(String string);
	void cartasJugador();
	void cartasMesa();

}
