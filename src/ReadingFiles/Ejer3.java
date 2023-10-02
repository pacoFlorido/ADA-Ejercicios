package ReadingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejer3 {
    public static void main(String[] args) {
        Path path = Path.of("files/numeros.txt");

        try {
            String[] numeros = Files.readString(path).split(",");
            int suma = 0;
            for (String s: numeros){
                suma+=Integer.parseInt(s);
            }
            System.out.println("Media: " + (suma/numeros.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
