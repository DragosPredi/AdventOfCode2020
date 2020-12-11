import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Day10 {
    public static void solvePartOne(ArrayList<Integer> adaptersList) {
        int nrOf1 = 0;
        int nrOf3 = 0;
        int comp = 0;
        for (Integer adapter : adaptersList) {
            if (comp + 1 == adapter) {
                nrOf1++;
            } else if (comp + 3 == adapter) {
                nrOf3++;
            }
            comp = adapter;
        }

        System.out.println(nrOf1 + " * " + nrOf3 + " -> " + nrOf1 * nrOf3);
    }

    public static void solvePartTwo(ArrayList<Integer> adaptersList){
        long[] steps = new long[adaptersList.size()];
        steps[0] = 1;

        for (int i = 1; i < adaptersList.size(); i++) {
            int j = i - 1;
            while (j >= 0 && adaptersList.get(i) - adaptersList.get(j) <= 3) {
                steps[i] += steps[j];
                j--;
            }
        }
        System.out.println(steps[steps.length - 1]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("advent10.in"));
        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());
        ArrayList<Integer> adaptersList = (ArrayList<Integer>) list.stream().mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());

        Collections.sort(adaptersList);
        adaptersList.add(0, 0);
        adaptersList.add(adaptersList.get(adaptersList.size() - 1) + 3);

        solvePartOne(adaptersList);
        solvePartTwo(adaptersList);


    }
}
