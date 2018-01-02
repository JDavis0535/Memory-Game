import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MemoryGame extends JFrame implements ActionListener
{
    // Core game play objects
    private Board gameBoard;
    private FlippableCard prevCard1, prevCard2;

    // Labels to display game info
    private JLabel errorLabel, timerLabel;

    // layout objects: Views of the board and the label area
    private JPanel boardView, labelView;

    // Record keeping counts and times
    private int clickCount = 0, gameTime = 0, errorCount = 0;
    private int pairsFound = 0;

    // Game timer: will be configured to trigger an event every second
    private Timer gameTimer;

    public MemoryGame()
    {
        // Call the base class constructor
        super("Hubble Memory Game");

        // Allocate the interface elements
        JButton restart = new JButton("Restart");
        JButton quit = new JButton("Quit");
        timerLabel = new JLabel("Timer: 0");
        errorLabel = new JLabel("Errors: 0");


        /*
         * To-Do: Setup the interface objects (timer, error counter, buttons)
         * and any event handling they need to perform
        */

        //Start the timer
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerLabel.setText("Timer: " + gameTime);
                gameTime ++;


            }
        });
        gameTimer.start();


        quit.addActionListener(this);
        restart.addActionListener(this);


        // Allocate two major panels to hold interface
        labelView = new JPanel();  // used to hold labels
        boardView = new JPanel();  // used to hold game board

        // get the content pane, onto which everything is eventually added
        Container c = getContentPane();

        // Setup the game board with cards
        gameBoard = new Board(24, this);

        // Add the game board to the board layout area
        boardView.setLayout(new GridLayout(4, 6, 2, 0));
        gameBoard.fillBoardView(boardView);

        // Add required interface elements to the "label" JPanel
        labelView.setLayout(new GridLayout(1, 4, 2, 2));
        labelView.add(quit);
        labelView.add(restart);
        labelView.add(timerLabel);
        labelView.add(errorLabel);

        // Both panels should now be individually layed out
        // Add both panels to the container
        c.add(labelView, BorderLayout.NORTH);
        c.add(boardView, BorderLayout.SOUTH);

        setSize(745, 470);
        setVisible(true);
    }

    /* Handle anything that gets clicked and that uses MemoryGame as an
     * ActionListener */
    public void actionPerformed(ActionEvent e)
    {

        // Get the currently clicked card from a click event
        FlippableCard currCard = (FlippableCard)e.getSource();

         /* To-Do: What happens
        */

        /*
        // The cards selected by the player, null if not yet picked.
        prevCard1 = null;
        prevCard2 = null;


        //Assign card to prevCard1
        prevCard1 = currCard;

        if (prevCard1 == null){

            //reveal the front of card
            currCard.showFront();

            //Assign card selected to the clicked one
            prevCard1 = currCard;

        }

        else{
            if(!prevCard1.equals(currCard)){

                //reveal the front of card
                currCard.showFront();

                //increment error
                errorCount ++;

               errorLabel.setText("Errors: " + errorCount);


                //Assign card 2 to the clicked one
                prevCard2 = currCard;

                //check if the cards are equal
                if(prevCard1 == prevCard2){
                    pairsFound ++;
                }
            }

        }

        */
        
        if(e.getActionCommand().equals("Quit")){
            System.exit(0);
        }

        if(e.getActionCommand().equals("Restart")){
            restartGame();
        }

    }


        public void restartGame()
    {
        pairsFound = 0;
        gameTime = 0;
        clickCount = 0;
        errorCount = 0;
        timerLabel.setText("Timer: 0");
        errorLabel.setText("Errors: 0");

        // Clear the boardView and have the gameBoard generate a new layout
        boardView.removeAll();
        gameBoard.resetBoard();
        gameBoard.fillBoardView(boardView);
    }

    public static void main(String args[])
    {
        MemoryGame M = new MemoryGame();

        M.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}
