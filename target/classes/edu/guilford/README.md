# Building the Card Deck for a Game

Previously, we built a dice roller, which could be adapted to any number of dice games. Another common type of game is based on a card deck. In this project we'll do the following.

- Create a Card class 
- Create a Deck class for poker containing Cards
- Make the Deck shuffle to randomize the cards
- Deal a card from a particular position
- Deal a card from the "top" of the deck

To do this, you'll need to understand what's going on with creating a class (and all the necessary methods), instantiating a class, using objects. To that end, it would be a huge advantage to have read Chapter 11 before starting on this project.

The project will give us practice with the following.

- creating a class
- analyzing fields
- making / using constructors
- making / using getters & setters
- dealing with arrays
- writing methods

## Sample Code

You have been given sample code that should print the Unicode characters for hearts, spades, diamonds, and clubs. Run it! If you don't get the appropriate symbols, we have to do something with your machine!

If all else fails, we'll just use H for hearts, S for spades, D for diamonds, and C for clubs. But the actual symbols would look cool!

## Building a Card

As we did with the Dice class, create a Java file named `Card.java` which has `package edu.guilford;` at the start, and opens with `public class Card`. Everything that's a part of the class has to be in the braces, but you'll still have to do import statements between the package and the header.

## Fields

Stop and think this one through before taking my answer. What are the important characteristics of a card?

...

Hopefully you came up with the suit - hearts, spades, diamonds, clubs - and the face value - either a number or one of ace, jack, queen, king. We're going to ignore the jokers.

For suit, we can represent these with a single character - assuming your Unicode display was working. So declare a `private char suit`.

For face, it's harder. Some are numbers, so it's tempting to use an `int`. But others are words, but could be abbreviated with a single letter. Unfortunately, the 10 would require two letters. The easiest thing is probably to make it a `private String face`.

Depending on the game you use this for, you may also need to have a separate value. These could range from 1 to 11 depending on the uses. But that suggests declaring `private int value`.

In later work, you might actually want a picture to represent each card. Let's keep that in mind, but not today!

## Constructor

Like in the dice assignment, let VSCode generate your constructor. However... if you think about `value`, you'll see that you don't really want to let a programmer set it. So let VSCode generate the constructor with all three, but then make the following changes.

Change `public Card(char suit, String face, int value)` to `public Card(char suit, String face)` - i.e. remove value from the method variables.

Delete `this.value = value;` and replace it with the following.

``` 
switch (face) {
    case "A":
        this.value = 11; // Ace commonly 11 initially
        break;
    case "K":
    case "Q":
    case "J":
    case "10":
        this.value = 10; // King, Queen, Jack, and 10 worth 10
        break;
    default:
        this.value = Integer.parseInt(face); // Numeric cards 2-9
        break;
}
```

The `switch` command is like an extended `if / else` statement that lists cases. It's handy for things like this, where you can enumerate all the possibilities. Notice, however, that the `default` could lead to trouble if we allow `face` to take on other values.

## Getters & setters

Let VSCode create these for you, as we did with the dice example. Because we made a big deal about `value`, it may seem as though `setValue` will need similar treatment. But because blackjack allows an ace to take on either the value 1 or 11, we really need to let the driver have access to `value` directly.

## toString

As with the dice example, let VSCode help you out. Afterwards, remove the portion that puts `value` into the string. If you do more with this example later, you may want to change it back then. But for now, it's just extra clutter.

## Testing

Save your class! In `Main.java`, write some code that will instantiate a `Card` object and then print it out. Run it! This will test your `toString()` method.

Next write code that changes the face & suit and prints it again. Finally write code that prints the `face`, `suit`, and `value` using their getters. Make sure you run this!

## The Deck class

A deck of cards used in poker or blackjack has 52 cards, divided into 4 suits of 13. What makes up the deck?

Well... the cards. So your field will be an array of `Card` objects. But you'll also want to have a way to shuffle them, so we'll have to write some methods.

## Create Deck.java

Make a class file for the `Deck` class. Let VSCode do the heavy lifting. But add `package edu.guilford;` as the first line.

## Fields

Add a line that declares the array. Something like `private Card[] deck = new Card[52];`.

For reasons that will become clear later, also add `private int topCard`.

## Constructor

Let VSCode create this for you, but don't check off any of the fields. This should generate a blank constructor with no arguments in the header. If not, delete the contents. What we're looking for is this.

```
public Deck() {
    // establish suit possibilities
    char[] suits = {'\u2660', '\u2665', '\u2666', '\u2663'};

    // establish face possibilities
    String[] faces = {"Ace", "2", "3", "4", "5", "6", "7", 
        "8", "9", "10", "Jack", "Queen", "King"};

    for (int i = 0; i < 4; i++) {           // suit loop
        for (int j = 0; j < 13; j++){       // face loop
            deck[13 * i + j] = new Card(faces[j], suits[i]);
        }
    }

    // sets a "pointer" for "current" card
    this.topCard = 0;
}
```

## Method to draw

Since our class is mostly just an array, it proves difficult to create setters and getters for each. Instead, we'll let a program just draw one of the cards. To do this, we create a method which accepts a single integer as input and returns the card for that position.

```
public Card draw(int position) {
    return this.deck[position];
}
```
Now we have enough mechanics built up to actually test our `Deck` class! Go to the `Main` class and write code that instantiates a `Deck` called `myDeck`. Then use this code for a loop.
```
for (int i = 0; i < 52; i++) {
    System.out.print("Card " + i + " " + myDeck.draw(i));
}
```
Each time through the loop, it should print out the card number, then draw a card based on that position. Since the `Card` class has a `toString()` redefined, it should print things like "King of ♥︎". Run this and look to see if it seems to be printing what you'd expect.

## Overloading draw

In movies or TV shows that deal with poker, a cheater is sometimes told they need to deal off the top of the deck. It doesn't make a great deal of sense to be able to draw cards from *any* spot. So we make a second method called `draw` that simply deals the "top" card.

```
public Card draw() {
    // record position that is the current top
    int position = this.topCard;

    // advance the top
    this.topCard++;

    // just in case we reach he end, this loops us back
    this.topCard = this.topCard % 52;

    return this.deck[position];
}
```
Notice how this is *very* similar to the other version of `draw`, but doesn't accept `position`?

Return to `Main` and modify your last print statement so it uses this version of `draw` (i.e. delete the `i`). Run it to make sure you're getting what you expect.

## Shuffling

Figuring out how to shuffle cards is actually pretty tricky. If you've ever seen a good dealer do it, you know that they "feather" them together in a way that alternates alternates every other card. Or almost? There's just a little randomness to it, and repetition makes it more random. 

We're not going to be that sophisticated! Our shuffle will simply pick two card positions and swap them. If we run through the whole deck and randomly swap every card with another, that should be a good randomization.

Insert this method that swaps two cards.
```
private void swap(int i, int j) {
    Card tempCard = deck[i];
    deck[i] = deck[j];
    deck[j] = tempCard;
}
```
This is such a common trick that it bears pointing out how it works. You have two positions, say position 13 and position 20. Maybe `deck[13]` is the 3 of ♥︎ and `deck[20]` is the jack of ♣. We want to reverse these.

We create a new card called `tempCard`, which is initially "blank", and move one of our two - maybe `deck[13]`, the 3 of ♥︎, into tempCard. Then we move the remaining card, the jack of ♣, from `deck[20]` into `deck[13]`. Finally, we move the contents of `tempCard`, the 3 of ♥︎, into `deck[20]`. 

When the method is done, the object `tempCard` disappears. It may seem weird to you that we need this third position to swap two things, but there really isn't a way around it because each step is only copying things, not actually changing their position!

Once we have a way to `swap` two cards in a `Deck`, the shuffle is relatively straightforward. We simply go through each deck position, 0 to 51, and randomly pick another position, then swap these. Because of the randomization, we might occasionally have a card swapped with itself, so it might not move. But we might also have a card that is accidentally swapped over & over. So... it's pretty random.
```
public void shuffle() {
    for (int i = 0; i < 52; i++) {
        swap(i, rand.nextInt(52));
    }
}
```

## Testing your `shuffle()`

Return to `Main` and insert code that prints a message about an unshuffled deck before your previous test of `draw()`. Then insert code to print a message about a shuffled deck after your test of `draw()` insert a line that says `myDeck.shuffle();` afterward.

Now copy the loop that tested `draw()` after you have issued the command to shuffle the deck. Because you have a printing loop before and after the shuffle, you should be able to compare the order and see that it was shuffled.

## Blackjack

Let's not get fancy, but let's start a game here. In `Main`, write code that declares two `Card` objects, `card1` and `card1`. Use `myDeck.draw()` to pull two cards from the deck and print them.

Now declare an integer variable called `points` and use the `getValue()` methods of `card1` and `card2` to find their values and store the sum in `points`. Then print a message that tells the player how many points they have.

## Wrapping up

That should be plenty for today! Remember to save everything, stage your changes, give a commit message, hit the commit button, and then hit the sync button when that comes up. Finally, visit Canvas to tell me you're done!