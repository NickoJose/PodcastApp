package comp3350.podcast.objects;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Russell on 2018-01-27.
 */
public class Playlist extends ChannelList{

    private EpisodeList episodes;

    private LinkedList<Date> lastChannelUpdates;

    //TODO not done, just got tired

    public Playlist(){
        super();
        episodes = new EpisodeList();
        lastChannelUpdates = new LinkedList<>();
    }

    public boolean update(){
        boolean updated = false;

        Iterator channelIter = iterator();
        int counter = 0;

        while(channelIter.hasNext()){
            Channel ch = (Channel) channelIter.next();

            // If our channel has been updated since the last time this playlist updated add the new
            // episodes to the episode list
            if(ch.getLastUpdate().compare(lastChannelUpdates.get(counter)) < 0){
               EpisodeList eps = ch.getEpisodes();

               Iterator newEps = eps.getEpisodesAfter(lastChannelUpdates.get(counter));

               episodes.add(newEps);
            }

            counter ++;
        }

        return updated;
    }

    public boolean add(Channel ch){
        boolean added = super.addChannel(ch);

        if(added){
            lastChannelUpdates.add(ch.getLastUpdate());
        }

        episodes.add(ch.getEpisodes());

        return added;
    }
}
