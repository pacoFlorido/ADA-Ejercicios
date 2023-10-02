package ReadingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class Ejer4 {
    public static void main(String[] args) {
        Path texto = Path.of("files/texto.txt");
        Path info = Path.of("files/info_filtrado.txt");
        if (!Files.exists(info)){
            try {
                Files.createFile(info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (Stream<String> lineas = Files.lines(texto)){
            lineas
                    .filter(linea -> linea.startsWith("INFO:"))
                    .forEach(linea -> {
                        try {
                            Files.writeString(info,linea + "\n",StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
