package class_methods_and_uml.assignment_problems;
import java.util.*;

enum ParcelStatus {
    BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
}

// 1. Shipping Strategy Abstraction
interface ShippingType {
    String getName();
    double calculateCharge(double weightKg);
}

class StandardShipping implements ShippingType {
    @Override
    public String getName() { return "Standard"; }

    @Override
    public double calculateCharge(double weightKg) {
        return 40.0 + (10.0 * weightKg);
    }
}

class ExpressShipping implements ShippingType {
    @Override
    public String getName() { return "Express"; }

    @Override
    public double calculateCharge(double weightKg) {
        return 80.0 + (15.0 * weightKg);
    }
}

// Reuses StandardShipping via composition
class FragileShipping implements ShippingType {
    private final ShippingType baseShipping = new StandardShipping();

    @Override
    public String getName() { return "Fragile"; }

    @Override
    public double calculateCharge(double weightKg) {
        return baseShipping.calculateCharge(weightKg) + 50.0;
    }
}

// 2. Notification Observer Abstraction
interface NotificationChannel {
    void sendUpdate(String parcelId, ParcelStatus status);
}

class SmsChannel implements NotificationChannel {
    @Override
    public void sendUpdate(String parcelId, ParcelStatus status) {
        System.out.printf("[SMS] %s is now %s.%n", parcelId, status);
    }
}

class EmailChannel implements NotificationChannel {
    @Override
    public void sendUpdate(String parcelId, ParcelStatus status) {
        System.out.printf("[Email] %s is now %s.%n", parcelId, status);
    }
}

// 3. Domain Model with State Transition Guards
class Parcel {
    private final String id;
    private final double weightKg;
    private final ShippingType shippingType;
    private ParcelStatus status;
    private final List<NotificationChannel> subscribers = new ArrayList<>();

    private static final List<ParcelStatus> FLOW = Arrays.asList(
        ParcelStatus.BOOKED, ParcelStatus.PICKED_UP, ParcelStatus.IN_TRANSIT,
        ParcelStatus.OUT_FOR_DELIVERY, ParcelStatus.DELIVERED
    );

    public Parcel(String id, double weightKg, ShippingType shippingType, List<NotificationChannel> channels) {
        this.id = id;
        this.weightKg = weightKg;
        this.shippingType = shippingType;
        this.subscribers.addAll(channels);
        this.status = ParcelStatus.BOOKED;

        System.out.printf("Parcel %s booked (%s, %.0f kg). Charge: ₹%.2f.%n",
                id, shippingType.getName(), weightKg, shippingType.calculateCharge(weightKg));
        notifySubscribers();
    }

    public void updateStatus(ParcelStatus nextStatus) {
        int currentIndex = FLOW.indexOf(this.status);
        int nextIndex = FLOW.indexOf(nextStatus);

        if (currentIndex == -1 || nextIndex != currentIndex + 1) {
            System.out.printf("Invalid transition: %s -> %s is not allowed.%n", this.status, nextStatus);
            return;
        }
        this.status = nextStatus;
        notifySubscribers();
    }

    public void cancel() {
        if (this.status != ParcelStatus.BOOKED) {
            System.out.printf("Cancellation failed: %s can be cancelled only while BOOKED.%n", id);
            return;
        }
        this.status = ParcelStatus.CANCELLED;
        notifySubscribers();
    }

    private void notifySubscribers() {
        for (NotificationChannel channel : subscribers) {
            channel.sendUpdate(id, status);
        }
    }
}

public class SwiftShipParcelTracker {
    public static void main(String[] args) {
        Parcel p101 = new Parcel("P101", 2.0, new ExpressShipping(),
                Arrays.asList(new SmsChannel(), new EmailChannel()));
        p101.updateStatus(ParcelStatus.PICKED_UP);
        p101.cancel();
        p101.updateStatus(ParcelStatus.IN_TRANSIT);
        p101.updateStatus(ParcelStatus.DELIVERED);
    }
}