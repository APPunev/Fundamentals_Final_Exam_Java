package Games;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class NeedforSpeedIII {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Car> carList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\|");
            String carName = input[0];
            int mileage = Integer.parseInt(input[1]);
            int fuel = Integer.parseInt(input[2]);

            Car car = new Car(carName, mileage, fuel);
            carList.add(car);
        }

        String command = scanner.nextLine();

        while(!command.equals("Stop")){
            String[] tokens = command.split(" : ");
            String action = tokens[0];

            switch (action){
                case "Drive"://"Drive : {car} : {distance} : {fuel}"
                    String driveCar = tokens[1];
                    int driveDistance = Integer.parseInt(tokens[2]);
                    int driveFuel = Integer.parseInt(tokens[3]);

                    driveCarMethod(carList,driveCar,driveDistance,driveFuel);

                    break;
                case "Refuel": //"Refuel : {car} : {fuel}"
                    String refuelCar = tokens[1];
                    int refuelFule = Integer.parseInt(tokens[2]);

                    refuelTank(refuelCar,refuelFule, carList);
                    break;
                case "Revert": //"Revert : {car} : {kilometers}"
                    String decreaseCar = tokens[1];
                    int decreaseKm = Integer.parseInt(tokens[2]);
                    
                    decreaseMileage(carList,decreaseCar,decreaseKm);
                    break;
            }
            command = scanner.nextLine();
        }

        carList
                .stream()
                .sorted((p1,p2)->{
                    int result = Integer.compare(p2.getMileage(), p1.getMileage());
                    if (result == 0) {
                        result = p1.getCarName().compareTo(p2.getCarName());
                    }
                    return result;
                })
                .forEach(el -> System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n",
                el.getCarName(), el.getMileage(), el.getFuel()));

    }

    private static void decreaseMileage(List<Car> carList, String decreaseCar, int decreaseKm) {
        for (Car car:carList) {
            if (car.getCarName().equals(decreaseCar)) {
                if (car.getMileage() - decreaseKm >= 10000) {
                    System.out.printf("%s mileage decreased by %d kilometers%n",decreaseCar, decreaseKm);
                car.setMileage(car.getMileage() - decreaseKm);
                }else{
                    car.setMileage(10000);
                }
                break;
            }

        }
    }

    private static void refuelTank(String refuelCar, int refuelFule, List<Car> carList) {
        for (Car car:carList) {
            if (car.getCarName().equals(refuelCar)) {
                int currentFuel = car.getFuel();
                if (currentFuel < 75 && currentFuel + refuelFule <= 75) {
                    System.out.printf("%s refueled with %d liters%n",refuelCar,refuelFule);
                    car.setFuel(currentFuel + refuelFule);

                }else if (currentFuel < 75 && currentFuel + refuelFule > 75) {
                    int overFuel = currentFuel + refuelFule;
                    int neededFule = 75 - currentFuel;
                    System.out.printf("%s refueled with %d liters%n",refuelCar,neededFule);
                    car.setFuel(75);
                }else{
                    System.out.printf("%s refueled with %d liters%n",refuelCar,0);
                }
                break;
            }
        }
    }


    private static void driveCarMethod(List<Car> carList, String driveCar, int driveDistance, int driveFuel) {
        for (Car car:carList) {
            if (car.getCarName().equals(driveCar)) {
                if (car.getFuel() >= driveFuel) {
                    car.setFuel(car.getFuel() - driveFuel);
                    car.setMileage(car.getMileage() + driveDistance);
                    System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n", driveCar,driveDistance,driveFuel);
                    if (car.getMileage() >= 100000) {
                        System.out.println("Time to sell the " + car.getCarName() + "!");
                        carList.remove(car);
                    }
                    break;
                }else{
                    System.out.println("Not enough fuel to make that ride");
                }
            }
        }
    }

    public static class Car{

        private String carName;
        private int mileage;
        private int fuel;

        public Car(String carName, int mileage, int fuel) {
            this.carName = carName;
            this.mileage = mileage;
            this.fuel = fuel;
        }

        public int getFuel() {
            return fuel;
        }

        public void setFuel(int fuel) {
            this.fuel = fuel;
        }

        public int getMileage() {
            return mileage;
        }

        public void setMileage(int mileage) {
            this.mileage = mileage;
        }

        public String getCarName() {
            return carName;
        }
    }
}
