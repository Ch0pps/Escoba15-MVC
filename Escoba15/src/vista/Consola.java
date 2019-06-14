package vista;

import carta.ICarta;
import controlador.Controlador;
import interfaz.EntradaConsola;
import jugador.IJugador;
import modelo.EstadoJuego;

public class Consola implements IVista {

	private Controlador controlador;
	private IJugador[] jugadores;
	private ICarta[] cartasJugador;
	private ICarta[] cartasMesa;

	public Consola() {
		controlador = new Controlador(this);
	}
	
	public void iniciarJuego() {
		controlador.setEstadoJuego(EstadoJuego.CONFIGURANDO);
		menuConfiguracion();
	}

	private void menuConfiguracion() {
		char opcion = 0;
		jugadores = controlador.getJugadores();
		while (opcion != 'S' && controlador.getEstadoJuego() != EstadoJuego.TERMINO) {
			opcion = tomarOpcion();
			switch (opcion) {
			case '1':
				setPuntajeMaximo();
				EntradaConsola.pausarConsola();
				configurarEstado(controlador.getEstadoJuego());
				break;
			case '2':
				agregarJugador();
				EntradaConsola.pausarConsola();
				configurarEstado(controlador.getEstadoJuego());
				break;
			case '3':
				jugadores = controlador.getJugadores();
				mostrarJugadores();
				EntradaConsola.pausarConsola();
				configurarEstado(controlador.getEstadoJuego());
				break;
			case '9':
				jugadores = controlador.getJugadores();
				controlador.iniciarJuego();
				menuJuego();
				opcion = 'a';
				break;
			case 'S':
				controlador.setEstadoJuego(EstadoJuego.TERMINO);
				break;
			}
		}
	}
	

	private void menuJuego() {
		char opcion = 0;
		cartasJugador = controlador.getManoJugador();
		cartasMesa = controlador.getCartasMesa();
		while (opcion != 'S' && controlador.getEstadoJuego() != EstadoJuego.CONFIGURANDO) {
			opcion = tomarOpcion();
			switch (opcion) {
			case '1':
				cartasJugador = controlador.getManoJugador();
				mostrarCartasJugador();
				EntradaConsola.pausarConsola();
				configurarEstado(controlador.getEstadoJuego());
				break;
			case '2':
				cartasMesa = controlador.getCartasMesa();
				mostrarCartasMesa();
				EntradaConsola.pausarConsola();
				configurarEstado(controlador.getEstadoJuego());
				break;
			case '3':
				cartasJugador = controlador.getManoJugador();
				tirarCarta();
				EntradaConsola.pausarConsola();
				configurarEstado(controlador.getEstadoJuego());
				break;
			case '4':
			case 'S':
				controlador.setEstadoJuego(EstadoJuego.CONFIGURANDO);
				break;
			}
		}
	}
	
	
	private void mostrarMenuJuego() {
		System.out.println("##################################");
		System.out.println("##    Turno de: " + turnoJugador() + "    ##");
		System.out.println("##################################");
		System.out.println("#######      Menu juego    #######");
		System.out.println("## 1- Mostrar cartas jugador    ##");
		System.out.println("## 2- Mostrar cartas mesa       ##");
		System.out.println("## 3- Tirar carta               ##");
		System.out.println("## 4- Armar juego               ##");
		System.out.println("##################################");
		System.out.println("## S- Salir                     ##");
		System.out.println("##################################");
	}
	

	private void mostrarMenuConfiguracion() {
		System.out.println("##################################");
		System.out.println("######  Menu Configuracion #######");
		System.out.println("## 1- Establecer puntaje maximo ##");
		System.out.println("## 2- Agregar jugador           ##");
		System.out.println("## 3- Mostrar jugadores         ##");
		System.out.println("## 9- Jugar                     ##");
		System.out.println("## S- Salir                     ##");
		System.out.println("##################################");
	}
	
	private void tirarCarta() {
		mostrarCartasJugador();
		System.out.println("Ingrese el numero de carta que desea tirar");
		int index = EntradaConsola.tomarEntero();
		controlador.tirarCarta(index);
	}
	
	private void separacion() {
		System.out.println("----------------------------------------");
	}
	
	private void mostrarCartasMesa() {
		int i = 0;
		System.out.println("Cartas en mesa: "
				+ "");
		for (ICarta iCarta : cartasMesa) {
			System.out.println(i++ + " - " + iCarta.toString());
		}
		separacion();
	}
	
	private void mostrarCartasJugador() {
		int i = 0;
		separacion();
		System.out.println("Cartas en mano: ");
		for (ICarta iCarta : cartasJugador) {
			System.out.println(i++ + " - " + iCarta.toString());
		}
		separacion();
	}
	
	private void setPuntajeMaximo() {
		separacion();
		System.out.println("Ingrese un puntaje maximo");
		int puntaje = EntradaConsola.tomarEntero();
		controlador.setPuntajeMaximo(puntaje);
	}
	
	private String turnoJugador() {
		String s = jugadores[controlador.getJugadorActual()].getNombre();
		return s;
	}

	private char tomarOpcion() {
		return EntradaConsola.tomarUpperChar();
	}
	
	private void agregarJugador() {
		separacion();
		System.out.println("Ingrese nombre de jugador");
		String nombre = EntradaConsola.tomarString();
		while (nombre.length() < 1) {
			nombre = EntradaConsola.tomarString();
		}
		controlador.agregarJugador(nombre);
	}

	private void mostrarJugadores() {
		int i = 1;
		separacion();
		System.out.println("Jugadores en esta partida: ");
		for (IJugador iJugador : jugadores) {
			System.out.println(i++ + " - " + iJugador.getNombre());
		}
		separacion();
	}
	
	private void configurarEstado(EstadoJuego estadoJuego) {
		switch (estadoJuego) {
		case CONFIGURANDO:
			mostrarMenuConfiguracion();
			break;
		case JUGANDO:
			mostrarMenuJuego();
			break;
		case TERMINO:
			//System.out.println("Juego terminado");
			break;
		}
	}

	@Override
	public void cambioEnJugador() {
		System.out.println("Jugador agregado");
		separacion();
	}

	@Override
	public void cambioDeTurno() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cambioDeOpciones() {
		System.out.println("Puntaje máximo establecido");
		System.out.println("Puntaje máximo: " + controlador.getPuntajeMaximo());
		separacion();
	}

	@Override
	public void hayCambioDeEstado(EstadoJuego estado) {
		switch (estado) {
		case JUGANDO:
			mostrarMenuJuego();
			break;
		case CONFIGURANDO: 
			mostrarMenuConfiguracion();
			break;
		case TERMINO:
			System.out.println(" Juego finalizado ");
			break;
		}

	}

	@Override
	public void hayError(String error) {
		System.out.println(error);

	}

	@Override
	public void cartasJugador() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cartasMesa() {
		cartasMesa = controlador.getCartasMesa();
		System.out.println("Carta tirada");
		System.out.println(cartasMesa[cartasMesa.length-1].toString());

	}

}
