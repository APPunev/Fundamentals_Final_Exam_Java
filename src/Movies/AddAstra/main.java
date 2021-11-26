package Movies.AddAstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String regex = "(?<sep>[#\\|])(?<food>[A-Za-z\\s]+)\\1(?<date>\\d{2}\\/\\d{2}\\/\\d{2})\\1(?<cal>\\d{1,5})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<Astra> itemsList = new ArrayList<>();
        int days = 0;
        int totalCal = 0;

        while(matcher.find()){
            String food = matcher.group("food");
            String date = matcher.group("date");
            String cal = matcher.group("cal");

            Astra astra = new Astra(food,date,cal);
            itemsList.add(astra);
            totalCal += Integer.parseInt(cal);
        }
        days = totalCal / 2000;
        System.out.printf("You have food to last you for: %d days!%n",days);
        for (Astra astra : itemsList) {
            System.out.println(astra);
        }
    }
    public static class Astra {
        private String food;
        private String date;
        private String cal;

        public Astra(String food, String date, String cal) {
            this.food = food;
            this.date = date;
            this.cal = cal;
        }

        public String getCal() {
            return cal;
        }

        public String getDate() {
            return date;
        }

        public String getFood() {
            return food;
        }

        @Override
        public String toString() {
            return "Item: "+this.food+", Best before: "+this.date+", Nutrition: "+this.cal;
        }
    }

}
