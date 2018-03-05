package comp3350.podcast.objects;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class EpisodeList extends ArrayList<Episode> {

    /**
     * Adds a Episode to the list if the list does not contain the Episode
     *
     * @parma index - The index to add the episode to
     * @param ep - The episode to add
     * @return - void
     */
    @Override
    public void add(int index, Episode ep) {
        if(ep == null){
            throw new NullPointerException("Episode must not be Null");
        }

        if (!contains(ep)) {
            super.add(index, ep);
        }
    }

    /**
     * Returns whether two lists contain exactly the same set of episodes
     *
     * @param obj - The object to compare against
     * @return - Returns object equality
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
     * Gets all episodes released strictly after a given date. Same date will not be included.
     * @param date - Cutoff date.
     * @return - void
     */
    public EpisodeList getEpisodesAfter(Date date) {

        EpisodeList epsAfter = new EpisodeList();

        Iterator iter = iterator();

        Episode ep;

        //add all episodes published after given date
        while (iter.hasNext()) {
            if((ep = (Episode) iter.next()).getPublishDate().compareTo(date) > 0){
                epsAfter.add(ep);
            }
        }

        return epsAfter;
    }
    /**
     * Adds multiple episodes to the list
     *
     * @param newEps - The episodes to list
     * @return - void
     */
    public void add(Iterable<Episode> newEps) {
        Iterator<Episode> iter = newEps.iterator();
        while (iter.hasNext()) {
            add(iter.next());
        }
    }

    /**
     * Adds a Episode to the list if the list does not contain the Episode
     *
     * @param ep - The Episode to add
     * @return - True if added, false if not added.
     */
    @Override
    public boolean add(Episode ep) {
        if(ep == null){
            throw new NullPointerException("Episode must not be Null");
        }

        if (contains(ep)) {
            return false;
        } else {
            return super.add(ep);
        }
    }
}
