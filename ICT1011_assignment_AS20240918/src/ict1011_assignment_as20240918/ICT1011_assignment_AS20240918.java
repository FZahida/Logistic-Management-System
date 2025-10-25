
package ict1011_assignment_as20240918;
import java.util.Scanner;

public class ICT1011_assignment_AS20240918 {
     private static int choice;

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        do {
            printMenu();
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    cityManagement();
                    break;   
                case 2:
                    distanceManagement();
                    break;
                case 3:
                    vehicleManagement();
                    break;
                case  4:
                    deliveryRequestHandling();
                    break;
                case 5:
                    calculations();
                    break;
                case 6:
                    performanceReport();
                    break;
                case 7:
                    System.out.println("Exiting....");
                    break;
                default :
                    System.out.println("Invalid choice.");       
            }
        }while (choice !=7);
    }
    public static void printMenu(){
        System.out.println("=====Logistic Management System=====");
        System.out.println("1. City Management");
        System.out.println("2. Distance Management");
        System.out.println("3. Vehicle Management");
        System.out.println("4. Delivery requests");
        System.out.println("5. Cost,time and fuel calculations");
        System.out.println("6. Performance Report");
        System.out.println("7. Exit");
        System.out.print("Enter your choice:");
    }
    public static void cityManagement(){
        Scanner sc=new Scanner(System.in);
        do {
            System.out.println("=====City Management Menu ===== ");
            System.out.println("1. Add a city");
            System.out.println("2. Rename a city");
            System.out.println("3. Remove a city");
            System.out.println("4. List of current cities");
            System.out.println("5. Back to main menu");
            System.out.println("Enter your choice:" );
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCity();
                    break;
                case 2:
                    renameCity();
                    break;
                case 3:
                    removeCity();
                    break;
                case 4:
                    cityList();
                    break;
                case 5:
                    System.out.println("Return to the Main Menu");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }
    public static void addCity(){
        
    }
    public static void renameCity(){
        
    }
    public static void removeCity(){
        
    }   
    public static void cityList(){
        
    }
    public static void distanceManagement(){
        
    }
    public static void vehicleManagement(){
        
    }
    public static void deliveryRequestHandling(){
        
    }
    public static void calculations(){
        
    }
     public static void performanceReport(){
        
    }  
    
}
