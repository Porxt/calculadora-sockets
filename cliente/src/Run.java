public class Run {
    public static void main(String[] args) throws Exception {
        if(args.length == 1 && (args[0].equals("--gui") || args[0].equals("-g"))) {
            new GraphicApp();
        } else if(args.length == 0) {
            new ConsoleApp().run();
        } else {
            System.out.println("Argumentos incorrectos");
        }
    }
}
