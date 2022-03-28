import java.util.ArrayList;

/** This class represents a CardArea object
 *
 *  @author Katrina Lin
 */
public class CardArea
{
    /** The playing area or array */
    public static String[][] playArea;

    /** The number of cards per row */
    private int cards;

    /** The array of flipped cards or matched pairs */
    private String[][] cardArea;

    /** The array of possible card values */
    private String[] emojis;

    /** The list of all pairs of row and column indexes in the board */
    private ArrayList<int[]> matchList;

    /** Instantiates a CardArea object
     *  <p>
     *  Initializes the emojis array to a String of emojis.
     *
     *  @param cards The number of cards per row
     *  @param matchList The list of all pairs of row and column indexes in the board
     */
    public CardArea(int cards, ArrayList<int[]> matchList)
    {
        this.cards = cards;
        this.matchList = matchList;
        cardArea = new String[cards][cards];
        playArea = new String[cards][cards];

        emojis = new String[] {"\uD83C\uDFC1", "\uD83D\uDCA0", "\uD83D\uDE47", "\uD83D\uDE4B", "\uD83D\uDC81", "\uD83E\uDD38", "\uD83C\uDF54", "\uD83D\uDCDD", "\uD83D\uDCC7", "\uD83D\uDD87", "\uD83D\uDCCC", "\uD83D\uDDD1", "\uD83E\uDDEC",
                "\uD83E\uDDFC", "\uD83E\uDDFA", "\uD83E\uDDE6", "\uD83D\uDECD", "\uD83E\uDDE2", "\uD83E\uDDF6", "\uD83D\uDD2E", "\uD83E\uDE80", "\uD83C\uDFA3", "\uD83C\uDFC0", "\uD83C\uDF9F", "\u2614", "\u26C8",
                "\uD83C\uDFD4", "\uD83C\uDF7A", "\uD83E\uDDC1", "\uD83E\uDD6F", "\uD83E\uDDA9", "\uD83D\uDE10"};
    }

    /** Returns the array of flipped cards or matched pairs
     *
     *  @return The array of flipped cards or matched pairs
     */
    public String[][] getCardArea()
    {
        return cardArea;
    }

    /** Returns the spacial status of the playing area
     *  <p>
     *  Returns true if the board is empty, returns false otherwise
     *
     *  @return If the board is empty or not
     */
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

    /** Creates the array of flipped cards or matched pairs
     *  <p>
     *  Selects two distinct random indexes of the matchList ArrayList.
     *  Sets the row and column indexes in cardArea indicated in the 1D array at the random index chosen
     *  to an emoji in the emojis array.
     *  Removes the two arrays at the random indexes chosen from matchList.
     *  Initializes the playing area.
     */
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
        numberArr(playArea);
    }

    /** Draws the board
     *  <p>
     *  Creates a horizontal divider of dashes after each row.
     *  If the element of the playing area is a single digit number,
     *  a space will be printed in front of it.
     *  Otherwise, the element will be printed along with a vertical divider after it.
     */
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
                if (playArea[row][col].length() == 1 && isNum(row, col))
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

    /** Returns an int array of the set of row and column index of the player's flip
     *  <p>
     *  The set will either be null or a length of two, indicating a row and column index.
     *  It will be assigned a row and column index that corresponds to the
     *  location of the player's card flip on the board if the value of their flip
     *  is found on the board.
     *  If the flip is found on the board and the value of set is initialized,
     *  the location of the flip on the playing area will be set to its corresponding
     *  value on cardArea to show the player the value of their flip.
     *
     *  @param choice The player's choice of card to flip
     *  @return The int array of the set of row and column index of the player's flip
     */
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
            playArea[set[0]][set[1]] = cardArea[set[0]][set[1]];
        }
        return set;
    }

    /** Returns if the value of a card flip is a number
     *  <p>
     *  Checks to see if the value of the card flip is a number or an emoji.
     *  If it is an emoji meaning it has already been flipped, an early false is returned.
     *  If it does not equal any emoji on the emojis array, it is a number which returns true.
     *  @param row The row index of the flip
     *  @param col The column index of the flip
     *  @return If the value of a card flip is a number
     */
    private boolean isNum(int row, int col)
    {
        for (String emoji : emojis)
        {
            if (playArea[row][col].equals(emoji))
            {
                return false;
            }
        }
        return true;
    }

    /** Numbers a 2D consecutively starting from 1 */
    private void numberArr(String[][] array)
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
    }
}
