package comp3350.podcast.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.business.AccessChannels;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.ChannelList;
import comp3350.podcast.objects.Date;
import comp3350.podcast.tests.persistence.StubData;

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
    private Channel channel4;
    private Channel channel5;
    private Channel channel6;
    private Channel channel7;
    private Date chDate1;
    private Date chDate2;
    private Date chDate3;
    private Date chDate4;

    public AccessChannelsTest() { super(); }

    @Before
    public void setUp() {
        Services.closeDataAccess();
        Services.createDataAccess(new StubData(dbName));

        ac = new AccessChannels();

        chDate1 = new Date(2009, 12, 24);
        chDate2 = new Date(2016, 2, 17);
        chDate3 = new Date(2012, 4, 22);
        chDate4 = new Date(2015, 3,12);

        channel1 = new Channel("The Joe Rogan Experience", "The Joe Rogan Experience podcast is a long form conversation hosted by comedian, " +
                "UFC color commentator, and actor Joe Rogan with friends and guests that have included comedians, actors, musicians, MMA instructors and " +
                "commentators, authors, artists, and porn stars.", "http://joerogan.net/podcasts/", chDate1, "Joe Rogan", "Comedy", "Brian Redban", "BOOKDEATHSQUAD@GMAIL.COM");
        channel2 = new Channel("NBA Hang Time", "Veteran NBA writer Sekou Smith and former player Greg Anthony analyze the latest NBA news, " +
                "storylines, and more with guests from around the NBA world.", "http://www.nba.com/podcast#/", chDate2, "Sekou Smith", "Sports", "NBA Digital", "@NBA");
        channel3 = new Channel("You Are Not So Smart", "You Are Not So Smart is a celebration of self delusion that explores topics related to " +
                "cognitive biases, heuristics, and logical fallacies. David McRaney interviews scientists about their research into how the mind works, " +
                "and then he eats a cookie", "https://youarenotsosmart.com/", chDate3, "David McRaney", "Social Sciences", "David McRaney", "davidmcraney@gmail.com");
        channel4 = new Channel("Seafood Mania", "In Seafood Mania, all we talk about is seafood and only seafood.",
                "https://seafoodmania.com/", chDate4, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com");
        channel5 = new Channel("Seafood Mania", "Chickens only",
                "https://seafoodmania.com/", chDate4, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com");
        channel6 = new Channel("Gimme dem beers", "All day baby",
                "https://seafoodmania.com/", chDate4, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com");
        channel7 = new Channel("Gimme dem beers", "For the boys",
                "https://seafoodmania.com/", chDate4, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com");

        channelList = new ChannelList();

        channelList.add(channel1);
        channelList.add(channel2);
        channelList.add(channel3);
    }

    @Test
    public void testInsertChannel()
    {
        System.out.println("\nStarting AccessChannelsTest: InsertChannel");
        resultCh = new ChannelList();
        Channel ch = null;

        // test retrieving the channels from the database
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test inserting a channel
        channelList.add(channel4);
        ac.insertChannel(channel4);
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test inserting multiple of channels
        channelList.add(channel6);
        ac.deleteChannel(channel3);
        ac.deleteChannel(channel4);
        ac.insertChannel(channel3);
        ac.insertChannel(channel4);
        ac.insertChannel(channel6);
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test inserting duplicate channel
        ac.insertChannel(channel4);
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));

        for ( int i = 0; i < resultCh.size(); i ++)
        {
            System.out.println(resultCh.get(i));
        }

        // test inserting a null channel
        try {
            ac.insertChannel(ch);
            ac.getChannels(resultCh);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        System.out.println("Finished AccessChannelsTest: InsertChannel");
    }

    @Test
    public void testDeleteChannel()
    {
        System.out.println("\nStarting AccessChannelsTest: DeleteChannel");
        resultCh = new ChannelList();

        channelList.add(channel4);

        // test deleting a channel
        channelList.remove(channel4);
        ac.insertChannel(channel4);
        ac.deleteChannel(channel4);
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test deleting multiple channels
        channelList.remove(channel3);
        channelList.remove(channel2);
        ac.deleteChannel(channel3);
        ac.deleteChannel(channel2);
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test deleting a channel that does not exist
        ac.deleteChannel(channel4);
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));
        System.out.println("Finished AccessChannelsTest: DeleteChannel");
    }

    @Test
    public void testUpdateChannel()
    {
        System.out.println("\nStarting AccessChannelsTest: UpdateChannel");
        resultCh = new ChannelList();

        channelList.add(channel4);
        ac.insertChannel(channel4);

        // test updating a channel
        ac.updateChannel(channel5);
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));
        assertEquals(resultCh.get(resultCh.size()-1).getDesc(), channel5.getDesc());

        // test updating a channel that does not exist
        ac.updateChannel(channel7);
        ac.getChannels(resultCh);
        assertEquals(resultCh.get(resultCh.size()-1).getDesc(), channel5.getDesc());

        // test updating multiple channels
        channelList.add(channel6);
        ac.insertChannel(channel6);
        ac.updateChannel(channel7);
        ac.updateChannel(channel4);
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));
        assertEquals(resultCh.get(resultCh.size()-1).getDesc(), channel7.getDesc());
        assertEquals(resultCh.get(resultCh.size()-2).getDesc(), channel4.getDesc());
        System.out.println("Finished AccessChannelsTest: UpdateChannel");
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
    }
}
