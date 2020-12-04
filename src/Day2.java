import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("advent2.in"));
        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());

        Pattern p = Pattern.compile("\\d+");
        int nrOfGoodStrings = 0;
        for (String st : list) {
            int start = 0, end = 0;
            Matcher m = p.matcher(st);
            if (m.find())
                start = Integer.parseInt(m.group());
            if (m.find())
                end = Integer.parseInt(m.group());

            String[] arr = st.split(":");

            char desiredChar = arr[0].charAt(arr[0].length() - 1);
            char[] chars = arr[1].toCharArray();
            if(arr[1].charAt(start) == desiredChar && arr[1].charAt(end) != desiredChar
                    || arr[1].charAt(end) == desiredChar && arr[1].charAt(start) != desiredChar){
                nrOfGoodStrings++;
            }



        }
        System.out.println(nrOfGoodStrings);
    }










}
