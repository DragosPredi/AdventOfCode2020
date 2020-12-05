import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Day5 {
    public static void tests(){
        String test1 = "BFFFBBFRRR";
        String test2 = "FFFBBBFRRR";
        String test3 = "BBFFBBFRLL";

        assert getRow(test1) == 70;
        assert getRow(test2) == 14;
        assert getRow(test3) == 102;

        assert getCol(test1) == 7;
        assert getCol(test2) == 7;
        assert getCol(test3) == 4;
    }
    public static int getRow(String pass){
        int startRow = 0;
        int endRow = 127;
        for (int i = 0; i < 7; i++) {
            if (pass.charAt(i) == 'B') {
                startRow = ((startRow + endRow) / 2) + 1;
            } else {
                endRow = ((startRow + endRow) / 2);
            }
        }
        return startRow;
    }

    public static int getCol(String pass){
        int startCol = 0;
        int endCol = 7;
        for(int i = 7 ; i < 10 ; i++){
            if (pass.charAt(i) == 'R') {
                startCol = ((startCol + endCol) / 2) + 1;
            } else {
                endCol = ((startCol + endCol) / 2);
            }
        }
        return startCol;
    }
    public static void main(String[] args) throws FileNotFoundException {
        //check for correctitude
        tests();

        BufferedReader reader = new BufferedReader(new FileReader("advent5.in"));
        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());

        ArrayList<Integer> passportList = new ArrayList<>();
        for (String pass : list) {
            long seatId = getRow(pass) * 8 + getCol(pass);
            passportList.add((int) seatId);
        }
        //First star
        System.out.println(Collections.max(passportList));

        //Second star
        Collections.sort(passportList);
        for(int i = 0 ; i < passportList.size() - 1; i++){
            if(passportList.get(i) != passportList.get(i+1) - 1){
                System.out.println(passportList.get(i) + " " + passportList.get(i+1));
            }
        }
    }
}
