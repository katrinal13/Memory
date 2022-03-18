public class Player
{
    public String[] players;
    private String name;
    private int score;

    public Player(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    public void incrementScore()
    {
        score++;
    }
}
