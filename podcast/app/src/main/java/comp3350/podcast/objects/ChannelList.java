package comp3350.podcast.objects;

import java.util.ArrayList;
import java.util.Iterator;

public class ChannelList extends ArrayList<Channel>{

    /**
     * Appends a channel to the list if the list does not contain the channel
     *
     * @param ch - The channel to addEpisodes
     * @return - True if added, false if not added.
     */
    @Override
    public boolean add(Channel ch) {
        if(ch == null){
            throw new NullPointerException("Channel must not be Null");
        }
        if (contains(ch)) {
            return false;
        } else {
            return super.add(ch);
        }
    }

    /**
     * Adds a channel to the list at the specified index if the list does not contain the channel
     *
     * @param index - The index to overwrite
     * @param ch    - The channel to addEpisodes
     * @return - True if added, false if not added.
     */
    @Override
    public void add(int index, Channel ch) {
        if(ch == null){
            throw new NullPointerException("Channel must not be null");
        }
        if (!contains(ch)) {
            super.add(index, ch);
        }
    }
    
    /**
     * Checks if a given object represents the same object as this. That is, if they both contain identical lists.
     *
     * @param obj - object being compared to this
     * @return - True if added, false if not added.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChannelList) {
            ChannelList other = (ChannelList) obj;

            if (other.size() == this.size()) {
                Iterator iter = this.iterator();

                while (iter.hasNext()) {
                    if (!other.contains((Channel) iter.next()))
                        return false;
                }
            } else
                return false;

        } else
            return false;

        return true;
    }
}
