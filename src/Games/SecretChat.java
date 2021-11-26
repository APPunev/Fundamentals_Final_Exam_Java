package Games;

import java.util.Scanner;

public class SecretChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        String command = scanner.nextLine();

        StringBuilder bld = new StringBuilder(text);

        while(!command.equals("Reveal")){
            String[] tokens = command.split(":\\|:");
            String action = tokens[0];

            switch (action){
                case "InsertSpace":
                    int index = Integer.parseInt(tokens[1]);
                    bld.insert(index," ");
                    System.out.println(bld.toString());
                    break;
                case "Reverse":
                    String sequence = tokens[1];
                    if (text.contains(sequence)) {
                        int indexOsSqs = bld.indexOf(sequence);
                        bld.delete(indexOsSqs,indexOsSqs + sequence.length());
                        String reversedSqc = "";
                        for (int i = sequence.length() - 1; i >= 0 ; i--) {
                            reversedSqc += sequence.charAt(i);
                        }
                        bld.append(reversedSqc);
                        System.out.println(bld.toString());
                    }else{
                        System.out.println("error");
                    }
                    break;
                case "ChangeAll":
                    String replaced = tokens[1];
                    String replacement = tokens[2];
                    bld = new StringBuilder(text = text.replaceAll(replaced,replacement));
                    System.out.println(bld.toString());
                    break;
            }

            command = scanner.nextLine();
            text = bld.toString();

        }

        System.out.println("You have a new text message: " + text);
    }
}
