package connection;

import util.Expression;

import java.io.*;
import java.net.*;

public class Client {

    private final int PORT = 20000;
    private final String HOST = "localhost";
    private DatagramSocket cs;

    public Client() throws SocketException {
        cs = new DatagramSocket();
    }

    public String start(Expression expression) throws IOException {
        DatagramPacket message;
        byte[] buffer;
        DatagramPacket response;
        
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

        // Close socket
        cs.close();

        return new String(response.getData());
    }
}
