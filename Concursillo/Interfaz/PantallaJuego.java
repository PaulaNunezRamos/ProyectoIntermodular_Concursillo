import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PantallaJuego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		// Icono de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// --- BOTONES DE COMODINES Y UTILIDADES ---
		
		JButton btnVerDinero = new JButton("");
		btnVerDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dinero ventanaDinero = new Dinero();
				ventanaDinero.setVisible(true);
			}
		});
		btnVerDinero.setIcon(new ImageIcon(getClass().getResource("/Ver dinero.png")));
		btnVerDinero.setOpaque(false);
		btnVerDinero.setFocusPainted(false);
		btnVerDinero.setContentAreaFilled(false);
		btnVerDinero.setBorderPainted(false);
		btnVerDinero.setBounds(10, 0, 93, 77);
		contentPane.add(btnVerDinero);

		JButton btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(getClass().getResource("/Salir.png")));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrin ventanaMenu = new PantallaPrin();
				ventanaMenu.setVisible(true);
				dispose();
			}
		});
		btnSalir.setOpaque(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(97, 0, 93, 70);
		contentPane.add(btnSalir);

		JButton btnComodin50 = new JButton("");
		btnComodin50.setIcon(new ImageIcon(getClass().getResource("/Comodin 50-50.png")));
		btnComodin50.setOpaque(false);
		btnComodin50.setFocusPainted(false);
		btnComodin50.setContentAreaFilled(false);
		btnComodin50.setBorderPainted(false);
		btnComodin50.setBounds(544, 0, 93, 70);
		contentPane.add(btnComodin50);
		
		JButton btnComodinPublico = new JButton("");
		btnComodinPublico.setIcon(new ImageIcon(getClass().getResource("/Comodin Publico.png")));
		btnComodinPublico.setOpaque(false);
		btnComodinPublico.setFocusPainted(false);
		btnComodinPublico.setContentAreaFilled(false);
		btnComodinPublico.setBorderPainted(false);
		btnComodinPublico.setBounds(636, 10, 93, 56);
		contentPane.add(btnComodinPublico);

		JButton btnComodinLlamada = new JButton("");
		btnComodinLlamada.setIcon(new ImageIcon(getClass().getResource("/Comodin Llamada.png")));
		btnComodinLlamada.setOpaque(false);
		btnComodinLlamada.setFocusPainted(false);
		btnComodinLlamada.setContentAreaFilled(false);
		btnComodinLlamada.setBorderPainted(false);
		btnComodinLlamada.setBounds(729, 10, 93, 56);
		contentPane.add(btnComodinLlamada);

		// --- LOGO Y CAJA DE PREGUNTAS ---

		JLabel lblLogoMedio = new JLabel("");
		lblLogoMedio.setIcon(new ImageIcon(getClass().getResource("/Logo Pequeño.png")));
		lblLogoMedio.setBounds(291, 41, 250, 233);
		contentPane.add(lblLogoMedio);
		
		JButton btnPreguntas = new JButton("");
		btnPreguntas.setIcon(new ImageIcon(getClass().getResource("/Caja Preguntas.png")));
		btnPreguntas.setOpaque(false);
		btnPreguntas.setFocusPainted(false);
		btnPreguntas.setContentAreaFilled(false);
		btnPreguntas.setBorderPainted(false);
		btnPreguntas.setBounds(71, 296, 700, 89);
		contentPane.add(btnPreguntas);
		
		// --- BOTONES DE RESPUESTAS ---
		
		JButton btnRespuestaA = new JButton("");
		btnRespuestaA.setIcon(new ImageIcon(getClass().getResource("/Caja Respuestas.png")));
		btnRespuestaA.setOpaque(false);
		btnRespuestaA.setFocusPainted(false);
		btnRespuestaA.setContentAreaFilled(false);
		btnRespuestaA.setBorderPainted(false);
		btnRespuestaA.setBounds(24, 395, 373, 56);
		contentPane.add(btnRespuestaA);
		
		JButton btnRespuestaB = new JButton("");
		btnRespuestaB.setIcon(new ImageIcon(getClass().getResource("/Caja Respuestas.png")));
		btnRespuestaB.setOpaque(false);
		btnRespuestaB.setFocusPainted(false);
		btnRespuestaB.setContentAreaFilled(false);
		btnRespuestaB.setBorderPainted(false);
		btnRespuestaB.setBounds(427, 395, 373, 56);
		contentPane.add(btnRespuestaB);
		
		JButton btnRespuestaC = new JButton("");
		btnRespuestaC.setIcon(new ImageIcon(getClass().getResource("/Caja Respuestas.png")));
		btnRespuestaC.setOpaque(false);
		btnRespuestaC.setFocusPainted(false);
		btnRespuestaC.setContentAreaFilled(false);
		btnRespuestaC.setBorderPainted(false);
		btnRespuestaC.setBounds(24, 452, 373, 56);
		contentPane.add(btnRespuestaC);
		
		JButton btnRespuestaD = new JButton("");
		btnRespuestaD.setIcon(new ImageIcon(getClass().getResource("/Caja Respuestas.png")));
		btnRespuestaD.setOpaque(false);
		btnRespuestaD.setFocusPainted(false);
		btnRespuestaD.setContentAreaFilled(false);
		btnRespuestaD.setBorderPainted(false);
		btnRespuestaD.setBounds(427, 452, 373, 56);
		contentPane.add(btnRespuestaD);
		
		// --- LÍNEAS DE DISEÑO ---
		
		JLabel lblLinea1 = new JLabel("");
		lblLinea1.setIcon(new ImageIcon(getClass().getResource("/Lineas Gorda.png")));
		lblLinea1.setBounds(0, 330, 93, 28);
		contentPane.add(lblLinea1);
		
		JLabel lblLinea2 = new JLabel("");
		lblLinea2.setIcon(new ImageIcon(getClass().getResource("/Lineas Fina.png")));
		lblLinea2.setBounds(0, 418, 76, 12);
		contentPane.add(lblLinea2);
		
		JLabel lblLinea3 = new JLabel("");
		lblLinea3.setIcon(new ImageIcon(getClass().getResource("/Lineas Gorda.png")));
		lblLinea3.setBounds(755, 330, 93, 28);
		contentPane.add(lblLinea3);
		
		JLabel lblLinea4 = new JLabel("");
		lblLinea4.setIcon(new ImageIcon(getClass().getResource("/Lineas Fina.png")));
		lblLinea4.setBounds(-19, 476, 76, 12);
		contentPane.add(lblLinea4);
		
		JLabel lblLinea5 = new JLabel("");
		lblLinea5.setIcon(new ImageIcon(getClass().getResource("/Lineas Fina.png")));
		lblLinea5.setBounds(789, 476, 76, 12);
		contentPane.add(lblLinea5);
		
		JLabel lblLinea6 = new JLabel("");
		lblLinea6.setIcon(new ImageIcon(getClass().getResource("/Lineas Fina.png")));
		lblLinea6.setBounds(789, 418, 76, 12);
		contentPane.add(lblLinea6);
		
		JLabel lblLinea7 = new JLabel("");
		lblLinea7.setIcon(new ImageIcon(getClass().getResource("/Lineas Fina.png")));
		lblLinea7.setBounds(387, 418, 76, 12);
		contentPane.add(lblLinea7);
		
		JLabel lblLinea8 = new JLabel("");
		lblLinea8.setIcon(new ImageIcon(getClass().getResource("/Lineas Fina.png")));
		lblLinea8.setBounds(387, 476, 76, 12);
		contentPane.add(lblLinea8);
		
		// --- FONDO (Añadido al final) ---
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(getClass().getResource("/Fondo .jpg")));
		lblFondo.setBounds(0, 0, 832, 518);
		contentPane.add(lblFondo);
	}
}

