import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReading {
    public static void main(String[] args) {
        //tipos fichero: bytes, caracteres
        //bytes: InputStream, InputReaderStream
        //caracteres: FileReader, BufferedReader

        /**
         * No se utiliza, poco eficiente
         *
         * FileReader fr = new FileReader("files/file.txt");
         *             int data;
         *             while ((data = fr.read()) != -1){
         *                 System.out.println((char) data);
         *             }
         */

        try (BufferedReader br = new BufferedReader( new FileReader("files/file.txt"))){
            br.lines().forEach(System.out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
