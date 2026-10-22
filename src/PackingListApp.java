import java.util.ArrayList;
import java.util.Scanner;

public class PackingListApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Trip> trips = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=================================");
        System.out.println("       PACKING LIST PLANNER");
        System.out.println("=================================");

        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> createTrip();
                case 2 -> viewTrips();
                case 3 -> addPackingItem();
                case 4 -> markItemPacked();
                case 5 -> viewPackingList();
                case 6 -> removePackingItem();
                case 0 -> {
                    running = false;
                    System.out.println("Happy travels! Goodbye.");
                }
                default -> System.out.println("Please choose a valid option.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\\n--- MAIN MENU ---");
        System.out.println("1. Create a trip");
        System.out.println("2. View trips");
        System.out.println("3. Add item to a trip");
        System.out.println("4. Mark item as packed");
        System.out.println("5. View packing list");
        System.out.println("6. Remove item from a trip");
        System.out.println("0. Exit");
    }

    private static void createTrip() {
        System.out.print("Trip name (e.g., Beach Weekend): ");
        String name = readText();
        System.out.print("Destination: ");
        String destination = readText();
        System.out.print("Departure date (e.g., 2026-11-15): ");
        String date = readText();

        trips.add(new Trip(name, destination, date));
        System.out.println("Trip created successfully!");
    }

    private static void viewTrips() {
        if (trips.isEmpty()) {
            System.out.println("No trips yet. Create a trip first.");
            return;
        }

        System.out.println("\\n--- YOUR TRIPS ---");
        for (int i = 0; i < trips.size(); i++) {
            Trip trip = trips.get(i);
            System.out.println((i + 1) + ". " + trip.getName()
                    + " | " + trip.getDestination()
                    + " | Departure: " + trip.getDepartureDate());
        }
    }

    private static void addPackingItem() {
        Trip trip = chooseTrip();
        if (trip == null) return;

        System.out.print("Item to pack: ");
        String itemName = readText();
        trip.addItem(new PackingItem(itemName));
        System.out.println("Item added to " + trip.getName() + ".");
    }

    private static void markItemPacked() {
        Trip trip = chooseTrip();
        if (trip == null) return;

        if (trip.getItems().isEmpty()) {
            System.out.println("This trip has no packing items yet.");
            return;
        }

        showItems(trip);
        int itemNumber = readInt("Enter item number to mark as packed: ");
        if (itemNumber < 1 || itemNumber > trip.getItems().size()) {
            System.out.println("Invalid item number.");
            return;
        }

        PackingItem item = trip.getItems().get(itemNumber - 1);
        item.setPacked(true);
        System.out.println(item.getName() + " marked as packed!");
    }

    private static void viewPackingList() {
        Trip trip = chooseTrip();
        if (trip == null) return;

        System.out.println("\\nPacking list for " + trip.getName()
                + " (" + trip.getDestination() + ")");
        if (trip.getItems().isEmpty()) {
            System.out.println("Your list is empty. Add some items!");
            return;
        }

        showItems(trip);
        int packed = 0;
        for (PackingItem item : trip.getItems()) {
            if (item.isPacked()) packed++;
        }
        System.out.println("Progress: " + packed + "/" + trip.getItems().size() + " items packed.");
    }

    private static void removePackingItem() {
        Trip trip = chooseTrip();
        if (trip == null) return;

        if (trip.getItems().isEmpty()) {
            System.out.println("This trip has no items to remove.");
            return;
        }

        showItems(trip);
        int itemNumber = readInt("Enter item number to remove: ");
        if (itemNumber < 1 || itemNumber > trip.getItems().size()) {
            System.out.println("Invalid item number.");
            return;
        }

        PackingItem removed = trip.getItems().remove(itemNumber - 1);
        System.out.println("Removed: " + removed.getName());
    }

    private static Trip chooseTrip() {
        if (trips.isEmpty()) {
            System.out.println("No trips found. Create a trip first.");
            return null;
        }

        viewTrips();
        int tripNumber = readInt("Choose trip number: ");
        if (tripNumber < 1 || tripNumber > trips.size()) {
            System.out.println("Invalid trip number.");
            return null;
        }
        return trips.get(tripNumber - 1);
    }

    private static void showItems(Trip trip) {
        System.out.println("\\n--- ITEMS ---");
        for (int i = 0; i < trip.getItems().size(); i++) {
            PackingItem item = trip.getItems().get(i);
            String status = item.isPacked() ? "[PACKED]" : "[NOT PACKED]";
            System.out.println((i + 1) + ". " + status + " " + item.getName());
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static String readText() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.print("This field cannot be empty. Try again: ");
        }
    }
}

class Trip {
    private final String name;
    private final String destination;
    private final String departureDate;
    private final ArrayList<PackingItem> items;

    public Trip(String name, String destination, String departureDate) {
        this.name = name;
        this.destination = destination;
        this.departureDate = departureDate;
        this.items = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getDestination() { return destination; }
    public String getDepartureDate() { return departureDate; }
    public ArrayList<PackingItem> getItems() { return items; }

    public void addItem(PackingItem item) {
        items.add(item);
    }
}

class PackingItem {
    private final String name;
    private boolean packed;

    public PackingItem(String name) {
        this.name = name;
        this.packed = false;
    }

    public String getName() { return name; }
    public boolean isPacked() { return packed; }
    public void setPacked(boolean packed) { this.packed = packed; }
}
