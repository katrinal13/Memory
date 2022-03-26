public class Card
{
    private String[][] cardArea;
    private int[] set;
    private String choice;

    public Card(String[][] cardArea, int[] set, String choice)
    {
        this.cardArea = cardArea;
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
