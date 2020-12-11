import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class Day9 {
    public final static int keyNumber = 258585477;

    public static boolean checkCondition(Vector<Integer> preamble, int nr){
        for(Integer el : preamble){
            if(nr >= el && preamble.contains(nr - el)){
                return true;
            }
        }
        return false;
    }

    public static int findSum(Vector<Integer> vec){
        return vec.stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }


    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("advent9.in"));
        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());

       /* Vector<Integer> preamble = new Vector<>();
        for(int i = 0 ; i < 25 ; i++){
            preamble.add(Integer.parseInt(list.get(i)));
        }
        for(int i = 25 ; i < list.size() ; i++){
            if(!checkCondition(preamble, Integer.parseInt(list.get(i)))){
                System.out.println(list.get(i));
            } else {
                preamble.remove(0);
                preamble.add(Integer.parseInt(list.get(i)));
            }
        }*/
        int end = 1;
        Vector<Integer> areOfInterest = new Vector<>();
        areOfInterest.add(Integer.parseInt(list.get(0)));
        areOfInterest.add(Integer.parseInt(list.get(1)));
        while(findSum(areOfInterest) != keyNumber){
            int nr = Integer.parseInt(list.get(end++));
            areOfInterest.add(nr);
            while(findSum(areOfInterest) > keyNumber){
                areOfInterest.remove(0);
            }
        }

        System.out.println(Collections.max(areOfInterest) + Collections.min(areOfInterest));

    }
}
