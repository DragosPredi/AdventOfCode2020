import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Day8 {
    public static boolean runConfig(ArrayList<AbstractMap.SimpleEntry<String, Integer>> instructionSeq, int cont){
        boolean[] wasCalled = new boolean[instructionSeq.size()];
        long acc = 0;
        for(int i = 0 ; i < instructionSeq.size() ; i++){
            if(wasCalled[i]){
                return false;
            }
            else{
                String opcode = instructionSeq.get(i).getKey();
                int value = instructionSeq.get(i).getValue();

                if(opcode.equals("acc")){
                    acc += value;
                }
                if(opcode.equals("jmp") && i != cont){
                    i += value - 1;
                }
                wasCalled[i] = true;
            }
        }
        System.out.println(acc);
        return true;
    }
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("advent8.in"));
        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());
        ArrayList<AbstractMap.SimpleEntry<String, Integer>> instructionSeq = new ArrayList<>();
        for(String instruction : list){
            String [] aux = instruction.split(" ");
            String opcode = aux[0];
            int value = Integer.parseInt(aux[1]);

            instructionSeq.add(new AbstractMap.SimpleEntry<>(opcode, value));
        }
        for(int i = 0 ; i < instructionSeq.size(); i++){
            if(runConfig(instructionSeq, i)){
                System.out.println(i);
                break;
            }
        }
    }
}
