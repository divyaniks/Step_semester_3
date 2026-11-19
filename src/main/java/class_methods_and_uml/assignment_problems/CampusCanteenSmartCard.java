package class_methods_and_uml.assignment_problems;
import java.util.*;

// 1. Pricing Plan Strategy
interface PricingPlan {
    double applyDiscount(double basePrice);
}

class DayScholarPlan implements PricingPlan {
    public double applyDiscount(double basePrice) { return basePrice; }
}

class HostellerPlan implements PricingPlan {
    public double applyDiscount(double basePrice) { return basePrice * 0.90; }
}

class StaffPlan implements PricingPlan {
    public double applyDiscount(double basePrice) { return basePrice * 0.80; }
}

// 2. Immutable Transaction Record
class Transaction {
    final String itemName;
    final double signedAmount; // + for top-up/refund, - for purchase
    boolean refunded = false;

    public Transaction(String itemName, double signedAmount) {
        this.itemName = itemName;
        this.signedAmount = signedAmount;
    }
}

// 3. Encapsulated SmartCard
class SmartCard {
    private final String cardId;
    private final PricingPlan plan;
    private boolean blocked = false;
    private final List<Transaction> transactions = new ArrayList<>();

    public SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
    }

    // Balance derived strictly from transaction ledger to guarantee invariant
    public double getBalance() {
        double sum = 0.0;
        for (Transaction t : transactions) {
            sum += t.signedAmount;
        }
        return sum;
    }

    public void block() { this.blocked = true; }
    public void unblock() { this.blocked = false; }

    public void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up failed: Card is blocked.");
            return;
        }
        if (amount < 100.0 || (getBalance() + amount) > 5000.0) {
            System.out.println("Top-up failed: Invalid top-up amount or max balance exceeded.");
            return;
        }
        transactions.add(new Transaction("TOP_UP", amount));
        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f.%n", cardId, amount, getBalance());
    }

    public void purchase(String itemName, double basePrice) {
        if (blocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return;
        }
        double finalCharge = plan.applyDiscount(basePrice);
        double currentBalance = getBalance();
        if (finalCharge > currentBalance) {
            System.out.printf("Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                    finalCharge, currentBalance);
            return;
        }
        transactions.add(new Transaction(itemName, -finalCharge));
        System.out.printf("%s purchased for ₹%.2f. Balance: ₹%.2f.%n", itemName, finalCharge, getBalance());
    }

    public void refund(String itemName) {
        for (Transaction t : transactions) {
            if (t.itemName.equals(itemName) && t.signedAmount < 0) {
                if (t.refunded) {
                    System.out.printf("Refund rejected: %s has already been refunded.%n", itemName);
                    return;
                }
                t.refunded = true;
                double refundAmt = Math.abs(t.signedAmount);
                transactions.add(new Transaction("REFUND:" + itemName, refundAmt));
                System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                        refundAmt, itemName, getBalance());
                return;
            }
        }
        System.out.printf("Refund rejected: Purchase '%s' not found.%n", itemName);
    }

    public void printMiniStatement() {
        List<String> entries = new ArrayList<>();
        for (Transaction t : transactions) {
            entries.add(String.format("%+.2f", t.signedAmount));
        }
        System.out.printf("Mini-statement for %s: %s = ₹%.2f.%n",
                cardId, String.join(", ", entries), getBalance());
    }
}

public class CampusCanteenSmartCard {
    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());
        card.topUp(500);
        card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Combo Meal", 400);
        card.refund("Veg Thali");
        card.refund("Veg Thali");
        card.printMiniStatement();
    }
}