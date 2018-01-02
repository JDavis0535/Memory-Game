import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Board
{
    // Array to hold board cards
    private FlippableCard cards[];

    // Resource loader
    private ClassLoader loader = getClass().getClassLoader();

    public Board(int size, ActionListener AL)
    {
        // Allocate and configure the game board: an array of cards
        cards = new FlippableCard[size];

        // Fill the Cards array
        int imageIdx = 1;
        for (int i = 0; i < size; i += 2) {

            // Load the front image from the resources folder
            String imgPath = "res/hub" + imageIdx + ".jpg";
            ImageIcon img = new ImageIcon(loader.getResource(imgPath));
            imageIdx++;  // get ready for the next pair of cards

            // Setup two cards at a time
            FlippableCard c1 = new FlippableCard(img);
            FlippableCard c2 = new FlippableCard(img);

            // Add them to the array
            cards[i] = c1;
            cards[i + 1] = c2;
        }

       Random position = new Random();

       for(int i=0; i<size - 1; i +=2){
           int j = position.nextInt (size - 1);

           FlippableCard temp = cards[i];
           cards[i] = cards[j];
           cards[j] = temp;
       }

    }

    public void fillBoardView(JPanel view)
    {
        for (FlippableCard c : cards) {
            view.add(c);
        }
    }

    public void resetBoard()
    {
        /*
         * To-Do: Reset the flipped cards and randomize the card positions*/
        for (int i = 0; i < 24; i ++) {

            cards[i].hideFront();

        }

        Random position = new Random();

        for (int i = 0; i < 24; i += 2) {
            int j = position.nextInt(24);

            FlippableCard temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }


    }








}
