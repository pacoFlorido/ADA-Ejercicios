import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student{
    private String name;
    private int age;
    private LocalDate birthday;

    public Student(String name, int age, LocalDate brirthday) {
        this.name = name;
        this.age = age;
        this.birthday = brirthday;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return name + "," + age + "," + birthday;
    }
}

public class FileWriting {
    public static void main(String[] args) {
        Path path = Path.of("files/student.csv");

        String header = "name,age,birthday";

        Student s1 = new Student("Paquito",19, LocalDate.now());
        Student s2 = new Student("Manolo",59, LocalDate.of(1956,12,31));

        List<Student> students = Arrays.asList(s1,s2);

        /*try {
            //Método writeString
            //StandardOpenOption
            Files.writeString(path,header + "\n");
            for (Student s: students){
                Files.writeString(path,s.toString() + "\n",StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


         */
        List<String> studString = new ArrayList<>();
        studString.add(s1.toString());
        studString.add(s2.toString());

        try {
            //Método write

            //Más eficiente porque solo abre una vez el fichero y escribe toda la lista

            Files.writeString(path,header + "\n", StandardOpenOption.APPEND);
            Files.write(path,studString, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try(FileWriter fw = new FileWriter("files/fileWriter.csv")){
            fw.write(header);
            fw.write(System.lineSeparator());
            for (Student s : students){
                fw.write(s.toString());
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(PrintWriter pw = new PrintWriter("files/printWriter.csv")){
            pw.println(header);
            for (Student s : students){
                /*
                %d -> digitos
                %s -> string
                %b -> boolean
                %f -> decimales
                %c -> char
                %.2f -> 2 decimales
                 */
                pw.format("%-10s,%-3d,%-2d/%-2d/%-4d\n", s.getName(),
                        s.getAge(),
                        s.getBirthday().getDayOfMonth(),
                        s.getBirthday().getMonthValue(),
                        s.getBirthday().getYear());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bw = Files.newBufferedWriter(Path.of("files/bufferWriter.csv"))){
            bw.write(header);
            bw.newLine();
            for (Student s : students){
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * BufferedWriter -> tiene buffer, caracteres o texto, metodo newLine separar lineas
         * Se utiliza para escribir grandes cantidades de texto.
         *
         * FileWriter -> tiene buffer pero menor que el de BufferedWriter, orientado a carácteres, no tiene separador
         * Se utiliza para cantidadesd de texto más pequeñas
         *
         * PrintWriter -> no buffer, carácteres, salto de linea println
         * Se utiliza para cuando quiera imprimir usando un formato en concreto
         */
    }
}
