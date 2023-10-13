package com.loveth.java.concurrent.withatomicinteger;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class PancakeShop {
   final private AtomicInteger pancakesMade;
   final private AtomicInteger pancakesEaten;

    public PancakeShop() {
        this.pancakesMade = new AtomicInteger(0);
        this.pancakesEaten = new AtomicInteger(0);
    }

    public void makePancakes() {
        Random random = new Random();
        pancakesMade.set(random.nextInt(13));
    }

    public void serveCustomers() {
        Random random = new Random();
        int user1DesiredPancakes = random.nextInt(6);
        int user2DesiredPancakes = random.nextInt(6);
        int user3DesiredPancakes = random.nextInt(6);

        int totalDesiredPancakes = user1DesiredPancakes + user2DesiredPancakes + user3DesiredPancakes;

        if (totalDesiredPancakes <= pancakesMade.get()) {
            pancakesEaten.set(totalDesiredPancakes);
            System.out.println("Shopkeeper met the needs of all customers.");
        } else {
            pancakesEaten.set(pancakesMade.get());
            System.out.println("Shopkeeper could not meet the needs of all customers.");
            int ordersNotMet = totalDesiredPancakes - pancakesMade.get();
            System.out.println("Pancake orders not met: " + ordersNotMet);
        }
    }

    public int getPancakesMade() {
        return pancakesMade.get();
    }

    public int getPancakesEaten() {
        return pancakesEaten.get();
    }
}

public class PancakeSimulation {
    public static void main(String[] args) {
        PancakeShop shop = new PancakeShop();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Round " + i + ":");

            shop.makePancakes();
            shop.serveCustomers();

            System.out.println("Starting time: " + (i - 1) * 30 + " seconds");
            System.out.println("Ending time: " + i * 30 + " seconds");
            System.out.println("Pancakes made by the shopkeeper: " + shop.getPancakesMade());
            System.out.println("Pancakes eaten by users: " + shop.getPancakesEaten());

            int pancakesWasted = shop.getPancakesMade() - shop.getPancakesEaten();
            System.out.println("Pancakes wasted: " + pancakesWasted);

            System.out.println("------------------------------");
        }
    }
}
