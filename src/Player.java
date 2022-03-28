/** This class represents a Player object
 *
 * @author Katrina Lin
 */
public class Player
{
    /** The player's name */
    private String name;

    /** The player's score */
    private int score;

    /** Instantiates a Player object
     *
     * @param name The name of the player
     */
    public Player(String name)
    {
        this.name = name;
        score = 0;
    }

    /** Returns the name of the player
     *
     * @return The name of the player
     */
    public String getName()
    {
        return name;
    }

    /** Returns the player's current score
     *
     * @return The player's score
     */
    public int getScore()
    {
        return score;
    }

    /** Increments the player's score by 1 */
    public void incrementScore()
    {
        score++;
    }
}
