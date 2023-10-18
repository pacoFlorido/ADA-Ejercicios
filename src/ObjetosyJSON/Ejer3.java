package ObjetosyJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ejer3 {
    public static String stream(String url) {
        try (InputStream input = new URL(url).openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        String url = "https://mocki.io/v1/35c7d907-68c6-454f-9161-aa98948838d3";
        List<Sede> sedes = new ArrayList<>();
        List<Proyecto> proyectos = new ArrayList<>();
        List<Evento> eventos = new ArrayList<>();

        String jsonString = stream(url);
        JSONArray ja = new JSONArray(jsonString);
        for (int i = 0; i<ja.length(); i++){
            JSONObject elementoArray = ja.getJSONObject(i);

            //Encontrando y asignando las Sedes

            List<String> carreras = new ArrayList<>();
            JSONObject joSede = elementoArray.getJSONObject("sede");
            JSONArray jaCarreras = joSede.getJSONArray("carreras");

            for (int j = 0; j<jaCarreras.length(); j++){
                carreras.add(jaCarreras.getString(j));
            }

            sedes.add(new Sede(
                    joSede.getString("nombre"),
                    joSede.getString("ubicacion"),
                    carreras
            ));

            //Encontrando y asignando los Proyectos

            JSONArray jaProyectosTotales = elementoArray.getJSONArray("proyectos");
            for (int k = 0; k<jaProyectosTotales.length(); k++){

                List<String> equipos = new ArrayList<>();
                JSONObject joProyecto = jaProyectosTotales.getJSONObject(k);
                JSONArray jaEquipo = joProyecto.getJSONArray("equipo");
                for (int l = 0; l<jaEquipo.length(); l++){
                    equipos.add(jaEquipo.getString(l));
                }
                proyectos.add(new Proyecto(
                        joProyecto.getString("nombre"),
                        equipos,
                        joProyecto.getBoolean("activo"),
                        joProyecto.getInt("presupuesto")
                ));
            }

            //Encontrando y asignando los Eventos
            List<Conferencia> conferencias = new ArrayList<>();
            JSONObject joEventos = elementoArray.getJSONObject("eventos");
            JSONArray jaConferencias = joEventos.getJSONArray("conferencias");

            for (int p = 0; p<jaConferencias.length(); p++){

                List<String> ponentes = new ArrayList<>();
                JSONObject joConferencias = jaConferencias.getJSONObject(p);
                JSONArray jaConferencia = joConferencias.getJSONArray("ponentes");
                for (int o = 0; o<jaConferencia.length(); o++){
                    ponentes.add(jaConferencia.getString(o));
                }
                conferencias.add(new Conferencia(
                        joConferencias.getString("nombre"),
                        ponentes,
                        joConferencias.getString("fecha")
                ));
            }
            eventos.add(new Evento(conferencias));
        }

        Path path = Path.of("result.dat");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        List<List<? extends Serializable>> objetos = List.of(sedes,proyectos,eventos);

        Ejer2.writeObject(path,objetos);
        Ejer2.readObject(path);
    }
}
