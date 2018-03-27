package comp3350.podcast.objects;
import java.util.Hashtable;
import java.util.Iterator;

public class Playlist {

    private EpisodeList episodes;
    private ChannelList channels;
    private String name;

    private Hashtable<Channel, Date> lastChannelUpdates;

    public Playlist(String name) {
        super();
        this.episodes = new EpisodeList();
        this.channels = new ChannelList();
        this.lastChannelUpdates = new Hashtable<>();
        this.name = name;
    }

    /**
     * Removes a Episode from the episodeList
     *
     * @param ep - The Episode to remove
     * @return - True if removed, false if not removed
     */
    public boolean removeEpisode(Episode ep) {
        return episodes.remove(ep);
    }

    /**
     * Returns the Episode at the given index
     *
     * @param index - Index of the Episode
     * @return - The Episode at the index
     */
    public Episode get(int index) throws IndexOutOfBoundsException {
        return episodes.get(index);
    }

    /**
     * Appends a channel to the list if the list does not contain the channel
     *
     * @param ch - The channel to addEpisodes from
     * @return - True if added, false if not added.
     */
    public boolean addChannel(Channel ch) {
        lastChannelUpdates.put(ch, new Date());
        return channels.add(ch);
    }

    /**
     * Removes a channel from the list
     *
     * @param ch - The channel to remove
     * @return - True if removed, false if not removed
     */
    public boolean removeChannel(Channel ch) {

        if (channels.contains(ch)) {
            lastChannelUpdates.remove(ch);
            return channels.remove(ch);
        }

        return false;
    }

    /**
     * Removes an episode to the list
     *
     * @param ep - The episode to add
     * @return - Returns success
     */
    public boolean addEpisode(Episode ep) {
        return episodes.add(ep);
    }

    /**
     * Get this playlist's channel list
     *
     * @return - Returns the channels this playlist subscribes to
     */
    public ChannelList getChannels(){
        return channels;
    }

    /**
     * This playlists episode list
     *
     * @return - Returns the episode list contained in this object
     */
    public EpisodeList getEpisodes(){
        return episodes;
    }

    /**
     * Get the name of the playlist
     *
     * @return - Returns the name of the playlist
     */
    public String getName(){
        return name;
    }

    /**
     * To String
     *
     * @return Returns string representing the playlist. Formatted as "CPlaylist name: <NAME>"
     */
    public String toString() {
        return ("Playlist name: " + name);
    }

    /**
     * Checks if a given object represents the same channel. Checks title and source URL.
     *
     * @param obj  - obj to compare
     * @return Returns true on success.
     */
    public boolean equals(Object obj)
    {
        boolean result = false;
        Playlist pl;

        if(obj instanceof Playlist)
        {
            pl = (Playlist)obj;
            if(pl.getName().equals(name))
            {
                result = true;
            }
        }
        return result;
    }//equals
}
