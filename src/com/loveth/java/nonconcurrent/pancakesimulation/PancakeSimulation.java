package com.loveth.java.nonconcurrent.pancakesimulation;

import java.util.Random;

class PancakeShopWithNonConcurrent {
    private int pancakesMade;
    private int pancakesEaten;

    public PancakeShopWithNonConcurrent() {
        this.pancakesMade = 0;
        this.pancakesEaten = 0;
    }

    public void makePancakes() {
        Random random = new Random();
        pancakesMade = random.nextInt(13);
    }

    public void serveCustomers() {
        Random random = new Random();
        int user1DesiredPancakes = random.nextInt(6);
        int user2DesiredPancakes = random.nextInt(6);
        int user3DesiredPancakes = random.nextInt(6);

        int totalDesiredPancakes = user1DesiredPancakes + user2DesiredPancakes + user3DesiredPancakes;

        if (totalDesiredPancakes <= pancakesMade) {
            pancakesEaten = totalDesiredPancakes;
            System.out.println("Shopkeeper met the needs of all customers.");
        } else {
            pancakesEaten = pancakesMade;
            System.out.println("Shopkeeper could not meet the needs of all customers.");
            int ordersNotMet = totalDesiredPancakes - pancakesMade;
            System.out.println("Pancake orders not met: " + ordersNotMet);
        }
    }

    public int getPancakesMade() {
        return pancakesMade;
    }

    public int getPancakesEaten() {
        return pancakesEaten;
    }
}

public class PancakeSimulation {
    public static void main(String[] args) {
        PancakeShopWithNonConcurrent shop = new PancakeShopWithNonConcurrent();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Round " + i + ":");
            shop.makePancakes();
            System.out.println("Starting time: " + (i - 1) * 30 + " seconds");
            System.out.println("Ending time: " + i * 30 + " seconds");
            System.out.println("Pancakes made by the shopkeeper: " + shop.getPancakesMade());

            shop.serveCustomers();
            System.out.println("Pancakes eaten by users: " + shop.getPancakesEaten());

            int pancakesWasted = shop.getPancakesMade() - shop.getPancakesEaten();
            System.out.println("Pancakes wasted: " + pancakesWasted);

            System.out.println("------------------------------");
        }
    }
}
