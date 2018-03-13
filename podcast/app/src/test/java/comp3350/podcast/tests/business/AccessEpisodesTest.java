package comp3350.podcast.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.business.AccessChannels;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.ChannelList;
import comp3350.podcast.objects.Date;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.EpisodeList;
import comp3350.podcast.tests.persistence.StubData;

import static org.junit.Assert.*;

public class AccessEpisodesTest
{
    private static String dbName = Main.dbName;
    private AccessEpisodes ae;
    private AccessChannels ac;
    private ChannelList channelList;
    private EpisodeList episodeList;
    private EpisodeList channelEpisodesList;
    private EpisodeList resultEp;
    private Channel channel5;
    private Episode episode1;
    private Episode episode2;
    private Episode episode3;
    private Episode episode4;
    private Episode episode5;
    private Episode episode6;
    private Episode episode7;
    private Episode episode8;
    private Episode episode9;
    private Episode episode10;
    private Episode episode11;
    private Episode episode12;
    private Episode episode13;
    private Date chDate4;
    private Date epDate1;
    private Date epDate2;
    private Date epDate3;
    private Date epDate4;
    private Date epDate5;
    private Date epDate6;
    private Date epDate7;
    private Date epDate8;
    private Date epDate9;
    private Date epDate10;

    public AccessEpisodesTest() { super(); }

    @Before
    public void setUp()
    {
        Services.closeDataAccess();
        Services.createDataAccess(new StubData(dbName));

        ae = new AccessEpisodes();
        ac = new AccessChannels();
        channelList = new ChannelList();
        ac.getChannels(channelList);

        chDate4 = new Date(2015, 3,12);

        epDate1 = new Date(2018, 1, 12);
        epDate2 = new Date(2016, 7, 27);
        epDate3 = new Date(2017, 12, 27);
        epDate4 = new Date(2017, 12, 14);
        epDate5 = new Date(2017, 6, 23);
        epDate6 = new Date(2016, 10, 21);
        epDate7 = new Date(2017, 2, 25);
        epDate8 = new Date(2015, 8, 1);
        epDate9 = new Date(2015, 5, 8);
        epDate10 = new Date(2015, 2, 24);

        channel5 = new Channel("Seafood Mania", "Chickens only",
                "https://seafoodmania.com/", chDate4, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com");

        episode1 = new Episode("JRE MMA Show #10 with Tyron Woodley", "http://traffic.libsyn.com/joeroganexp/mmashow010.mp3?dest-id=19997",
                "Joe Rogan sits down with UFC Welterweight Champion Tyron Woodley", 6120, channelList.get(0), epDate1, "Joe Rogan", "Sports", 10);
        episode2 = new Episode("#990 - Jamie Foxx", "http://traffic.libsyn.com/joeroganexp/p990.mp3?dest-id=19997",
                "Jamie Foxx is an Academy Award winning actor, singer, and comedian. He can currently be seen hosting \"Beat Shazam\" on Fox and " +
                        "in the movie \"Baby Driver\" in theaters now.", 4080, channelList.get(0), epDate2, "Joe Rogan", "Comedy", 990);
        episode3 = new Episode("#890 - Fight Breakdown", "http://traffic.libsyn.com/joeroganexp/p890.mp3?dest-id=19997",
                "Joe sits down with Eddie Bravo & Brendan Schaub to discuss upcoming fights in MMA", 13740, channelList.get(0) , epDate3, "Joe Rogan", "Sports", 890);
        episode4 = new Episode("State of the Philadelphia 76ers", "http://media.adknit.com/a/f/13/nba-hangtime/xpe2c8.1-1.mp3",
                "Sixers insider Jessica Camerato of NBC Sports Philadelphia joins us to dig deep into the state of the 76ers. We talk about Joel Embiid, " +
                        "Ben Simmons, chemistry, playoff hopes, Jahlil Okafor trade, and much more. Then John Schuhmann calls in with his weekly trivia question.",
                2220, channelList.get(1), epDate4, "Sekou Smith", "Sports", 82);
        episode5 = new Episode("2017 NBA Draft: Instant Analysis", "http://media.adknit.com/a/f/13/nba-hangtime/rl1o6s.1-1.mp3",
                "Lang Whitaker, John Schuhmann, Scott-Howard Cooper and Trey Kerby recap Thursday's draft.", 3600, channelList.get(1), epDate5, "Sekou Smith", "Sports", 61);
        episode6 = new Episode("More or Less? Predicting Win Totals For All 30 Nba Teams", "http://media.adknit.com/a/f/13/nba-hangtime/htwxqy.1-1.mp3",
                "Which NBA teams will be better this season? Which will be worse? Sekou Smith and Lang Whitaker predict how many games each team will win " +
                        "during the 2016-17 NBA season.", 2640, channelList.get(1), epDate6,
                "Sekou Smith", "Sports", 15);
        episode7 = new Episode("096 - Progress", "http://feeds.soundcloud.com/stream/309444172-youarenotsosmart-096-progress.mp3",
                "Do we have the power to change the outcome of history? Is progress inevitable? Is it natural? Are we headed somewhere definite, or is " +
                        "change just chaos that seems organized in hindsight? In this episode we explore these questions with University of Chicago historian " +
                        "Ada Palmer", 3900, channelList.get(2), epDate7, "David McRaney",
                "Social Sciences", 96);
        episode8 = new Episode("055 - Weird People - Steven J. Heine", "http://feeds.soundcloud.com/stream/217283672-youarenotsosmart-055-weird-people-steven-j-heine.mp3",
                "JIs psychology too WEIRD? That's what this episode's guest, psychologist Steven J. Heine suggested when he and his colleagues published a " +
                        "paper suggesting that psychology wasn't the study of the human mind, but the study of one kind of human mind, the sort generated by the " +
                        "kinds of brains that happen to be conveniently located near the places where research is usually conducted - North American college " +
                        "undergraduates.", 2880, channelList.get(2), epDate8, "David McRaney",
                "Social Sciences", 55);
        episode9 = new Episode("049 - Rejection - Jia Jiang", "http://feeds.soundcloud.com/stream/204471744-youarenotsosmart-049-rejection-jia-jiang.mp3",
                "What if you could give yourself a superpower? That's what Jia Jiang wondered when he began a quest to remove the fear of rejection from his " +
                        "brain and become the risk-taking, adventurous person he always wanted to be.", 3240, channelList.get(2) ,epDate9, "David McRaney", "Social Sciences", 48);
        episode10 = new Episode("Seafood Mania - Lobstah Madness", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "We talk about lobster and lobster only", 3900, channel5, epDate10, "Joe Jones",
                "Food",13 );
        episode11 = new Episode("Seafood Mania - Lobstah Madness", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "CRABBBBBBS", 3900, channel5, epDate10, "Joe Jones",
                "Food",13 );
        episode12 = new Episode("Gimme dem beers", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "All day baby", 3900, channelList.get(0), epDate10, "Joe Jones",
                "Food",13 );
        episode13 = new Episode("Gimme dem beers", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "For the boys", 3900, channelList.get(0), epDate10, "Joe Jones",
                "Food",13 );

        episodeList = new EpisodeList();
        episodeList.add(episode1);
        episodeList.add(episode2);
        episodeList.add(episode3);
        episodeList.add(episode4);
        episodeList.add(episode5);
        episodeList.add(episode6);
        episodeList.add(episode7);
        episodeList.add(episode8);
        episodeList.add(episode9);
    }

    @Test
    public void testInsertEpisode()
    {
        System.out.println("\nStarting AccessEpisodesTest: InsertEpisode");
        resultEp = new EpisodeList();
        Episode ep = null;

        // test retrieving the episodes from the database
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test inserting a episodes
        episodeList.add(episode10);
        ae.insertEpisode(episode10);
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test inserting multiple of episodes
        episodeList.add(episode11);
        ae.deleteEpisode(episode3);
        ae.deleteEpisode(episode4);
        ae.insertEpisode(episode3);
        ae.insertEpisode(episode4);
        ae.insertEpisode(episode11);
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test inserting duplicate episode
        ae.insertEpisode(episode10);
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test inserting a null episode
        try {
            ae.insertEpisode(ep);
            ae.getEpisodes(resultEp);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        System.out.println("Finished AccessEpisodesTest: InsertEpisode");
    }

    @Test
    public void testDeleteEpisode()
    {
        System.out.println("\nStarting AccessEpisodesTest: DeleteEpisode");
        resultEp = new EpisodeList();

        episodeList.add(episode10);

        // test deleting a episode
        episodeList.remove(episode10);
        ae.insertEpisode(episode10);
        ae.deleteEpisode(episode10);
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test deleting multiple episodes
        episodeList.remove(episode1);
        episodeList.remove(episode2);
        ae.deleteEpisode(episode1);
        ae.deleteEpisode(episode2);
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test deleting a episode that does not exist
        ae.deleteEpisode(episode13);
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));

        System.out.println("Finished AccessEpisodesTest: DeleteEpisode");
    }

    @Test
    public void testUpdateEpisode()
    {
        System.out.println("\nStarting AccessEpisodesTest: UpdateEpisode");
        resultEp = new EpisodeList();

        episodeList.add(episode10);
        ae.insertEpisode(episode10);

        // test updating a episode
        ae.updateEpisode(episode11);
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));
        assertEquals(resultEp.get(resultEp.size()-1).getDesc(), episode11.getDesc());

        // test updating a episode that does not exist
        ae.updateEpisode(episode13);
        ae.getEpisodes(resultEp);
        assertEquals(resultEp.get(resultEp.size()-1).getDesc(), episode11.getDesc());

        // test updating multiple channels
        episodeList.add(episode12);
        ae.insertEpisode(episode12);
        ae.updateEpisode(episode13);
        ae.updateEpisode(episode10);
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));
        assertEquals(resultEp.get(resultEp.size()-1).getDesc(), episode13.getDesc());
        assertEquals(resultEp.get(resultEp.size()-2).getDesc(), episode10.getDesc());

        System.out.println("Finished AccessEpisodesTest: UpdateEpisode");
    }

    @Test
    public void testChannelEpisodeList()
    {
        System.out.println("\nStarting AccessEpisodesTest: ChannelEpisodeList");
        resultEp = new EpisodeList();
        channelEpisodesList = new EpisodeList();
        channelEpisodesList.add(episode1);
        channelEpisodesList.add(episode2);
        channelEpisodesList.add(episode3);

        // test retrieving the episodes based from a channel
        ae.getChannelEpisodes(resultEp, channelList.get(0));
        assertTrue(resultEp.equals(channelEpisodesList));

        // test retrieving the episodes based from a channel that does not exist
        ae.getChannelEpisodes(resultEp, channel5);
        channelEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(channelEpisodesList));

        System.out.println("Finished AccessEpisodesTest: ChannelEpisodeList");
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
    }
}
