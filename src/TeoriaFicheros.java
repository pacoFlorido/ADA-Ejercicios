import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class TeoriaFicheros {
    public static void main(String[] args) {
        String fileName = "ada.csv";

        Path path = Path.of(fileName);
        infoPath(path);
    }

    public static void useFile(String fileName){
        File file = new File(fileName);
        boolean exists = file.exists();
        if (exists) {
            System.out.println("Borrando");
            exists = !file.delete();
        }

        if (!exists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("No se ha podido crear");
            }
        }
    }
    public static void usePath(String fileName){
        Path path = Path.of(fileName);
        boolean exists = Files.exists(path);
        if (exists) {
            System.out.println("Borrando");
            try {
                Files.delete(path);
                exists = false;
            } catch (IOException e) {
                System.out.println("Fallo en la creacion del archivo");
            }
        }

        if (!exists) {
            try {
                Files.createFile(path);
                Files.writeString(path,"edatos fatos");
                Files.readAllLines(path).forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("No se ha podido crear");
            }
        }
    }
    public static void infoPath(Path path){
        System.out.println(path.getFileName());
        System.out.println(path.getParent());
        System.out.println(path.toAbsolutePath().getRoot());
        System.out.println(path.getRoot());
        System.out.println(path.isAbsolute());

        try {
            Map<String, Object> att = Files.readAttributes(path,"*");
            att.entrySet().forEach(System.out::println);
            System.out.println("********");
            System.out.println(Files.probeContentType(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
