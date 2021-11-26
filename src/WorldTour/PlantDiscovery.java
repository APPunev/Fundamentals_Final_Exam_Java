package WorldTour;

import java.util.*;
import java.util.stream.Collectors;

public class PlantDiscovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, PlantData> currentPlants = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split("<->");
            String name = data[0];
            int rarity = Integer.parseInt(data[1]);
            PlantData newPlant = new PlantData(new ArrayList<>(), rarity);
            currentPlants.put(name,newPlant);
        }

        String command = scanner.nextLine();
        while(!command.equals("Exhibition")){
            String[] commandParts = command.split(": | - ");
            String commandName = commandParts[0];
            String plantName = commandParts[1];

            if (!currentPlants.containsKey(plantName)) {
                System.out.println("Error");
                continue;
            }

            switch (commandParts[0]){
                case "Rate":
                    int rating = Integer.parseInt(commandParts[2]);
                    currentPlants.get(plantName).addRating(rating);
                    break;
                case "Update":
                    int rarity = Integer.parseInt(commandParts[2]);
                    currentPlants.get(plantName).setRarity(rarity);
                    break;
                case "Reset":
                    currentPlants.get(plantName).removeAllRatings();
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
            command = scanner.nextLine();
        }

        System.out.println("Plants for the exhibition:");
        currentPlants.entrySet().stream()
                .sorted((p1,p2) -> {
                    int result = Integer.compare(p2.getValue().getRarity(),p1.getValue().getRarity());
                    if (result == 0) {
                        result = Double.compare(p2.getValue().getAvr(),p1.getValue().getAvr());
                    }
                    return result;
                }).forEach(p -> System.out.printf("- %s; Rarity: %d; Rating: %.2f%n",p.getKey(),p.getValue().getRarity(),p.getValue().getAvr()));
    }

    public static class PlantData{
        private List<Integer> ratings;
        private int rarity;

        public void removeAllRatings(){
            this.ratings.clear(); // method clear() - clears all registered data.
        }

        public void addRating(int rating){
            ratings.add(rating);
        }

        public void setRarity(int rarity) {
            this.rarity = rarity;
        }

        public PlantData(List<Integer> ratings, int rarity) {
            this.ratings = ratings;
            this.rarity = rarity;
        }

        public int getRarity() {
            return rarity;
        }

        public double getAvr(){
            double sumAllRaitings = 0;
            for (Integer rating:this.ratings) {
                sumAllRaitings += rating;
            }
            if (sumAllRaitings == 0) {
                return 0;
            }else{
                return sumAllRaitings / this.ratings.size();
            }
        }
    }
}
