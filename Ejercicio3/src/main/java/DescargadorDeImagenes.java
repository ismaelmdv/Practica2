import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescargadorDeImagenes {

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://terminalcheatsheet.com/sample-file.jpg";
        String html = descargarHTML(url);
        String[] urlsDeImagenes = extraerUrlsDeImagenes(html);

        // TODO: Crear y gestionar procesos para descargar cada imagen
        // TODO: Asegurarse de que todos los procesos de descarga finalicen correctamente

        ProcessBuilder pb;
        InputStream is;
        OutputStream os;

        Scanner salida;
        try {
            int i=0;
            while (true) {
                if(urlsDeImagenes.length!=i){
                    System.out.println(urlsDeImagenes[i]);
                    pb = new ProcessBuilder("curl",urlsDeImagenes[i]);
                    pb.redirectError(new File("ErrorEj2.txt"));
                    pb.start();

                    URL urlIm=new URL(urlsDeImagenes[i]);
                    String fileName = urlIm.getFile();
                    String destName = "./figures" + fileName.substring(fileName.lastIndexOf("/"));


                    is=urlIm.openStream();
                    os=new FileOutputStream(destName);

                    byte[] b=new byte[2048];
                    int length;

                    while ((length = is.read(b)) != -1) {
                        os.write(b,0 , length);
                    }

                    i++;

                }else
                    break;
            }
            Thread.sleep(1000);





        }catch (IOException e){
            System.out.println("Error en la ejecución");
        }catch (InterruptedException ex){
            System.out.println("Error en el tiempo de ejecución");
        }


    }

    public static String descargarHTML(String url) throws IOException, InterruptedException {
        ProcessBuilder curlPB = new ProcessBuilder("/usr/bin/curl", "-s", "-X", "GET", url);
        Process proceso = curlPB.start();

        StringBuilder html = new StringBuilder();
        try (Scanner scanner = new Scanner(proceso.getInputStream())) {
            while (scanner.hasNextLine()) {
                html.append(scanner.nextLine()).append(System.lineSeparator());
            }
        }
        proceso.waitFor();
        return html.toString();
    }

    public static String[] extraerUrlsDeImagenes(String html) {
        List<String> urlsDeImagenes = new ArrayList<>();
        Pattern pattern = Pattern.compile("<img [^>]*src=[\"']([^\"']+)[\"'][^>]*>");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            urlsDeImagenes.add(matcher.group(1));
        }

        return urlsDeImagenes.toArray(new String[0]);
    }
}
