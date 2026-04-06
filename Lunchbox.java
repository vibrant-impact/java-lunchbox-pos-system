
public class Lunchbox {
    private String customerName;
    private String[] items = new String[5]; // Can hold up to 5 items
    private int itemCount = 0; // Tracks how many items are in the bag
    private double totalPrice = 0;
    private int orderNumber;
    private boolean isCancelled = false;
    private boolean isDelivered = false;
    private static int orderCount = 1;

    public Lunchbox(String name) {
        this.customerName = name;
        this.orderNumber = orderCount++;
    }

    //GETTERS
    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() { 
        return totalPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    } 

    public boolean getIsCancelled() { 
        return isCancelled; 
    }

    public boolean getIsDelivered() {
        return isDelivered;
    }

    public int getItemCount() {
        return itemCount;
    }

    //SETTERS
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDelivered(boolean isDelivered) { 
        this.isDelivered = isDelivered;
    }

    public void markDelivered() {
        this.isDelivered = true;
    }

    public double calculateCost() {
        return totalPrice;
    }

    public void cancelOrder() {
        this.isCancelled = true;
    }
    
    // METHODS
    // Add an item into the bag
    public void addItem(String itemName, double price) {
        if (itemCount < items.length) {
            items[itemCount] = itemName;
            this.totalPrice += price;
            itemCount++;
        } else {
            System.out.println("This lunchbox is full!");
        }
    }

    // Display the order receipt
    public void displayOrder() {
        String border = isCancelled ? "x x x x x x x x x x x x x" : "══════════════════════════";
        
        System.out.println(border);
        if (isCancelled) {
            System.out.println(" !!! ORDER CANCELLED !!!");
        }
        System.out.println(" RECEIPT FOR: " + customerName.toUpperCase());
        System.out.println(" TICKET: #" + String.format("%03d", orderNumber));
        
        String statusText;
            if (isCancelled) {
                statusText = "VOID ❌";
            } else if (isDelivered) {
                statusText = "DELIVERED ✅";
            } else {
                statusText = "PENDING ⏳";
            }

            System.out.println(" STATUS: " + statusText);

        System.out.println("..........................");
         

        // Loop through the items array to print each thing they bought
        for (int i = 0; i < itemCount; i++) {
            System.out.printf("- %-20s\n", items[i]);
        }
          
        System.out.println("..........................");
        System.out.printf(" TOTAL: $%.2f\n", totalPrice);
        System.out.println("══════════════════════════");
    }
}
