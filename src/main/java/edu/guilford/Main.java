package edu.guilford;

public class Main {
    public static void main(String[] args) {
        Deck myDeck = new Deck();

        System.out.println("Here's the unshuffled deck:");

        for (int i = 0; i < 52; i++) {
            System.out.println("Card " + i + " " + myDeck.draw(i));
        }

        System.out.println("Shuffle deck!");
        myDeck.shuffle();

        System.out.println("Here's the shuffled deck:");

        for (int i = 0; i < 52; i++) {
            System.out.println("Card " + i + " " + myDeck.draw(i));
        }
    }
}