package main.java.oop_and_collections.class_problems;
import java.util.*;

class Customer {
    private final int id;
    private final String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer c = (Customer) o;
        return id == c.id && Objects.equals(name, c.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class CustomerRegistry {
    private final Set<Customer> customers = new HashSet<>();

    public void addCustomer(Customer c) {
        if (customers.add(c)) {
            System.out.println("true");
        } else {
            System.out.println("false (duplicate rejected)");
        }
    }

    public static void main(String[] args) {
        CustomerRegistry registry = new CustomerRegistry();
        registry.addCustomer(new Customer(101, "Asha"));
        registry.addCustomer(new Customer(101, "Asha"));
        registry.addCustomer(new Customer(102, "Ravi"));
        System.out.println("unique count " + registry.customers.size());
        System.out.println("contains: " + registry.customers.contains(new Customer(101, "Asha")));
    }
}