package vista;

import javax.swing.JFrame;

import controlador.Controlador;
import jugador.IJugador;
import modelo.EstadoJuego;

public class Gui implements IVista {

	JFrame frame = new JFrame();
	private Controlador controlador;
	private IJugador[] jugadores;

	public Gui() {
		controlador = new Controlador(this);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void cambioEnJugador() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cambioDeTurno() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cambioDeOpciones() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hayCambioDeEstado(EstadoJuego estado) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hayError(String string) {
		// TODO Auto-generated method stub

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
