package RandomAccesFiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Ejer3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String palabra1, palabra2;
        System.out.println("##Introduce dos palabras con la misma longitud##");
        System.out.print("Introduce la palabra a buscar: ");
        palabra1 = sc.nextLine();

            System.out.print("Introduce una palabra con la misma longitud que la primera (" + palabra1.length() + "):");
            palabra2 = sc.nextLine();
        if (palabra2.length() != palabra1.length()) {
            do {
                System.out.print("Longitud erronea, vuelve a introducir la palabra o introduce 'f' para salir:");
                palabra2 = sc.nextLine();
            } while (palabra2.length() != palabra1.length() || !palabra2.equals("f"));
        }

        Path path = Path.of("files/RAF/randomCambiarPalabra.txt");

        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try (RandomAccessFile raf = new RandomAccessFile(path.toFile(),"rw")){

            long posicionAntesDeLeer;
            while (raf.length() != raf.getFilePointer()){
                posicionAntesDeLeer = raf.getFilePointer();
                String linea = raf.readLine().replaceAll(palabra1,palabra2);
                raf.seek(posicionAntesDeLeer);
                raf.writeUTF(linea);
                raf.writeChar('\n');
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
