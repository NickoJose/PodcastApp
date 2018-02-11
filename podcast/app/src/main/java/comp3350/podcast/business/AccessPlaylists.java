package comp3350.podcast.business;

import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Playlist;
import comp3350.podcast.persistence.StubData;

/**
 * Created by Almach on 2018-02-06.
 */

public class AccessPlaylists
{
    private StubData accessData;
    private Playlist playlists;
    private int currentEpisode;
    private int currentChannel;

    public AccessPlaylists()
    {
        accessData = (StubData) Services.getDataAccess(Main.dbName);
        playlists = null;
        currentChannel = 0;
        currentEpisode = 0;
    }

    public String getPlaylistChannels(List<Channel> channels)
    {
        channels.clear();
        return accessData.getPlaylistChannelSequential(channels);
    }

    public String getPlaylistEpisodes(List<Episode> episodes)
    {
        episodes.clear();
        return accessData.getPlayListEpisodeSequential(episodes);
    }

    public boolean insertPlaylistChannel(Channel currentChannel)
    {
        return accessData.insertPlaylistChannel(currentChannel);
    }

    public boolean insertPlaylistEpisode(Episode currentEpisode)
    {
        return accessData.insertPlaylistEpisode(currentEpisode);
    }

    public boolean deletePlaylistChannel(Channel currentChannel)
    {
        return accessData.deletePlaylistChannel(currentChannel);
    }

    public boolean deletePlaylistEpisode(Episode currentEpisode)
    {
        return accessData.deletePlaylistEpisode(currentEpisode);
    }
}

