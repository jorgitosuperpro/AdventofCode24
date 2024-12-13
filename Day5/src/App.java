import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("src\\input.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String> normas = new ArrayList<String>();
        ArrayList<String> lineas = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            try {
                normas.add(scanner.next("[0-9]{2}\\|[0-9]{2}"));
            } catch (Exception InputMismatchException) {
                lineas.add(scanner.nextLine());
            }
        }
        int suma_validas = 0;
        for (String linea: lineas) {
            int pareja_valida = 0;
            int length = array_numeros(linea).toArray().length;
            for (int i = 0; i < length-1; i++) {
                String pareja = array_numeros(linea).get(i) + "|" + array_numeros(linea).get(i+1);
                if (normas.contains(pareja)) {
                    pareja_valida = pareja_valida +1;
                }
            }
            if (pareja_valida == (length-1)) {
                suma_validas = suma_validas + array_numeros(linea).get((length-1)/2);
            }
        }
        System.out.println(suma_validas);
    }
    public static ArrayList<Integer> array_numeros(String linea) {
        ArrayList<Integer> array_numeros1 = new ArrayList<Integer>();
        for (String numero: linea.split(",")) {
            array_numeros1.add(Integer.parseInt(numero));
        }
        return array_numeros1;
    }
}

