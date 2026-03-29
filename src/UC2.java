public class UC2 {
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


    public static void main(String[] args) {

            System.out.println("===== Book My Stay App v2.0 =====");

            // Create room objects
            Room r1 = new SingleRoom();
            Room r2 = new DoubleRoom();
            Room r3 = new SuiteRoom();

            // Availability (simple variables)
            int singleAvailable = 5;
            int doubleAvailable = 3;
            int suiteAvailable = 2;

            // Display details
            r1.displayRoom();
            System.out.println("Available: " + singleAvailable);
            System.out.println();

            r2.displayRoom();
            System.out.println("Available: " + doubleAvailable);
            System.out.println();

            r3.displayRoom();
            System.out.println("Available: " + suiteAvailable);
    }

}
