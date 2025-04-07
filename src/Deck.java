import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();

    public Deck()
    {
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] values = {"02", "03", "04", "05", "06", "07", "08", "09", "10", "A", "J", "K", "Q"};
        for (String s : suits) {
            for (String v : values) {
                Card c = new Card(s, v);
                deck.add(c);
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
