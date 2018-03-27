package comp3350.podcast.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.ChannelList;
import comp3350.podcast.objects.Date;
import comp3350.podcast.persistence.StubData;

import static org.junit.Assert.*;

public class AccessChannelsTest
{
    private static String dbName = Main.dbName;
    private AccessChannels ac;
    private ChannelList channelList;
    private ChannelList resultCh;
    private Channel channel1;
    private Channel channel2;
    private Channel channel3;
    private Date chDate1;
    private Date chDate2;
    private Date chDate3;

    public AccessChannelsTest() { super(); }

    @Before
    public void setUp()
    {
        Services.closeDataAccess();
        Services.createDataAccess(new StubData(dbName));

        ac = new AccessChannels();

        chDate1 = new Date(2009, 12, 24);
        chDate2 = new Date(2016, 2, 17);
        chDate3 = new Date(2012, 4, 22);

        channel1 = new Channel("The Joe Rogan Experience", "The Joe Rogan Experience podcast is a long form conversation hosted by comedian, " +
                "UFC color commentator, and actor Joe Rogan with friends and guests that have included comedians, actors, musicians, MMA instructors and " +
                "commentators, authors, artists, and porn stars.", "http://joerogan.net/podcasts/", chDate1, "Joe Rogan", "Comedy", "Brian Redban", "BOOKDEATHSQUAD@GMAIL.COM", "");
        channel2 = new Channel("NBA Hang Time", "Veteran NBA writer Sekou Smith and former player Greg Anthony analyze the latest NBA news, " +
                "storylines, and more with guests from around the NBA world.", "http://www.nba.com/podcast#/", chDate2, "Sekou Smith", "Sports", "NBA Digital", "@NBA", "");
        channel3 = new Channel("You Are Not So Smart", "You Are Not So Smart is a celebration of self delusion that explores topics related to " +
                "cognitive biases, heuristics, and logical fallacies. David McRaney interviews scientists about their research into how the mind works, " +
                "and then he eats a cookie", "https://youarenotsosmart.com/", chDate3, "David McRaney", "Social Sciences", "David McRaney", "davidmcraney@gmail.com", "");

        channelList = new ChannelList();

        channelList.add(channel1);
        channelList.add(channel2);
        channelList.add(channel3);
    }

    @Test
    public void testGetChannels()
    {
        System.out.println("Starting AccessChannelsTest: GetChannels");
        resultCh = new ChannelList();

        // test retrieving the channels from the database
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));

        System.out.println("Finished AccessChannelsTest: GetChannels");
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
        System.out.println();
    }
}
