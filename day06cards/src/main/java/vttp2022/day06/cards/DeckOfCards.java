package vttp2022.day06.cards;

public class DeckOfCards {
  private static final int NCARDS = 52;
  private Card[] deckOfCards;
  private int currentCard;

  public DeckOfCards(){
    deckOfCards = new Card[NCARDS];
    int index = 0 ;

    //Populate deck of cards
    for (int suit = 0; suit < 4; suit++){
      for (int rank = 1; rank <= 13; rank++){
        this.deckOfCards[index] = new Card(rank, suit);
        index++;
      }
    }
    currentCard = 0;
    System.out.println("Initialised Deck of cards");
    printDeck(deckOfCards);
  }

  public Card take(int n){
   	 if ( currentCard < NCARDS )
   	 {
        System.out.println("Taking " + n +" cards from deck!");
   	    return ( deckOfCards[ currentCard += n ] );
   	 }
   	 else
   	 {
   	    System.out.println("Out of cards error");
   	    return ( null );  // Error;
   	 }
  }

  public void shuffle (int n){
    int i, j, k;

    for (k = 0; k < n ; k++){
      i = (int) (NCARDS * Math.random());
      j = (int) (NCARDS * Math.random());

      Card temp = deckOfCards[i];
      deckOfCards[i] = deckOfCards[j];
      deckOfCards[j] = temp;
    }
  }

  public int getRemaining(){
    return NCARDS - currentCard;
  }

  public static void printDeck(Card[] cards) {
    for (int i = 0; i < cards.length; i++) {
        System.out.println(cards[i]);
    }

  }
}
