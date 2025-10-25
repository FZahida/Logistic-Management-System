
package ict1011_assignment_as20240918;
import java.util.Scanner;

public class ICT1011_assignment_AS20240918 {
     private static int choice;
     static final int MAX_CITIES=30;
     static int cityCount;
     
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] cities = new String[MAX_CITIES];
        int[][] distance = new int[MAX_CITIES][MAX_CITIES];

        do {
            printMenu();
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    cityManagement(cities);
                    break;   
                case 2:
                    distanceManagement(distance,cities);
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
    
    public static void cityManagement(String[]cities){
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
                    addCity(cities);
                    break;
                case 2:
                    renameCity(cities);
                    break;
                case 3:
                    removeCity(cities);
                    break;
                case 4:
                    cityList(cities);
                    break;
                case 5:
                    System.out.println("Return to the Main Menu");
                    break;
                default: 
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }
    
    public static void addCity(String[] cities ){
        Scanner sc=new Scanner(System.in);
        if (cityCount>=MAX_CITIES){
            System.out.println("City list is already full.");
            return;
        }
        System.out.print("Enter city name: ");
        String name = sc.nextLine();
        cities[cityCount++] = name;
        System.out.println("City added successfully.");
        System.out.println("There are "+cityCount+" cities in the city list.");
        
    }
    
    public static void renameCity(String[]cities){
        Scanner sc=new Scanner (System.in);
        System.out.println("Enter city index to rename: ");
        int index =sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new name of the city: ");
        cities[index] = sc.nextLine();
        System.out.println("City renamed successfully.");   
    }
    
    public static void removeCity(String[]cities){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter city index to remove: ");
        int index =sc.nextInt();
        for (int i=index; i<cityCount- 1;i++ ){
            cities[i]=cities[i+1];          
        }   
    }   
    
    public static void cityList(String[]cities){
        System.out.println("List of the cities");
        for(int i=0; i<cityCount; i++ ){
            System.out.println(i+1 +"."+cities[i]);      
        }    
    }
    
    public static void distanceManagement(int[][]distance,String[]cities){
        Scanner sc= new Scanner(System.in);
         do {
             System.out.println("=====Distance Management Menu ===== ");
             System.out.println("1. Input distance between two cities");
             System.out.println("2. Edit distance between two cities");
             System.out.println("3. View distance table");
             System.out.println("4. Back to the main menu");
             System.out.println("Enter your choice: ");
             choice = sc.nextInt();
             sc.nextLine();
 
            switch (choice) {
                case 1:
                    inputDistance(distance);
                    break;
                case 2:
                    editDistance(distance,cities);
                   break;
                case 3:
                    distanceTable(cities,distance);
                    break;
                case 4:
                    System.out.println("Return to the main menu");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);        
    }
    
    public static void inputDistance(int[][] distance){
        Scanner sc=new Scanner(System.in);
        if(cityCount<2){
            System.out.println("Add at least 2 cities first");
            return;
        }
        System.out.print("Enter source city index :");
        int source=sc.nextInt();
        System.out.print("Enter destination city index :");
        int destination=sc.nextInt();
        
        if(source==destination){
            System.out.println("Source and Destination city cannot be same");
            return;
        }
        if(distance[source][destination]!=0){
            System.out.println("Distance already exists.");
            return;           
        }
        System.out.print("Enter distance in (km): ");
        int dist=sc.nextInt();
        
        distance[source][destination]=dist;
        distance[destination][source]=dist;
        
        System.out.println("Distance added successfully");   
    }
    
    public static void editDistance(int[][]distance,String[]cities){
        Scanner sc=new Scanner(System.in);
        if(cityCount<2){
            System.out.println("Add at least 2 cities first");
            return;
        }
        System.out.print("Enter source city index :");
        int source=sc.nextInt();
        System.out.print("Enter destination city index :");
        int destination=sc.nextInt();
        
        if(source==destination){
            System.out.println("Invalid!...Source and Destination city cannot be same");
            return;
        }
         if(distance[source][destination] == 0){
            System.out.println("No existing distance found.");
            return;           
        }
        System.out.println("Current distance between"+ cities[source]+"and"+cities[destination]+":"+distance[source][destination]+"km");
        System.out.print("Enter new distance (km): ");
        int NewDist=sc.nextInt();
        
        distance[source][destination]=NewDist;
        distance[destination][source]=NewDist;
        
        System.out.println("Distance updated successfully");           
    }
    
    public static void distanceTable(String[]cities,int[][]distance){
        if (cityCount == 0) {
            System.out.println("No cities added yet!");
            return;
        }

        System.out.println("--- Distance Table (km) ---");
        System.out.print("        ");
        for (int i = 0; i < cityCount; i++) {
           System.out.printf("%10s", cities[i]);
        }
        System.out.println();

        for (int i = 0; i < cityCount; i++) {
           System.out.printf("%-8s", cities[i]);
           for (int j = 0; j < cityCount; j++) {
                System.out.printf("%10d", distance[i][j]);
            }
           System.out.println();
        }
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
