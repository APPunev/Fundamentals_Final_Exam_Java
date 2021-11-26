package Pirats;

import java.util.Scanner;

public class ActivationKeys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder input = new StringBuilder(scanner.nextLine());
        String command = scanner.nextLine();
        while(!command.equals("Generate")){

            String[] tokens = command.split(">>>");
            String action = tokens[0];

            switch (action){
                case "Contains":
                    String substring = tokens[1];
                    if (input.indexOf(substring) != -1) {
                        System.out.printf("%s contains %s.%n",input, substring);
                    }else{
                        System.out.println("Substring not found!");
                    }
                    break;
                case "Flip":
                    String upperLower = tokens[1];
                    int startIndex = Integer.parseInt(tokens[2]);
                    int endIndex = Integer.parseInt(tokens[3]);

                    for (int i = startIndex; i < endIndex; i++) {
                        char c = input.charAt(i);
                        if (upperLower.equals("Lower")) {
                            input.setCharAt(i,Character.toLowerCase(c));
                        }else{
                            input.setCharAt(i,Character.toUpperCase(c));
                        }
                    }
                    System.out.println(input);
                    break;
                case "Slice":
                    int start = Integer.parseInt(tokens[1]);
                    int end = Integer.parseInt(tokens[2]);
                    input.delete(start,end);
                    System.out.println(input);
                    break;
            }

            command = scanner.nextLine();
        }

        System.out.println("Your activation key is: " + input);
    }
}
