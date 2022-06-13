package vttp2022.day06.cards;

import java.io.Console;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        DeckOfCards doc = new DeckOfCards();
        Console cons = System.console();

        int numOfCards = Integer.parseInt(cons.readLine("Please enter number of cards to take from deck"));
        System.out.println("Taking card from Deck: " + doc.take(numOfCards));
        System.out.println("Get remaining cards from Deck: " + doc.getRemaining());
    }
}
