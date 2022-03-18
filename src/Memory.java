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
                takeTurn(players[i]);
                if (takeTurn(players[i]))
                {
                    gameOver = true;
                    break;
                }
            }
        }
        System.out.println("Thanks for playing!");
    }

    public boolean takeTurn(Player player)
    {
        boolean selectedValidSpace = false;
        while (!selectedValidSpace)
        {
            System.out.print("Player " + player.getName() + ", Choose a card: ");
            String choice = scanner.nextLine();
            card1 = new Card(cardArea.getCardArea(), cardArea.getPlayArea(), cardArea.recordFlip(choice, player), choice);
            selectedValidSpace = card1.selectedValidSpace();
        }

        while (!selectedValidSpace)
        {
            System.out.print("Choose another card: ");
            String choice2 = scanner.nextLine();
            card2 = new Card(cardArea.getCardArea(), cardArea.getPlayArea(), cardArea.recordFlip(choice2, player), choice2);
            selectedValidSpace = card2.selectedValidSpace();
        }

        card1.checkMatch(card2);

        if (cardArea.isMatch())
        {
            player.incrementScore();
        }
        return cardArea.isEmpty();
    }

    private void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
