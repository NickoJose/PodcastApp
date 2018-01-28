package comp3350.podcast.objects;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Russell on 2018-01-27.
 */

public class ChannelList implements Iterable<Channel> {
    private LinkedList<Channel> channels;

    public ChannelList() {
        channels = new LinkedList<>();
    }

    /**
     * Adds a channel to the list if the list does not contain the channel
     *
     * @param ch - The channel to add
     * @return - True if added, false if not added.
     */
    public boolean addChannel(Channel ch) {
        if (channels.contains(ch)) {
            return false;
        } else {
            return channels.add(ch);
        }
    }

    /**
     * Removes a channel from the list
     *
     * @param ch - The channel to remove
     * @return - True if removed, false if not removed
     */
    public boolean removeChannel(Channel ch) {
        if (channels.contains(ch)) {
            return channels.remove(ch);
        } else {
            return false;
        }
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
    public int size() {
        return channels.size();
    }

    /**
     * Returns an iterator with the channels
     *
     * @return - Iterator with channels
     */
    @NonNull
    @Override
    public Iterator<Channel> iterator() {
        return channels.iterator();
    }


    /**
     * Returns a clone of this Channel List
     *
     * @return - Channel List that is a clone
     */
    @Override
    public ChannelList clone() {
        ChannelList clone = new ChannelList();

        clone.channels = (LinkedList<Channel>) this.channels.clone();

        return clone;
    }

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

    /**
     * Returns the index of the given channel or -1 if not contained
     * @param ch
     * @return
     */
    public int indexOf(Channel ch){
        return channels.indexOf(ch);
    }

    /**
     * returns the channel at the given index
     * @param index - Index of the channel
     * @return - The channel at the index
     */
    public Channel get(int index) throws IndexOutOfBoundsException{
        return channels.get(index);
    }

}
