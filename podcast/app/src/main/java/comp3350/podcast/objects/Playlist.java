package comp3350.podcast.objects;


import android.support.v4.os.IResultReceiver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import comp3350.podcast.persistence.StubData;

/**
 * Created by Russell on 2018-01-27.
 *
 * TODO: Nothing to test because it wraps a bunch of already tested methods, and update is not required yet
 */
public class Playlist {

    private EpisodeList episodes;
    private ChannelList channels;

    private Hashtable<Channel, Date> lastChannelUpdates;

    public Playlist() {
        super();
        episodes = new EpisodeList();
        channels = new ChannelList();
        lastChannelUpdates = new Hashtable<>();
    }

    /**
     * Adds a Episode to the list if the list does not contain the Episode
     *
     * @param ep - The Episode to add
     */
    public void addEpisode(int index, Episode ep) {
        episodes.add(index, ep);
    }

    /**
     * Removes a Episode from the list
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
     * Does this sublist contain the given Episode
     *
     * @param ep - The Episode
     * @return - If the list contains the Episode
     */
    public boolean contains(Episode ep) {
        return episodes.contains(ep);
    }

    /**
     * Returns the index of the given Episode or -1 if not contained
     */
    public int indexOf(Episode ep) {
        return episodes.indexOf(ep);
    }

    /**
     * returns the Episode at the given index
     *
     * @param index - Index of the Episode
     * @return - The Episode at the index
     */
    public Episode get(int index) throws IndexOutOfBoundsException {
        return episodes.get(index);
    }

    public EpisodeList getEpisodesAfter(Date date) {
        return episodes.getEpisodesAfter(date);
    }

    /**
     * Adds multiple episodes
     *
     * @param newEpisodes - An iterable object containing the new episodes (dupes won't be added)
     */
    public void addEpisodes(Iterable<Episode> newEpisodes) {
        episodes.add(newEpisodes);
    }

    /**
     * Appends a channel to the list if the list does not contain the channel
     *
     * @param ch - The channel to addEpisodes
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

    public boolean update() {
        // TODO update update, this is a temporary method, Do not test until it is required to be implmented
        boolean updated = false;

        Iterator channelIter = channels.iterator();
        int counter = 0;

        StubData data = new StubData("LIES");
        Channel ch;
        EpisodeList eps;
        Iterator iter;

        while (channelIter.hasNext()) {
            ch = (Channel) channelIter.next();

            // If our channel has been updated since the last time this playlist updated addEpisodes
            // the new episodes to the episode list
            if (ch.getLastUpdate().compareTo(lastChannelUpdates.get(ch)) < 0) {
                updated = true;

                //TODO Temporary way of getting episodes from persistence, will be optimized in later iteration
                eps = new EpisodeList();
                data.getChannelEpisodeSequential(eps, ch);

                episodes.add(eps.getEpisodesAfter(lastChannelUpdates.get(ch)));
            }

            counter++;
        }

        return updated;
    }

    /**
     * Get this playlists channel list
     * @return
     */
    public ChannelList getChannels(){
        return channels;
    }

    /**
     * This playlists episode list
     * @return
     */
    public EpisodeList getEpisodes(){
        return episodes;
    }


}
