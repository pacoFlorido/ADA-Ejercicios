package ReadingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class Ejer3 {
    public static void main(String[] args) {
        Path path = Path.of("files/numeros.txt");

        try {
            Pattern p = Pattern.compile(",");
            List<String> stringNums =  Files.lines(path)
                    .flatMap(cadena -> p.splitAsStream(cadena))
                    .toList();
            List<Double> nums = stringNums.stream()
                    .filter(s -> s.matches("\\d"))
                    .map(s -> Double.parseDouble(s))
                    .toList();
            double suma=0;
            for (Double i : nums){
                suma+=i;
            }
            System.out.printf("La media es: " + (suma/nums.size()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
