import java.util.ArrayList;

/** This class represents a WinCondition object
 *
 *  @author Katrina Lin
 */
public class WinCondition
{
    /** The list of all pairs of row and column indexes in the board */
    private ArrayList<int[]> matchList;

    /** Instantiates a WinCondition object
     *  <p>
     *  Sets matchList to a new ArrayList of int arrays.
     *  For each row index of the board, each column index is also added in 1D array
     *  representing one of the match locations.
     *  Each match is added to the ArrayList.
     *
     *  @param cards The number of cards per row
     */
    public WinCondition(int cards)
    {
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
    }

    /** Returns the list of all pairs of row and column indexes in the board
     *
     *  @return The list of all pairs of row and column indexes in the board
     */
    public ArrayList<int[]> getMatchList()
    {
        return matchList;
    }
}
