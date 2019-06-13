package vista;

import controlador.Controlador;
import interfaz.EntradaConsola;
import jugador.IJugador;
import modelo.EstadoJuego;

public class Consola implements IVista {

	private Controlador controlador;
	private IJugador[] jugadores;

	public Consola() {
		controlador = new Controlador(this);
		configurarEstado(EstadoJuego.CONFIGURANDO);
		menuConfiguracion();
	}

	private void menuConfiguracion() {
		char opcion = 0;
		while (opcion != '9' && controlador.getEstadoJuego() != EstadoJuego.TERMINO) {
			opcion = tomarOpcion();
			switch (opcion) {
			case '1':
				break;
			case '2':
				agregarJugador();
				configurarEstado(controlador.getEstadoJuego());
				break;
			case '3':
				mostrarJugadores();
				configurarEstado(controlador.getEstadoJuego());
				break;
			case '9':
				controlador.iniciarJuego();
				configurarEstado(controlador.getEstadoJuego());
				menuJuego();
				opcion = 'a';
				break;
			case 'S':
				controlador.setEstadoJuego(EstadoJuego.TERMINO);
				configurarEstado(controlador.getEstadoJuego());
				break;
			}
		}
	}
	
	private void menuJuego() {
		// TODO menu juego
	}
	
	
	private void mostrarMenuJuego() {
		System.out.println("##################################");
		System.out.println("Turno de: " + jugadores[controlador.getJugadorActual()].getNombre());
		System.out.println("##################################");
		System.out.println("#######      Menu juego    #######");
		System.out.println("## 1- Mostrar cartas jugador    ##");
		System.out.println("## 2- Mostrar cartas mesa       ##");
		System.out.println("## 4- Tirar carta               ##");
		System.out.println("## 5- Armar juego               ##");
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

	private char tomarOpcion() {
		return EntradaConsola.tomarUpperChar();
	}
	
	private void agregarJugador() {
		System.out.println("Ingrese nombre de jugador");
		String nombre = EntradaConsola.tomarString();
		controlador.agregarJugador(nombre);
	}

	private void mostrarJugadores() {
		int i = 1;
		jugadores = controlador.getJugadores();
		for (IJugador iJugador : jugadores) {
			System.out.println(i++ + " - " + iJugador.getNombre());
		}
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
			System.out.println("Juego terminado");
			break;
		}
	}

	@Override
	public void cambioEnJugador() {
		System.out.println("Jugador agregado");
	}

	@Override
	public void cambioDeTurno() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cambioDeOpciones() {

	}

	@Override
	public void hayCambioDeEstado(EstadoJuego estado) {
		System.out.println(estado.toString());

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
		// TODO Auto-generated method stub

	}

}
