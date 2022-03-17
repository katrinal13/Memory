import java.util.ArrayList;
import java.util.Arrays;

public class Matches
{
    private ArrayList<int[]> matchList;
    private int cards;

    public Matches(int cards)
    {
        this.cards = cards;
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
        makeMatches();
    }

    public ArrayList<int[]> getMatchList()
    {
        return matchList;
    }
    public void makeMatches()
    {
        /*
        for (int[] arr : matchList)
        {
            System.out.print("[ ");
            for (int num : arr)
            {
                System.out.print(num + " ");
            }
            System.out.print("]");
        }
         */
        /*
        int pair = 1;
        for (int i = 0; i < matchPairs; i++)
        {
            int rand1 = 0;
            int rand2 = 0;

            while (rand1 == rand2)
            {
                rand1 = (int) (Math.random() * matchPairs);
                rand2 = (int) (Math.random() * matchPairs);
            }

            for (int j = 0; j < matchList.size(); j++)
            {
                if (matchList.get(j)[j] == rand1 || matchList.get(j)[j] == rand2)
                {
                    int[] match = matchList.get(j);
                    match[0] = pair;
                    pair++;
                }
            }
        }
         */
    }
}
