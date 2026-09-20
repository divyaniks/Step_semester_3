package interfaces_and_abstraction.class_problems;

public class LibraryCheckoutMain {

    static void processCheckouts(Renewable[] items) {

        for (Renewable item : items) {
            System.out.println(item.renew());
        }
    }

    static String reserveIfSupported(Object o) {

        if (o instanceof Reservable) {
            Reservable item = (Reservable) o;
            return item.reserve();
        }

        return "Reservation not supported";
    }

    public static void main(String[] args) {

        Textbook textbook =
                new Textbook("TB-101", "Java Programming");

        Magazine magazine =
                new Magazine("MG-201", "Tech Monthly");

        DigitalPass pass =
                new DigitalPass("DP-301");

        Renewable[] items = {
            textbook,
            magazine,
            pass
        };

        processCheckouts(items);

        System.out.println(
                reserveIfSupported(textbook)
        );

        System.out.println(
                reserveIfSupported(magazine)
        );

        System.out.println(
                reserveIfSupported(pass)
        );
    }
}