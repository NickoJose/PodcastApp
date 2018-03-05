package comp3350.podcast.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.persistence.StubData;

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

    /**
     * Puts all the episodes in a playlist into a given List<Channel> object. The source playlist is stored in the database
     * The input/output follows a design pattern from the sample project.
     *
     * @param episodes - the List where the result will be stored
     * @return - null. This pattern was taken from sample project
     */
    public String getEpisodes(List<Episode> episodes)
    {
        episodes.clear();
        return accessData.getEpisodesSequential(episodes);
    }

    /**
     * Puts all the episodes from a channel in a playlist into a given List<Channel> object. The source playlist is stored in this object
     * The input/output follows a design pattern from the sample project.
     *
     * @param episodes - the List where the result will be stored
     * @param currentChannel - the channel we want episodes from
     * @return - null. This pattern was taken from sample project
     */
    public String getChannelEpisodes(List<Episode> episodes, Channel currentChannel)
    {
        episodes.clear();
        return accessData.getChannelEpisodeSequential(episodes, currentChannel);
    }
    
    /**
     * Gets the next episode in the playlist from a channel
     *
     * @param currentChannel - the channel we want to get the next episode from
     * @return - next episode
     */
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
    
    /**
     * Sorts episodes according to a given data field. Valid firelds are:
     * "title", "date", "length".
     * The input/output follows a design pattern from the sample project.
     *
     * @param channel - Channel supplying the episodes we want to sort
     * @param type - A string representing the field we want. See description for valid fields.
     * @return - null. This pattern was taken from sample project
     */
    public String sortEpisode(List<Episode> channel, String type)
    {
        Sort.episode((ArrayList<Episode>)channel, type);
        return null;
    }

    /**
     * Inserts an episode into the current playlist.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentEpisode - Episode to be inserted into the playlist
     * @return - null. This pattern was taken from sample project
     */
    public String insertEpisode(Episode currentEpisode)
    {
        return accessData.insertEpisode(currentEpisode);
    }

    /**
     * Removes an episode from the current playlist.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentEpisode - Episode to be removed from the playlist
     * @return - null. This pattern was taken from sample project
     */
    public String deleteEpisode(Episode currentEpisode)
    {
        return accessData.deleteEpisode(currentEpisode);
    }

    /**
     * Updates an episode in the playlist
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentEpisode - Updated version of an episode, to update the playlist with.
     * @return - null. This pattern was taken from sample project
     */
    public String updateEpisode(Episode currentEpisode)
    {
        return accessData.updateEpisode(currentEpisode);
    }
}
