package edu.guilford;

public class Main {
    public static void main(String[] args) {
        Deck myDeck = new Deck();

        System.out.println("Here's the unshuffled deck:");

        for (int i = 0; i < 52; i++) {
            System.out.print("Card " + i + " " + myDeck.draw() + "\t");
        }

        System.out.println("\nShuffle deck!");
        myDeck.shuffle();

        System.out.println("Here's the shuffled deck:");

        for (int i = 0; i < 52; i++) {
            System.out.print("Card " + i + " " + myDeck.draw() + "\t");
        }
    }
}