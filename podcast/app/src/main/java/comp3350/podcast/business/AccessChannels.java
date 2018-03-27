package comp3350.podcast.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.persistence.AccessData;

/* Note to marker:
* Android Studio marks most methods in the Access classes as "never used".
* However, when removing them, we encounter some reference errors where the values
* are actually used. While they could probably be removed with some work, we leave them in for safety.
*/

public class AccessChannels
{
    private AccessData accessData;
    private List<Channel> channels;
    private Channel channel;
    private int currentChannel;

    public AccessChannels()
    {
        accessData = Services.getDataAccess(Main.dbName);
        channels = null;
        channel = null;
        currentChannel = 0;
    }

    /**
     * Puts all the channels in a playlist into a given List<Channel> object.
     * The input/output follows a design pattern from the sample project.
     *
     * @param channels - list that we will store the result in
     * @return - null. This pattern was taken from sample project
     */
    public String getChannels(List<Channel> channels)
    {
        channels.clear();
        return accessData.getChannelSequential(channels);
    }

    /**
     * Sorts a channel list in-object, according to a given data field.
     * Valid fields are: "title", "date".
     * The input/output follows a design pattern from the sample project.
     *
     * @param channels - list that we are sorting
     * @param type  - data field that we sort by. See description.
     * @return - null. This pattern was taken from sample project
     */
    public String sortChannel(List<Channel> channels, String type)
    {
        Sort.channel((ArrayList<Channel>)channels, type);
        return null;
    }
}
