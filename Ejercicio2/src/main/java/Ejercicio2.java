import java.io.*;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {

        Process p;
        ProcessBuilder pb;
        FileWriter fichero;
        PrintWriter escritura;
        BufferedReader lectura;
        OutputStream salida;

        Scanner scanner=new Scanner(System.in);

        String cadena,cadenaInvertida;
        System.out.println("A partir de ahora todas las cadenas que me introduzcas y pulses enter yo les daré la vuelta, cuando termines, escribe la cadena: stop");

        try {
            pb=new ProcessBuilder("java", "Reverse");
            fichero=new FileWriter("reverses.txt");
            pb.redirectError(new File("Error.txt"));
            p= pb.start();

            cadena= scanner.nextLine();

            lectura=new BufferedReader(new InputStreamReader(p.getInputStream()));
            salida = p.getOutputStream();
            escritura=new PrintWriter(fichero);

            while (!cadena.equals("stop")){

                cadena=cadena+System.lineSeparator();
                salida.write(cadena.getBytes());
                salida.flush();

                cadenaInvertida= lectura.readLine();
                if (cadenaInvertida!=null){
                    System.out.println("La cadena dada la vuelta es: "+cadenaInvertida);
                    escritura.write(cadenaInvertida+"\n");
                }
                cadena= scanner.nextLine();

            }
            System.out.println("Encantado de haber jugado contigo, un saludo.");
            fichero.close();
            escritura.close();
            salida.close();
            lectura.close();

        }catch (IOException e){
            System.out.println("Error en ejecucion");
        }

    }
}
