import java.io.*;
import java.util.*;

// Reservation class
class Reservation implements Serializable {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

// Inventory class
class RoomInventory implements Serializable {
    Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " → " + inventory.get(key));
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "data.ser";

    // Save data
    public static void save(RoomInventory inventory, List<Reservation> bookings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            oos.writeObject(bookings);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data
    public static Object[] load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            RoomInventory inventory = (RoomInventory) ois.readObject();
            List<Reservation> bookings = (List<Reservation>) ois.readObject();
            System.out.println("Data loaded successfully!");
            return new Object[]{inventory, bookings};
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

// Main class
public class UC12 {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v12.0 =====");

        RoomInventory inventory;
        List<Reservation> bookings;

        // Try loading data
        Object[] data = PersistenceService.load();

        if (data != null) {
            inventory = (RoomInventory) data[0];
            bookings = (List<Reservation>) data[1];
        } else {
            inventory = new RoomInventory();
            bookings = new ArrayList<>();
        }

        // Simulate booking
        bookings.add(new Reservation("Abhijit", "Single Room"));
        bookings.add(new Reservation("Rahul", "Double Room"));

        // Display current state
        inventory.display();

        System.out.println("\nBookings:");
        for (Reservation r : bookings) {
            r.display();
        }

        // Save before exit
        PersistenceService.save(inventory, bookings);
    }
}