	import java.awt.*;
	import javax.swing.*;
	import javax.swing.border.EmptyBorder;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	
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
			try {
				setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Logo Grande.png")));
			} catch (Exception e) {
				System.out.println("Error al cargar icono de ventana");
			}

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 846, 556);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblLogoGrande = new JLabel("");
			lblLogoGrande.setIcon(new ImageIcon(getClass().getResource("/Logo Grande.png")));
			lblLogoGrande.setBounds(262, 49, 300, 320);
			contentPane.add(lblLogoGrande);
			
			JButton btnJugar = new JButton("Jugar");
			btnJugar.setFont(new Font("Arial", Font.BOLD, 18));
			btnJugar.setForeground(Color.WHITE);
			btnJugar.setHorizontalTextPosition(SwingConstants.CENTER); 
			
			btnJugar.setIcon(new ImageIcon(getClass().getResource("/Boton Jugar.png")));
			btnJugar.setOpaque(false);
			btnJugar.setFocusPainted(false);
			btnJugar.setBorderPainted(false);
			btnJugar.setContentAreaFilled(false);
			btnJugar.setBorder(null);
			
			btnJugar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PantallaJuego ventanaJuego = new PantallaJuego();
					ventanaJuego.setVisible(true);
					dispose();
				}
			});
			btnJugar.setBounds(332, 379, 150, 55);
			contentPane.add(btnJugar);
			
			JLabel lblPersonaje2 = new JLabel("");
			lblPersonaje2.setIcon(new ImageIcon(getClass().getResource("/Personaje2.png")));
			lblPersonaje2.setBounds(581, 256, 178, 283);
			contentPane.add(lblPersonaje2);
			
			JLabel lblPersonaje1 = new JLabel("");
			lblPersonaje1.setIcon(new ImageIcon(getClass().getResource("/Personaje1 .png")));
			lblPersonaje1.setBounds(46, 253, 210, 276);
			contentPane.add(lblPersonaje1);
			
			JLabel lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(getClass().getResource("/Fondo .jpg")));
			lblFondo.setBounds(0, 0, 841, 539);
			contentPane.add(lblFondo);
		}
	}

