import java.util.Scanner;
import util.Expression;

public class ConsoleApp {
    public void run() {
        Scanner reader = new Scanner(System.in);
        String input;
        Expression expression;
        do {
            System.out.println("Ingrese la expresión matematica que desea realizar");
            System.out.println("\n\tPotenciación: a ** b");
            System.out.println("\tModulo: a % b");
            System.out.println("\tSuma: a + b");
            System.out.println("\tResta: a - b");
            System.out.println("\tMultiplicación: a * b");
            System.out.println("\tDivisión: a / b");
            System.out.println("\nPara salir escriba \"exit\".");
            System.out.print("Input: ");
            
            input = reader.nextLine();
            expression = Expression.build(input.replaceAll(" ", ""));
            System.out.println(expression);
        } while(!input.equals("exit"));
        reader.close();
    }
}
