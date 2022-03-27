import java.util.ArrayList;

public class CardArea
{
    public static String[][] playArea;
    private int cards;
    private String[][] cardArea;
    private String[] emojis;
    private ArrayList<int[]> matchList;
    private Card card1;
    private Card card2;

    public CardArea(int cards, ArrayList<int[]> matchList)
    {
        this.cards = cards;
        cardArea = new String[cards][cards];
        playArea = new String[cards][cards];
        this.matchList = matchList;
        //makeMatches();

        emojis = new String[] {"\uD83C\uDFC1", "\uD83D\uDCA0", "\uD83D\uDE47", "\uD83D\uDE4B", "\uD83D\uDC81", "\uD83E\uDD38", "\uD83C\uDF54", "\uD83D\uDCDD", "\uD83D\uDCC7", "\uD83D\uDD87", "\uD83D\uDCCC", "\uD83D\uDDD1", "\uD83E\uDDEC",
                "\uD83E\uDDFC", "\uD83E\uDDFA", "\uD83E\uDDE6", "\uD83D\uDECD", "\uD83E\uDDE2", "\uD83E\uDDF6", "\uD83D\uDD2E", "\uD83E\uDE80", "\uD83C\uDFA3", "\uD83C\uDFC0", "\uD83C\uDF9F", "\u2614", "\u26C8",
                "\uD83C\uDFD4", "\uD83C\uDF7A", "\uD83E\uDDC1", "\uD83E\uDD6F", "\uD83E\uDDA9", "\uD83D\uDE10"};
    }

    public String[][] getCardArea()
    {
        return cardArea;
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

//    public void makeMatches()
//    {
//        for (int[] arr : matchList)
//        {
//            System.out.print("[ ");
//            for (int num : arr)
//            {
//                System.out.print(num + " ");
//            }
//            System.out.print("]");
//        }
//    }

    public void layoutCardArea()
    {
        System.out.println();
        int matchPairs = cards * cards / 2;

        int pair = 0;
        for (int i = 0; i < matchPairs; i++)
        {
            int rand1 = 0;
            int rand2 = 0;

            while (rand1 == rand2)
            {
                rand1 = (int) (Math.random() * matchList.size());
                rand2 = (int) (Math.random() * matchList.size());
            }

            cardArea[matchList.get(rand1)[0]][matchList.get(rand1)[1]] = "" + emojis[pair];
            cardArea[matchList.get(rand2)[0]][matchList.get(rand2)[1]] = "" + emojis[pair];
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

        drawArea();
        System.out.println();
        drawCArea();
    }

    public void drawArea()
    {
        for (int row = 0; row < playArea.length; row++)
        {
            String dashes = "";
            int dashesAmt = cards * 3;

            for (int i = 0; i < dashesAmt - 1; i++)
            {
                dashes += "-";
            }
            for (int col = 0; col < playArea[0].length; col++)
            {
                if (playArea[row][col].length() == 1)
                {
                    System.out.print(" ");
                }
                System.out.print(playArea[row][col]);
                if (col != cards - 1)
                {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println(dashes);
        }
    }

    public void drawCArea()
    {
        for (int row = 0; row < cardArea.length; row++)
        {
            String dashes = "";
            int dashesAmt = cards * 3;

            for (int i = 0; i < dashesAmt - 1; i++)
            {
                dashes += "-";
            }
            for (int col = 0; col < cardArea[0].length; col++)
            {
                if (cardArea[row][col].length() == 1)
                {
                    System.out.print(" ");
                }
                System.out.print(cardArea[row][col]);
                if (col != cards - 1)
                {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println(dashes);
        }
    }

    public int[] recordFlip(String choice)
    {
        Memory.clearConsole();
        int[] set = null;

        for (int row = 0; row < playArea.length; row++)
        {
            for (int col = 0; col < playArea[0].length; col++)
            {
                if (playArea[row][col].equals(choice))
                {
                    set = new int[2];
                    set[0] = row;
                    set[1] = col;
                }
            }
        }

        if (set != null)
        {
            if (!playArea[set[0]][set[1]].equals(" "))
            {
                playArea[set[0]][set[1]] = cardArea[set[0]][set[1]];
            }
            else
            {
                set = null;
            }
        }
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
