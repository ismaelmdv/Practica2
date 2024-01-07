import java.util.Scanner;

public class Reverse {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        String cadena,cadenaInvertida;
        cadena = scanner.nextLine();
        while (!cadena.equals("stop")){
            StringBuilder stringBuilder=new StringBuilder(cadena);
            cadenaInvertida=stringBuilder.reverse().toString();
            System.out.println(cadenaInvertida);
            cadena = scanner.nextLine();
        }
    }
}
