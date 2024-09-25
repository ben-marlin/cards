package edu.guilford;

// long term - use images from <a href="https://www.freepik.com/free-vector/set-poker-cards-with-front-back-design_1169379.htm#query=poker%20card%20set&position=0&from_view=keyword&track=ais_hybrid&uuid=966578ff-475b-4481-b06d-09d5ea2a4b9f">Image by brgfx</a> on Freepik


import java.util.Objects;

public class Card {
    int value;      // 1 to 10 points
    char suit;      // spades \u2660 ♠, hearts \u2665 ♥︎, diamonds \u2666 ◆, clubs \u2663 ♣
    String face;    // Ace, 2, 3, 4, 5 6, 7, 8, 9, 10, Jack, Queen, King

    public Card(String face, char suit, int value) {
        this.face = face;
        this.suit = suit;
        this.value = value; 
    }

    public int getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }

    public String getFace() {
        return face;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.value;
        hash = 67 * hash + this.suit;
        hash = 67 * hash + Objects.hashCode(this.face);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.value != other.value) {
            return false;
        }
        if (this.suit != other.suit) {
            return false;
        }
        return Objects.equals(this.face, other.face);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Card{");
        sb.append("face=").append(face);
        sb.append(", suit=").append(suit);
        sb.append('}');
        return sb.toString();
    }

}