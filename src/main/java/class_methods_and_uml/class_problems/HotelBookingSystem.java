package main.java.class_methods_and_uml.class_problems;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

interface RoomPricingStrategy {
    double calculatePrice(long nights);
}

class Room {
    private final String roomName;
    private final RoomPricingStrategy pricing;

    public Room(String roomName, RoomPricingStrategy pricing) {
        this.roomName = roomName;
        this.pricing = pricing;
    }

    public String getRoomName() { return roomName; }
    public double getPrice(long nights) { return pricing.calculatePrice(nights); }
}

class Reservation {
    final Room room;
    final LocalDate checkIn;
    final LocalDate checkOut;
    boolean active = true;

    public Reservation(Room room, LocalDate checkIn, LocalDate checkOut) {
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public boolean overlaps(LocalDate start, LocalDate end) {
        return active && checkIn.isBefore(end) && start.isBefore(checkOut);
    }
}

class BookingManager {
    private final List<Reservation> reservations = new ArrayList<>();

    public Reservation bookRoom(Room room, LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.room == room && r.overlaps(start, end)) {
                System.out.printf("Booking failed: %s is not available for %s to %s.%n",
                        room.getRoomName(), start, end);
                return null;
            }
        }
        long nights = ChronoUnit.DAYS.between(start, end);
        Reservation res = new Reservation(room, start, end);
        reservations.add(res);
        System.out.printf("%s booked from %s to %s. Total price: $%.2f.%n",
                room.getRoomName(), start, end, room.getPrice(nights));
        return res;
    }

    public void cancelReservation(Reservation res, LocalDate currentDate) {
        if (res == null || !res.active) return;
        if (!currentDate.isBefore(res.checkIn)) {
            System.out.println("Cancellation failed: Past cancellation deadline.");
            return;
        }
        res.active = false;
        System.out.printf("Reservation for %s cancelled successfully.%n", res.room.getRoomName());
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        BookingManager manager = new BookingManager();
        Room deluxe101 = new Room("Deluxe Room 101", nights -> nights * 200.0);
        Room standard205 = new Room("Standard Room 205", nights -> nights * 150.0);

        Reservation r1 = manager.bookRoom(deluxe101, LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5));
        manager.bookRoom(standard205, LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));
        manager.bookRoom(deluxe101, LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));
        manager.cancelReservation(r1, LocalDate.of(2024, 11, 25));
    }
}