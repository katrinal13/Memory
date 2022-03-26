import java.util.Scanner;

public class Memory
{
    private Player[] players;
    private CardArea cardArea;
    private Scanner scanner;
    private Card card1;
    private Card card2;

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

        if (cards == 1)
        {
            cards = 4;
        }
        else if (cards == 2)
        {
            cards = 6;
        }
        else
        {
            cards = 8;
        }
        WinCondition wins = new WinCondition(cards);
        cardArea = new CardArea(cards, wins.getMatchList());
        cardArea.layoutCardArea();
        run();
    }

    public void run()
    {
        boolean gameOver = false;
        while (!gameOver)
        {
            for (int i = 0; i < players.length; i++)
            {
                printScore();
                takeTurn(players[i]);

                if (cardArea.isEmpty())
                {
                    gameOver = true;
                    break;
                }
            }
        }

        System.out.println("Thanks for playing!");
        printScore();

        Player winner = players[0];
        for (int i = 0; i < players.length; i++)
        {
            if (players[i].getScore() > winner.getScore())
            {
                winner = players[i];
            }
        }
        System.out.println(winner.getName() + " won the Memory game with " + winner.getScore() + " matches!");
    }

     public void printScore()
     {
         System.out.println("----------- SCORE BOARD -----------");
         System.out.println(players[0].getName() + ": " + players[0].getScore());
         System.out.println(players[1].getName() + ": " + players[1].getScore());
         System.out.println();
     }

    public void takeTurn(Player player)
    {
        int[] validSet;
        boolean isMatch = true;
        String choice = "";
        String choice2 = "";
        cardArea.drawArea();

        while (isMatch && !cardArea.isEmpty())
        {
            validSet = null;
            while (validSet == null)
            {
                System.out.print("Player " + player.getName() + ", Choose a card: ");
                choice = scanner.nextLine();
                validSet = cardArea.recordFlip(choice);
            }
            card1 = new Card(cardArea.getCardArea(), validSet, choice);

            validSet = null;
            while (validSet == null)
            {
                System.out.print("Choose another card: ");
                choice2 = scanner.nextLine();
                validSet = cardArea.recordFlip(choice2);
            }
            card2 = new Card(cardArea.getCardArea(), validSet, choice2);

            isMatch = card1.checkMatch(card2);

            if (isMatch)
            {
                cardArea.drawArea();
                player.incrementScore();
            }
            else
            {
                isMatch = false;
                cardArea.drawArea();
                System.out.println();
            }
        }
    }

    private void clearScreen()
    {
        System.out.print("\\033[H\\033[2J");
        System.out.flush();
    }
}
