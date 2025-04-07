import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Card> hand;
    private Deck deck = new Deck();

    //represents a rectangle
    private Rectangle button;

    public DrawPanel() {
        //get new cards button
        button = new Rectangle(147, 380, 160, 26);
        this.addMouseListener(this);
        hand = Card.buildHand(deck.getDeck());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 10;

        for (int i = 0; i < 3; i++) {
            int x = 125;
            for(int j = 0; j < 3; j++)
            {
                Card c = hand.get(i * 3 + j);

                //if should be highlighted
                if (c.getHighlight()) {
                    //draws border rect around card
                    g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
                }

                //establish location of rectangle "hitbox"
                c.setRectangleLocation(x, y);
                g.drawImage(c.getImage(), x, y, null);

                x = x + c.getImage().getWidth() + 10;
            }
            y += 80;
        }
        //drawing button
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 150, 400); //how to sout in jpanel
        g.drawString("CARDS LEFT: " + deck.getDeck().size(), 0, 450);
        //draws rectangle border
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();
        System.out.println(clicked);

        //if left click on button
        if (e.getButton() == 1) {

            //if point is inside rect (if button clicked)
            if (button.contains(clicked)) {
                hand = Card.buildHand(deck.getDeck());
            }

            //go thru each card
            //check if any were clicked
            //if was clicked, flip card
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
            }
        }

        //if right click on button
        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    if(hand.get(i).getHighlight())
                    {
                        Card.replaceCard(deck, hand, i);
                    }
                    else
                    {
                    hand.get(i).flipHighlight();
                    }
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}