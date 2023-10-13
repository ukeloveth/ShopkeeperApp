package com.loveth.java.concurrent.withdelay;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class PancakeShop {
    private int pancakesMade;
    private int pancakesEaten;

    public PancakeShop() {
        this.pancakesMade = 0;
        this.pancakesEaten = 0;
    }

    public void makePancakes() {
        Random random = new Random();
        pancakesMade = random.nextInt(13);
    }

    public void serveCustomers(int userDesiredPancakes) {
        if (userDesiredPancakes <= pancakesMade) {
            pancakesEaten += userDesiredPancakes;
            pancakesMade -= userDesiredPancakes;
        } else {
            pancakesEaten += pancakesMade;
            pancakesMade = 0;
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
        PancakeShop shop = new PancakeShop();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 1; i <= 5; i++) {
            System.out.println("Round " + i + ":");

            executor.execute(shop::makePancakes);

            Random random = new Random();
            int user1DesiredPancakes = random.nextInt(6);
            int user2DesiredPancakes = random.nextInt(6);
            int user3DesiredPancakes = random.nextInt(6);

            executor.execute(() -> shop.serveCustomers(user1DesiredPancakes));
            executor.execute(() -> shop.serveCustomers(user2DesiredPancakes));
            executor.execute(() -> shop.serveCustomers(user3DesiredPancakes));

            try {
                executor.awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Starting time: " + (i - 1) * 30 + " seconds");
            System.out.println("Ending time: " + i * 30 + " seconds");
            System.out.println("Pancakes made by the shopkeeper: " + shop.getPancakesMade());
            System.out.println("Pancakes eaten by users: " + shop.getPancakesEaten());

            int pancakesWasted = shop.getPancakesMade();
            System.out.println("Pancakes wasted: " + pancakesWasted);

            System.out.println("------------------------------");
        }

        executor.shutdown();
    }
}
