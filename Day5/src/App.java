import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("Day5\\src\\input.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String> normas = new ArrayList<String>();
        ArrayList<String> lineas = new ArrayList<String>();
        ArrayList<String> lineas_novalidas = new ArrayList<String>();
        ArrayList<String> lineas_corregidas = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            try {
                normas.add(scanner.next("[0-9]{2}\\|[0-9]{2}"));
            } catch (Exception InputMismatchException) {
                lineas.add(scanner.nextLine());
            }
        }
        int suma_validas = 0;
        for (String linea: lineas) {
            int length = array_numeros(linea).toArray().length;
            if (compruebaLineas(linea, normas, lineas_novalidas)) {
                suma_validas = suma_validas + array_numeros(linea).get((length-1)/2);
            }
        }
        System.out.println(suma_validas);
        //System.out.println(lineas_novalidas);
        for (String linea_novalida: lineas_novalidas) {
            while(compruebaLineasNovalidas(linea_novalida, normas) == false) {
                linea_novalida = arreglaLineas(linea_novalida, normas, devuelveindice(linea_novalida, normas));
            }
            lineas_corregidas.add(linea_novalida);
        }
        System.out.println(lineas_corregidas);
        int suma_novalidas = 0;
        for (String linea_corregida: lineas_corregidas) {
            int length = array_numeros(linea_corregida).toArray().length;
            suma_novalidas = suma_novalidas + array_numeros(linea_corregida).get((length-1)/2);
        }
        System.out.println(suma_novalidas);
    }
    
    public static ArrayList<Integer> array_numeros(String linea) {
        ArrayList<Integer> array_numeros1 = new ArrayList<Integer>();
        for (String numero: linea.split(",")) {
            array_numeros1.add(Integer.parseInt(numero));
        }
        return array_numeros1;
    }
    public static boolean compruebaLineas(String linea, ArrayList<String> normas, ArrayList<String> lineas_novalidas) {
        int pareja_valida = 0;
        int length = array_numeros(linea).toArray().length;
        for (int i = 0; i < length-1; i++) {
            String pareja = array_numeros(linea).get(i) + "|" + array_numeros(linea).get(i+1);
            if (normas.contains(pareja)) {
                pareja_valida = pareja_valida +1;
            } else {
                lineas_novalidas.add(linea);
                break;
            }
        }
        return (pareja_valida == (length-1));
    }
    public static boolean compruebaLineasNovalidas(String linea, ArrayList<String> normas) {
        int pareja_valida = 0;
        int length = array_numeros(linea).size();
        for (int i = 0; i < length-1; i++) {
            String pareja = array_numeros(linea).get(i) + "|" + array_numeros(linea).get(i+1);
            if (normas.contains(pareja)) {
                pareja_valida = pareja_valida +1;
            } else {
                break;
            }
        }
        return (pareja_valida == (length-1));
    }
    public static int devuelveindice(String linea, ArrayList<String> normas) {
        int length = array_numeros(linea).toArray().length;
        int index= 0;
        for (int i = 0; i < length-1; i++) {
            String pareja = array_numeros(linea).get(i) + "|" + array_numeros(linea).get(i+1);
            if (!normas.contains(pareja)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public static String arreglaLineas(String linea, ArrayList<String> normas, int index) {
        ArrayList<Integer> numeros = array_numeros(linea); // Extraemos los números una sola vez
    
        // Intercambiamos los números en los índices dados
        int numa = numeros.get(index);
        int numb = numeros.get(index + 1);
        numeros.set(index, numb);
        numeros.set(index + 1, numa);
    
        // Construimos la nueva línea con separadores
        StringBuilder linea_corregida = new StringBuilder();
        for (int i = 0; i < numeros.size(); i++) {
            linea_corregida.append(numeros.get(i));
            if (i < numeros.size() - 1) { // Añadimos comas entre los números
                linea_corregida.append(",");
            }
        }
        return linea_corregida.toString(); // Devolvemos la línea corregida
    }
}