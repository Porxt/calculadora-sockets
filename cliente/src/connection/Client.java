package connection;

import util.Expression;
// import java.io.*;
import java.net.*;

public class Client {

    private final int PORT = 20000;
    private final String HOST = "localhost";
    private DatagramSocket cs;

    public Client() throws SocketException {
        cs = new DatagramSocket();
    }

    public String start(Expression expression) {
        DatagramPacket message;
        byte[] buffer;
        DatagramPacket response;
        try {
            // Send message
            message = new DatagramPacket(
                expression.toString().getBytes(),
                expression.toString().length(),
                InetAddress.getByName(HOST),
                PORT
            );
            cs.send(message);

            // Receive response
            buffer = new byte[1000];
            response = new DatagramPacket(buffer, buffer.length);
            cs.receive(response);

            // Prints response
            System.out.println("Respuesta: " + new String(response.getData()));

            // Close socket
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = null;
        }

        return new String(response.getData());
    }
}
