import java.util.ArrayList;

public class CardArea
{
    private ArrayList<Matches> matches;
    private int cards;

    public CardArea(int cards)
    {
        this.cards = cards;
        matches = new ArrayList<Matches>();

        for (int i = 0; i < (cards * cards) / 2; i++)
        {

        }
    }

    public void layoutCardArea()
    {
        System.out.println();

        for (int i = 0; i < cards * cards; i++)
        {

        }
    }
}
