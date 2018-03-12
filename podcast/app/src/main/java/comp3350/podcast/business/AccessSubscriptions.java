package comp3350.podcast.business;


import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.persistence.AccessData;

public class AccessSubscriptions
{
    private AccessData accessData;

    public AccessSubscriptions()
    {
        accessData = Services.getDataAccess(Main.dbName);
    }

    public String getSubs(List<Channel> subs)
    {
        subs.clear();
        return accessData.getSubSequential(subs);
    }

    public String insertSub(Channel currentChannel)
    {
        return accessData.insertSub(currentChannel);
    }

    public String deleteSub(Channel currentChannel)
    {
        return accessData.deleteSub(currentChannel);
    }
}