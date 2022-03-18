import java.util.ArrayList;

public class CardArea
{
    private int cards;
    private String[][] cardArea;
    private String[][] playArea;
    private ArrayList<int[]> matchList;
    private boolean match;
    private Card card1;
    private Card card2;

    public CardArea(int cards, ArrayList<int[]> matchList)
    {
        this.cards = cards;
        cardArea = new String[cards][cards];
        playArea = new String[cards][cards];
        match = false;
        this.matchList = matchList;
        makeMatches();
    }

    public String[][] getCardArea()
    {
        return cardArea;
    }

    public String[][] getPlayArea()
    {
        return playArea;
    }

    public boolean isMatch()
    {
        return match;
    }

    public boolean isEmpty()
    {
        for (String[] row: playArea)
        {
            for (String element : row)
            {
                if (!element.equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
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

            cardArea[matchList.get(rand1)[0]][matchList.get(rand1)[1]] = "" + pair;
            cardArea[matchList.get(rand2)[0]][matchList.get(rand2)[1]] = "" + pair;
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

        playArea = copyArr(playArea);

        drawArea(playArea);
        System.out.println();
        drawArea(cardArea);
    }

    public void drawArea(String[][] array)
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
                if (array[row][col].length() == 1)
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

    public int[] recordFlip(String choice, Player player)
    {
        int[] set = new int[2];

        for (int row = 0; row < playArea.length; row++)
        {
            for (int col = 0; col < playArea[0].length; col++)
            {
                if (playArea[row][col].equals(choice))
                {
                    set[0] = row;
                    set[1] = col;
                }
            }
        }

        playArea[set[0]][set[1]] = cardArea[set[0]][set[1]];
        drawArea(playArea);

        return set;
    }

    private String[][] copyArr(String[][] array)
    {
        int idx = 1;
        for (int row = 0; row < array.length; row++)
        {
            for (int col = 0; col < array[0].length; col++)
            {
                array[row][col] = "" + idx;
                idx++;
            }
        }
        return array;
    }
}
