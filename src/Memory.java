import java.util.Scanner;

/** This class represents a Memory object
 *
 * @author Katrina Lin
 */
public class Memory
{
    /** The players of the game */
    private Player[] players;

    /** The cardArea object of CardArea */
    private CardArea cardArea;

    /** The scanner object of Scanner */
    private Scanner scanner;

    /** The first card being flipped */
    private Card card1;

    /** The second card being flipped */
    private Card card2;

    /** Instantiates a Memory object
     *  <p>
     *  Initializes the Scanner object
     *  Initializes the players instance variable to two Player objects
     *  Initializes both cards being flipped to null
     *  Creates two players based on the user's input of each player's names
     *  Creates a WinCondtion object, initializes cardArea, and lays out or creates
     *  the matched pairs based on the user's choice of board size
     */
    public Memory()
    {
        scanner = new Scanner(System.in);
        players = new Player[2];
        card1 = null;
        card2 = null;

        System.out.print("Enter player 1's name: ");
        String player1 = scanner.nextLine();
        players[0] = new Player(player1);

        System.out.print("Enter player 2's name: ");
        String player2 = scanner.nextLine();
        players[1] = new Player(player2);

        System.out.print("Select a board size: (1) 4x4, (2) 6x6, (3) 8x8 ");
        int cards = scanner.nextInt();
        scanner.nextLine();

        if (cards == 1) {
            cards = 4;
        } else if (cards == 2) {
            cards = 6;
        } else {
            cards = 8;
        }
        WinCondition wins = new WinCondition(cards);
        cardArea = new CardArea(cards, wins.getMatchList());
        cardArea.layoutCardArea();
    }

    /** Runs the Memory game
     *  <p>
     *  While the board is not empty, each player takes turns.
     *  After each player's turn, the board space will be checked.
     *  If the board is empty, the game is over, the console is cleared, and the score and winner are displayed.
     */
    public void run()
    {
        boolean gameOver = false;
        while (!gameOver)
        {
            for (Player player : players)
            {
                takeTurn(player);
                if (cardArea.isEmpty())
                {
                    gameOver = true;
                    break;
                }
            }
        }

        clearConsole();
        printScore();
        System.out.println();
        System.out.println(winner());
        System.out.println("Thanks for playing!");
    }

    /** The score is displayed */
    public void printScore() {
        System.out.println("----------- SCORE BOARD -----------");
        System.out.println(players[0].getName() + ": " + players[0].getScore());
        System.out.println(players[1].getName() + ": " + players[1].getScore());
        System.out.println();
    }

    /** A player's turn is taken
     *  <p>
     *  Until the player's two flips are not a match or the board is empty,
     *  the players turn will continue to run.
     *  The players are presented with the current score board along with the board before each of their turns.
     *  They will be asked for their choice of flip for two cards.
     *  If both flips are valid, those cards will be flipped and tested to see if they are match.
     *  If either or none of their flips are valid, they will be asked to flip a card until it is valid.
     *  If their cards are a match, the current player's score will be incremented by 1
     *  and they will be permitted to continue their turn until they fail to make a match.
     *
     *  @param player The Player object taking the turn
     */
    public void takeTurn(Player player)
    {
        int[] validSet;
        boolean isMatch = true;
        String choice = "";
        String choice2 = "";

        while (isMatch && !cardArea.isEmpty())
        {
            clearConsole();
            printScore();
            cardArea.drawArea();
            System.out.println();
            validSet = null;

            while (validSet == null)
            {
                System.out.print("Player " + player.getName() + ", Choose a card: ");
                choice = scanner.nextLine();
                validSet = cardArea.recordFlip(choice);
                printScore();
                cardArea.drawArea();
                System.out.println();
            }
            card1 = new Card(cardArea.getCardArea(), validSet, choice);

            validSet = null;
            while (validSet == null)
            {
                System.out.print("Choose another card: ");
                choice2 = scanner.nextLine();
                validSet = cardArea.recordFlip(choice2);
                printScore();

                if (validSet == null)
                {
                    cardArea.drawArea();
                    System.out.println();
                }
                else
                {
                    try
                    {
                        cardArea.drawArea();
                        System.out.println();
                        Thread.sleep(1500);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            card2 = new Card(cardArea.getCardArea(), validSet, choice2);

            isMatch = card1.checkMatch(card2);

            if (isMatch)
            {
                player.incrementScore();
                printScore();
                cardArea.drawArea();
            }
            else
            {
                isMatch = false;
                System.out.println();
            }
        }
    }

    /** Returns a String that includes the win status of the game
     *  <p>
     *  If one player has a higher score than another, they are the winner and
     *  their score and name will be returned.
     *  If both players have the same score, an announced tie will be returned.
     *
     *  @return String representation of the win status
     */
    public String winner()
    {
        Player winner = players[0];
        if (players[1].getScore() > players[0].getScore())
        {
            winner = players[1];
        }
        else if (players[0].getScore() == players[1].getScore())
        {
            return "The game is a tie with " + players[0].getName() + " and " + players[1].getName() + " both having " + players[0].getScore() + " matches!";
        }
        return winner.getName() + " won the Memory game with " + winner.getScore() + " matches!";
    }

    /** Clears the console
     *  <p>
     *  Creates several lines from the last printed statement in the console
     *  in order to "clear" it.
     */
    public static void clearConsole()
    {
        System.out.println(System.lineSeparator().repeat(2000));
    }
}
