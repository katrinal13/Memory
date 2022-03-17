import java.util.Scanner;

public class Memory
{
    private Player[] players;
    private CardArea cardArea;
    private Scanner scanner;

    public Memory()
    {
        scanner = new Scanner(System.in);
        players = new Player[2];

        System.out.print("Enter player 1's name: ");
        String player1 = scanner.nextLine();
        players[0] = new Player(player1);

        System.out.print("Enter player 2's name: ");
        String player2 = scanner.nextLine();
        players[1] = new Player(player2);

        System.out.print("Select a board size: (1) 4x4, (2) 6x6, (3) 8x8 ");
        int cards = scanner.nextInt();

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

        cardArea = new CardArea(cards);
        cardArea.layoutCardArea();
        run();
    }

    public void run()
    {
        System.out.println("Hi");
    }
}
