import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Card {
    private String suit;
    private String value;
    private String imageFileName;
    private String backImageFileName;
    private boolean show;
    private BufferedImage image;
    private Rectangle cardBox;
    private boolean highlight;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.imageFileName = "images/card_"+suit+"_"+value+".png";
        this.show = true;
        this.backImageFileName = "images/card_back.png";

        //which image should i be showing for the card
        //front or back
        this.image = readImage();
        this.cardBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
        this.highlight = false;
    }

    public Rectangle getCardBox() {
        return cardBox;
    }

    public String getSuit() {
        return suit;
    }

    public void setRectangleLocation(int x, int y) {
        cardBox.setLocation(x, y);
    }

    public String getValue() {
        return value;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String toString() {
        return suit + " " + value;
    }

    public void flipCard() {
        show = !show;
        this.image = readImage();
    }

    public void flipHighlight() {
        highlight = !highlight;
    }

    public boolean getHighlight() {
        return highlight;
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage readImage() {
        try {
            BufferedImage image;
            //if show is true, show front
            //else show back
            if (show) {
                image = ImageIO.read(new File(imageFileName));
            }
            else {
                image = ImageIO.read(new File(backImageFileName));
            }
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

//    public static ArrayList<Card> buildDeck() {
//        ArrayList<Card> deck = new ArrayList<Card>();
//        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
//        String[] values = {"02", "03", "04", "05", "06", "07", "08", "09", "10", "A", "J", "K", "Q"};
//        for (String s : suits) {
//            for (String v : values) {
//                Card c = new Card(s, v);
//                deck.add(c);
//            }
//        }
//        return deck;
//    }

    public static ArrayList<Card> buildHand(ArrayList<Card> deck) {
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < 9; i++) {
            int r = (int)(Math.random()*deck.size());
            Card c = deck.remove(r);
            hand.add(c);
        }
        return hand;
    }

    public static ArrayList<Card> replaceCard(Deck deck, ArrayList<Card> currentHand, int i)
    {
        currentHand.remove(i);
        int r = (int)(Math.random() * deck.getDeck().size());
        Card c = deck.getDeck().remove(r);
        currentHand.add(i, c);
        return currentHand;
    }
}