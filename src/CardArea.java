import java.util.ArrayList;

public class CardArea
{
    private ArrayList<int[]> matchList;
    private int cards;
    private int[][] cardArea;
    private int[][] playArea;

    public CardArea(int cards)
    {
        this.cards = cards;
        cardArea = new int[cards][cards];
        playArea = new int[cards][cards];
        matchList = new ArrayList<int[]>();
        for (int i = 0; i < cards; i++)
        {
            for (int j = -1; j < cards - 1; j++)
            {
                int[] match = new int[2];
                match[0] = i;
                match[1] = j + 1;
                matchList.add(match);
            }
        }
        makeMatches();
    }

    public void makeMatches()
    {
//        for (int[] arr : matchList)
//        {
//            System.out.print("[ ");
//            for (int num : arr)
//            {
//                System.out.print(num + " ");
//            }
//            System.out.print("]");
//        }
    }

    public void layoutCardArea()
    {
        System.out.println();
        int matchPairs = cards * cards / 2;

        int pair = 1;
        for (int i = 0; i < matchPairs; i++)
        {
            int rand1 = 0;
            int rand2 = 0;

            while (rand1 == rand2)
            {
                rand1 = (int) (Math.random() * matchList.size());
                rand2 = (int) (Math.random() * matchList.size());
            }

            cardArea[matchList.get(rand1)[0]][matchList.get(rand1)[1]] = pair;
            cardArea[matchList.get(rand2)[0]][matchList.get(rand2)[1]] = pair;
            if (rand1 > rand2)
            {
                matchList.remove(rand1);
                matchList.remove(rand2);
            }
            else
            {
                matchList.remove(rand2);
                matchList.remove(rand1);
            }
            pair++;
        }

        int idx = 1;
        for (int row = 0; row < playArea.length; row++)
        {
            for (int col = 0; col < playArea[0].length; col++)
            {
                playArea[row][col] = idx;
                idx++;
            }
        }
        drawArea(playArea);
        System.out.println();
        drawArea(cardArea);
    }

    public void drawArea(int[][] array)
    {
        for (int row = 0; row < array.length; row++)
        {
            String dashes = "";
            int dashesAmt = cards * 3;

            for (int i = 0; i < dashesAmt - 1; i++)
            {
                dashes += "-";
            }
            for (int col = 0; col < array[0].length; col++)
            {
                if (array[row][col] % 10 == array[row][col])
                {
                    System.out.print(" ");
                }
                System.out.print(array[row][col]);
                if (col != cards - 1)
                {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println(dashes);
        }
    }

    private void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
