package connection;

import util.Expression;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Client extends Connection {
    public Client() throws IOException { super(); }

    public String start(Expression expression) {
        String response;
        try {
            // Flujo de datos hacia el servidor
            output = new DataOutputStream(cs.getOutputStream());

            // Flujo de datos desde el servidor
            input = new DataInputStream(cs.getInputStream());

            output.writeUTF(expression.toString());

            response = input.readUTF();

            cs.close();//Fin de la conexión

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        }

        return response;
    }
}
