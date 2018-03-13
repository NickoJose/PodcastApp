package comp3350.podcast.objects;
import java.util.Hashtable;
import java.util.Iterator;

import comp3350.podcast.application.Main;
import comp3350.podcast.persistence.AccessData;
import comp3350.podcast.persistence.ObjectData;

public class Playlist {

    private EpisodeList episodes;
    private ChannelList channels;
    private String name;

    private Hashtable<Channel, Date> lastChannelUpdates;

    public Playlist(String name) {
        super();
        episodes = new EpisodeList();
        channels = new ChannelList();
        lastChannelUpdates = new Hashtable<>();
        this.name = name;
    }

    /**
     * Adds a Episode to the episodeList if the episodeList does not contain the Episode
     *
     * @param index - The position in the list the episode should be added at
     * @param ep - The Episode to add
     * @return - void
     */
    public void addEpisode(int index, Episode ep) {
        episodes.add(index, ep);
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
     * @return - The amount of elements in the list
     */
    public int episodeCount() {
        return episodes.size();
    }

    /**
     * Returns an iterator with the episodes
     *
     * @return - Iterator with episodes
     */
    public Iterator<Episode> episodeIterator() {
        return episodes.iterator();
    }

    /**
     * Does this sublist contain the given Episode?
     *
     * @param ep - The Episode
     * @return - Returns whether the list contains the Episode
     */
    public boolean contains(Episode ep) {
        return episodes.contains(ep);
    }

    /**
     * Returns the position in the list, of the given Episode - or -1 if not contained
     *
     * @param ep - The Episode
     * @return - the position in the episode list of the episode. Measured from 0.
     */
    public int indexOf(Episode ep) {
        return episodes.indexOf(ep);
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
     * Returns the a subset of this list containing only episodes posted exclusively after this date. If it was posted
     * on the same date, it will not be included.
     *
     * @param date - The cutoff date
     * @return - An episodeList containing only  episodes posted after the given date
     */
    public EpisodeList getEpisodesAfter(Date date) {
        return episodes.getEpisodesAfter(date);
    }

    /**
     * Adds multiple episodes
     *
     * @param newEpisodes - An iterable object containing the new episodes (dupes won't be added)
     * @return - void
     */
    public void addEpisodes(Iterable<Episode> newEpisodes) {
        episodes.add(newEpisodes);
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
     * Adds a channel to the list at the specified index if the list does not contain the channel
     *
     * @param index - The index to overwrite
     * @param ch    - The channel to addEpisodes
     * @return - True if added, false if not added.
     */
    public void addChannel(int index, Channel ch) {
        lastChannelUpdates.put(ch, new Date());
        channels.add(index, ch);
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
     * Does this sublist contain the given channel
     *
     * @param ch - The channel
     * @return - If the list contains the channel
     */
    public boolean contains(Channel ch) {
        return channels.contains(ch);
    }

    /**
     * @return - The amount of elements in the list
     */
    public int channelCount() {
        return channels.size();
    }

    /**
     * Returns an iterator with the channels
     *
     * @return - Iterator with channels
     */
    public Iterator<Channel> channelIterator() {
        return channels.iterator();
    }

    /**
     * Returns the index of the given channel or -1 if not contained
     */
    public int indexOf(Channel ch) {
        return channels.indexOf(ch);
    }

    /**
     * Is this list empty
     *
     * @return empty?
     */
    public boolean isEmpty() {
        return channels.isEmpty();
    }

    /**
     * Adds a Episode to the list if the list does not contain the Episode
     *
     * @param ep - The Episode to add
     * @return - True if added, false if not added.
     */
    public boolean addEpisode(Episode ep) {
        return episodes.add(ep);
    }

//    /**
//     * Updates the current episode list with new content from channel subscriptions
//     *
//     * @return - Returns if the channel episode list has changed.
//     */
//    public boolean update() {
//
//        boolean updated = false;
//
//        Iterator channelIter = channels.iterator();
//        int counter = 0;
//
//        AccessData data = new ObjectData(Main.dbName);
//        Channel ch;
//        EpisodeList eps;
//        Iterator iter;
//
//        while (channelIter.hasNext()) {
//            ch = (Channel) channelIter.next();
//
//            // If our channel has been updated since the last time this playlist updated addEpisodes
//            // the new episodes to the episode list
//            if (ch.getLastUpdate().compareTo(lastChannelUpdates.get(ch)) < 0) {
//                updated = true;
//
//                eps = new EpisodeList();
//                data.getChannelEpisodeSequential(eps, ch);
//
//                episodes.add(eps.getEpisodesAfter(lastChannelUpdates.get(ch)));
//            }
//
//            counter++;
//        }
//
//        return updated;
//    }

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
