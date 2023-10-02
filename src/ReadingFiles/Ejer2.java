package ReadingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejer2 {
    public static void main(String[] args) {
        Path path = Path.of("files/file.txt");
        try(Stream<String> lineas = Files.lines(path)){
            Pattern p = Pattern.compile("\\p{javaWhitespace}+");

            System.out.println(lineas
                    .flatMap(linea -> p.splitAsStream(linea))
                    .filter(palabra -> palabra.length()<4)
                    .count());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
