package oop_fundamentals.assignment_problems;

import java.util.Scanner;

public class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String hashedSecurityAnswer;
    private boolean isIdSet = false;

    public LibraryMember() {
        this(null, null);
    }

    public LibraryMember(String name) {
        this(null, name);
    }

    public LibraryMember(String membershipId, String name) {
        if (membershipId != null) {
            this.membershipId = membershipId;
            this.isIdSet = true;
        }
        this.name = name;
        this.premiumMember = false;
    }

    public void setMembershipId(String id) {
        if (!this.isIdSet && id != null) {
            this.membershipId = id;
            this.isIdSet = true;
        }
    }

    public String getMembershipId() {
        return this.membershipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.hashedSecurityAnswer = "HASHED_" + answer.hashCode();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryMember m = new LibraryMember();
        
        if (scanner.hasNext()) {
            String name = scanner.nextLine();
            m.setName(name);
            
            String id1 = scanner.next();
            m.setMembershipId(id1);
            
            String id2 = scanner.next();
            m.setMembershipId(id2);
            
            String answer = scanner.next();
            m.setSecurityAnswer(answer);
            
            System.out.println(m.getName());
            System.out.println(m.getMembershipId());
        }
        scanner.close();
    }
}