package edu.guilford;

import java.util.Random; // stuff

public class Deck {
    Card[] deck = new Card[52];

    Random rand = new Random();

    public Deck() {
        // deck = this.deck;

        char[] suits = {'\u2660', '\u2665', '\u2666', '\u2663'};

        String[] faces = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        int tempval;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++){
                if (j < 10) {
                    tempval = j + 1;   // numbered cards
                } else {
                    tempval = 10;      // face cards
                }

                deck[13 * i + j] = new Card(faces[j], suits[i], tempval);
            }
        }
    }

    public Card draw(int position) {
        return this.deck[position];
    }

    private void swap(int i, int j) {
        Card tempCard = deck[i];
        deck[i] = deck[j];
        deck[j] = tempCard;
    }

    public void shuffle() {
        for (int i = 0; i < 52; i++) {
            swap(i, rand.nextInt(52));
        }
    }
}
