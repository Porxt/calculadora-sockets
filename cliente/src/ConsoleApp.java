import java.io.IOException;
import java.util.Scanner;
import connection.Client;
import util.Expression;

public class ConsoleApp {
    public void run() {
        Scanner reader = new Scanner(System.in);
        String input;
        Expression expression;
        Client client;
        do {
            System.out.println("Ingrese la operacion matematica que desea realizar");
            System.out.println("\n\tPotenciacion: a ** b");
            System.out.println("\tModulo: a % b");
            System.out.println("\tSuma: a + b");
            System.out.println("\tResta: a - b");
            System.out.println("\tMultiplicacion: a * b");
            System.out.println("\tDivision: a / b");
            System.out.println("\nPara salir escriba \"exit\".");
            System.out.print("Operacion: ");
            
            input = reader.nextLine();
            System.out.println();
            expression = Expression.build(input.replaceAll(" ", ""));
            if(expression != null) {
                try {
                    client = new Client();
                    System.out.println(
                        expression.number1 + " " + expression.operator + " "
                        + expression.number2 + " = " + client.sendOperation(expression)
                    );
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println();
        } while(!input.equals("exit"));
        reader.close();
    }
}
