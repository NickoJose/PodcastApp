package comp3350.podcast.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.persistence.StubData;

/**
 * Created by Almach on 2018-02-06.
 */

public class AccessEpisodes
{
    private StubData accessData;
    private List<Episode> episodes;
    private Episode episode;
    private int currentEpisode;

    public AccessEpisodes()
    {
        accessData = (StubData) Services.getDataAccess(Main.dbName);
        episodes = null;
        episode = null;
        currentEpisode = 0;
    }

    public String getEpisodes(List<Episode> episodes)
    {
        episodes.clear();
        return accessData.getEpisodesSequential(episodes);
    }

    public String getChannelEpisodes(List<Episode> episodes, Channel currentChannel)
    {
        episodes.clear();
        return accessData.getChannelEpisodeSequential(episodes, currentChannel);
    }
    
    public Episode getSequential(Channel currentChannel)
    {
        String result = null;
        if (episodes == null)
        {
            result = accessData.getChannelEpisodeSequential(episodes, currentChannel);
            currentEpisode = 0;
        }
        if (currentEpisode < episodes.size())
        {
            episode = (Episode) episodes.get(currentEpisode);
            currentEpisode++;
        }
        else
        {
            episodes = null;
            episode = null;
            currentEpisode = 0;
        }
        return episode;
    }

    public String sortEpisode(List<Episode> channels, String type)
    {
        Sort.episode((ArrayList<Episode>)channels, type);
        return null;
    }

    public String insertEpisode(Episode currentEpisode)
    {
        return accessData.insertEpisode(currentEpisode);
    }

    public String deleteEpisode(Episode currentEpisode)
    {
        return accessData.deleteEpisode(currentEpisode);
    }

    public String updateEpisode(Episode currentEpisode)
    {
        return accessData.updateEpisode(currentEpisode);
    }
}
