package WorldTour;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> destinations = new ArrayList<>();

        String input = scanner.nextLine();
        int points = 0;

        String regex = "(?<separator>[=\\/])(?<city>[A-Z][A-Za-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()){
            destinations.add(matcher.group("city"));
            points += matcher.group("city").length();
        }

        System.out.print("Destinations: ");
        System.out.println(String.join(", ",destinations));
        System.out.printf("Travel Points: %d%n",points);
    }
}
