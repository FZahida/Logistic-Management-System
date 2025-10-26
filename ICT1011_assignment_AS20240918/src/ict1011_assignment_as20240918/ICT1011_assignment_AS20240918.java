
package ict1011_assignment_as20240918;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class ICT1011_assignment_AS20240918 {
     private static int choice;
     
     static final int MAX_CITIES=30;
     static int cityCount;
     
     static final double FUEL_PRICE = 310.0;
     
     static final int MAX_DELIVERIES = 50;
     static int deliveryCount;
     static int[] sourceCity = new int[MAX_DELIVERIES];
     static int[] destinationCity = new int[MAX_DELIVERIES];
     static double[] vehicleWeight = new double[MAX_DELIVERIES];
     static int[] vehicleType = new int[MAX_DELIVERIES];
     static double[] totalCost = new double[MAX_DELIVERIES];
     static double[] totalTime = new double[MAX_DELIVERIES];
     
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] cities = new String[MAX_CITIES];
        int[][] distance = new int[MAX_CITIES][MAX_CITIES];
        
        String[] vehicles = {"Van", "Truck", "Lorry"};
        int[] capacity = {1000, 5000, 10000};
        int[] ratePerKm = {30, 40, 80};
        int[] speed = {60, 50, 45};
        int[] efficiency = {12, 6, 4};
        loadDataFromFile(cities,distance);
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
                    deliveryRequestHandling(distance,vehicles,capacity,cities,ratePerKm,speed,efficiency);
                    break;
                case 4:
                    findLeastCostPath(cities,distance);
                    break;
                case 5:
                    performanceReport(distance);
                    break;
                case 6:
                    System.out.println("Exiting.....");
                    break;
                default :
                    System.out.println("Invalid choice.");       
            }
        }while (choice !=6);
    }
    
    public static void printMenu(){
        System.out.println("/=====Logistic Management System=====/");
        System.out.println("1. City Management");
        System.out.println("2. Distance Management");
        System.out.println("3. Delivery requests");
        System.out.println("4. Find Least-Cost Path");
        System.out.println("5. Performance Report");
        System.out.println("6. Exit");
        System.out.print("Enter your choice:");
    }
    
    public static void cityManagement(String[]cities){
        Scanner sc=new Scanner(System.in);
        do {
             System.out.println("====City Management Menu==== ");
             System.out.println("1. Add a city");
             System.out.println("2. Rename a city");
             System.out.println("3. Remove a city");
             System.out.println("4. List of current cities");
             System.out.println("0. Back to Main Menu");
             System.out.println("Enter your choice: ");
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
                case 0:
                    System.out.println("Return to the Main Menu");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);  
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
        cities[index-1] = sc.nextLine();
        System.out.println("City renamed successfully.");   
    }
    
    public static void removeCity(String[]cities){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter city index to remove: ");
        int index =sc.nextInt();
        for (int i=index-1; i<cityCount- 1;i++ ){
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
             System.out.println("====Distance Management Menu==== ");
             System.out.println("1. Input distance between two cities");
             System.out.println("2. Edit distance between two cities");
             System.out.println("3. View distance table");
             System.out.println("0. Back to the Main Menu");
             System.out.println("Enter your choice: ");
             choice = sc.nextInt();
             sc.nextLine();
 
            switch (choice) {
                case 1:
                    inputDistance(distance,cities);
                    break;
                case 2:
                    editDistance(distance,cities);
                   break;
                case 3:
                    distanceTable(cities,distance);
                    break;
                case 0:
                    System.out.println("Return to the Main Menu");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);        
    }
    
    public static void inputDistance(int[][] distance,String[]cities){
        cityList(cities);
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
        
        distance[source-1][destination-1]=dist;
        distance[destination-1][source-1]=dist;
        
        System.out.println("Distance added successfully");   
    }
    
    public static void editDistance(int[][]distance,String[]cities){
        Scanner sc=new Scanner(System.in);
        if(cityCount<2){
            System.out.println("Add at least 2 cities ");
            return;
        }
        System.out.print("Enter source city index :");
        int source=sc.nextInt();
        System.out.print("Enter destination city index :");
        int destination=sc.nextInt();
        
        if(source==destination){
            System.out.println("Invalid! starting city and Destination city cannot be same");
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
    public static void deliveryRequestHandling(int[][]distance,String[]vehicles,int[]capacity,String[]cities,int[]ratePerKm,int[]speed,int[]efficiency){
        Scanner sc=new Scanner(System.in);
        if(cityCount<2){
            System.out.println("please add two cities.");
            return;
        }
        System.out.print("Enter source city index :");
        int source=sc.nextInt();
        System.out.print("Enter destination city index :");
        int destination=sc.nextInt();
        if (distance[source][destination] == 0) {
            System.out.println("Distance not defined between these cities!");
            return;
        }

        System.out.println("Vehicle Types:");
        for (int i = 0; i < vehicles.length; i++)
            System.out.println((i + 1) + ". " + vehicles[i]);
        System.out.print("Select vehicle type: ");
        int vehicle = sc.nextInt() - 1;

        System.out.print("Enter weight (kg): ");
        double weight = sc.nextDouble();

        if (source == destination) {
            System.out.println("Source and destination cannot be same.");
            return;
        }
        if (weight > capacity[vehicle]) {
            System.out.println("Weight exceeds vehicle capacity!");
            return;
        }
       
        double [] result =calculateCost(source,destination,vehicle,weight,distance,ratePerKm,speed,efficiency);
        
        saveDelivaryRecord(source,destination,vehicle,weight,result[5],result[1]);
        
        displayDeliverySummary(source,destination,vehicle,weight,result,cities,vehicles);
           
    }
    public static double [] calculateCost(int source, int destination, int vehicle, double weight, int[][]distance,int[]ratePerKm,int[]speed,int[]efficiency) {
        double D = distance[source][destination];
        double R = ratePerKm[vehicle];
        double S = speed[vehicle];
        double E = efficiency[vehicle];
        double F = FUEL_PRICE;

        double baseCost = D * R * (1 + (weight / 10000));
        double time = D/S;
        double fuelUsed = D / E;
        double fuelCost = fuelUsed * F;
        double totalOperational = baseCost + fuelCost;
        double profit=baseCost*0.25;
        double customerCharge = totalOperational + profit;
        
        return new double[]{baseCost, time, fuelUsed, fuelCost, totalOperational, customerCharge};  
        
    }
    public static void saveDelivaryRecord(int source,int destination, int vehicle,double weight,double totalCharge, double time){
       
        if (deliveryCount>=MAX_DELIVERIES){
            System.out.println("Delivery record list is already filled.");
            return;
        }
        sourceCity[deliveryCount] = source;
        destinationCity[deliveryCount] = destination;
        vehicleType[deliveryCount] = vehicle;
        vehicleWeight[deliveryCount] = weight;
        totalCost[deliveryCount] = totalCharge;
        totalTime[deliveryCount] = time;
        deliveryCount++;

        System.out.println("Delivery record saved successfully");
    }   
    public static void displayDeliverySummary(int source, int destination, int vehicle, double weight, double[] r,String[]cities,String[]vehicles) {
        System.out.println("==================================================== ");
        System.out.println("DELIVERY COST ESTIMATION ");
        System.out.println("====================================================");
        System.out.println("From: " + cities[source]);
        System.out.println("To: " + cities[destination]);
        System.out.println("Vehicle: " + vehicles[vehicle]);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("---------------------------------------------------- ");
        System.out.printf("Base Cost: %.2f LKR%n", r[0]);
        System.out.printf("Fuel Used: %.2f L%n", r[2]);
        System.out.printf("Fuel Cost: %.2f LKR%n", r[3]);
        System.out.printf("Operational Cost: %.2f LKR%n", r[4]);
        System.out.printf("Customer Charge: %.2f LKR%n", r[5]);
        System.out.printf("Estimated Time: %.2f hours%n", r[1]);
        System.out.println("---------------------------------------------------- ");
    }
    public static void findLeastCostPath(String[]cities,int[][]distance) {
        Scanner sc=new Scanner(System.in);
        cityList(cities);
        System.out.print("Enter source city index: ");
        int source = sc.nextInt();
        System.out.print("Enter destination city index: ");
        int destination = sc.nextInt();

        if (source == destination) {
            System.out.println("Source and destination cannot be same!");
            return;
        }
         calculateShortestDistance(source,destination,distance,cities);
    }
    public static int calculateShortestDistance(int source, int destination,int[][]distance,String[]cities) {
        int mDist = distance[source][destination];
        int bestVia = -1; 

        for (int i = 0; i < cityCount; i++) {         
            if (i == source || i == destination){                
                continue;
            }
            if (distance[source][i] > 0 && distance[i][destination] > 0) {               
                int total = distance[source][i] + distance[i][destination];
                if (mDist == 0 || total < mDist) {              
                    mDist = total;
                    bestVia = i; 
                }
            }
        }
        System.out.println("===== LEAST-COST PATH RESULT =====");
        if (mDist == 0) {
            System.out.println("No route found between " + cities[source] + " and " + cities[destination] + "!");
        } else if (bestVia == -1) {
            System.out.println("Best route: " + cities[source] + " → " + cities[destination]);
            System.out.println("Total Distance: " + mDist + " km");
        } else {
            System.out.println("Best route: " + cities[source] + " → " + cities[bestVia] + " → " + cities[destination]);
            System.out.println("Total Distance: " + mDist + " km");
        }
        return mDist;
    }
    public static void performanceReport(int[][]distance){
        System.out.println("--- Performance Report ---");
        System.out.println("Completed Total Deliveries: " + deliveryCount);

        double totalRevenue = 0, totalDistance = 0, totalTimeSum = 0;
        for (int i = 0; i < deliveryCount; i++) {
            totalRevenue += totalCost[i];
            totalDistance += distance[sourceCity[i]][destinationCity[i]];
            totalTimeSum += totalTime[i];
        }
        if (deliveryCount > 0) {
            System.out.printf("Covered Total Distance: %.2f km%n", totalDistance);
            System.out.printf("Average Delivery Time: %.2f hours%n", (totalTimeSum / deliveryCount));
            System.out.printf("Total Revenue: %.2f LKR%n", totalRevenue);    
        }  
     }
    
    public static void loadDataFromFile(String[]cities,int[][]distance) {
        try{
            File file = new File("data.txt");
            if (!file.exists()) {
                System.out.println("No saved data found (data.txt missing). Starting fresh!");
                return;
            }
            try (Scanner reader = new Scanner(file)) {
                cityCount = reader.nextInt();
                reader.nextLine();
            
                for (int i = 0; i < cityCount; i++) {
                    cities[i] = reader.nextLine();
                }
            
                for (int i = 0; i < cityCount; i++) {
                    for (int j = 0; j < cityCount; j++) {
                        distance[i][j] = reader.nextInt();
                    }
                }
            }
            System.out.println("Data loaded successfully from data.txt");

        } catch (Exception e) {
            System.out.println(" Error loading data!");
        }
    }
}
