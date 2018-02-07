package comp3350.podcast.objects;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Russell on 2018-01-27.
 */

public class EpisodeList implements Iterable<Episode> {
    private ArrayList<Episode> episodes;

    public EpisodeList() {
        episodes = new ArrayList<>();
    }
    
    public ArrayList<Episode> getEpisodeList() {
        return episodes;
    }

    /**
     * Adds a Episode to the list if the list does not contain the Episode
     *
     * @parma index - The index to add the episode to
     * @param ep - The episode to add
     * @return - True if added, false if not added.
     */
    public boolean addEpisode(int index, Episode ep) {
        if (episodes.contains(ep)) {
            return false;
        } else {
            episodes.add(index, ep);
            return true;
        }
    }

    /**
     * Removes a Episode from the list
     *
     * @param ep - The Episode to remove
     * @return - True if removed, false if not removed
     */
    public boolean removeEpisode(Episode ep) {
        if (episodes.contains(ep)) {
            return episodes.remove(ep);
        } else {
            return false;
        }
    }

    /**
     * If two lists contain the same episodes return true.
     *
     * @param obj - The object to compare against
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EpisodeList) {
            EpisodeList other = (EpisodeList) obj;

            if (other.size() == this.size()) {
                Iterator iter = this.iterator();

                while (iter.hasNext()) {
                    if (!other.contains((Episode) iter.next()))
                        return false;
                }
            } else
                return false;

        } else
            return false;

        return true;
    }

    /**
     * @return - The amount of elements in the list
     */
    public int size() {
        return episodes.size();
    }

    /**
     * Returns an iterator with the episodes
     *
     * @return - Iterator with episodes
     */
    @NonNull
    @Override
    public Iterator<Episode> iterator() {
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
     * Returns a clone of this Episode List
     *
     * @return - Episode List that is a clone
     */
    @Override
    public EpisodeList clone() {
        EpisodeList clone = new EpisodeList();

        clone.episodes = (ArrayList<Episode>) this.episodes.clone();

        return clone;
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

        LinkedList<Episode> eps = new LinkedList<>();

        Iterator iter = episodes.iterator();

        Episode ep;

        //add all episodes published after given date
        while (iter.hasNext() && (ep = (Episode) iter.next()).getPublishDate().compareTo(date) > -1) {
            eps.add(ep);
        }

        return eps.iterator();
    }

    /**
     * Adds multiple episodes
     */
    public void addEpisodes(EpisodeList newEps) {
        addEpisodes(newEps.iterator());
    }

    /**
     * Adds multiple episodes to the list
     *
     * @param newEps - The episodes to list
     */
    public void addEpisodes(Iterator<Episode> newEps) {
        while (newEps.hasNext()) {
            addEpisode(newEps.next());
        }
    }

    /**
     * Adds a Episode to the list if the list does not contain the Episode
     *
     * @param ep - The Episode to add
     * @return - True if added, false if not added.
     */
    public boolean addEpisode(Episode ep) {
        if (episodes.contains(ep)) {
            return false;
        } else {
            return episodes.add(ep);
        }
    }
}
