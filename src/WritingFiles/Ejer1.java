package WritingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Ejer1 {
    public static void main(String[] args) {
        Path f1 = Path.of("files/texto.txt");
        Path f2 = Path.of("files/numeros.txt");
        Path fileFinal = Path.of("files/fileFinal.txt");

        try {
            List<String> file1 = Files.readAllLines(f1);
            List<String> file2 = Files.readAllLines(f2);
            if (!Files.exists(fileFinal)){
                Files.createFile(fileFinal);
            }
            Files.write(fileFinal,file1, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(fileFinal,file2, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
