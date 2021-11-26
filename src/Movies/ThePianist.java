package Movies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ThePianist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Main> piecesList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String pieces = scanner.nextLine();
            //Moonlight Sonata|Beethoven|C# Minor
            String[] tokens = pieces.split("\\|");
            String pieceName = tokens[0];
            String composer = tokens[1];
            String key = tokens[2];

            Main main = new Main(pieceName,composer,key);
            piecesList.add(main);
        }

        String command = scanner.nextLine();
        while(!command.equals("Stop")){

            String[] tokens = command.split("\\|");

            switch (tokens[0]){
                case "Add"://Add|Sonata No.2|Chopin|B Minor
                    String addName = tokens[1];
                    String addComposer = tokens[2];
                    String addKey = tokens[3];
                    if (addNameDoNotExist(piecesList,addName)) {
                        Main main = new Main(addName,addComposer,addKey);
                        piecesList.add(main);
                        System.out.printf("%s by %s in %s added to the collection!%n",addName, addComposer,addKey);
                    }else{
                        System.out.println(addName + " is already in the collection!");
                    }
                    break;
                case "Remove":
                    //Remove|{piece}
                    String removePiece = tokens[1];
                    if (addNameDoNotExist(piecesList,removePiece)) {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n",removePiece);
                    }else{
                        piecesList = removePieceMethod(piecesList,removePiece);
                        System.out.printf("Successfully removed %s!%n",removePiece);
                    }
                    break;
                case "ChangeKey":
                    //ChangeKey|{piece}|{new key}
                    String changeName = tokens[1];
                    String newKey = tokens[2];
                    if (addNameDoNotExist(piecesList,changeName)) {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n",changeName);
                    }else{
                        piecesList = changeKey(piecesList,changeName,newKey);
                        System.out.printf("Changed the key of %s to %s!%n",changeName, newKey);
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        //{Piece} -> Composer: {composer}, Key: {key}
        piecesList.sort(Comparator.comparing(Main::getPieceName).thenComparing(Main::getComposer));
        piecesList.forEach(el -> System.out.println(el));
    }

    private static List<Main> changeKey(List<Main> piecesList, String changeName, String newKey) {
        for (Main piece:piecesList) {
            boolean nameExist = piece.getPieceName().equals(changeName);
            if (nameExist) {
                piece.setKey(newKey);
                return piecesList;
            }
        }
        return piecesList;
    }

    private static List<Main> removePieceMethod(List<Main> piecesList, String removePiece) {
        for (Main piece:piecesList) {
            boolean nameExist = piece.getPieceName().equals(removePiece);
            if (nameExist) {
                piecesList.remove(piece);
                return piecesList;
            }
        }
        return piecesList;
    }

    private static boolean addNameDoNotExist(List<Main> piecesList, String addName) {
        for (Main piece:piecesList) {
            boolean nameExist = piece.getPieceName().equals(addName);
            String p  = piece.getPieceName();
            String a = addName;
            if (nameExist) {
                return false;
            }
        }
        return true;
    }

    public static class Main{
        private String pieceName;
        private String composer;
        private String key;

        public Main(String pieceName, String composer, String key) {
            this.pieceName = pieceName;
            this.composer = composer;
            this.key = key;
        }

        public String getPieceName() {
            return pieceName;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getComposer() {
            return composer;
        }

        @Override
        public String toString() {
            return this.pieceName + " -> Composer: " + this.composer + ", Key: "+ this.key;
        }
    }
}


