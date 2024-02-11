package org.example;

public class Main {
    public static void main(String[] args) {

        CarRentalSystem carRentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001","Toyota","creta",1000);
        Car car2 = new Car("C002","TATA","punch",900);
        Car car3 = new Car("C003","SUZUKI","dsire",800);
        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.addCar(car3);

        carRentalSystem.menu();


        }
    }
