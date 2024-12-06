
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
public class Day1 {
    public static void main(String[] args) throws Exception {
        long start1 = System.nanoTime();
        Scanner reader = new Scanner(new File("src/input.txt"));
        String [] first_column = new String[1000];
        String [] second_column = new String[1000];
        int i =0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String [] pair = line.split("   "); //Separa la pareja de numeros por espacios.
            String first_column_number = String.valueOf(pair[0]);
            String second_column_number = String.valueOf(pair[1]);
            first_column[i] = first_column_number;
            second_column[i] = second_column_number; 
            i = i+1;
        } 
        Arrays.sort(first_column); //Clasifica de menor a mayor la primera columna
        Arrays.sort(second_column);
        int suma = 0;
        for (int j = 0; j < 1000; j++ ) {
            if (Integer.parseInt(first_column[j]) >= Integer.parseInt(second_column[j])) {
                int diferencia = Integer.parseInt(first_column[j]) - Integer.parseInt(second_column[j]);
                suma = suma + diferencia;
            } else {
                int diferencia = Integer.parseInt(second_column[j]) - Integer.parseInt(first_column[j]);
                suma = suma + diferencia;
            }
        }
        System.out.println("La suma total de las diferencias de menor a mayor es: " + suma);
        int valor_repetido = 0;
        for (int j = 0; j < 1000; j++ ) {
            int repetido = 0;
            for (int k = 0; k < 1000; k++ ) {
                if(Integer.parseInt(first_column[j]) == Integer.parseInt(second_column[k])) {
                    repetido = repetido +1;
                }
            }
            valor_repetido = valor_repetido + Integer.parseInt(first_column[j]) * repetido;
        }
        System.out.println("La puntuación de similaridad es de: " + valor_repetido);
        long end1 = System.nanoTime();  
        System.out.println("Elapsed Time in nano seconds: "+ (end1-start1)); 
    }
}
