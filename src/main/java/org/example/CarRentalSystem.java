package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars=new ArrayList<>();
        customers=new ArrayList<>();
        rentals=new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car,Customer customer,int days){
        if(car.isAvailable())
        {
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }
        else{
            System.out.println("Car is not available to rent");
        }
    }

    public void returnCar(Car car){
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals)
        {
            if(rental.getCar()== car){
                rentalToRemove=rental;
                break;
            }
        }
        if(rentalToRemove != null)
        {
            rentals.remove(rentalToRemove);

        }else
        {
            System.out.println("Car was not rented.");
        }

    }
    public void menu(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("------Car Rental system------");
            System.out.println("1.Rent a car");
            System.out.println("2.Return a car");
            System.out.println("3.Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice==1){
                System.out.println("--Rent a Car");
                System.out.println("Enter your name ");
                String customerName = sc.nextLine();

                System.out.println("available cars:");
                for(Car car:cars){
                    if(car.isAvailable()){
                        System.out.println(car.getCarId()+" "+car.getBrand()+" "+car.getModel());
                    }
                }
                System.out.println("Enter a carID you want to rent: ");
                String carId = sc.nextLine();

                System.out.println("Enter a number of days for rental");
                int rentalDays = sc.nextInt();
                sc.nextLine();

                Customer newCustomer = new Customer("CUS"+(customers.size()+1),customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for(Car car:cars){
                    if(car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar=car;
                        break;
                    }
                }
                if(selectedCar!=null){
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("---Rental Info---");
                    System.out.println("CustomerId "+ newCustomer.getCustomerId());
                    System.out.println("Customer Name "+newCustomer.getName());
                    System.out.println("Car "+selectedCar.getModel()+" "+selectedCar.getBrand());
                    System.out.println("Rental days"+rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = sc.nextLine();
                    if(confirm.equalsIgnoreCase("Y")){
                        rentCar(selectedCar,newCustomer,rentalDays);
                        System.out.println("Rented successfully");
                    }else{
                        System.out.println("rented cancelled");
                    }

                    }else {
                    System.out.println("Invalid selecetion");

                }
            } else if (choice==2) {

                    System.out.println("-----Return a car-----");
                    System.out.println("Enter the car ID you want to return: ");
                    String carId = sc.nextLine();

                    Car carToReturn = null;
                    for (Car car:cars){
                        if(car.getCarId().equals(carId) && !car.isAvailable()){
                            carToReturn = car;
                            break;
                        }
                    }

                    if(carToReturn!=null){
                        Customer customer =null;
                        for(Rental rental:rentals){
                            if(rental.getCar() == carToReturn){
                                customer=rental.getCustomer();
                                break;
                            }
                        }
                        if(customer!=null){
                            returnCar(carToReturn);
                            System.out.println("Returned car successfully by "+customer.getName());
                        }else {
                            System.out.println("Car was not rented");
                        }
                    }else {
                        System.out.println("Invalid carId or Car was not rented");

                    }

                } else if (choice==3) {
                    break;

                }else {
                    System.out.println("Invalid choice.Please enter a valid option.");
                }
            }
            System.out.println("Thanks you for using car rental system");
        }

    }


