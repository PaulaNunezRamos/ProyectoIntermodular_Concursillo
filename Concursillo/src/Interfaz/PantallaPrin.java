package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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

		// LOGO PRINCIPAL

		JLabel lblLogoGrande = new JLabel("");
		lblLogoGrande.setIcon(cargarIcono("/assets/Logo Grande.png"));
		lblLogoGrande.setBounds(262, 49, 300, 320);
		contentPane.add(lblLogoGrande);

		// BOTÓN JUGAR

		JButton btnJugar = new JButton("Jugar");
		btnJugar.setFont(new Font("Arial", Font.BOLD, 18));
		btnJugar.setForeground(Color.WHITE);
		btnJugar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnJugar.setVerticalTextPosition(SwingConstants.CENTER);
		btnJugar.setIcon(cargarIcono("/assets/Boton Jugar.png"));

		configurarBotonImagen(btnJugar);

		btnJugar.addActionListener(e -> {

			String nombre = JOptionPane.showInputDialog(
					PantallaPrin.this,
					"Introduce tu nombre:",
					"Nuevo jugador",
					JOptionPane.QUESTION_MESSAGE
			);

			if (nombre != null && !nombre.trim().equals("")) {
				PantallaJuego ventanaJuego = new PantallaJuego(nombre.trim());
				ventanaJuego.setVisible(true);
				dispose();
			}
		});

		btnJugar.setBounds(332, 379, 150, 55);
		contentPane.add(btnJugar);

		// PERSONAJES

		JLabel lblPersonaje2 = new JLabel("");
		lblPersonaje2.setIcon(cargarIcono("/assets/Personaje2.png"));
		lblPersonaje2.setBounds(581, 256, 178, 283);
		contentPane.add(lblPersonaje2);

		JLabel lblPersonaje1 = new JLabel("");
		lblPersonaje1.setIcon(cargarIcono("/assets/Personaje1 .png"));
		lblPersonaje1.setBounds(46, 253, 210, 276);
		contentPane.add(lblPersonaje1);

		// FONDO

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(cargarIcono("/assets/Fondo .jpg"));
		lblFondo.setBounds(0, 0, 841, 539);
		contentPane.add(lblFondo);
	}

	private void configurarBotonImagen(JButton boton) {

		boton.setOpaque(false);
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setContentAreaFilled(false);
		boton.setBorder(null);
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