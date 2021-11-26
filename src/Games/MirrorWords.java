package Games;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MirrorWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String regex = "(?<separator>[@#])(?<first>[A-Za-z]{3,})\\1\\1(?<second>[A-Za-z]{3,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        Map<String, String> words = new LinkedHashMap<>();
        List<String> lastPrint = new ArrayList<>();

        int counter = 0;

        while(matcher.find()){
            words.put(matcher.group("first"), matcher.group("second"));
        }
        if (words.size() == 0) {
            System.out.println("No word pairs found!");
        }else{
            System.out.println(words.size() + " word pairs found!");
        }

        if (!mirrorWordsExist(words)) {
            System.out.println("No mirror words!");
        }else{
            System.out.println("The mirror words are:");
            words.entrySet()
                    .stream()
                    .forEach(el -> {
                String firstWord = el.getKey();
                String secondWord = el.getValue();
                String secondWordRev = "";
                for (int i = secondWord.length()-1; i >= 0 ; i--) {
                    secondWordRev += secondWord.charAt(i);
                }
                if (firstWord.equals(secondWordRev)) {
                    String bld = el.getKey() + " <=> " + el.getValue();
                    lastPrint.add(bld);
                }
            });
            System.out.print(String.join(", ", lastPrint));
        }
    }



    private static boolean mirrorWordsExist(Map<String, String> words) {
        for (Map.Entry<String, String> entry:words.entrySet()) {
            String firstWord = entry.getKey();
            String secondWord = entry.getValue();
            String secondWordRev = "";
            for (int i = secondWord.length()-1; i >= 0 ; i--) {
                secondWordRev += secondWord.charAt(i);
            }
            if (firstWord.equals(secondWordRev)) {
                return true;
            }
        }
        return false;
    }
}
