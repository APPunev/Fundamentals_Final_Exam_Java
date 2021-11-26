package Movies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TheImitationGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //	•	"Insert {index} {value}":
        //	•	Inserts the given value before the given index in the string

        //	•	"ChangeAll {substring} {replacement}":
        //	•	Changes all occurrences of the given substring with the replacement text

        //Input / Constraints
        //	•	On the first line, you will receive a string with a message.
        //	•	On the following lines, you will be receiving commands, split by '|' .

        //Output
        //	•	After the "Decode" command is received, print this message: "The decrypted message is: {message}"

        String message = scanner.nextLine();

        String command = scanner.nextLine();
        while (!command.equals("Decode")) {
            String[] data = command.split("\\|");
            switch (data[0]) {
                case "Move":
                    int lettersCount = Integer.parseInt(data[1]);
                    String leftPart = message.substring(0, lettersCount);
                    String rightPart = message.substring(lettersCount);
                    message = String.valueOf(rightPart) + String.valueOf(leftPart);
                    break;
                case "Insert":
                    int index = Integer.parseInt(data[1]);
                    String value = data[2];
                    String left = message.substring(0, index);
                    String right = message.substring(index);
                    message = left + value + right;
                    break;
                case "ChangeAll":
                    char current = data[1].charAt(0);
                    char newChar = data[2].charAt(0);
                    message = message.replace(current, newChar);
                    break;
            }
            command = scanner.nextLine();
        }
        System.out.println("The decrypted message is: " + message);

    }

}