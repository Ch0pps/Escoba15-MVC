package modelo;

import java.util.ArrayList;

import carta.Carta;
import carta.ICarta;
import carta.Mazo;
import jugador.IJugador;
import jugador.Jugador;
import jugador.Mesa;

public class Juego {

	// Attributes
	
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private Jugador mesa = new Jugador("mesa");
	private int puntajeMaximo = 21;
	private Mazo mazo = new Mazo();
	private Cambios cambio;
	private ArrayList<IObservador> observadores = new ArrayList<>();
	private EstadoJuego estadoJuego;
	private String msgError = "";
	private int jugadorActual = 0;
	
	// Patron Observer
	
	public void agregarObservador(IObservador observador) {
		observadores.add(observador);
	}

	private void notificarObservadores() {
		for (IObservador obs : observadores)
			obs.actualizar(cambio);
	}
	
	private void notificar(Cambios cambio) {
		this.cambio = cambio;
		this.notificarObservadores();
	}
	
	// Methods
	
	public void iniciarJuego() {
		if (jugadores.size() != 0 && jugadores.size() % 2 == 0) {
			mesa.nuevaCarta(mazo.getCarta());
			mesa.nuevaCarta(mazo.getCarta());
			mesa.nuevaCarta(mazo.getCarta());
			mesa.nuevaCarta(mazo.getCarta());
			for (Jugador jugador : jugadores) {
				repartirCartas(jugador);
			}
			estadoJuego = EstadoJuego.JUGANDO;
			notificar(Cambios.ESTADO_JUEGO);
		} else {
			msgError = "Necesita al menos dos jugadores para comenzar";
			notificar(Cambios.ERROR);
		}
	}
	
	public void agregarJugador(String jugador) {
		if (jugador.length() >= 1) {
			jugadores.add(new Jugador(jugador));
			notificar(Cambios.LISTA_JUGADORES);
		} else {
			msgError = "Nombre del jugador no valido";
			notificar(Cambios.ERROR);
		}
	}
	
	public void repartirCartas(Jugador jugador) {
		if (jugadores.contains(jugador)) {
			jugador.nuevaCarta(mazo.getCarta());
			jugador.nuevaCarta(mazo.getCarta());
			jugador.nuevaCarta(mazo.getCarta());
			notificar(Cambios.CARTAS_JUGADOR);
		} else {
			msgError = "El jugador no se encontro";
			notificar(Cambios.ERROR);
		}
	}
	
	// Getters & Setters
	
	public String getMsgError() {
		return msgError;
	}

	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}
	
	public int getPuntajeMaximo() {
		return puntajeMaximo;
	}
	
	public void setPuntajeMaximo(int puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
		this.notificar(Cambios.PUNTAJE_MAXIMO);
	}
	
	public IJugador[] getJugadores() {
		IJugador[] ij = new Jugador[jugadores.size()];
		int i = 0;
		for(Jugador j : jugadores) {
			ij[i++] = j;
		}
		return ij;
	}
	
	public ICarta[] getManoJugador() {
		return jugadores.get(jugadorActual).getMano();
	}
	
	public ICarta[] getCartasJugador() {
		return jugadores.get(jugadorActual).getCartas();
	}
	
	public int getCantidadJugadores() {
		return jugadores.size();
	}

	public void setEstadoJuego(EstadoJuego estadoJuego) {
		this.estadoJuego = estadoJuego;
		this.notificar(Cambios.ESTADO_JUEGO);
	}
	
	public int getJugadorActual() {
		return jugadorActual;
	}

	public void reset() {
		jugadores = new ArrayList<>();
		mesa = new Jugador("Mesa");
		mazo = new Mazo();
		msgError = "";
		jugadorActual = 0;
	}

	public ICarta[] getCartasMesa() {
		return mesa.getMano();
	}

	public void tirarCarta(int index) {
		Jugador jugAux = jugadores.get(jugadorActual);
		Carta[] cartaAux = jugAux.getManoCarta();
		Carta aux = jugAux.tirarCarta(cartaAux[index]);
		mesa.nuevaCarta(aux);
		this.notificar(Cambios.CARTAS_MESA);
	}

}
