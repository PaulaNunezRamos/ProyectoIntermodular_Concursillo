package basedatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {

    private static MongoClient cliente;
    private static MongoDatabase baseDatos;

    public static MongoDatabase conectar() {

        if (cliente == null) {

            String uri = "mongodb+srv://app_millonario:app_millonario@concursillo.5owslqy.mongodb.net/?retryWrites=true&w=majority&appName=Concursillo";

            cliente = MongoClients.create(uri);

            baseDatos = cliente.getDatabase("millonarioDB");
        }

        return baseDatos;
    }

    public static void cerrar() {

        if (cliente != null) {
            cliente.close();
            cliente = null;
            baseDatos = null;
        }
    }
}