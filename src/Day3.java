import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("advent3.in"));
        int nrLines = 323;
        int nrCols = 31;
        char[][] map = new char[nrLines][nrCols];

        for (int i = 0; i < nrLines; i++) {
            String st = reader.readLine();
            for (int j = 0; j < st.length(); j++) {
                map[i][j] = st.charAt(j);
            }
        }

        int lineCont = 0;
        int lineCont2 = 0;
        int colCont1 = 0;
        int colCont3 = 0;
        int colCont5 = 0;
        int colCont7 = 0;
        BigInteger treesCont1 = new BigInteger(String.valueOf(0));
        BigInteger treesCont3 = new BigInteger(String.valueOf(0));
        BigInteger treesCont5 = new BigInteger(String.valueOf(0));
        BigInteger treesCont7 = new BigInteger(String.valueOf(0));
        BigInteger treesCont12 = new BigInteger(String.valueOf(0));

        while (lineCont < nrLines - 1) {
            lineCont++;
            lineCont2 += 2;
            colCont1 = (colCont1 + 1) % nrCols;
            colCont3 = (colCont3 + 3) % nrCols;
            colCont5 = (colCont5 + 5) % nrCols;
            colCont7 = (colCont7 + 7) % nrCols;
            if (map[lineCont][colCont1] == '#') {
                treesCont1 = treesCont1.add(BigInteger.valueOf(1));
            }
            if (map[lineCont][colCont3] == '#') {
                treesCont3 = treesCont3.add(BigInteger.valueOf(1));
            }
            if (map[lineCont][colCont5] == '#') {
                treesCont5 = treesCont5.add(BigInteger.valueOf(1));
            }
            if (map[lineCont][colCont7] == '#') {
                treesCont7 = treesCont7.add(BigInteger.valueOf(1));
            }
            if (lineCont2 < nrLines - 1 && map[lineCont2][colCont1] == '#') {
                treesCont12 = treesCont12.add(BigInteger.valueOf(1));
            }
        }
        treesCont1 = treesCont1.multiply(treesCont3);
        treesCont1 = treesCont1.multiply(treesCont5);
        treesCont1 = treesCont1.multiply(treesCont7);
        treesCont1 = treesCont1.multiply(treesCont12);
        System.out.println(treesCont1);
    }
}
