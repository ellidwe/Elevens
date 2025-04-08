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
    private Rectangle playAgainButton;

    public DrawPanel() {
        //get new cards button
        button = new Rectangle(147, 380, 160, 26);
        playAgainButton = new Rectangle(147, 250, 160, 26);
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
        g.drawString("REPLACE CARDS", 150, 400); //how to sout in jpanel
        g.drawString("PLAY AGAIN", 167, 270); //how to sout in jpanel
        g.drawString("CARDS LEFT: " + deck.getDeck().size(), 0, 450);
        //draws rectangle border
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
        g.drawRect((int)playAgainButton.getX(), (int)playAgainButton.getY(), (int)playAgainButton.getWidth(), (int)playAgainButton.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        //if left click on button
        if (e.getButton() == 1) {

            //if point is inside rect (if button clicked)
            if (button.contains(clicked)) {

                int selectedTotal = 0;
                ArrayList<Integer> selectedIdxs = new ArrayList<>();

                boolean j = false;
                boolean q = false;
                boolean k = false;

                for(int i = 0; i < hand.size(); i++)
                {
                    if(hand.get(i).getHighlight())
                    {
                        selectedIdxs.add(i);
                        if(hand.get(i).getValue().equals("A"))
                        {
                            selectedTotal += 1;
                        }
                        else if(hand.get(i).getValue().equals("J"))
                        {
                            j = true;
                        }
                        else if(hand.get(i).getValue().equals("Q"))
                        {
                            q = true;
                        }
                        else if(hand.get(i).getValue().equals("K"))
                        {
                            k = true;
                        }
                        else
                        {
                            selectedTotal += Integer.parseInt(hand.get(i).getValue());
                        }
                    }
                }
                if(selectedTotal == 11)
                {
                    for(int idx : selectedIdxs)
                    {
                        Card.replaceCard(deck, hand, idx);
                    }
                }
                else if(j && q && k)
                {
                    for(int idx : selectedIdxs)
                    {
                        Card.replaceCard(deck, hand, idx);
                    }
                }
                else
                {
                    for(int idx : selectedIdxs)
                    {
                        hand.get(idx).flipHighlight();
                    }
                }
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