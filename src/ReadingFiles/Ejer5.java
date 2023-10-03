package ReadingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class PalabraNumeroVeces{
    String palabra;
    int numVeces;

    public PalabraNumeroVeces(String palabra, int numVeces) {
        this.palabra = palabra;
        this.numVeces = numVeces;
    }

    public void setNumVeces(int numVeces) {
        this.numVeces = numVeces;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getNumVeces() {
        return numVeces;
    }

    @Override
    public String toString() {
        return palabra + ", " + numVeces;
    }
}
public class Ejer5 {
    public static void main(String[] args) {
        Path art = Path.of("files/articulo.txt");
        Map<String,Integer> palabraContador = new HashMap<>();
        List<PalabraNumeroVeces> diezMasRepes = new ArrayList<>();
        int contador;

        try (Stream<String> lineas = Files.lines(art)){
            Pattern p = Pattern.compile("\\p{javaWhitespace}+");
            List<String> palabras = lineas
                    //a
                    //Dividimos las lineas en palabras
                    .flatMap(linea -> p.splitAsStream(linea))
                    //b
                    //Eliminamos todos los signos de puntiación
                    .map(palabra -> palabra.replaceAll("\\p{Punct}", ""))
                    //c
                    //Ignoramos las palabras de 5 o menos carácteres.
                    .filter(palabra -> palabra.length()>5)
                    .toList();
            //d Contar las veces que aparece cada palabra
            for (String s : palabras){
                if (palabraContador.containsKey(s)){
                    contador = palabraContador.get(s);
                    contador++;
                    palabraContador.put(s,contador);
                } else {
                    palabraContador.put(s,1);
                }
            }
            //palabraContador.forEach((key,value) -> System.out.println("Key: " + key + ", Value: " + value));

            //e Mostrar las diez más repetidas
            for (Map.Entry<String,Integer> o: palabraContador.entrySet()){
                diezMasRepes.add(new PalabraNumeroVeces(o.getKey(),o.getValue()));
            }
            diezMasRepes
                    .stream()
                    .sorted((num1,num2) -> num2.getNumVeces()-num1.getNumVeces())
                    .limit(10)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
