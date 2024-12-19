import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("Day6\\src\\sample.txt");
        Scanner scanner1 = new Scanner(file);
        ArrayList<String> lineas = new ArrayList<>();
        Character tablero [][] = new Character[10][10];
        while (scanner1.hasNextLine()) {
            lineas.add(scanner1.nextLine());
        }
        scanner1.close();
        int fila= 0;
        for (String linea: lineas) {
            for (int columna = 0; columna <lineas.size(); columna++) {
                tablero[fila][columna] = linea.charAt(columna);
            }
            fila++;
        }

        for (int x=0; x<10; x++) {
            for (int y=0; y<10; y++) {
                System.out.print(tablero[x][y]);
            }
            System.out.println("");
        }
        int posicion[] = position(tablero);
        System.out.println(posicion[0] + "" + posicion[1]);
    }

    public static int[] position(Character[][] tablero){
        int posXY[] = new int[2];
        for (int x=0; x<10; x++) {
            for (int y=0; y<10; y++) {
                if (tablero[x][y] == '^' | tablero[x][y] == '>' | tablero[x][y] == '<' | tablero[x][y] == 'v') {
                    //System.out.println("Posicion X:" + x + " Posicion Y:" + y);
                    int posX = x;
                    int posY = y;
                    posXY[0] = posX;
                    posXY[1] = posY;
                }
            }
        }
        return posXY;
    }
}
