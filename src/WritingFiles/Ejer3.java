package WritingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Ejer3 {
    public static void main(String[] args) throws IOException {
        Path json = Path.of("files/students.json");
        List<Estudiante> students = new ArrayList<>();
        for (int i = 0; i<50; i++){
            students.add(generarStudent());
        }

        List<String> objetosJSON = students.stream()
                .map(student -> {
                    return "\t{\n\t\t\"name\": \"" + student.getName() + "\",\n\t\t"
                    + "\"age\": \"" + student.getAge() + "\"\n\t},";
                }).toList();

        if (!Files.exists(json)){
            Files.createFile(json);
        }
        Files.writeString(json,"[\n");
        Files.write(json,objetosJSON, StandardOpenOption.APPEND);
        Files.writeString(json,"]",StandardOpenOption.APPEND);
    }

    public static Estudiante generarStudent(){
        int randomAge = (int) (Math.random()*100 + 1);
        String randomName = "Paco" + (int) (Math.random()*100 +1);
        return new Estudiante(randomName,randomAge);
    }
}
