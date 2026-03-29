import java.util.HashMap;
import java.util.Map;

// Room class
abstract class Room {
    String type;
    int price;

    Room(String type, int price) {
        this.type = type;
        this.price = price;
    }

    void displayRoom() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: ₹" + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 2000);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 3500);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 5000);
    }
}

// Inventory class (from UC3)
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // unavailable
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllRooms() {
        return inventory;
    }
}

// Search Service (NEW)
class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory) {

        System.out.println("===== Available Rooms =====");

        // Create room objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.type);

            // Filter unavailable rooms
            if (available > 0) {
                room.displayRoom();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

// Main class
public class UC4 {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v4.0 =====");

        RoomInventory inventory = new RoomInventory();

        RoomSearchService searchService = new RoomSearchService();

        // Only READ operation
        searchService.searchAvailableRooms(inventory);
    }
}