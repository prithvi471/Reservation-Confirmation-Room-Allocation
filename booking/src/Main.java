import java.util.*;

public class Main {

    static Queue<String> bookingQueue = new LinkedList<>();
    static Map<String, Integer> inventory = new HashMap<>();
    static Map<String, Set<String>> allocatedRooms = new HashMap<>();
    static int roomCounter = 1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of room types: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter room type: ");
            String type = sc.nextLine();

            System.out.print("Enter number of rooms available: ");
            int count = sc.nextInt();
            sc.nextLine();

            inventory.put(type, count);
        }

        System.out.print("Enter number of booking requests: ");
        int requests = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < requests; i++) {
            System.out.print("Enter requested room type: ");
            String request = sc.nextLine();
            bookingQueue.add(request);
        }

        processBookings();
    }

    public static void processBookings() {

        while (!bookingQueue.isEmpty()) {

            String roomType = bookingQueue.poll();
            System.out.println("\nProcessing booking request for: " + roomType);

            int available = inventory.getOrDefault(roomType, 0);

            if (available > 0) {

                String roomId = generateRoomId(roomType);

                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                Set<String> roomSet = allocatedRooms.get(roomType);

                if (!roomSet.contains(roomId)) {

                    roomSet.add(roomId);
                    inventory.put(roomType, available - 1);

                    System.out.println("Reservation confirmed. Room Assigned: " + roomId);
                }

            } else {
                System.out.println("No rooms available for " + roomType);
            }

            System.out.println("Current Inventory: " + inventory);
        }

        System.out.println("\nFinal Allocated Rooms: " + allocatedRooms);
    }

    public static String generateRoomId(String roomType) {
        return roomType.substring(0, 1).toUpperCase() + roomCounter++;
    }
}