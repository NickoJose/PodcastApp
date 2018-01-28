package comp3350.podcast.objects;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Russell on 2018-01-27.
 */

public class EpisodeList implements Iterable<Episode> {
    private LinkedList<Episode> episodes;

    public EpisodeList() {
        episodes = new LinkedList<>();
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
     * Does this sublist contain the given Episode
     *
     * @param ep - The Episode
     * @return - If the list contains the Episode
     */
    public boolean contains(Episode ep) {
        return episodes.contains(ep);
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
     * Returns a clone of this Episode List
     *
     * @return - Episode List that is a clone
     */
    @Override
    public EpisodeList clone() {
        EpisodeList clone = new EpisodeList();

        clone.episodes = (LinkedList<Episode>) this.episodes.clone();

        return clone;
    }

    /**
     * If two lists contain the same episodes return true.
     * @param obj - The object to compare against
     * @return
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
     * Returns the index of the given Episode or -1 if not contained
     * @param ep
     * @return
     */
    public int indexOf(Episode ep){
        return episodes.indexOf(ep);
    }

    /**
     * returns the Episode at the given index
     * @param index - Index of the Episode
     * @return - The Episode at the index
     */
    public Episode get(int index) throws IndexOutOfBoundsException{
        return episodes.get(index);
    }

    public Iterator<Episode> getEpisodesAfter(Date date) {

        LinkedList<Episode> eps = new LinkedList<>();

        Iterator iter = episodes.iterator();

        Episode ep;

        //add all episodes published after given date
        while(iter.hasNext() && (ep = (Episode) iter.next()).getPublishDate().compare(date) > -1){
            eps.add(ep);
        }

        return eps.iterator();
    }

    /**
     * Adds multiple episodes to the list
     * @param newEps - The episodes to add
     */
    public void add(Iterator<Episode> newEps) {
        while(newEps.hasNext()){
            addEpisode(newEps.next());
        }
    }

    /**
     * Adds multiple episodes
     * @param newEps
     */
    public void add(EpisodeList newEps){
        add(newEps.iterator());
    }
}
