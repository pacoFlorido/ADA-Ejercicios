import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ListarFicheros {
    //list, walk, and find
    public static void main(String[] args) {
        Path path = Path.of(".");

        //list -> no es recursivo, no entra a subdirectorios
        System.out.println("\t\t\t\t\t\t\t\t\tLIST");
        try (Stream<Path> paths = Files.list(path)) {
            paths.forEach(path1 -> System.out.println(listDir(path1)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //walk -> es recursivo, entra a todos los subdirectorios
        System.out.println("\t\t\t\t\t\t\t\t\tWALK");
        try (Stream<Path> paths = Files.walk(path)) {
            paths
                    .filter(path1 -> Files.isRegularFile(path1))
                    .forEach(path1 -> System.out.println(listDir(path1)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //find -> es recursivo, entra a todos los subdirectorios
        System.out.println("\t\t\t\t\t\t\t\t\tFIND");
        try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE, (p, attr) -> attr.isRegularFile() && attr.size()>300)) {
            paths
                    .forEach(path1 -> System.out.println(listDir(path1)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String listDir(Path path){
        try {
            boolean isDir = Files.isDirectory(path);
            return Files.getLastModifiedTime(path) + "\t\t"
                    + (isDir ? "<DIR>\t\t" : "\t\t\t")
                    + Files.size(path) + "\t\t\t" + path;
        } catch (IOException e) {
            return "";
        }
    }
}
