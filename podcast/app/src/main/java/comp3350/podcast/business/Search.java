package comp3350.podcast.business;

import java.util.ArrayList;
import java.util.LinkedList;

import comp3350.podcast.objects.Episode;

public class Search {
    /**
     * Populates a linkedlist with episodes with the top being the most similar to the key.
     * Similarity is measured based on the length of the longest common subsequence of chars.
     *
     * @return - void
     */
    public LinkedList<Episode> getRelavenceList(ArrayList<Episode> in, String key)
    {
        LinkedList<Episode> out2 = new LinkedList<>();
        int l;
        int bestL = 0;

        // not the best way to sort
        for(Episode a : in)
        {
            l = lcs(a.getTitle(), key).length();

            if (l >= bestL) {
                out2.addFirst(a);
                bestL = l;
            }

        }

        return out2;
    }

    /**
     * Computes the longest common subsequence between two strings
     *
     * @return - String containing the longest common subsequence
     */
    public static String lcs(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int[][] lengths = new int[a.length()+1][b.length()+1];

        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < b.length(); j++)
                if (a.charAt(i) == b.charAt(j))
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                            Math.max(lengths[i+1][j], lengths[i][j+1]);

        // recover string from matrix
        StringBuffer sb = new StringBuffer();
        for (int x = a.length(), y = b.length();
             x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x-1][y])
                x--;
            else if (lengths[x][y] == lengths[x][y-1])
                y--;
            else {
                assert a.charAt(x-1) == b.charAt(y-1);
                sb.append(a.charAt(x-1));
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }
}
