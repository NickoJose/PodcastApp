package comp3350.podcast.business;

import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Playlist;
import comp3350.podcast.persistence.AccessData;

public class AccessPlaylists{
    
    private AccessData accessData;
    private Playlist playlists;
    private int currentEpisode;
    private int currentChannel;

    public AccessPlaylists(){
        accessData = Services.getDataAccess(Main.dbName);
        playlists = null;
        currentChannel = 0;
        currentEpisode = 0;
    }

    /**
     * Puts all the playlist in a playlist into a given List<Playlist> object.
     * The input/output follows a design pattern from the sample project.
     *
     * @param playlists - list that we will store the result in
     * @return - null. This pattern was taken from sample project
     */

    public String getPlaylists(List<Playlist> playlists)
    {
        playlists.clear();
        return accessData.getPlaylistSequential(playlists);
    }

    /**
     * Inserts playlist into database.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentPlaylist - the playlist we are inserting
     * @return - null. This pattern was taken from sample project
     */
    public String insertPlaylist(Playlist currentPlaylist)
    {
        return accessData.insertPlaylist(currentPlaylist);
    }

    /**
     * Deletes a given playlist from the database
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentPlaylist - the playlist being removed from the database
     * @return - null. This pattern was taken from sample project
     */
    public String deletePlaylist(Playlist currentPlaylist)
    {
        return accessData.deletePlaylist(currentPlaylist);
    }

    /**
     * Puts all the channels in a playlist into a given List<Channel> object. The source playlist is stored in this object
     * The input/output follows a design pattern from the sample project.
     *
     * @param channels - the List where the result will be stored
     * @return - null string pointer
     */
    public String getPlaylistChannels(List<Channel> channels, Playlist currentPlaylist){
        channels.clear();
        return accessData.getPlaylistChannelSequential(channels, currentPlaylist);
    }

    /**
     * Puts all the episodes in a playlist into a given List<Channel> object. The source playlist is stored in this object
     * The input/output follows a design pattern from the sample project.
     *
     * @param episodes - A List<Episode> object to store the result
     * @return - null string pointer
     */    
    public String getPlaylistEpisodes(List<Episode> episodes, Playlist currentPlaylist)
    {
        episodes.clear();
        return accessData.getPlaylistEpisodeSequential(episodes, currentPlaylist);
    }

    /**
     * Inserts a channel into a playlist
     *
     * @param currentChannel - the channel that will be inserted
     * @return - success
     */    
    public boolean insertPlaylistChannel(Channel currentChannel, Playlist currentPlaylist)
    {
        return accessData.insertPlaylistChannel(currentChannel, currentPlaylist);
    }

    /**
     * Inserts an episode into a playlist
     *
     * @param currentEpisode - the episode that will be inserted
     * @return - success
     */  
    public boolean insertPlaylistEpisode(Episode currentEpisode, Playlist currentPlaylist)
    {
        return accessData.insertPlaylistEpisode(currentEpisode, currentPlaylist);
    }

    /**
     * Deletes a channel from a playlist
     *
     * @param currentChannel - the channel that will be removed
     * @return - success
     */      
    public boolean deletePlaylistChannel(Channel currentChannel, Playlist currentPlaylist)
    {
        return accessData.deletePlaylistChannel(currentChannel, currentPlaylist);
    }
    
    /**
     * Deletes an episode from a playlist
     *
     * @param currentEpisode - the episode to be removed
     * @return - success
     */  
    public boolean deletePlaylistEpisode(Episode currentEpisode, Playlist currentPlaylist)
    {
        return accessData.deletePlaylistEpisode(currentEpisode, currentPlaylist);
    }
}

