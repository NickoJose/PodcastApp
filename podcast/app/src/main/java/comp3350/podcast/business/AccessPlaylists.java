package comp3350.podcast.business;

import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Playlist;
import comp3350.podcast.persistence.StubData;

public class AccessPlaylists{
    
    private StubData accessData;
    private Playlist playlists;
    private int currentEpisode;
    private int currentChannel;

    public AccessPlaylists(){
        accessData = (StubData) Services.getDataAccess(Main.dbName);
        playlists = null;
        currentChannel = 0;
        currentEpisode = 0;
    }

    /**
     * Puts all the channels in a playlist into a given List<Channel> object. The source playlist is stored in this object
     * The input/output follows a design pattern from the sample project.
     *
     * @param channels - the List where the result will be stored
     * @return - null string pointer
     */
    public String getPlaylistChannels(List<Channel> channels){
        channels.clear();
        return accessData.getPlaylistChannelSequential(channels);
    }

    /**
     * Puts all the episodes in a playlist into a given List<Channel> object. The source playlist is stored in this object
     * The input/output follows a design pattern from the sample project.
     *
     * @param episodes - A List<Episode> object to store the result
     * @return - null string pointer
     */    
    public String getPlaylistEpisodes(List<Episode> episodes)
    {
        episodes.clear();
        return accessData.getPlayListEpisodeSequential(episodes);
    }

    /**
     * Inserts a channel into a playlist
     *
     * @param currentChannel - the channel that will be inserted
     * @return - success
     */    
    public boolean insertPlaylistChannel(Channel currentChannel)
    {
        return accessData.insertPlaylistChannel(currentChannel);
    }

    /**
     * Inserts an episode into a playlist
     *
     * @param currentEpisode - the episode that will be inserted
     * @return - success
     */  
    public boolean insertPlaylistEpisode(Episode currentEpisode)
    {
        return accessData.insertPlaylistEpisode(currentEpisode);
    }

    /**
     * Deletes a channel from a playlist
     *
     * @param currentChannel - the channel that will be removed
     * @return - success
     */      
    public boolean deletePlaylistChannel(Channel currentChannel)
    {
        return accessData.deletePlaylistChannel(currentChannel);
    }
    
    /**
     * Deletes an episode from a playlist
     *
     * @param currentEpisode - the episode to be removed
     * @return - success
     */  
    public boolean deletePlaylistEpisode(Episode currentEpisode)
    {
        return accessData.deletePlaylistEpisode(currentEpisode);
    }
}

