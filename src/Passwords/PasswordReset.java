package Passwords;

import java.util.Scanner;

public class PasswordReset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        //Siiceercaroetavm!:?:ahsott.:i:nstupmomceqr

        String command = scanner.nextLine();
        while(!command.equals("Done")){
            String[] tokens = command.split(" ");
            switch (tokens[0]){
                case "TakeOdd":
                    //TakeOdd
                    input = oddString(input);
                    break;
                case "Cut":
                    //Cut 15 3
                    int startIndex = Integer.parseInt(tokens[1]);
                    int endIndex = startIndex + Integer.parseInt(tokens[2]);
                    String substring = input.substring(startIndex, endIndex);
                    input = input.replaceFirst(substring,"");
                    System.out.println(input);
                    break;
                case "Substitute":
                    //Substitute | ^
                    String substringToReplace = tokens[1];
                    String newString = tokens[2];
                    if (input.contains(substringToReplace)) {
                        input = input.replaceAll(substringToReplace,newString);
                        System.out.println(input);
                    }else{
                        System.out.println("Nothing to replace!");
                    }
                    break;
            }
            command = scanner.nextLine();
        }

        System.out.println("Your password is: " + input);
    }

    private static String oddString(String input) {
        String newInput = "";
        for (int i = 1; i < input.length(); i+=2) {
            newInput += input.charAt(i);
        }
        System.out.println(newInput);
        return newInput;
    }
}
