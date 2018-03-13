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

    /**
     * Puts all the subscriptions into a given List<Channel> object.
     * The input/output follows a design pattern from the sample project.
     *
     * @param subs - list that we will store the result in
     * @return - null. This pattern was taken from sample project
     */
    public String getSubs(List<Channel> subs)
    {
        subs.clear();
        return accessData.getSubSequential(subs);
    }

    /**
     * Inserts subscription into database.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - the channel we are inserting
     * @return - null. This pattern was taken from sample project
     */
    public String insertSub(Channel currentChannel)
    {
        return accessData.insertSub(currentChannel);
    }

    /**
     * Deletes a given subscription from the database
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - the channel being removed from the database
     * @return - null. This pattern was taken from sample project
     */
    public String deleteSub(Channel currentChannel)
    {
        return accessData.deleteSub(currentChannel);
    }
}
