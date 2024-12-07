import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class App {
    public static void main(String[] args) {
        try {
            File file = new File("Day4\\src\\input.txt");
            Scanner scanner = new Scanner(file);
            int xmas = 0;
            //horizontal
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                xmas+= validar(line);
                line = new StringBuilder(line).reverse().toString();
                xmas+= validar(line);
            }
            //vertical
            ArrayList<String> lines = new ArrayList<String>();
            Scanner scanner2 = new Scanner(file);
            while (scanner2.hasNextLine()) {
                lines.add(scanner2.nextLine());
            }
            for (int i = 0; i<=139; i++) {
                String vertical_line = "";
                for (String line: lines) {
                    char chr = line.charAt(i);
                    vertical_line += chr;
                }
                xmas+= validar(vertical_line);
                vertical_line = new StringBuilder(vertical_line).reverse().toString();
                xmas+= validar(vertical_line);
            }
            scanner2.close();
            //diagonal izq a der
            int size = 139;
            ArrayList<String> diagonal_izqder = new ArrayList<String>();
            for (int i = size; i>=0; i--) {
                String diagonal_izqder_str = "";
                for (int j=0; j<=i; j++) {
                    diagonal_izqder_str += lines.get(i-j).charAt(j);
                }
                diagonal_izqder.add(diagonal_izqder_str);
            }
            size = 139;
            //falta por hacer la diagonal de abajo
            for (int i = 1; i < lines.get(0).length(); i++) { // Empieza en la segunda columna
                String diagonal_izqder_str = "";
                for (int j = 0; j < size - i + 1; j++) {
                    diagonal_izqder_str += lines.get(size - j).charAt(i + j);
                }
                diagonal_izqder.add(diagonal_izqder_str);
            }
            System.out.println(diagonal_izqder);
            for (String line: diagonal_izqder) {
                xmas += validar(line);
                xmas += validar(new StringBuilder(line).reverse().toString());
            }
            // Parte superior 
            size = 139;
            ArrayList<String> diagonal_derizq = new ArrayList<String>();
            for (int i = size; i >= 0; i--) {
                String diagonal_derizq_str = "";
                for (int j = 0; j <= i; j++) {
                    diagonal_derizq_str += lines.get(i - j).charAt(size - j);
                }
                diagonal_derizq.add(diagonal_derizq_str);
            }
            // Parte inferior 
            for (int i = 1; i < lines.get(0).length(); i++) { // Empieza en la segunda columna desde la derecha
                String diagonal_derizq_str = "";
                for (int j = 0; j < size - i + 1; j++) {
                    diagonal_derizq_str += lines.get(size - j).charAt(size - (i + j));
                }
                diagonal_derizq.add(diagonal_derizq_str);
            }
            System.out.println(diagonal_derizq);
            for (String line: diagonal_derizq) {
                xmas += validar(line);
                xmas += validar(new StringBuilder(line).reverse().toString());
            }
            System.out.println("Se han encontrado: " + xmas);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    public static int validar (String line) {
        Pattern pattern = Pattern.compile("XMAS");
        Matcher matcher = pattern.matcher(line);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}

