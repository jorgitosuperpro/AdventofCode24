import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Part1 {
    public static void main(String[] args) throws Exception {
        File file = new File("/home/jorgitosuperpro/Documents/AdventofCode24/Day3/Part1/input.txt");
        Scanner scanner = new Scanner(file);
        String expresion = "mul\\([0-9]{1,3},[0-9]{1,3}\\)";
        Pattern pattern = Pattern.compile(expresion);
        int total = 0;
        int multiplicacion = 0;
        int validas = 0;
        while (scanner.hasNextLine()) {
            Matcher matcher = pattern.matcher(scanner.nextLine());
            while (matcher.find()) {
                validas++;
                String cadena = matcher.group();
                cadena = cadena.split("mul\\(")[1];
                cadena = cadena.split("\\)")[0];
                String[] cadena_separada = {cadena.split(",")[0],cadena.split(",")[1]};
                multiplicacion = Integer.parseInt(cadena_separada[0]) * Integer.parseInt(cadena_separada[1]);
                total = total + multiplicacion;
            }
        }
        System.out.println("Resultado: " + total);
        System.out.println("Validas: " + validas);
        scanner.close();
    }
}
