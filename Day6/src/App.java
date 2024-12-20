import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("Day6\\src\\input.txt");
        Scanner scanner1 = new Scanner(file);
        ArrayList<String> lineas = new ArrayList<>();
        Character tablero [][] = new Character[130][130];
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
        int sum_x = 0;
        int posicion[] = position(tablero);
        

        for (int i=0; i<6000; i++){
            checkObstacle(posicion, tablero);
            move(posicion, tablero);
            posicion = position(tablero);
            System.out.println(posicion[0] + "" + posicion[1]);
             
            for (int x=0; x<130; x++) {
                for (int y=0; y<130; y++) {
                    System.out.print(tablero[x][y]);
                }
                System.out.println("");
            }     
            
        }
        for (int x=0; x<130; x++) {
            for (int y=0; y<130; y++) {
                if (tablero[x][y] == 'X') {
                    sum_x++;
                }
            }
        }
        sum_x++;
        System.out.println("Resultado: " + sum_x);   
    }

    public static int[] position(Character[][] tablero){
        int posXY[] = new int[2];
        for (int x=0; x<130; x++) {
            for (int y=0; y<130; y++) {
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

    public static void move(int posXY[], Character[][] tablero) {
        int nextX = posXY[0];
        int nextY = posXY[1];
    
        if (tablero[posXY[0]][posXY[1]] == '^') {
            nextX = posXY[0] - 1;
        } else if (tablero[posXY[0]][posXY[1]] == '>') {
            nextY = posXY[1] + 1;
        } else if (tablero[posXY[0]][posXY[1]] == '<') {
            nextY = posXY[1] - 1;
        } else if (tablero[posXY[0]][posXY[1]] == 'v') {
            nextX = posXY[0] + 1;
        }
    
        // Check if the next position is within bounds
        if (isInsideBoard(nextX, nextY, tablero)) {
            tablero[nextX][nextY] = tablero[posXY[0]][posXY[1]];
            tablero[posXY[0]][posXY[1]] = 'X';
        } else {
            //System.out.println("Next position out of bounds: " + nextX + ", " + nextY);
        }
    }
    
    //comprueba obstaculo if simbolo mas obstaculo en esa posicion +1 gira a derexa
    public static void checkObstacle(int posXY[], Character[][] tablero) {
        int x = posXY[0];
        int y = posXY[1];
    
        if (tablero[x][y] == '^' && isInsideBoard(x - 1, y, tablero) && tablero[x - 1][y] == '#') {
            tablero[x][y] = '>';
        }
        if (tablero[x][y] == '>' && isInsideBoard(x, y + 1, tablero) && tablero[x][y + 1] == '#') {
            tablero[x][y] = 'v';
        }
        if (tablero[x][y] == 'v' && isInsideBoard(x + 1, y, tablero) && tablero[x + 1][y] == '#') {
            tablero[x][y] = '<';
        }
        if (tablero[x][y] == '<' && isInsideBoard(x, y - 1, tablero) && tablero[x][y - 1] == '#') {
            tablero[x][y] = '^';
        }
    }
    
    public static boolean isInsideBoard(int x, int y, Character[][] tablero) {
        int rows = tablero.length;    // Number of rows in the board
        int cols = tablero[0].length; // Number of columns in the board
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    
}
