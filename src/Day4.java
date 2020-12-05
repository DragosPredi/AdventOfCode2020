import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {
    static HashMap<String, Boolean> initMap() {
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("byr", false);
        map.put("iyr", false);
        map.put("eyr", false);
        map.put("hgt", false);
        map.put("hcl", false);
        map.put("ecl", false);
        map.put("pid", false);
        map.put("cid", false);
        return map;
    }

    static boolean isValid(HashMap<String, Boolean> map) {
        if (!map.get("byr")) {
            return false;
        }
        if (!map.get("iyr")) {
            return false;
        }
        if (!map.get("eyr")) {
            return false;
        }
        if (!map.get("hgt")) {
            return false;
        }
        if (!map.get("hcl")) {
            return false;
        }
        if (!map.get("ecl")) {
            return false;
        }
        return map.get("pid");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("advent4.in"));

        ArrayList<String> list = (ArrayList<String>) reader.lines().collect(Collectors.toList());
        HashMap<String, Boolean> map = initMap();
        List<String> eyeColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        int passCont = 0;
        for (String el : list) {
            if (el.isBlank()) {
                if (isValid(map)) {
                    passCont++;
                }
                map = initMap();
            } else {
                String[] credentials = el.split(" ");
                for (String cred : credentials) {
                    String key = cred.split(":")[0];
                    String value = cred.split(":")[1];
                    boolean ok = true;
                    if (key.equals("byr")) {
                        int val = Integer.parseInt(value);
                        if (val < 1920 || val > 2002)
                            ok = false;
                    }
                    if (key.equals("iyr")) {
                        int val = Integer.parseInt(value);
                        if (val < 2010 || val > 2020)
                            ok = false;
                    }
                    if (key.equals("eyr")) {
                        int val = Integer.parseInt(value);
                        if (val < 2020 || val > 2030)
                            ok = false;
                    }
                    if (key.equals("hgt")) {
                        Pattern p = Pattern.compile("\\d+");
                        Matcher m = p.matcher(value);
                        int val = 0;
                        if (m.find())
                            val = Integer.parseInt(m.group());
                        if (value.contains("in")) {
                            if (val < 59 || val > 76) {
                                ok = false;
                            }
                        } else if (value.contains("cm")) {
                            if (val < 150 || val > 193) {
                                ok = false;
                            }
                        }
                    }
                    if (key.equals("hcl")) {
                        char[] seq = value.toCharArray();
                        for (int i = 1; i < seq.length; i++) {
                            if ((seq[i] < 'a' || seq[i] > 'f') &&
                                    seq[i] < '0' && seq[i] > '9') {
                                ok = false;
                                break;
                            }
                        }
                        if (seq[0] != '#')
                            ok = false;
                    }
                    if (key.equals("ecl")) {
                        if (!eyeColors.contains(value)) {
                            ok = false;
                        }
                    }
                    if (key.equals("pid")) {
                        Pattern p = Pattern.compile("\\d+");
                        Matcher m = p.matcher(value);
                        if (m.find())
                            if (m.group().length() != 9)
                                ok = false;
                    }
                    System.out.println(key + " " + " " + value + " " + ok);
                    map.put(key, ok);
                }
            }
        }
        if (isValid(map))
            //passCont++;
            System.out.println(passCont);
    }
}
