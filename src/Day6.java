import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 {
    public static void partOne() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("advent6.in"));
        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());

        Set<Character> uniqueAnswers = new HashSet<>();

        int numberOfAnswers = 0;
        for (String el : list) {
            if (el.isBlank()) {
                numberOfAnswers += uniqueAnswers.size();
                uniqueAnswers = new HashSet<>();
            }
            for (Character ch : el.toCharArray()) {
                uniqueAnswers.add(ch);
            }
        }
        numberOfAnswers += uniqueAnswers.size();
        System.out.println(numberOfAnswers);
    }
    public static int getNrOfAnswers(HashMap<Character, Integer> freqMap, int groupSize){
        int numberOfAnswers = 0;
        for (Character ch : freqMap.keySet()) {
            if (freqMap.get(ch) == groupSize) {
                numberOfAnswers++;
            }
        }
        return numberOfAnswers;
    }

    public static void partTwo() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("advent6.in"));
        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());
        HashMap<Character, Integer> freqMap = new HashMap<>();


        int groupSize = 0;
        int numberOfAnswers = 0;
        for (String el : list) {
            if (el.isBlank()) {
                numberOfAnswers += getNrOfAnswers(freqMap, groupSize);
                freqMap = new HashMap<>();
                groupSize = 0;
            } else {
                for (Character ch : el.toCharArray()) {
                    int valueToAdd = freqMap.getOrDefault(ch, 0);
                    freqMap.put(ch, valueToAdd + 1);
                }
                groupSize++;
            }
        }
        numberOfAnswers += getNrOfAnswers(freqMap, groupSize);
        System.out.println(numberOfAnswers);


    }

    public static void main(String[] args) throws FileNotFoundException {
        partOne();
        partTwo();
    }

}
