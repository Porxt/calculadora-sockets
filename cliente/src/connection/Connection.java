package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
    private final int PORT = 20000; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected Socket cs; //Socket del cliente
    protected DataOutputStream output; // Flujo de datos de salida
    protected DataInputStream input; // Flujo de datos de entrada

    public Connection() throws IOException {
        cs = new Socket(HOST, PORT);
    }
}
