import java.util.Scanner;

import basedatos.ConexionMongo;
import controlador.Partida;
import modelo.Pregunta;
import java.util.ArrayList;

public class PrincipalApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("=================================");
		System.out.println("       BIENVENIDO AL CONCURSILLO");
		System.out.println("=================================");

		System.out.print("Introduce tu nombre: ");
		String nombre = sc.nextLine();

		Partida partida = new Partida(nombre);
		ArrayList<String> opcionesEliminadas = new ArrayList<String>();

		while (!partida.isPartidaTerminada()) {

			Pregunta p = partida.getPreguntaActual();

			if (p == null) {
				System.out.println("No se ha podido cargar ninguna pregunta.");
				break;
			}

			System.out.println("\n---------------------------------");
			System.out.println("Jugador: " + partida.getNombreJugador());
			System.out.println("Nivel: " + partida.getNivelActual());
			System.out.println("Dinero acumulado: " + partida.getDineroAcumulado());
			System.out.println("---------------------------------");

			System.out.println("\nPregunta:");
			System.out.println(p.getPregunta());
			mostrarOpciones(p, opcionesEliminadas);

			System.out.println("\nOpciones:");
			System.out.println("A / B / C / D - Responder");
			System.out.println("1 - Comodin 50:50");
			System.out.println("2 - Comodin Chat/Publico");
			System.out.println("3 - Comodin Llamada");
			System.out.println("4 - Comodin Sacrificio");
			System.out.println("5 - Comodin Ruleta (elimina 0, 1, 2 o 3 respuestas incorrectas)");
			System.out.println("6 - Comodin Mago (disponible desde la pregunta 7)");
			System.out.println("6 - Comodin Mago (disponible desde la pregunta 7)");
			System.out.println("7 - Recuperar un comodin usado (disponible desde la pregunta 8)");
			System.out.println("P - Plantarse");

			System.out.print("\nElige una opcion: ");
			String opcion = sc.nextLine().toUpperCase();

			switch (opcion) {
				case "A":
				case "B":
				case "C":
				case "D":

					if (opcionesEliminadas.contains(opcion)) {
						System.out.println("No puedes elegir esa opcion porque ha sido eliminada por un comodin.");
						break;
					}

					boolean acierto = partida.comprobarRespuesta(opcion);

					if (acierto) {
						System.out.println("Correcto!");

						// IMPORTANTE:
						// Como se carga una pregunta nueva, limpiamos las opciones eliminadas
						opcionesEliminadas.clear();

					} else {
						System.out.println("Incorrecto.");
						System.out.println("La respuesta correcta era: " + p.getCorrecta());
					}

					break;

				case "1":
					String[] eliminadas = partida.usarComodin5050(opcionesEliminadas);
					if (eliminadas.length == 0) {
						System.out.println("El comodin 50:50 ya no esta disponible.");
					} else {
						System.out.println("Opciones eliminadas:");

						for (String eliminada : eliminadas) {
							opcionesEliminadas.add(eliminada);
							System.out.println("- " + eliminada);
						}
					}
					break;

				case "2":
					int[] porcentajes = partida.usarComodinChat();

					if (porcentajes.length == 0) {
						System.out.println("El comodin del chat ya no esta disponible.");
					} else {
						System.out.println("Resultados del chat:");
						System.out.println("A: " + porcentajes[0] + "%");
						System.out.println("B: " + porcentajes[1] + "%");
						System.out.println("C: " + porcentajes[2] + "%");
						System.out.println("D: " + porcentajes[3] + "%");
					}
					break;

				case "3":
					System.out.println(partida.usarComodinLlamada());
					break;

				case "4":
					String respuestaSacrificio = partida.usarComodinSacrificio();

					if (respuestaSacrificio.equals("")) {
						System.out.println("El comodin sacrificio ya no esta disponible.");
					} else {
						System.out.println("El sacrificio responde: " + respuestaSacrificio);

						boolean aciertoSacrificio = partida.comprobarRespuesta(respuestaSacrificio);

						if (aciertoSacrificio) {
							System.out.println("El sacrificio ha acertado.");

							// Como cambia de pregunta, limpiamos el 50:50
							opcionesEliminadas.clear();

						} else {
							System.out.println("El sacrificio ha fallado.");
							System.out.println("La respuesta correcta era: " + p.getCorrecta());
						}
					}
					break;

				case "5":
					String[] eliminadasRuleta = partida.usarComodinRuleta(opcionesEliminadas);
					int numeroRuleta = partida.getUltimoNumeroRuleta();

					if (numeroRuleta == -1) {
						System.out.println("El comodin ruleta ya no esta disponible.");
					} else if (numeroRuleta == 0) {
						System.out.println("La ruleta ha sacado 0.");
						System.out.println("No se elimina ninguna respuesta.");
					} else {
						System.out.println("La ruleta ha sacado " + numeroRuleta + ".");

						if (eliminadasRuleta.length == 0) {
							System.out.println("No quedan respuestas incorrectas para eliminar.");
						} else {
							System.out.println("Opciones eliminadas por la ruleta:");

							for (String eliminada : eliminadasRuleta) {
								if (!opcionesEliminadas.contains(eliminada)) {
									opcionesEliminadas.add(eliminada);
								}

								System.out.println("- " + eliminada);
							}
						}
					}
					break;

				case "6":
					boolean cambiada = partida.usarComodinMago();

					if (cambiada) {
						System.out.println("El mago ha cambiado la pregunta.");

						// Como cambia de pregunta, limpiamos el 50:50
						opcionesEliminadas.clear();

					} else {
						if (partida.getNivelActual() < 7) {
							System.out.println("El comodin del mago se desbloquea a partir de la pregunta 7.");
						} else if (partida.isComodinMagoUsado()) {
							System.out.println("El comodin del mago ya ha sido usado.");
						} else {
							System.out.println("No hay otra pregunta disponible de la misma categoria y nivel.");
						}
					}
				case "P":
					partida.plantarse();
					System.out.println("Te has plantado.");
					break;

				case "7":

					if (!partida.puedeRecuperarComodin()) {
						System.out.println("Todavia no puedes recuperar comodines o ya has usado esta recuperacion.");
						break;
					}

					System.out.println("Que comodin quieres recuperar?");
					System.out.println("5050");
					System.out.println("chat");
					System.out.println("llamada");
					System.out.println("sacrificio");
					System.out.println("ruleta");
					System.out.println("mago");

					System.out.print("Escribe el comodin: ");
					String comodinElegido = sc.nextLine();

					boolean recuperado = partida.recuperarComodinElegido(comodinElegido);

					if (recuperado) {
						System.out.println("Has recuperado el comodin: " + comodinElegido);
					} else {
						System.out.println("No se ha podido recuperar ese comodin.");
						System.out.println("Recuerda que solo puedes recuperar comodines que ya hayas usado.");
					}

					break;

				default:
					System.out.println("Opcion no valida.");
					break;

			}
		}

		System.out.println("\n=================================");
		System.out.println("          FIN DE LA PARTIDA");
		System.out.println("=================================");
		System.out.println("Jugador: " + partida.getNombreJugador());
		System.out.println("Dinero final: " + partida.getDineroAcumulado());

		ConexionMongo.cerrar();
		sc.close();
	}

	public static void mostrarOpciones(Pregunta p, ArrayList<String> opcionesEliminadas) {

		if (!opcionesEliminadas.contains("A")) {
			System.out.println("A: " + p.getOpcionA());
		} else {
			System.out.println("A: [opcion eliminada]");
		}

		if (!opcionesEliminadas.contains("B")) {
			System.out.println("B: " + p.getOpcionB());
		} else {
			System.out.println("B: [opcion eliminada]");
		}

		if (!opcionesEliminadas.contains("C")) {
			System.out.println("C: " + p.getOpcionC());
		} else {
			System.out.println("C: [opcion eliminada]");
		}

		if (!opcionesEliminadas.contains("D")) {
			System.out.println("D: " + p.getOpcionD());
		} else {
			System.out.println("D: [opcion eliminada]");
		}
	}
}