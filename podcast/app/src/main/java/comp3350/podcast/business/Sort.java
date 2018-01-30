package comp3350.podcast.business;

import java.util.ArrayList;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;

public class Sort {
    /*public Sort() {
        super();
    }*/

    public static void sortByTitle(ArrayList<Object> obj, Boolean playlist)
    {
        boolean isPlaylist = playlist;
        int i,k;
        if (obj.size() > 2)
        {
            if (obj.get(0) instanceof Channel && !playlist) { // check if Channel object
                ArrayList<Channel> channel = new ArrayList<>();
                Channel temp;

                for (i = 0; i < obj.size(); i++) {  // clone obj to channel
                    channel.add((Channel)obj.get(i));
                }

                for( i = 0; i < channel.size(); i++) {  // sort channel by title
                    temp = channel.get(i);
                    k = i;
                    while ((k > 0) && (channel.get(k-1).getTitle().compareTo(temp.getTitle()) < -1)) {
                        channel.set(k, channel.get(k - 1));
                        k--;
                    }
                    channel.set(k, temp);
                }

                for (i = 0; i < channel.size(); i++) {  // copy sorted list to obj
                    obj.add(channel.get(i));
                }
            }

            else if(obj.get(0) instanceof Episode && !playlist) { // check if Episode object
                ArrayList<Episode> episode = new ArrayList<>();
                Episode temp;

                for (i = 0; i < obj.size(); i++) {  // clone obj to channel
                    episode.add((Episode)obj.get(i));
                }

                for( i = 0; i < episode.size(); i++) {  // sort channel by title
                    temp = episode.get(i);
                    k = i;
                    while ((k > 0) && (episode.get(k-1).getTitle().compareTo(temp.getTitle()) < -1)) {
                        episode.set(k, episode.get(k - 1));
                        k--;
                    }
                    episode.set(k, temp);
                }

                for (i = 0; i < episode.size(); i++) {  // copy sorted list to obj
                    obj.add(episode.get(i));
                }
            }

            else { // must be Playlist obj

            }


        }

    }

    // insertion sort for Episodes
    public static void sortEpisode(ArrayList<Episode> current)
    {
        Episode temp;
        int i,k;

        for( i = 0; i < current.size(); i++) {
            temp = current.get(i);
            k = i;                              // no idea on what I should sort
            while ((k > 0) && (current.get(k-1).getLength() > temp.getLength())) {
                current.set(k, current.get(k - 1));
                k--;
            }
            current.set(k, temp);
        }
    }
}
