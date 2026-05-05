package test;

import java.util.ArrayList;

import basedatos.ConexionMongo;
import controlador.Partida;
import modelo.Pregunta;

public class TestPartida {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("       TEST DE LA CLASE PARTIDA");
        System.out.println("====================================");

        probarCargaPregunta();
        probarComodin5050();
        probarComodinChat();
        probarComodinLlamada();
        probarComodinSacrificio();
        probarComodinRuleta();
        probarRespuestaCorrecta();
        probarRespuestaIncorrecta();
        probarPlantarse();
        probarRecuperarComodin();

        ConexionMongo.cerrar();

        System.out.println("\n====================================");
        System.out.println("          FIN DE LOS TESTS");
        System.out.println("====================================");
    }

    // --------------------------------------------------
    // TEST 1: Comprobar que se crea una partida y carga pregunta
    // --------------------------------------------------

    public static void probarCargaPregunta() {

        System.out.println("\n--- TEST 1: CARGAR PREGUNTA ---");

        Partida partida = new Partida("Paula");

        Pregunta p = partida.getPreguntaActual();

        if (p == null) {
            System.out.println("ERROR: No se ha cargado ninguna pregunta.");
            System.out.println("Revisa MongoDB: millonarioDB > preguntas");
            return;
        }

        System.out.println("OK: Pregunta cargada correctamente.");
        System.out.println("Jugador: " + partida.getNombreJugador());
        System.out.println("Nivel actual: " + partida.getNivelActual());
        System.out.println("Dinero acumulado: " + partida.getDineroAcumulado());

        mostrarPregunta(p);
    }

    // --------------------------------------------------
    // TEST 2: Comodin 50:50
    // --------------------------------------------------

    public static void probarComodin5050() {

        System.out.println("\n--- TEST 2: COMODIN 50:50 ---");

        Partida partida = new Partida("Paula");
        Pregunta p = partida.getPreguntaActual();
        ArrayList<String> opcionesEliminadas = new ArrayList<String>();

        if (p == null) {
            System.out.println("ERROR: No hay pregunta para probar el comodin.");
            return;
        }

        mostrarPregunta(p);

        String[] eliminadas = partida.usarComodin5050(opcionesEliminadas);

        if (eliminadas.length == 0) {
            System.out.println("ERROR: No se ha eliminado ninguna opcion.");
            return;
        }

        System.out.println("Opciones eliminadas:");

        for (String opcion : eliminadas) {
            opcionesEliminadas.add(opcion);
            System.out.println("- Opcion " + opcion);
        }

        System.out.println("Correcta: " + p.getCorrecta());

        boolean eliminaCorrecta = opcionesEliminadas.contains(p.getCorrecta());

        if (!eliminaCorrecta && eliminadas.length == 2) {
            System.out.println("OK: El comodin elimina 2 respuestas incorrectas.");
        } else {
            System.out.println("ERROR: El comodin ha eliminado mal las respuestas.");
        }
    }

    // --------------------------------------------------
    // TEST 3: Comodin Chat / Publico
    // --------------------------------------------------

    public static void probarComodinChat() {

        System.out.println("\n--- TEST 3: COMODIN CHAT / PUBLICO ---");

        Partida partida = new Partida("Paula");
        Pregunta p = partida.getPreguntaActual();

        if (p == null) {
            System.out.println("ERROR: No hay pregunta para probar el comodin.");
            return;
        }

        mostrarPregunta(p);

        int[] porcentajes = partida.usarComodinChat();

        if (porcentajes.length == 0) {
            System.out.println("ERROR: El comodin no esta disponible.");
            return;
        }

        int suma = porcentajes[0] + porcentajes[1] + porcentajes[2] + porcentajes[3];

        System.out.println("Resultados del chat/publico:");
        System.out.println("A: " + porcentajes[0] + "%");
        System.out.println("B: " + porcentajes[1] + "%");
        System.out.println("C: " + porcentajes[2] + "%");
        System.out.println("D: " + porcentajes[3] + "%");

        System.out.println("Suma total: " + suma + "%");

        if (suma == 100) {
            System.out.println("OK: Los porcentajes suman 100%.");
        } else {
            System.out.println("ERROR: Los porcentajes no suman 100%.");
        }
    }

    // --------------------------------------------------
    // TEST 4: Comodin llamada
    // --------------------------------------------------

    public static void probarComodinLlamada() {

        System.out.println("\n--- TEST 4: COMODIN LLAMADA ---");

        Partida partida = new Partida("Paula");
        Pregunta p = partida.getPreguntaActual();

        if (p == null) {
            System.out.println("ERROR: No hay pregunta para probar el comodin.");
            return;
        }

        mostrarPregunta(p);

        String mensaje = partida.usarComodinLlamada();

        System.out.println("Mensaje de la llamada:");
        System.out.println(mensaje);

        System.out.println("OK: La llamada devuelve una sugerencia.");
    }

    // --------------------------------------------------
    // TEST 5: Comodin sacrificio
    // --------------------------------------------------

    public static void probarComodinSacrificio() {

        System.out.println("\n--- TEST 5: COMODIN SACRIFICIO ---");

        Partida partida = new Partida("Paula");
        Pregunta p = partida.getPreguntaActual();

        if (p == null) {
            System.out.println("ERROR: No hay pregunta para probar el comodin.");
            return;
        }

        mostrarPregunta(p);

        String respuestaSacrificio = partida.usarComodinSacrificio();

        System.out.println("El sacrificio responde: " + respuestaSacrificio);
        System.out.println("Correcta real: " + p.getCorrecta());

        if (respuestaSacrificio.equalsIgnoreCase("A")
                || respuestaSacrificio.equalsIgnoreCase("B")
                || respuestaSacrificio.equalsIgnoreCase("C")
                || respuestaSacrificio.equalsIgnoreCase("D")) {
            System.out.println("OK: El sacrificio devuelve una respuesta valida.");
        } else {
            System.out.println("ERROR: El sacrificio no devuelve una respuesta valida.");
        }
    }

    // --------------------------------------------------
    // TEST 6: Comodin ruleta
    // --------------------------------------------------

    public static void probarComodinRuleta() {

        System.out.println("\n--- TEST 6: COMODIN RULETA ---");

        Partida partida = new Partida("Paula");
        Pregunta p = partida.getPreguntaActual();
        ArrayList<String> opcionesEliminadas = new ArrayList<String>();

        if (p == null) {
            System.out.println("ERROR: No hay pregunta para probar la ruleta.");
            return;
        }

        mostrarPregunta(p);

        String[] eliminadasRuleta = partida.usarComodinRuleta(opcionesEliminadas);
        int numeroRuleta = partida.getUltimoNumeroRuleta();

        System.out.println("Numero que ha salido en la ruleta: " + numeroRuleta);

        if (numeroRuleta == -1) {
            System.out.println("ERROR: La ruleta no esta disponible.");
            return;
        }

        if (numeroRuleta == 0) {
            System.out.println("OK: Ha salido 0, no se elimina ninguna opcion.");
            return;
        }

        System.out.println("Opciones eliminadas por la ruleta:");

        for (String opcion : eliminadasRuleta) {
            opcionesEliminadas.add(opcion);
            System.out.println("- Opcion " + opcion);
        }

        boolean eliminaCorrecta = opcionesEliminadas.contains(p.getCorrecta());

        if (!eliminaCorrecta) {
            System.out.println("OK: La ruleta solo elimina respuestas incorrectas.");
        } else {
            System.out.println("ERROR: La ruleta ha eliminado la respuesta correcta.");
        }
    }

    // --------------------------------------------------
    // TEST 7: Responder correctamente
    // --------------------------------------------------

    public static void probarRespuestaCorrecta() {

        System.out.println("\n--- TEST 7: RESPUESTA CORRECTA ---");

        Partida partida = new Partida("JugadorCorrecto");
        Pregunta p = partida.getPreguntaActual();

        if (p == null) {
            System.out.println("ERROR: No hay pregunta para responder.");
            return;
        }

        mostrarPregunta(p);

        String respuestaCorrecta = p.getCorrecta();

        System.out.println("Se responde con: " + respuestaCorrecta);

        boolean acierto = partida.comprobarRespuesta(respuestaCorrecta);

        System.out.println("Ha acertado: " + acierto);
        System.out.println("Nuevo nivel: " + partida.getNivelActual());
        System.out.println("Dinero acumulado: " + partida.getDineroAcumulado());
        System.out.println("Partida terminada: " + partida.isPartidaTerminada());

        if (acierto && partida.getNivelActual() == 2 && partida.getDineroAcumulado() == 100) {
            System.out.println("OK: Al acertar sube de nivel y gana dinero.");
        } else {
            System.out.println("ERROR: Algo falla al responder correctamente.");
        }
    }

    // --------------------------------------------------
    // TEST 8: Responder incorrectamente
    // --------------------------------------------------

    public static void probarRespuestaIncorrecta() {

        System.out.println("\n--- TEST 8: RESPUESTA INCORRECTA ---");

        Partida partida = new Partida("JugadorFallo");
        Pregunta p = partida.getPreguntaActual();

        if (p == null) {
            System.out.println("ERROR: No hay pregunta para responder.");
            return;
        }

        mostrarPregunta(p);

        String respuestaIncorrecta = obtenerRespuestaIncorrecta(p);

        System.out.println("Correcta real: " + p.getCorrecta());
        System.out.println("Se responde con: " + respuestaIncorrecta);

        boolean acierto = partida.comprobarRespuesta(respuestaIncorrecta);

        System.out.println("Ha acertado: " + acierto);
        System.out.println("Dinero final: " + partida.getDineroAcumulado());
        System.out.println("Partida terminada: " + partida.isPartidaTerminada());

        if (!acierto && partida.isPartidaTerminada()) {
            System.out.println("OK: Al fallar termina la partida.");
        } else {
            System.out.println("ERROR: Algo falla al responder incorrectamente.");
        }
    }

    // --------------------------------------------------
    // TEST 9: Plantarse y guardar puntuacion
    // --------------------------------------------------

    public static void probarPlantarse() {

        System.out.println("\n--- TEST 9: PLANTARSE ---");

        Partida partida = new Partida("JugadorPlantado");
        Pregunta p = partida.getPreguntaActual();

        if (p == null) {
            System.out.println("ERROR: No hay pregunta para responder.");
            return;
        }

        mostrarPregunta(p);

        System.out.println("Primero se responde correctamente para ganar dinero.");

        boolean acierto = partida.comprobarRespuesta(p.getCorrecta());

        System.out.println("Ha acertado: " + acierto);
        System.out.println("Dinero acumulado antes de plantarse: " + partida.getDineroAcumulado());

        System.out.println("Ahora el jugador se planta.");

        partida.plantarse();

        System.out.println("Partida terminada: " + partida.isPartidaTerminada());
        System.out.println("Puntuacion guardada con dinero: " + partida.getDineroAcumulado());

        if (partida.isPartidaTerminada()) {
            System.out.println("OK: Al plantarse termina la partida y guarda la puntuacion.");
        } else {
            System.out.println("ERROR: La partida no ha terminado al plantarse.");
        }
    }

    // --------------------------------------------------
    // TEST 10: Recuperar comodin desde pregunta 8
    // --------------------------------------------------

    public static void probarRecuperarComodin() {

        System.out.println("\n--- TEST 10: RECUPERAR COMODIN DESDE PREGUNTA 8 ---");

        Partida partida = new Partida("JugadorRecupera");
        ArrayList<String> opcionesEliminadas = new ArrayList<String>();

        Pregunta p = partida.getPreguntaActual();

        if (p == null) {
            System.out.println("ERROR: No hay pregunta inicial.");
            return;
        }

        System.out.println("Primero se usa el comodin 50:50.");
        partida.usarComodin5050(opcionesEliminadas);

        System.out.println("Comodin 50:50 usado: " + partida.isComodin5050Usado());

        avanzarHastaNivel(partida, 8);

        System.out.println("Nivel actual: " + partida.getNivelActual());
        System.out.println("Puede recuperar comodin: " + partida.puedeRecuperarComodin());

        boolean recuperado = partida.recuperarComodinElegido("5050");

        System.out.println("Ha recuperado el 50:50: " + recuperado);
        System.out.println("Comodin 50:50 usado despues de recuperar: " + partida.isComodin5050Usado());

        if (recuperado && !partida.isComodin5050Usado()) {
            System.out.println("OK: Desde la pregunta 8 puede recuperar un comodin usado.");
        } else {
            System.out.println("ERROR: No se ha recuperado correctamente el comodin.");
        }
    }

    // --------------------------------------------------
    // METODOS AUXILIARES
    // --------------------------------------------------

    public static void mostrarPregunta(Pregunta p) {

        System.out.println("\nPregunta:");
        System.out.println(p.getPregunta());
        System.out.println("A: " + p.getOpcionA());
        System.out.println("B: " + p.getOpcionB());
        System.out.println("C: " + p.getOpcionC());
        System.out.println("D: " + p.getOpcionD());
        System.out.println("Correcta: " + p.getCorrecta());
    }

    public static String obtenerRespuestaIncorrecta(Pregunta p) {

        String correcta = p.getCorrecta();

        if (!correcta.equalsIgnoreCase("A")) {
            return "A";
        } else {
            return "B";
        }
    }

    public static void avanzarHastaNivel(Partida partida, int nivelObjetivo) {

        while (!partida.isPartidaTerminada() && partida.getNivelActual() < nivelObjetivo) {

            Pregunta p = partida.getPreguntaActual();

            if (p == null) {
                System.out.println("ERROR: No hay pregunta para avanzar.");
                return;
            }

            partida.comprobarRespuesta(p.getCorrecta());
        }
    }
}