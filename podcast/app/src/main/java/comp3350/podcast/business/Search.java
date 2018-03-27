package comp3350.podcast.business;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import comp3350.podcast.objects.Episode;

public class Search {
    /**
     * Populates a linkedlist with episodes with the top being the most similar to the key.
     * Similarity is measured based on a heuristic calculated by matchHeuristic.
     *
     * @return - void
     */
    public LinkedList getRelavenceList(ArrayList<Episode> in, String key)
    {
        LinkedList<WeightedEpNode> weightedList = new LinkedList<>();
        int l;
        int minL = key.length();

        // not the best way to sort
        for(Episode a : in)
        {
            l = matchHeuristic(a.getTitle(), key);

            if (l >= minL)
                weightedList.add(new WeightedEpNode(a,l));

        }

        Collections.sort(weightedList);
        return weightedList;
    }

    /**
     * Creates a heuristic for the similarity between two strings using the length of
     * the longest common subsequence and substring
     *
     * @param a - First string
     * @param b - Second string
     * @return - int of the heuristic
     */
    public int matchHeuristic (String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        int m = a.length();
        int n = b.length();

        int max = 0;

        int[][] dp = new int[m][n];
        int[][] lengths = new int[a.length()+1][b.length()+1];

        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }

                    if (max < dp[i][j])
                        max = dp[i][j];
                }
                if (a.charAt(i) == b.charAt(j))
                    lengths[i + 1][j + 1] = lengths[i][j] + 1;
                else
                    lengths[i + 1][j + 1] =
                            Math.max(lengths[i + 1][j], lengths[i][j + 1]);
            }

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

        String lcsub = sb.reverse().toString();
        return lcsub.length() + max*2;
    }
}

class WeightedEpNode extends Episode implements Comparable<Object>
{
    private Episode episode;
    private int weight;

    public WeightedEpNode(Episode episode, int weight)
    {
        super(episode.getTitle(), episode.getUrl(), episode.getDesc(),
        episode.getLength(), episode.getChannel(), episode.getPublishDate(),
            episode.getAuthor(), episode.getCategory(), episode.getEpNum(),episode.getImg());

        this.episode = episode;
        this.weight = weight;
    }

    public Episode getEpisode()
    {
        return episode;
    }

    public int getWeight()
    {
        return weight;
    }

    public int compareTo(Object weightedEpNode) {
        if (weightedEpNode instanceof WeightedEpNode)
        {
            return ((WeightedEpNode)weightedEpNode).getWeight() - this.weight;
        }
        return 0;
    }
}
