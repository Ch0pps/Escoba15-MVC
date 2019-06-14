package controlador;

import carta.ICarta;
import jugador.IJugador;
import modelo.Cambios;
import modelo.EstadoJuego;
import modelo.IObservador;
import modelo.Juego;
import vista.IVista;

public class Controlador implements IObservador {

	// Attributes

	Juego juego = new Juego();
	IVista vista;

	// Constructor

	public Controlador(IVista v) {
		this.vista = v;
		juego.agregarObservador(this);
	}

	// Methods
	
	public void iniciarJuego() {
		juego.iniciarJuego();
	}

	@Override
	public void actualizar(Cambios cambio) {
		switch (cambio) {
		case LISTA_JUGADORES:
			vista.cambioEnJugador();
			break;
		case ERROR:
			vista.hayError(juego.getMsgError());
			break;
		case CARTAS_JUGADOR:
			vista.cartasJugador();
			break;
		case CARTAS_MESA:
			vista.cartasMesa();
			break;
		case TURNO:
			vista.cambioDeTurno();
			break;
		case PUNTAJE_MAXIMO:
			vista.cambioDeOpciones();
			break;
		case ESTADO_JUEGO:
			vista.hayCambioDeEstado(juego.getEstadoJuego());
			break;
		}
	}

	public void agregarJugador(String jugador) {
		juego.agregarJugador(jugador);
	}

	public void setPuntajeMaximo(int puntajeMaximo) {
		juego.setPuntajeMaximo(puntajeMaximo);
	}

	public int getPuntajeMaximo() {
		return juego.getPuntajeMaximo();
	}

	public IJugador[] getJugadores() {
		return juego.getJugadores();
	}
	
	public ICarta[] getManoJugador() {
		return juego.getManoJugador();
	}
	
	public ICarta[] getCartasJugador() {
		return juego.getCartasJugador();
	}
	
	public EstadoJuego getEstadoJuego() {
		return juego.getEstadoJuego();
	}
	
	public int getCantidadJugadores() {
		return juego.getCantidadJugadores();
	}

	public void setEstadoJuego(EstadoJuego estadoJuego) {
		juego.setEstadoJuego(estadoJuego);
	}
	
	public int getJugadorActual() {
		return juego.getJugadorActual();
	}

	public void reset() {
		juego.reset();
	}

	public ICarta[] getCartasMesa() {
		return juego.getCartasMesa();
	}

	public void tirarCarta(int index) {
		juego.tirarCarta(index);
	}
}
