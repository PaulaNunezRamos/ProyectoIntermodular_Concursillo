package Interfaz;

import java.awt.EventQueue;

public class PrincipalApp {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrin ventana = new PantallaPrin();
					ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}