import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpresionesRegulares {
    public static void main(String[] args) {
        String cadena = "Curso 2023-2024";
        System.out.println(
                cadena.matches("^(?i)\\w{5}[ ]20\\d{2}[-]20\\d{2}$"));

        String telefono = "(+34) 658-748-958";
        String regex = "^[\\(][+]\\d{1,4}[\\)][ ]\\d{3}[-]\\d{3}[-]\\d{3}$";
        System.out.println(telefono.matches(regex));

        StringBuilder html = new StringBuilder("<header>meta ....</header>");
        html.append("<a href=\"google.com\"> </a>");
        html.append("<h1>title </h1>");
        html.append("<h2>etiqueta2 </h2>");
        html.append("<p>parrafo </p>");
        html.append("<h2>resumen </h2>");
        html.append("<footer>title </footer>");

        //greedy y lazy
        //*, +
        Pattern p = Pattern.compile("(<a href=\")(.*?)(\")(.*)>(.*?)(</a>)");
        Matcher m = p.matcher(html);
        while (m.find()){
            System.out.println(m.group(2));
        }
    }
}
