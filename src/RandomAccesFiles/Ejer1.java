package RandomAccesFiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejer1 {
    public static void main(String[] args) {
        Path path = Path.of("files/RAF/randomAccesFile.txt");

        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(path.toFile(),"rw")){
            raf.writeUTF("hola");
            //El String "hola" en este caso ocupa 6 bytes porque el método writeUTF siempre escribe primero dos bytes y luego cada letra ocupará un byte.
            raf.writeUTF("adios adios adios");
            //El String "adios adios adios" ocupa 19 por lo mismo que el anterior.
            raf.seek(6);
            System.out.println(raf.readUTF() + raf.getFilePointer() + " " + raf.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
