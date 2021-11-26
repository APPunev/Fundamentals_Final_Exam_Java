package Passwords;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regex = "@#+(?<product>[A-Z][A-Za-z0-9]{4,}[A-Z])@#+";
        Pattern pattern = Pattern.compile(regex);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String product = matcher.group("product");
                printProductGroup(product);
            }
            else{
                System.out.println("Invalid barcode");
            }

        }

    }

    private static void printProductGroup(String product) {
        StringBuilder bld = new StringBuilder();
        boolean hasDigit = false;
        for (char c :product.toCharArray()) {
            if (Character.isDigit(c)) {
                bld.append(c);
                hasDigit = true;
            }
        }
        if (!hasDigit) {
            bld.append("00");
        }
        System.out.println("Product group: " + bld.toString());
    }
}
