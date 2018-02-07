package comp3350.podcast.business;

import java.util.ArrayList;

import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.EpisodeList;
import comp3350.podcast.persistence.StubData;

/**
 * Created by Gareth on 2018-02-07.
 */

public class MainActivityHelper {
    private StubData stubData;
    private ArrayList<Episode> episodeList;

    public MainActivityHelper()
    {
        stubData = new StubData();
        stubData.open("Timmy The Database");
        episodeList = stubData.getEpisodeList();
    }

    // returns a list of recommended episodes for the user
    public EpisodeList getRecList()
    {
        EpisodeList output = new EpisodeList();

        // get the first 5 or the whole list, whichever is less
        for (int i = 0; i < 5 && i < episodeList.size(); i++)
        {
            output.addEpisode(episodeList.get(i));
        }

        return output;
    }
}
