package ObjetosyJSON;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

class Sede implements Serializable {
    private String nombre, ubicacion;
    private List<String> carreras;

    public Sede(String nombre, String ubicacion, List<String> carreras) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.carreras = carreras;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", carreras=" + carreras +
                '}';
    }
}
class Proyecto implements Serializable {
    private String nombre;
    private List<String> equipo;
    private boolean activo;
    private int presupuesto;

    public Proyecto(String nombre, List<String> equipo, boolean activo, int presupuesto) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.activo = activo;
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "nombre='" + nombre + '\'' +
                ", equipo=" + equipo +
                ", activo=" + activo +
                ", presupuesto=" + presupuesto +
                '}';
    }
}
class Evento implements Serializable {
    private List<Conferencia> conferencias;

    public Evento(List<Conferencia> conferencias) {
        this.conferencias = conferencias;
    }

    public List<Conferencia> getConferencias() {
        return conferencias;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "conferencias=" + conferencias +
                '}';
    }
}
class Conferencia implements Serializable {
    private String nombre;
    private List<String> ponentes;
    private String fecha;

    public Conferencia(String nombre, List<String> ponentes, String fecha) {
        this.nombre = nombre;
        this.ponentes = ponentes;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Conferencia{" +
                "nombre='" + nombre + '\'' +
                ", ponentes=" + ponentes +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
public class Ejer1 {
    public static void main(String[] args) {
        Sede sede = new Sede("Universidad Tecnológica"
                ,"Ciudad Universitaria"
                ,List.of("Ingeniería Informática","Ciencias de la Computación","Ingeniería Eléctrica"));

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

        Gson gson = new Gson();
        Path path = Path.of("files/escrituraObjetos/universidad.json");

        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Files.writeString(path,"{\n\t\"sede\":\n");
            Files.writeString(path,gson.toJson(sede) + ",", StandardOpenOption.APPEND);
            Files.writeString(path,"\n\t\"proyectos\":\n", StandardOpenOption.APPEND);
            Files.writeString(path,gson.toJson(proyectos) + ",", StandardOpenOption.APPEND);
            Files.writeString(path,"\n\t\"eventos\":\n", StandardOpenOption.APPEND);
            Files.writeString(path,gson.toJson(eventos) + "\n}", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
