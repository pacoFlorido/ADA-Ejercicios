import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

/**
 * Cambios incompatibles serializacion/deserializacion
 * - Cambiar el tipo de datos de un campo primitivo
 * - Eliminar atributos de la clase
 * - Cambiar de no estático - a estático
 *
 * Cambios compatibles
 * - Añadir campos
 * - Cambiar el modificador, public/private/protected
 * - Cambiar de estático - a no estático
 */
class Player implements Serializable {
    //Campo de versión de la clase
    private static final long serialVersionUID = 1L;

    //Indica que el campo no debe serializarse
    private transient long playerID;


    private String name;
    private long score;
    private List<String> weapons;

    public Player(String name, int score, List<String> weapons) {
        this.name = name;
        this.score = score;
        this.weapons = weapons;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", weapons=" + weapons +
                '}';
    }
}
public class ReadWriteObjects {
    public static void main(String[] args) {
        Path path = Path.of("files/escrituraObjetos/objetos.dat");
        Player p1 = new Player("Juni",100,List.of("Espada","Pistola","Cañon"));
        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //writeObject(path,p1);
        readObject(path);
    }
    public static void writeObject(Path path, Player player){
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))){
            oos.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readObject(Path path){
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))){
            System.out.println(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
