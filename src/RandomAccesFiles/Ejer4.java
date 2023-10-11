package RandomAccesFiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejer4 {
    public static void main(String[] args) {
        //Cambiar todas las 'a' por 'i'
        Path path = Path.of("files/RAF/convertirAenI.txt");

        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(path.toFile(),"rw")){
            long posInicial;
            while (raf.length() != raf.getFilePointer()){
                posInicial = raf.getFilePointer();
                String linea = raf.readLine().replaceAll("a","i");

                raf.seek(posInicial);

                raf.writeUTF(linea);
                raf.writeChar('\n');

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
