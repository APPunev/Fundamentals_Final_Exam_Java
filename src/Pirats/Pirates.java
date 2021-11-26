package Pirats;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pirates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\|\\|");

        List<City> cityList = new ArrayList<>();

        while(!input[0].equals("Sail")){
            String city = input[0];
            int population = Integer.parseInt(input[1]);
            int gold = Integer.parseInt(input[2]);

            City cityObj = new City(city,population,gold);

            if (cityExist(cityList,city)) {
                updateExistingCity(city,population,gold,cityList);
            }else{
                cityList.add(cityObj);
            }


            input = scanner.nextLine().split("\\|\\|");
        }

        String command = scanner.nextLine();
        while(!command.equals("End")){
            String[] tokens = command.split("=>");
            String action = tokens[0];
            String town = tokens[1];

            switch (action){
                case "Plunder":
                    int peopleKilled = Integer.parseInt(tokens[2]);
                    int stolenGold = Integer.parseInt(tokens[3]);
                    plunderCity(cityList,town,peopleKilled,stolenGold);
                    break;
                case "Prosper":
                    int prosperGold = Integer.parseInt(tokens[2]);
                    if (prosperGold < 0) {
                        System.out.println("Gold added cannot be a negative number!");
                    }else{
                        addGold(cityList,town,prosperGold);
                    }
                    break;
            }

            command = scanner.nextLine();
        }

        if (cityList.size() > 0) {
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n",cityList.size());
            cityList.stream()
                    .sorted((p1,p2)->{
                        int result = Integer.compare(p2.getGold(), p1.getGold());
                        if (result == 0) {
                            result = p1.getCity().compareTo(p2.getCity());
                        }
                        return result;
                    }).forEach(el-> System.out.printf("%s -> Population: %d citizens, Gold: %d kg%n", el.getCity(),el.getPopulation(),el.getGold()));
        }else{
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        }
    }

    private static void addGold(List<City> cityList, String town, int prosperGold) {
        for (City city:cityList) {
            if (city.getCity().equals(town)) {
                int currentGold = city.getGold();
                city.setGold(currentGold + prosperGold);
                System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n",prosperGold,town,city.getGold());
                break;
            }
        }
    }

    private static void plunderCity(List<City> cityList, String town, int peopleKilled, int stolenGold) {
        for (City city:cityList) {
            if (city.getCity().equals(town)) {
                System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n",town,stolenGold,peopleKilled);
                city.setGold(city.getGold() - stolenGold);
                city.setPopulation(city.getPopulation() - peopleKilled);
                if (city.getGold() <= 0 || city.getPopulation() <= 0) {
                    cityList.remove(city);
                    System.out.println(town + " has been wiped off the map!");
                }
                break;
            }
        }
    }

    private static boolean cityExist(List<City> cityList, String city) {
        for (City cityInList:cityList) {
            if (cityInList.getCity().equals(city)) {
                return true;
            }
        }
        return false;
    }

    private static void updateExistingCity(String city, int population, int gold, List<City> cityList) {

        for (City cityInList:cityList) {
            if (cityInList.getCity().equals(city)) {
                int currentPopulation = cityInList.getPopulation();
                int currentGold = cityInList.getGold();
                cityInList.setGold(currentGold + gold);
                cityInList.setPopulation(currentPopulation + population);
                break;
            }
        }
    }

    public static class City{
        private String city;
        private int population;
        private int gold;

        public City(String city, int population, int gold) {
            this.city = city;
            this.population = population;
            this.gold = gold;
        }

        public String getCity() {
            return city;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }
    }
}
