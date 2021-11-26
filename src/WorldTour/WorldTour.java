package WorldTour;

import java.lang.reflect.Array;
import java.util.Scanner;

public class WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder destinations = new StringBuilder(scanner.nextLine());

        String input = scanner.nextLine();

        while(!input.equals("Travel")){
            String[] command = input.split(":");
            //Hawai::Cyprys-Greece
            switch (command[0]){
                case "Add Stop":
                    int index = Integer.parseInt(command[1]);
                    if (indexIsValid(index,destinations.toString())) {
                        destinations.insert(index,command[2]);
                    }
                    break;
                case "Remove Stop":
                    int start = Integer.parseInt(command[1]);
                    int end = Integer.parseInt(command[2]);
                    if (indexIsValid(start,destinations.toString()) &&
                            indexIsValid(end,destinations.toString())){

                        destinations.delete(start,end + 1);
                    }
                    break;
                case "Switch":
                    String old = command[1];
                    String newString = command[2];
                    String destinationString = destinations.toString().replace(old,newString);
                    destinations = new StringBuilder(destinationString);
                    break;
            }
            System.out.println(destinations);
            input = scanner.nextLine();
        }
        System.out.printf("Ready for world tour! Planned stops: %s", destinations);
    }

    private static boolean indexIsValid(int index, String destinations) {
        return index >= 0 && index < destinations.length();
    }
}
