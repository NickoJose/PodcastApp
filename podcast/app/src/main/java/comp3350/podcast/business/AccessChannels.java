package comp3350.podcast.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.persistence.StubData;

/**
 * Created by Almach on 2018-02-06.
 */

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

    public String getChannels(List<Channel> channels)
    {
        channels.clear();
        return accessData.getChannelSequential(channels);
    }

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

    public String sortChannel(List<Channel> channels, String type)
    {
        Sort.channel((ArrayList<Channel>)channels, type);
        return null;
    }

    public String insertChannel(Channel currentChannel)
    {
        return accessData.insertChannel(currentChannel);
    }

    public String deleteChannel(Channel currentChannel)
    {
        return accessData.deleteChannel(currentChannel);
    }

    public String updateChannel(Channel currentChannel)
    {
        return accessData.updateChannel(currentChannel);
    }
}
