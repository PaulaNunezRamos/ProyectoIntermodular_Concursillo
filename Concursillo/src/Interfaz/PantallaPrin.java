package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
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

import basedatos.GestorPuntuaciones;
import modelo.Puntuacion;

public class PantallaPrin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrin frame = new PantallaPrin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaPrin() {

		URL iconoVentana = getClass().getResource("/assets/Logo Grande.png");

		if (iconoVentana != null) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(iconoVentana));
		} else {
			System.out.println("No se encontró el icono de la ventana.");
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 556);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogoGrande = new JLabel("");
		lblLogoGrande.setIcon(cargarIcono("/assets/Logo Grande.png"));
		lblLogoGrande.setBounds(262, 35, 300, 320);
		contentPane.add(lblLogoGrande);

		JButton btnJugar = crearBotonMenu("Jugar");
		btnJugar.addActionListener(e -> {

			String nombre = JOptionPane.showInputDialog(
					PantallaPrin.this,
					"Introduce tu nombre:",
					"Nueva partida",
					JOptionPane.QUESTION_MESSAGE
			);

			if (nombre != null && !nombre.trim().equals("")) {
				PantallaJuego ventanaJuego = new PantallaJuego(nombre.trim());
				ventanaJuego.setVisible(true);
				dispose();
			}
		});
		btnJugar.setBounds(323, 350, 180, 50);
		contentPane.add(btnJugar);

		JButton btnRanking = crearBotonMenu("Ranking");
		btnRanking.addActionListener(e -> mostrarRanking());
		btnRanking.setBounds(323, 405, 180, 50);
		contentPane.add(btnRanking);

		JButton btnInfo = crearBotonMenu("Información");
		btnInfo.addActionListener(e -> mostrarInformacionJuego());
		btnInfo.setBounds(323, 460, 180, 50);
		contentPane.add(btnInfo);

		JLabel lblPersonaje2 = new JLabel("");
		lblPersonaje2.setIcon(cargarIcono("/assets/Personaje2.png"));
		lblPersonaje2.setBounds(581, 256, 178, 283);
		contentPane.add(lblPersonaje2);

		JLabel lblPersonaje1 = new JLabel("");
		lblPersonaje1.setIcon(cargarIcono("/assets/Personaje1 .png"));
		lblPersonaje1.setBounds(46, 253, 210, 276);
		contentPane.add(lblPersonaje1);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(cargarIcono("/assets/Fondo .jpg"));
		lblFondo.setBounds(0, 0, 841, 539);
		contentPane.add(lblFondo);
	}

	private JButton crearBotonMenu(String texto) {

		JButton boton = new JButton(texto);

		ImageIcon iconoOriginal = cargarIcono("/assets/CAJA AZUL.png");

		if (iconoOriginal == null) {
			iconoOriginal = cargarIcono("/assets/Boton Jugar.png");
		}

		if (iconoOriginal != null) {
			Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(180, 50, Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(imagenEscalada));
		}

		boton.setFont(new Font("Arial", Font.BOLD, 17));
		boton.setForeground(Color.WHITE);
		boton.setHorizontalTextPosition(SwingConstants.CENTER);
		boton.setVerticalTextPosition(SwingConstants.CENTER);

		boton.setOpaque(false);
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setContentAreaFilled(false);
		boton.setBorder(null);

		return boton;
	}

	private void mostrarInformacionJuego() {

		String mensaje = "EL CONCURSILLO\n\n"
				+ "Responde correctamente a las preguntas para subir de nivel\n"
				+ "y acumular más dinero.\n\n"
				+ "Funcionamiento:\n"
				+ "- Cada pregunta tiene 4 posibles respuestas: A, B, C y D.\n"
				+ "- Si aciertas, pasas al siguiente nivel.\n"
				+ "- Si fallas, la partida termina.\n"
				+ "- Puedes consultar tu dinero pulsando el botón del euro.\n\n"
				+ "Comodines:\n"
				+ "- 50:50: elimina dos respuestas incorrectas.\n"
				+ "- Público/Chat: muestra porcentajes de ayuda.\n"
				+ "- Llamada: una persona te sugiere una respuesta.\n\n"
				+ "Objetivo:\n"
				+ "Llegar al último nivel y conseguir el máximo premio.";

		JOptionPane.showMessageDialog(
				this,
				mensaje,
				"Información del juego",
				JOptionPane.INFORMATION_MESSAGE
		);
	}

	private void mostrarRanking() {

		try {
			GestorPuntuaciones gestor = new GestorPuntuaciones();
			ArrayList<Puntuacion> ranking = gestor.obtenerRanking();

			if (ranking.isEmpty()) {
				JOptionPane.showMessageDialog(
						this,
						"Todavía no hay puntuaciones guardadas.",
						"Ranking",
						JOptionPane.INFORMATION_MESSAGE
				);
				return;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("RANKING DE JUGADORES\n\n");

			int posicion = 1;

			for (Puntuacion p : ranking) {

				sb.append(posicion)
						.append(". ")
						.append(p.getNombre())
						.append(" - ")
						.append(formatearDinero(p.getPuntos()))
						.append("\n");

				posicion++;

				if (posicion > 10) {
					break;
				}
			}

			JOptionPane.showMessageDialog(
					this,
					sb.toString(),
					"Ranking",
					JOptionPane.INFORMATION_MESSAGE
			);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					this,
					"No se ha podido cargar el ranking.\nRevisa que MongoDB esté funcionando.",
					"Error",
					JOptionPane.ERROR_MESSAGE
			);
		}
	}

	private String formatearDinero(int cantidad) {
		return String.format("%,d €", cantidad).replace(",", ".");
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