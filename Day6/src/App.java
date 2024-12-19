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
        for (int i=0; i<49; i++){
            checkObstacle(posicion, tablero);
            move(posicion, tablero);
            posicion = position(tablero);
            System.out.println(posicion[0] + "" + posicion[1]);
            for (int x=0; x<10; x++) {
                for (int y=0; y<10; y++) {
                    System.out.print(tablero[x][y]);
                }
                System.out.println("");
            }
        }
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

    public static void move(int poxXY[], Character[][] tablero) {
        if (tablero[poxXY[0]][poxXY[1]] == '^') {
            tablero[poxXY[0]-1][poxXY[1]] = '^';
            tablero[poxXY[0]][poxXY[1]] = 'X';
        } else if (tablero[poxXY[0]][poxXY[1]] == '>') {
            tablero[poxXY[0]][poxXY[1]+1] = '>';
            tablero[poxXY[0]][poxXY[1]] = 'X';
        } else if (tablero[poxXY[0]][poxXY[1]] == '<') {
            tablero[poxXY[0]][poxXY[1]-1] = '<';
            tablero[poxXY[0]][poxXY[1]] = 'X';
        } else if (tablero[poxXY[0]][poxXY[1]] == 'v') {
            tablero[poxXY[0]+1][poxXY[1]] = 'v';
            tablero[poxXY[0]][poxXY[1]] = 'X';
        }
    }
    //comprueba obstaculo if simbolo mas obstaculo en esa posicion +1 gira a derexa
    public static void checkObstacle(int posXY[], Character[][] tablero) {
        if (tablero[posXY[0]][posXY[1]] == '^' && tablero[posXY[0]-1][posXY[1]] == '#') {
            tablero[posXY[0]][posXY[1]] = '>';
        }
        if (tablero[posXY[0]][posXY[1]] == '>' && tablero[posXY[0]][posXY[1]+1] == '#') {
            tablero[posXY[0]][posXY[1]] = 'v';
        }
        if (tablero[posXY[0]][posXY[1]] == 'v' && tablero[posXY[0]+1][posXY[1]] == '#') {
            tablero[posXY[0]][posXY[1]] = '<';
        }
        if (tablero[posXY[0]][posXY[1]] == '<' && tablero[posXY[0]][posXY[1]-1] == '#') {
            tablero[posXY[0]][posXY[1]] = '^';
        }
    }
}
