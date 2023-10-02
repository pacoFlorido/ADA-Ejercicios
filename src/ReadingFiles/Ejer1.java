package ReadingFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Ejer1 {
    public static void main(String[] args) {
        Path path = Path.of("files/file.txt");
        int sumaTamanyos = 0;
        try {
            List<String> lineas = Files.readAllLines(path);
            for (String linea : lineas){
                sumaTamanyos += linea.length();
            }
            System.out.println("Promedio tamaño de las lineas: " + (sumaTamanyos/ lineas.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
