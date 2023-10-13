package ObjetosyJSON;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Ejer2 {
    public static void main(String[] args) {
        Sede sede = new Sede("Universidad Tecnológica"
                ,"Ciudad Universitaria"
                , List.of("Ingeniería Informática","Ciencias de la Computación","Ingeniería Eléctrica"));

        List<Proyecto> proyectos = List.of(
                new Proyecto("Sistema de Gestión Escolar"
                        ,List.of("Ana Gómez", "Luis Rodríguez", "María Sánchez")
                        ,true
                        ,15000),
                new Proyecto("Investigación en Energías Renovables"
                        ,List.of("Pedro Martínez", "Laura Fernández")
                        ,false
                        ,47000)
        );

        Evento eventos = new Evento(List.of(
                new Conferencia("Tecnologías Emergentes"
                        ,List.of("Dr. Carlos Ramírez", "Dra. Sofia López")
                        ,"2023-3-20"),
                new Conferencia("Innovación en Energías"
                        ,List.of("Ing. Marta González", "Dr. Juan Pérez")
                        ,"2023-05-10")));

        List<Object> objetos = List.of(sede,proyectos,eventos);

        Path path = Path.of("files/escrituraObjetos/universidad.dat");

        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        writeObject(path,objetos);
        readObject(path);

    }
    public static void writeObject(Path path, Object o){
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))){
            oos.writeObject(o);
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
