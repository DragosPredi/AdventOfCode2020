import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("advent1.in"));
        ArrayList<Integer> list = (ArrayList<Integer>) reader.lines().map(Integer::parseInt).collect(Collectors.toList());
        for(Integer el : list){
            for(Integer elAux : list){
                for(Integer elTres : list)
                if(el + elAux + elTres == 2020) {
                    System.out.println(el + " " + elAux + " " + elTres);
                }
            }
        }
    }
}
