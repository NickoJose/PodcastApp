package comp3350.podcast.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.persistence.StubData;

public class AccessChannels
{
    private StubData accessData;
    private List<Channel> channels;
    private Channel channel;
    private int currentChannel;

    public AccessChannels()
    {
        accessData = (StubData) Services.getDataAccess(Main.dbName);
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
     * Gets the next channel in order. Functions similar to an iterator's get()
     * The input/output follows a design pattern from the sample project.
     *
     * @return - next channel
     */
    public Channel getSequential()
    {
        String result = null;
        if (channels == null)
        {
            result = accessData.getChannelSequential(channels);
            currentChannel = 0;
        }
        if (currentChannel < channels.size())
        {
            channel = (Channel) channels.get(currentChannel);
            currentChannel++;
        }
        else
        {
            channels = null;
            channel = null;
            currentChannel = 0;
        }
        return channel;
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

    /**
     * Inserts channel into database.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - the channel we are inserting
     * @return - null. This pattern was taken from sample project
     */
    public String insertChannel(Channel currentChannel)
    {
        return accessData.insertChannel(currentChannel);
    }

    /**
     * Deletes a given channel from the database
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - the channel being removed from the database
     * @return - null. This pattern was taken from sample project
     */
    public String deleteChannel(Channel currentChannel)
    {
        return accessData.deleteChannel(currentChannel);
    }

    /**
     * Updates a channel in the database with a new version. Must have same title and URL to identify channel being updated.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - channel being updated
     * @return - null. This pattern was taken from sample project
     */
    public String updateChannel(Channel currentChannel)
    {
        return accessData.updateChannel(currentChannel);
    }
}
