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

public class AccessSubscriptionsTest
{
    private static String dbName = Main.dbName;
    private AccessSubscriptions as;
    private ChannelList subs;
    private ChannelList resultCh;
    private Channel channel1;
    private Channel channel2;
    private Channel channel3;
    private Channel channel4;
    private Date chDate1;
    private Date chDate2;
    private Date chDate3;
    private Date chDate4;

    public AccessSubscriptionsTest()
    {
        super();
    }

    @Before
    public void setUp()
    {
        Services.closeDataAccess();
        Services.createDataAccess(new StubData(dbName));

        chDate1 = new Date(2009, 12, 24);
        chDate2 = new Date(2016, 2, 17);
        chDate3 = new Date(2012, 4, 22);
        chDate4 = new Date(2015, 3,12);

        channel1 = new Channel("The Joe Rogan Experience", "The Joe Rogan Experience podcast is a long form conversation hosted by comedian, " +
                "UFC color commentator, and actor Joe Rogan with friends and guests that have included comedians, actors, musicians, MMA instructors and " +
                "commentators, authors, artists, and porn stars.", "http://joerogan.net/podcasts/", chDate1, "Joe Rogan", "Comedy", "Brian Redban", "BOOKDEATHSQUAD@GMAIL.COM","");
        channel2 = new Channel("NBA Hang Time", "Veteran NBA writer Sekou Smith and former player Greg Anthony analyze the latest NBA news, " +
                "storylines, and more with guests from around the NBA world.", "http://www.nba.com/podcast#/", chDate2, "Sekou Smith", "Sports", "NBA Digital", "@NBA","");
        channel3 = new Channel("You Are Not So Smart", "You Are Not So Smart is a celebration of self delusion that explores topics related to " +
                "cognitive biases, heuristics, and logical fallacies. David McRaney interviews scientists about their research into how the mind works, " +
                "and then he eats a cookie", "https://youarenotsosmart.com/", chDate3, "David McRaney", "Social Sciences", "David McRaney", "davidmcraney@gmail.com","");
        channel4 = new Channel("Seafood Mania", "In Seafood Mania, all we talk about is seafood and only seafood.",
                "https://seafoodmania.com/", chDate4, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com","");
        as = new AccessSubscriptions();


        subs = new ChannelList();
        subs.add(channel2);
    }

    @Test
    public void testInsertSub()
    {
        System.out.println("Starting AccessSubscriptionsTest: InsertSub");
        resultCh = new ChannelList();

        // test retrieving the subscriptions from the database
        as.getSubs(resultCh);
        assertTrue(resultCh.equals(subs));

        // test inserting a subscription
        subs.add(channel4);
        as.insertSub(channel4);
        as.getSubs(resultCh);
        assertTrue(resultCh.equals(subs));

        // test inserting duplicate subscription
        as.insertSub(channel4);
        as.getSubs(resultCh);
        assertTrue(resultCh.equals(subs));

        System.out.println("Finished AccessSubscriptionsTest: InsertSub");
    }

    @Test
    public void testDeleteSub()
    {
        System.out.println("Starting AccessSubscriptionsTest: DeleteSub");
        resultCh = new ChannelList();

        subs.add(channel4);

        // test deleting a subscription
        subs.remove(channel4);
        as.insertSub(channel4);
        as.deleteSub(channel4);
        as.getSubs(resultCh);
        assertTrue(resultCh.equals(subs));

        // test deleting a subscription that does not exist
        as.deleteSub(channel4);
        as.getSubs(resultCh);
        assertTrue(resultCh.equals(subs));
        System.out.println("Finished AccessSubscriptionsTest: DeleteSub");
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
        System.out.println();
    }
}
