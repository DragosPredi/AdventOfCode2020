import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;

public class Day7 {
    public static HashMap<String, HashMap<String, Integer>> parseInputPartTwo(ArrayList<String> input) {
        HashMap<String, HashMap<String, Integer>> result = new HashMap<>();

        for (String element : input) {
            String[] aux = element.split("contain ");

            String parentBag = aux[0].trim();
            parentBag = parentBag.substring(0, parentBag.lastIndexOf(" "));

            HashMap<String, Integer> canContain = new HashMap<>();
            if (aux[1].contains("no")) {
                result.put(parentBag, canContain);
                continue;
            }

            String[] canContainAux = aux[1].split(", ");


            for (String elementAux : canContainAux) {
                String[] parseElAux = elementAux.split(" ");
                StringBuilder toBeAdded = new StringBuilder();
                for (int i = 1; i < parseElAux.length - 1; i++) {
                    toBeAdded.append(parseElAux[i]).append(" ");
                }
                canContain.put(toBeAdded.toString().trim(), Integer.parseInt(parseElAux[0]));
            }
            result.put(parentBag, canContain);

        }
        return result;
    }

    public static HashMap<String, ArrayList<String>> parseInputPartOne(ArrayList<String> input) {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        for (String element : input) {
            String[] aux = element.split("contain ");
            String parentBag = aux[0].trim();
            String[] canContainAux = aux[1].split(", ");

            ArrayList<String> canContain = new ArrayList<>();

            for (String elementAux : canContainAux) {
                String[] parseElAux = elementAux.split(" ");
                StringBuilder toBeAdded = new StringBuilder();
                for (int i = 1; i < parseElAux.length - 1; i++) {
                    toBeAdded.append(parseElAux[i]).append(" ");
                }
                canContain.add(toBeAdded.toString().trim());
            }

            result.put(parentBag.substring(0, parentBag.lastIndexOf(" ")), canContain);
        }
        return result;
    }

    public static void partOne(ArrayList<String> list) {
        HashMap<String, ArrayList<String>> map = parseInputPartOne(list);

        HashMap<String, Boolean> uniqueBags = new HashMap<>();
        for (String el : map.keySet()) {
            uniqueBags.put(el, false);
        }

        Vector<String> fifo = new Vector<>();
        fifo.add("shiny gold");

        while (fifo.size() > 0) {
            for (String el : map.keySet()) {
                if (map.get(el).contains(fifo.get(0))) {
                    uniqueBags.put(el, true);
                    fifo.add(el);
                }
            }
            fifo.remove(0);
        }

        int cont = 0;
        for (String el : uniqueBags.keySet()) {
            if (uniqueBags.get(el))
                cont++;
        }
        System.out.println(cont);

    }

    public static void partTwo(ArrayList<String> list) {
        HashMap<String, HashMap<String, Integer>> map = parseInputPartTwo(list);

        Vector<AbstractMap.SimpleEntry<String, Integer>> fifo = new Vector<>();
        fifo.add(new AbstractMap.SimpleEntry<>("shiny gold", 1));

        int nrOfBags = 0;
        while (fifo.size() > 0) {
            HashMap<String, Integer> bagsContained = map.get(fifo.get(0).getKey());
            int multiplier = fifo.get(0).getValue();

            for(String el : bagsContained.keySet()){
                nrOfBags += bagsContained.get(el) * multiplier;
                for(int i = 0 ; i < multiplier ; i++)
                    fifo.add(new AbstractMap.SimpleEntry<>(el, bagsContained.get(el)));
            }
            fifo.remove(0);
        }
        System.out.println(nrOfBags);
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("advent7.in"));
        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());
        partOne(list);
        partTwo(list);
    }

}
