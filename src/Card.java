/** This class represents a Card object
 *
 *  @author Katrina Lin
 */
public class Card
{
    /** The organization of matched pairs */
    private String[][] cardArea;

    /** The set of row and column index of the player's flip */
    private int[] set;

    /** The player's flip */
    private String choice;

    /** Instantiates a Card object
     *
     *  @param cardArea the board of matched pairs
     *  @param set the index of the player's flip
     *  @param choice the player's flip
     */
    public Card(String[][] cardArea, int[] set, String choice)
    {
        this.cardArea = cardArea;
        this.set = set;
        this.choice = choice;
    }

    /** Returns the current set of row and column index of the player's flip
     *
     *  @return The set of row and column index of the player's flip
     */
    public int[] getSet()
    {
        return set;
    }

    /** Returns the current choice of card of the player
     *
     *  @return The player's flip
     */
    public String getChoice()
    {
        return choice;
    }

    /** Returns a boolean that indicates if the player's two flips are a matched pair
     *  <p>
     *  A match is determined by accessing the set of two Card objects.
     *  If the element of playArea that corresponds to the row and column index of the set of this Card object
     *  is equal to that of the second Card object, it is a match.
     *  If the player's flips are a match, the spaces that correspond to their flips will be set to a blank String value.
     *  If the player's flips are not a match, the spaces that correspond to their flips will be set back to their original value
     *
     *  @param card2 the second Card object to be compared with
     *  @return whether the player's flips are a matched pair
     */
    public boolean checkMatch(Card card2)
    {
        boolean isMatch = false;
        if (cardArea[this.set[0]][this.set[1]].equals(cardArea[card2.getSet()[0]][card2.getSet()[1]]))
        {
            CardArea.playArea[this.set[0]][this.set[1]] = " ";
            CardArea.playArea[card2.getSet()[0]][card2.getSet()[1]] = " ";
            isMatch = true;
        }
        else
        {
            CardArea.playArea[this.set[0]][this.set[1]] = this.getChoice();
            CardArea.playArea[card2.getSet()[0]][card2.getSet()[1]] = card2.getChoice();
        }
        return isMatch;
    }
}
