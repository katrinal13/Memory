public class Card
{
    private String[][] cardArea;
    private String[][] playArea;
    private int[] set;
    private String choice;

    public Card(String[][] cardArea, String[][] playArea, int[] set, String choice)
    {
        this.cardArea = cardArea;
        this.playArea = playArea;
        this.set = set;
        this.choice = choice;
    }

    public int[] getSet()
    {
        return set;
    }

    public String getChoice()
    {
        return choice;
    }

    public boolean checkMatch(Card card2)
    {
        boolean isMatch = false;
        if (cardArea[this.set[0]][this.set[1]].equals(cardArea[card2.getSet()[0]][card2.getSet()[1]]))
        {
            playArea[this.set[0]][this.set[1]] = " ";
            playArea[card2.getSet()[0]][card2.getSet()[1]] = " ";
            isMatch = true;
        }
        return isMatch;
    }

    public boolean selectedValidSpace()
    {
        if (!playArea[set[0]][set[1]].equals(" "))
        {
            return true;
        }
        return false;
    }
}
