import java.util.ArrayList;

public class Matches
{
    private ArrayList<int[]> matchList;
    private int matchPairs;

    public Matches(int cards)
    {
        matchPairs = cards * cards / 2;
        ArrayList<Integer> idx = new ArrayList<Integer>();

        for (int i = 0; i < matchPairs; i++)
        {
            idx.add(i);
        }

        matchList = new ArrayList<int[]>();

        for (int i = 0; i < matchPairs; i++)
        {
            int rand1 = 0;
            int rand2 = 0;

            while (rand1 == rand2)
            {
                rand1 = (int) (Math.random() * matchPairs);
                rand2 = (int) (Math.random() * matchPairs);
            }

            for (int j = 0; j < idx.size(); j++)
            {
                if (idx.get(j) == rand1 || idx.get(j) == rand2)
                {
                    idx.remove(j);
                    j--;
                }
            }
            matchList.add(new int{})
        }

        for (int i = 0; i < )
    }
}
