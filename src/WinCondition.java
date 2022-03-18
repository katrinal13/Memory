import java.util.ArrayList;

public class WinCondition
{
    private ArrayList<int[]> matchList;

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

    public ArrayList<int[]> getMatchList()
    {
        return matchList;
    }
}
