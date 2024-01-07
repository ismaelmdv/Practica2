import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Ejercicio1 {

    public static void main(String[] args) {

        List<String> lista= List.of(args);
        Process p;
        ProcessBuilder pb;
        FileWriter fichero;
        PrintWriter escritura;
        Scanner salida;
        String texto;
        try {

            pb = new ProcessBuilder(lista);
            //pb.redirectOutput(new File("output.txt"));
            pb.redirectError(new File("error.txt"));

            p = pb.start();
            salida = new Scanner(p.getInputStream());

            fichero=new FileWriter("output.txt");
            escritura = new PrintWriter(fichero);

            while (salida.hasNextLine()) {
                texto=salida.nextLine();
                escritura.println(texto);
                System.out.println(texto);
            }
            p.waitFor(2, TimeUnit.SECONDS);

            escritura.close();
            fichero.close();
            salida.close();

        }catch (IOException e){
            System.out.println("Error en la ejecución");
        }catch (InterruptedException ex){
            System.out.println("El tiempo se a acabado");

        }
    }
}
