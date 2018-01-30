package comp3350.podcast.objects;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Russell on 2018-01-27.
 */
public class Playlist {

    private EpisodeList episodes;
    private ChannelList channels;

    private ArrayList<Date> lastChannelUpdates;

    public Playlist() {
        super();
        episodes = new EpisodeList();
        channels = new ChannelList();
        lastChannelUpdates = new ArrayList<>();
    }

    /**
     * Adds a Episode to the list if the list does not contain the Episode
     *
     * @param ep - The Episode to add
     * @return - True if added, false if not added.
     */
    public boolean addEpisode(int index, Episode ep) {
        return episodes.addEpisode(index, ep);
    }

    /**
     * Removes a Episode from the list
     *
     * @param ep - The Episode to remove
     * @return - True if removed, false if not removed
     */
    public boolean removeEpisode(Episode ep) {
        return episodes.removeEpisode(ep);
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

    public Iterator<Episode> getEpisodesAfter(Date date) {
        return episodes.getEpisodesAfter(date);
    }

    /**
     * Adds multiple episodes
     */
    public void addEpisodes(EpisodeList newEps) {
        episodes.addEpisodes(newEps);
    }

    /**
     * Adds multiple episodes to the list
     *
     * @param newEps - The episodes to list
     */
    public void addEpisodes(Iterator<Episode> newEps) {
        episodes.addEpisodes(newEps);
    }

    /**
     * Appends a channel to the list if the list does not contain the channel
     *
     * @param ch - The channel to addEpisodes
     * @return - True if added, false if not added.
     */
    public boolean addChannel(Channel ch) {
        lastChannelUpdates.add(new Date());
        return channels.addChannel(ch);
    }

    /**
     * Adds a channel to the list at the specified index if the list does not contain the channel
     *
     * @param index - The index to overwrite
     * @param ch    - The channel to addEpisodes
     * @return - True if added, false if not added.
     */
    public boolean addChannel(int index, Channel ch) {
        lastChannelUpdates.add(index, new Date());
        return channels.addChannel(index, ch);
    }

    /**
     * Removes a channel from the list
     *
     * @param ch - The channel to remove
     * @return - True if removed, false if not removed
     */
    public boolean removeChannel(Channel ch) {

        if (channels.contains(ch)) {
            lastChannelUpdates.remove(channels.indexOf(ch));
            return channels.removeChannel(ch);
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
        return episodes.addEpisode(ep);
    }

    public boolean update() {
        boolean updated = false;

        Iterator channelIter = channels.iterator();
        int counter = 0;

        while (channelIter.hasNext()) {
            Channel ch = (Channel) channelIter.next();

            // If our channel has been updated since the last time this playlist updated addEpisodes the new
            // episodes to the episode list
            if (ch.getLastUpdate().compareTo(lastChannelUpdates.get(counter)) < 0) {
                EpisodeList eps = ch.getEpisodes();

                Iterator newEps = eps.getEpisodesAfter(lastChannelUpdates.get(counter));

                episodes.addEpisodes(newEps);
            }

            counter++;
        }

        return updated;
    }


}
