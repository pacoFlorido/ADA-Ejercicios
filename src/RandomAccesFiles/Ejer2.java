package RandomAccesFiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejer2 {
    public static void main(String[] args) {
        Path path = Path.of("files/RAF/randomAccessFile2.txt");

        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(path.toFile(),"rw")){
            //a
            raf.writeInt(1);
            raf.writeChar('A');
            raf.writeDouble(2.3);

            //b
            raf.seek(0);
            //c
            System.out.println(raf.readInt() + " " + raf.readChar() + " " + raf.readDouble());

            //d
            raf.seek(4);
            System.out.println(raf.readChar());

            //e
            raf.seek(raf.length());
            raf.writeChar('A');
            raf.writeBoolean(true);

            //f
            raf.seek(14);
            System.out.println(raf.readChar());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
