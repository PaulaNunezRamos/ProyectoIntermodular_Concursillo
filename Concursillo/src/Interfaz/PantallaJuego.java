package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Partida;
import modelo.Pregunta;

public class PantallaJuego extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JButton btnPreguntas;
	private JButton btnRespuestaA;
	private JButton btnRespuestaB;
	private JButton btnRespuestaC;
	private JButton btnRespuestaD;

	private Partida partida;
	private ArrayList<String> opcionesEliminadas = new ArrayList<String>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaJuego frame = new PantallaJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaJuego() {
		this("Jugador");
	}

	public PantallaJuego(String nombreJugador) {

		partida = new Partida(nombreJugador);

		URL iconoVentana = getClass().getResource("/assets/Logo Grande.png");
		if (iconoVentana != null) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(iconoVentana));
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 555);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// --- BOTONES DE COMODINES Y UTILIDADES ---

		JButton btnVerDinero = new JButton("");
		btnVerDinero.setIcon(cargarIcono("/assets/Ver dinero.png"));
		configurarBotonImagen(btnVerDinero);
		btnVerDinero.setBounds(10, 0, 93, 77);
		contentPane.add(btnVerDinero);

		btnVerDinero.addActionListener(e -> {
			Dinero ventanaDinero = new Dinero();
			ventanaDinero.setVisible(true);
		});

		JButton btnSalir = new JButton("");
		btnSalir.setIcon(cargarIcono("/assets/Salir.png"));
		configurarBotonImagen(btnSalir);
		btnSalir.setBounds(97, 0, 93, 70);
		contentPane.add(btnSalir);

		btnSalir.addActionListener(e -> {
			PantallaPrin ventanaMenu = new PantallaPrin();
			ventanaMenu.setVisible(true);
			dispose();
		});

		JButton btnComodin50 = new JButton("");
		btnComodin50.setIcon(cargarIcono("/assets/Comodin 50-50.png"));
		configurarBotonImagen(btnComodin50);
		btnComodin50.setBounds(544, 0, 93, 70);
		contentPane.add(btnComodin50);

		btnComodin50.addActionListener(e -> {
			String[] eliminadas = partida.usarComodin5050(opcionesEliminadas);

			if (eliminadas.length == 0) {
				JOptionPane.showMessageDialog(this, "El comodín 50:50 ya no está disponible.");
			} else {
				for (String eliminada : eliminadas) {
					opcionesEliminadas.add(eliminada);
					ocultarRespuesta(eliminada);
				}

				btnComodin50.setEnabled(false);
			}
		});

		JButton btnComodinPublico = new JButton("");
		btnComodinPublico.setIcon(cargarIcono("/assets/Comodin Publico.png"));
		configurarBotonImagen(btnComodinPublico);
		btnComodinPublico.setBounds(636, 10, 93, 56);
		contentPane.add(btnComodinPublico);

		btnComodinPublico.addActionListener(e -> {
			int[] porcentajes = partida.usarComodinChat();

			if (porcentajes.length == 0) {
				JOptionPane.showMessageDialog(this, "El comodín del público ya no está disponible.");
			} else {
				JOptionPane.showMessageDialog(
						this,
						"A: " + porcentajes[0] + "%\n"
								+ "B: " + porcentajes[1] + "%\n"
								+ "C: " + porcentajes[2] + "%\n"
								+ "D: " + porcentajes[3] + "%");

				btnComodinPublico.setEnabled(false);
			}
		});

		JButton btnComodinLlamada = new JButton("");
		btnComodinLlamada.setIcon(cargarIcono("/assets/Comodin Llamada.png"));
		configurarBotonImagen(btnComodinLlamada);
		btnComodinLlamada.setBounds(729, 10, 93, 56);
		contentPane.add(btnComodinLlamada);

		btnComodinLlamada.addActionListener(e -> {
			String mensaje = partida.usarComodinLlamada();
			JOptionPane.showMessageDialog(this, mensaje);
			btnComodinLlamada.setEnabled(false);
		});

		// --- LOGO Y CAJA DE PREGUNTAS ---

		JLabel lblLogoMedio = new JLabel("");
		lblLogoMedio.setIcon(cargarIcono("/assets/Logo Pequeño.png"));
		lblLogoMedio.setBounds(291, 41, 250, 233);
		contentPane.add(lblLogoMedio);

		btnPreguntas = new JButton("");
		btnPreguntas.setIcon(cargarIcono("/assets/Caja Preguntas.png"));
		configurarBotonImagen(btnPreguntas);
		configurarBotonTexto(btnPreguntas, 16);
		btnPreguntas.setBounds(71, 296, 700, 89);
		contentPane.add(btnPreguntas);

		// --- BOTONES DE RESPUESTAS ---

		btnRespuestaA = new JButton("");
		btnRespuestaA.setIcon(cargarIcono("/assets/Caja Respuestas.png"));
		configurarBotonImagen(btnRespuestaA);
		configurarBotonTexto(btnRespuestaA, 14);
		btnRespuestaA.setBounds(24, 395, 373, 56);
		contentPane.add(btnRespuestaA);

		btnRespuestaB = new JButton("");
		btnRespuestaB.setIcon(cargarIcono("/assets/Caja Respuestas.png"));
		configurarBotonImagen(btnRespuestaB);
		configurarBotonTexto(btnRespuestaB, 14);
		btnRespuestaB.setBounds(427, 395, 373, 56);
		contentPane.add(btnRespuestaB);

		btnRespuestaC = new JButton("");
		btnRespuestaC.setIcon(cargarIcono("/assets/Caja Respuestas.png"));
		configurarBotonImagen(btnRespuestaC);
		configurarBotonTexto(btnRespuestaC, 14);
		btnRespuestaC.setBounds(24, 452, 373, 56);
		contentPane.add(btnRespuestaC);

		btnRespuestaD = new JButton("");
		btnRespuestaD.setIcon(cargarIcono("/assets/Caja Respuestas.png"));
		configurarBotonImagen(btnRespuestaD);
		configurarBotonTexto(btnRespuestaD, 14);
		btnRespuestaD.setBounds(427, 452, 373, 56);
		contentPane.add(btnRespuestaD);

		btnRespuestaA.addActionListener(e -> comprobarRespuesta("A"));
		btnRespuestaB.addActionListener(e -> comprobarRespuesta("B"));
		btnRespuestaC.addActionListener(e -> comprobarRespuesta("C"));
		btnRespuestaD.addActionListener(e -> comprobarRespuesta("D"));

		// --- LÍNEAS DE DISEÑO ---

		JLabel lblLinea1 = new JLabel("");
		lblLinea1.setIcon(cargarIcono("/assets/Lineas Gorda.png"));
		lblLinea1.setBounds(0, 330, 93, 28);
		contentPane.add(lblLinea1);

		JLabel lblLinea2 = new JLabel("");
		lblLinea2.setIcon(cargarIcono("/assets/Lineas Fina.png"));
		lblLinea2.setBounds(0, 418, 76, 12);
		contentPane.add(lblLinea2);

		JLabel lblLinea3 = new JLabel("");
		lblLinea3.setIcon(cargarIcono("/assets/Lineas Gorda.png"));
		lblLinea3.setBounds(755, 330, 93, 28);
		contentPane.add(lblLinea3);

		JLabel lblLinea4 = new JLabel("");
		lblLinea4.setIcon(cargarIcono("/assets/Lineas Fina.png"));
		lblLinea4.setBounds(-19, 476, 76, 12);
		contentPane.add(lblLinea4);

		JLabel lblLinea5 = new JLabel("");
		lblLinea5.setIcon(cargarIcono("/assets/Lineas Fina.png"));
		lblLinea5.setBounds(789, 476, 76, 12);
		contentPane.add(lblLinea5);

		JLabel lblLinea6 = new JLabel("");
		lblLinea6.setIcon(cargarIcono("/assets/Lineas Fina.png"));
		lblLinea6.setBounds(789, 418, 76, 12);
		contentPane.add(lblLinea6);

		JLabel lblLinea7 = new JLabel("");
		lblLinea7.setIcon(cargarIcono("/assets/Lineas Fina.png"));
		lblLinea7.setBounds(387, 418, 76, 12);
		contentPane.add(lblLinea7);

		JLabel lblLinea8 = new JLabel("");
		lblLinea8.setIcon(cargarIcono("/assets/Lineas Fina.png"));
		lblLinea8.setBounds(387, 476, 76, 12);
		contentPane.add(lblLinea8);

		// --- FONDO ---

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(cargarIcono("/assets/Fondo .jpg"));
		lblFondo.setBounds(0, 0, 832, 518);
		contentPane.add(lblFondo);

		cargarPregunta();
	}

	private void cargarPregunta() {

		if (partida.isPartidaTerminada()) {
			terminarPartida("La partida ha terminado.");
			return;
		}

		Pregunta p = partida.getPreguntaActual();

		if (p == null) {
			terminarPartida("No se ha podido cargar ninguna pregunta.");
			return;
		}

		btnPreguntas.setText(
				"<html><center>Pregunta " + partida.getNivelActual() + ": " + p.getPregunta() + "</center></html>");
		btnRespuestaA.setText("<html><center>A: " + p.getOpcionA() + "</center></html>");
		btnRespuestaB.setText("<html><center>B: " + p.getOpcionB() + "</center></html>");
		btnRespuestaC.setText("<html><center>C: " + p.getOpcionC() + "</center></html>");
		btnRespuestaD.setText("<html><center>D: " + p.getOpcionD() + "</center></html>");

		btnRespuestaA.setEnabled(true);
		btnRespuestaB.setEnabled(true);
		btnRespuestaC.setEnabled(true);
		btnRespuestaD.setEnabled(true);
	}

	private void comprobarRespuesta(String respuestaElegida) {

		if (opcionesEliminadas.contains(respuestaElegida)) {
			JOptionPane.showMessageDialog(this, "Esa respuesta ha sido eliminada por un comodín.");
			return;
		}

		Pregunta preguntaRespondida = partida.getPreguntaActual();

		boolean acierto = partida.comprobarRespuesta(respuestaElegida);

		if (acierto) {

			if (partida.isPartidaTerminada()) {
				terminarPartida("¡Has ganado el juego!");
			} else {
				JOptionPane.showMessageDialog(this, "¡Correcto!");
				opcionesEliminadas.clear();
				cargarPregunta();
			}

		} else {

			String correcta = "";

			if (preguntaRespondida != null) {
				correcta = preguntaRespondida.getCorrecta();
			}

			terminarPartida("Incorrecto. La respuesta correcta era: " + correcta);
		}
	}

	private void ocultarRespuesta(String letra) {

		if (letra.equalsIgnoreCase("A")) {
			btnRespuestaA.setText("<html><center>A: [eliminada]</center></html>");
			btnRespuestaA.setEnabled(false);

		} else if (letra.equalsIgnoreCase("B")) {
			btnRespuestaB.setText("<html><center>B: [eliminada]</center></html>");
			btnRespuestaB.setEnabled(false);

		} else if (letra.equalsIgnoreCase("C")) {
			btnRespuestaC.setText("<html><center>C: [eliminada]</center></html>");
			btnRespuestaC.setEnabled(false);

		} else if (letra.equalsIgnoreCase("D")) {
			btnRespuestaD.setText("<html><center>D: [eliminada]</center></html>");
			btnRespuestaD.setEnabled(false);
		}
	}

	private void terminarPartida(String mensaje) {

		JOptionPane.showMessageDialog(
				this,
				mensaje + "\nDinero final: " + partida.getDineroAcumulado() + " €");

		PantallaPrin ventanaMenu = new PantallaPrin();
		ventanaMenu.setVisible(true);
		dispose();
	}

	private void configurarBotonImagen(JButton boton) {

		boton.setOpaque(false);
		boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}

	private void configurarBotonTexto(JButton boton, int tamanoLetra) {

		boton.setFont(new Font("Arial", Font.BOLD, tamanoLetra));
		boton.setForeground(Color.WHITE);
		boton.setHorizontalTextPosition(SwingConstants.CENTER);
		boton.setVerticalTextPosition(SwingConstants.CENTER);
	}

	private ImageIcon cargarIcono(String ruta) {

		URL url = getClass().getResource(ruta);

		if (url == null) {
			System.out.println("No se encontró la imagen: " + ruta);
			return null;
		}

		return new ImageIcon(url);
	}
}