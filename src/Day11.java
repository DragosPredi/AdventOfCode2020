import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day11 {
    public static final int nrOfLinesInDocument = 99;

    public static int[] directionsX = {0, 1, 1, 1, 0, -1, -1, -1};
    public static int[] directionsY = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static char[][] getLayout(ArrayList<String> list) {
        char[][] mat = new char[nrOfLinesInDocument][list.get(0).length()];
        for (int i = 0; i < nrOfLinesInDocument; i++) {
            char[] line = list.get(i).toCharArray();
            System.arraycopy(line, 0, mat[i], 0, line.length);
        }
        return mat;
    }

    public static void copyArray(char[][] source, char[][] dest) {
        for (int i = 0; i < dest.length; i++) {
            dest[i] = source[i].clone();
        }
    }

    public static int countOccupiedSeatsPartOne(char[][] mat, int i, int j) {
        int nrOfOccupiedSeats = 0, linie, coloana;
        for (int dir = 0; dir < 8; dir++) {
            linie = i + directionsY[dir];
            coloana = j + directionsX[dir];

            if (linie >= 0 && coloana >= 0 && linie < mat.length && coloana < mat[i].length) {
                if (mat[linie][coloana] == '#') {
                    nrOfOccupiedSeats++;
                }
            }
        }
        return nrOfOccupiedSeats;
    }

    public static int countOccupiedSeatsWholeLayout(char[][] mat) {
        int nrOfOccupiedSeats = 0;
        for (char[] chars : mat) {
            for (char aChar : chars) {
                if (aChar == '#') {
                    nrOfOccupiedSeats++;
                }
            }
        }
        return nrOfOccupiedSeats;
    }

    public static void solvePartOne(char[][] mat) {
        int ok = 1, nrOfOccupiedSeats;
        char[][] aux = new char[nrOfLinesInDocument][];


        while (ok != 0) {
            ok = 0;
            copyArray(mat, aux);
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[i].length; j++) {
                    nrOfOccupiedSeats = countOccupiedSeatsPartOne(mat, i, j);

                    if (mat[i][j] == 'L' && nrOfOccupiedSeats == 0) {
                        ok = 1;
                        aux[i][j] = '#';
                    }
                    if (mat[i][j] == '#' && nrOfOccupiedSeats >= 4) {
                        ok = 1;
                        aux[i][j] = 'L';
                    }
                }
            }
            copyArray(aux, mat);

        }
    }

    public static void solvePartTwo(char[][] mat) {
        int ok = 1, nrOfOccupiedSeats;
        char[][] aux = new char[nrOfLinesInDocument][];
        while (ok != 0) {
            ok = 0;
            copyArray(mat, aux);
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[i].length; j++) {
                    nrOfOccupiedSeats = countOccupiedSeatsPartTwo(mat, i, j);

                    if (mat[i][j] == 'L' && nrOfOccupiedSeats == 0) {
                        ok = 1;
                        aux[i][j] = '#';
                    }
                    if (mat[i][j] == '#' && nrOfOccupiedSeats >= 5) {
                        ok = 1;
                        aux[i][j] = 'L';
                    }
                }
            }
            copyArray(aux, mat);

        }
    }

    private static int countOccupiedSeatsPartTwo(char[][] mat, int i, int j) {
        int occupiedSeats = 0;

        int auxI = i - 1, auxJ = j;
        while (auxI >= 0) {
            if(mat[auxI][auxJ] == '#'){
                occupiedSeats++;
                break;
            }
            if(mat[auxI][auxJ] == 'L'){
                break;
            }
            auxI--;
        }

        auxI = i; auxJ = j + 1;
        while ( auxJ < mat[auxI].length) {
            if(mat[auxI][auxJ] == '#'){
                occupiedSeats++;
                break;
            }
            if(mat[auxI][auxJ] == 'L'){
                break;
            }
            auxJ++;
        }

        auxI = i + 1; auxJ = j;
        while (auxI < mat.length) {
            if(mat[auxI][auxJ] == '#'){
                occupiedSeats++;
                break;
            }
            if(mat[auxI][auxJ] == 'L'){
                break;
            }
            auxI++;
        }

        auxI = i; auxJ = j - 1;
        while (auxJ >= 0) {
            if(mat[auxI][auxJ] == '#'){
                occupiedSeats++;
                break;
            }
            if(mat[auxI][auxJ] == 'L'){
                break;
            }
            auxJ--;
        }

        auxI = i - 1; auxJ = j + 1;
        while (auxI >= 0 && auxJ < mat[auxI].length) {
            if(mat[auxI][auxJ] == '#'){
                occupiedSeats++;
                break;
            }
            if(mat[auxI][auxJ] == 'L'){
                break;
            }
            auxI--;
            auxJ++;
        }

        auxI = i + 1; auxJ = j + 1;
        while (auxI <mat.length  && auxJ < mat[auxI].length) {
            if(mat[auxI][auxJ] == '#'){
                occupiedSeats++;
                break;
            }
            if(mat[auxI][auxJ] == 'L'){
                break;
            }
            auxI++;
            auxJ++;
        }

        auxI = i + 1; auxJ = j - 1;
        while (auxI < mat.length && auxJ >= 0) {
            if(mat[auxI][auxJ] == '#'){
                occupiedSeats++;
                break;
            }
            if(mat[auxI][auxJ] == 'L'){
                break;
            }
            auxI++;
            auxJ--;
        }

        auxI = i - 1; auxJ = j - 1;
        while (auxI >= 0 && auxJ >= 0) {
            if(mat[auxI][auxJ] == '#'){
                occupiedSeats++;
                break;
            }
            if(mat[auxI][auxJ] == 'L'){
                break;
            }
            auxI--;
            auxJ--;
        }


        return occupiedSeats;
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("advent11.in"));
        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());
        char[][] mat = getLayout(list);
        //solvePartOne(mat);
        solvePartTwo(mat);

        System.out.println(countOccupiedSeatsWholeLayout(mat));
    }
}
