import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileReadingNio {
    //Character Set
    //Letras, números, simbolos, signos de puntuación, etc...

    /**
     * Carácter se le asigna un código numérico
     * único conocido como CODE POINT
     * 2 conjuntos de carácteres más comunes ASCII y Unicode
     * ASCII -> más viejo
     * Unicode -> más reciente, para incluir más escrituras
     *
     * Character encoding, consiste en codificar los carácteres en
     * números -> glifos
     * Glifo -> caract. alfanu, emoji, simbolo, signo punf
     *
     * ASCII -> codificaciones: US-ASCII(7bits), ISO(8bits)
     * Unicode -> codificaciones: UTF-8(de 1 a 4 bytes), UTF-16, UTF-32
     * clase StandardCharsets
     **/
    //4 métodos --> leen el fichero en memoria hasta 2 gigabytes

    //readAllBytes -> devuelve byte[], cierra automáticamente
    //readAllLines -> devuelve List<String>, cierra automáticamente
    //readString -> devuelve un String de todo el fichero, cierra automáticamente
    //lines -> devuelve un Stream de String, hay que cerrarlo manualmente

    public static void main(String[] args) {
        Path path = Path.of("files/file.txt");
        try (Stream<String> stringStream = Files.lines(path)){
            Pattern p = Pattern.compile("\\p{javaWhitespace}+");
            stringStream
                    .flatMap(s -> p.splitAsStream(s))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
