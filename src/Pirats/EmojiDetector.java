package Pirats;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String regex = "(?<output>(?<separator>[:]{2}|[*]{2})(?<emoji>[A-Z][a-z]{2,})\\2)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<String> coolEmoji = new ArrayList<>();
        long trashold = 1;
        for (char c :input.toCharArray()) {
            if (Character.isDigit(c)) {
                trashold *= Integer.parseInt(String.valueOf(c));
            }
        }

        int counter = 0;
        while(matcher.find()){
            counter++;
            long sum = 0;
            String emoji = matcher.group("emoji");
            sum = sumEmoji(emoji);
            if (sum >= trashold) {
                coolEmoji.add(matcher.group("output"));
            }
        }
        System.out.println("Cool threshold: " + trashold);
        System.out.println(counter + " emojis found in the text. The cool ones are:");
        coolEmoji.stream().forEach(el -> System.out.println(el));
    }

    private static long sumEmoji(String emoji) {
        long sum = 0;
        for (char c:emoji.toCharArray()) {
            sum += c;
        }
        return sum;
    }
}
