package WritingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Pattern;

public class Ejer2 {
    public static void main(String[] args) {
        Path f1 = Path.of("files/articulo.txt");
        Path fin = Path.of("files/prepo.txt");

        try {
            Pattern p = Pattern.compile("\\p{javaWhitespace}+");
            List<String> prepos =  Files.readAllLines(f1)
                    .stream()
                    .flatMap(cadena -> p.splitAsStream(cadena))
                    .map(palabra -> palabra.replaceAll("\\p{Punct}", ""))
                    .filter(palabra -> palabra.length()<5)
                    .toList();
            if (!Files.exists(fin)){
                Files.createFile(fin);
            }
            Files.write(fin,prepos, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
