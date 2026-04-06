import java.util.Scanner;
import java.security.SecureRandom;

public class Lunch {

    private static Scanner input = new Scanner(System.in);
    private static SecureRandom random = new SecureRandom();
    private static Lunchbox[] orders = new Lunchbox[10]; 
    private static int currentOrderCount = 0; 

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n～～～ LUNCHBOX ORDER SYSTEM ～～～");
            System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
            System.out.printf("%-20s %-20s %-20s %-20s %-20s", "1. Add New Order", "2. Edit an Order", "3. Show All Orders", "4. Total Sales", "5. Exit");
            System.out.println();
            System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
            System.out.print("Select an option: ");
            
            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine(); 

                switch (choice) {
                    case 1 -> addOrder();
                    case 2 -> editOrder();
                    case 3 -> viewAllOrders(); 
                    case 4 -> showTotalSales();
                    case 5 -> running = false;
                    default -> System.out.println("Invalid option, please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); 
            }
        }
        System.out.println("\nThank you for using the Lunchbox Order System!\n");
    }

    // --- MAIN LOGIC METHODS ---

    public static void addOrder() {
        if (currentOrderCount >= orders.length) {
            System.out.println("Sorry, the kitchen is full!");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = input.nextLine();
        
        if (name.isBlank()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        Lunchbox currentBox = new Lunchbox(name);
        
        boolean addingItems = true;
        while (addingItems) {
            // Helper method to pick item and price
            handleItemSelection(currentBox);

            System.out.print("Add more items to this bag? (y/n): ");
            String more = input.nextLine();
            if (!more.equalsIgnoreCase("y")) {
                addingItems = false;
            }
        }

        checkForReward(currentBox);
        orders[currentOrderCount] = currentBox;
        currentOrderCount++;
        System.out.println("\nOrder added successfully!");
    }

    // Helper to handle the logic of picking an item type
    public static void handleItemSelection(Lunchbox box) {
        System.out.println("\nWhat would you like to add?");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-15s %-15s %-15s %-15s\n", "1. Food ($7.99)", "2. Drink ($2.99)", "3. Snack ($1.99)", "4. Cancel");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.print("Choice: ");
        int type = input.nextInt();
        input.nextLine(); 

        String item = "";
        double price = 0;

        switch (type) {
            case 1 -> { item = displayFoodMenu(); price = 7.99; }
            case 2 -> { item = displayDrinkMenu(); price = 2.99; }
            case 3 -> { item = displaySnackMenu(); price = 1.99; }
            default -> { return; }
        }
        box.addItem(item, price);
    }

    // --- MENU DISPLAY METHODS ---

    public static String displayFoodMenu() {
        System.out.println("\n～～～ FOOD MENU ～～～");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-25s %-25s %-25s\n", "1. Pizza", "2. Taco", "3. Hot Dog");
        System.out.printf("%-25s %-25s %-25s\n", "4. Burrito", "5. Fries", "6. Pasta");
        System.out.printf("%-25s %-25s %-25s\n", "7. Cheeseburger", "8. Chicken Burger", "9. Caesar Salad");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.print("Select (1-9): ");
        int choice = input.nextInt();
        input.nextLine();

        return switch (choice) {
            case 1 -> "Pizza"; case 2 -> "Taco"; case 3 -> "Hot Dog";
            case 4 -> "Burrito"; case 5 -> "Fries"; case 6 -> "Pasta";
            case 7 -> "Cheeseburger"; case 8 -> "Chicken Burger"; case 9 -> "Caesar Salad";
            default -> "Cheeseburger";
        };
    }

    public static String displayDrinkMenu() {
        System.out.println("\n～～～ DRINK MENU ～～～");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-25s %-25s %-25s\n", "1. Water", "2. Pop", "3. Juice");
        System.out.printf("%-25s %-25s %-25s\n", "4. Tea", "5. Coffee", "6. Milk");
        System.out.printf("%-25s %-25s %-25s\n", "7. Lemonade", "8. Iced Tea", "9. Energy Drink");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.print("Select (1-9): ");
        int choice = input.nextInt();
        input.nextLine();
        return switch (choice) {
            case 1 -> "Water"; case 2 -> "Pop"; case 3 -> "Juice";
            case 4 -> "Tea"; case 5 -> "Coffee"; case 6 -> "Milk";
            case 7 -> "Lemonade"; case 8 -> "Iced Tea"; case 9 -> "Energy Drink";
            default -> "Water";
        };
    }

    public static String displaySnackMenu() {
        System.out.println("\n～～～ SNACK MENU ～～～");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-25s %-25s %-25s\n", "1. Ice Cream", "2. Cookie", "3. Chips");
        System.out.printf("%-25s %-25s %-25s\n", "4. Chocolate Bar", "5. Fruit Salad", "6. Yogurt");
        System.out.printf("%-25s %-25s %-25s\n", "7. Granola Bar", "8. Muffin", "9. Pudding");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.print("Select (1-9): ");
        int choice = input.nextInt();
        input.nextLine();
        return switch (choice) {
            case 1 -> "Ice Cream"; case 2 -> "Cookie"; case 3 -> "Chips";
            case 4 -> "Chocolate Bar"; case 5 -> "Fruit Salad"; case 6 -> "Yogurt";
            case 7 -> "Granola Bar"; case 8 -> "Muffin"; case 9 -> "Pudding";
            default -> "Cookie";
        };
    }

    public static void viewAllOrders() {
        if (currentOrderCount == 0) {
            System.out.println("No orders found.");
            return;
        }
        for (int i = 0; i < currentOrderCount; i++) {
            orders[i].displayOrder();
        }
    }

    public static void showTotalSales() {
        double total = 0;
        int activeCount = 0;
        int cancelledCount = 0;

        for (int i = 0; i < currentOrderCount; i++) {
            if (!orders[i].getIsCancelled()) {
                total += orders[i].calculateCost();
                activeCount++;
            } else {
                cancelledCount++;
            }
        }
        System.out.printf("\nTOTAL ACTIVE ORDERS: %d\n", activeCount);
        System.out.printf("TOTAL CANCELLED ORDERS: %d\n", cancelledCount);  
        System.out.printf("TOTAL SALES TO DATE: $%.2f\n", total);
    }

    public static void checkForReward(Lunchbox box) {
        if (random.nextInt(5) == 0) {
            String[] prizes = {"Cookie", "Muffin", "Chips", "Brownie"};
            String prize = prizes[random.nextInt(prizes.length)];
            box.addItem(prize + " ★ WINNER ★", 0.00);
            System.out.println("\n🌟 You won a free " + prize + "! 🌟");
        } 
    }

    public static void editOrder() {
        if (currentOrderCount == 0) {
            System.out.println("No orders to edit.");
            return;
        }
        System.out.println("\n～～～ SELECT ORDER TO EDIT ～～～");
        for (int i = 0; i < currentOrderCount; i++) {
            System.out.printf("%d. Ticket #%03d - %s\n", (i + 1), orders[i].getOrderNumber(), orders[i].getCustomerName());
        }
        System.out.print("Enter selection: ");
        int index = input.nextInt() - 1;
        input.nextLine();

        if (index >= 0 && index < currentOrderCount) {
            manageOrderMenu(index);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    public static void manageOrderMenu(int index) {
        boolean editing = true;
        Lunchbox box = orders[index];

        while (editing) {
            System.out.println("\n～～～ EDITING TICKET #" + String.format("%03d", box.getOrderNumber()) + " ～～～");
            System.out.println("1. Add Item\n2. Mark Delivered\n3. CANCEL Order\n4. Print Receipt\n5. Return");
            System.out.print("Choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> handleItemSelection(box);
                case 2 -> {
                    box.setDelivered(true);
                    System.out.println("Status updated to Delivered.");
                }
                case 3 -> {
                    box.cancelOrder();
                    System.out.println("Order has been marked as CANCELLED.");
                }
                case 4 -> box.displayOrder();
                case 5 -> editing = false;
            }
        }
    }
} // End of Lunch Class