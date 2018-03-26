package comp3350.podcast.integration;

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

import static org.junit.Assert.*;

public class BusinessPersistenceSeamTest
{
    private AccessChannels ac;
    private AccessEpisodes ae;
    private Channel channel1;
    private Channel channel2;
    private Channel channel3;
    private Episode episode1;
    private Episode episode2;
    private Episode episode3;
    private Episode episode4;
    private Episode episode5;
    private Episode episode6;
    private Episode episode7;
    private Episode episode8;
    private Episode episode9;
    private Date chDate1;
    private Date chDate2;
    private Date chDate3;
    private Date epDate1;
    private Date epDate2;
    private Date epDate3;
    private Date epDate4;
    private Date epDate5;
    private Date epDate6;
    private Date epDate7;
    private Date epDate8;
    private Date epDate9;

    public BusinessPersistenceSeamTest() { super(); }

    @Before
    public void setUp()
    {
        Services.closeDataAccess();
        Services.createDataAccess(Main.dbName);

        ac = new AccessChannels();
        ae = new AccessEpisodes();

        chDate1 = new Date(2009, 12, 24);
        chDate2 = new Date(2016, 2, 17);
        chDate3 = new Date(2012, 4, 22);

        epDate1 = new Date(2018, 1, 12);
        epDate2 = new Date(2016, 7, 27);
        epDate3 = new Date(2016, 12, 27);
        epDate4 = new Date(2017, 12, 14);
        epDate5 = new Date(2017, 6, 23);
        epDate6 = new Date(2016, 10, 21);
        epDate7 = new Date(2017, 2, 25);
        epDate8 = new Date(2015, 8, 1);
        epDate9 = new Date(2015, 5, 8);

        channel1 = new Channel("The Joe Rogan Experience", "The Joe Rogan Experience podcast is a long form conversation hosted by comedian, " +
                "UFC color commentator, and actor Joe Rogan with friends and guests that have included comedians, actors, musicians, MMA instructors and " +
                "commentators, authors, artists, and porn stars.", "http://joerogan.net/podcasts/", chDate1, "Joe Rogan", "Comedy", "Brian Redban", "BOOKDEATHSQUAD@GMAIL.COM");
        channel2 = new Channel("NBA Hang Time", "Veteran NBA writer Sekou Smith and former player Greg Anthony analyze the latest NBA news, " +
                "storylines, and more with guests from around the NBA world.", "http://www.nba.com/podcast#/", chDate2, "Sekou Smith", "Sports", "NBA Digital", "@NBA");
        channel3 = new Channel("You Are Not So Smart", "You Are Not So Smart is a celebration of self delusion that explores topics related to " +
                "cognitive biases, heuristics, and logical fallacies. David McRaney interviews scientists about their research into how the mind works, " +
                "and then he eats a cookie", "https://youarenotsosmart.com/", chDate3, "David McRaney", "Social Sciences", "David McRaney", "davidmcraney@gmail.com");

        episode1 = new Episode("JRE MMA Show #10 with Tyron Woodley", "http://traffic.libsyn.com/joeroganexp/mmashow010.mp3?dest-id=19997",
                "Joe Rogan sits down with UFC Welterweight Champion Tyron Woodley", 6120, channel1, epDate1, "Joe Rogan", "Sports", 10);
        episode2 = new Episode("#990 - Jamie Foxx", "http://traffic.libsyn.com/joeroganexp/p990.mp3?dest-id=19997",
                "Jamie Foxx is an Academy Award winning actor, singer, and comedian. He can currently be seen hosting \"Beat Shazam\" on Fox and " +
                        "in the movie \"Baby Driver\" in theaters now.", 4080, channel1, epDate2, "Joe Rogan", "Comedy", 990);
        episode3 = new Episode("#890 - Fight Breakdown", "http://traffic.libsyn.com/joeroganexp/p890.mp3?dest-id=19997",
                "Joe sits down with Eddie Bravo & Brendan Schaub to discuss upcoming fights in MMA", 13740, channel1 , epDate3, "Joe Rogan", "Sports", 890);
        episode4 = new Episode("State of the Philadelphia 76ers", "http://media.adknit.com/a/f/13/nba-hangtime/xpe2c8.1-1.mp3",
                "Sixers insider Jessica Camerato of NBC Sports Philadelphia joins us to dig deep into the state of the 76ers. We talk about Joel Embiid, " +
                        "Ben Simmons, chemistry, playoff hopes, Jahlil Okafor trade, and much more. Then John Schuhmann calls in with his weekly trivia question.",
                2220, channel2, epDate4, "Sekou Smith", "Sports", 82);
        episode5 = new Episode("2017 NBA Draft: Instant Analysis", "http://media.adknit.com/a/f/13/nba-hangtime/rl1o6s.1-1.mp3",
                "Lang Whitaker, John Schuhmann, Scott-Howard Cooper and Trey Kerby recap Thursday's draft.", 3600, channel2, epDate5, "Sekou Smith", "Sports", 61);
        episode6 = new Episode("More or Less? Predicting Win Totals For All 30 Nba Teams", "http://media.adknit.com/a/f/13/nba-hangtime/htwxqy.1-1.mp3",
                "Which NBA teams will be better this season? Which will be worse? Sekou Smith and Lang Whitaker predict how many games each team will win " +
                        "during the 2016-17 NBA season.", 2640, channel2, epDate6,
                "Sekou Smith", "Sports", 15);
        episode7 = new Episode("096 - Progress", "http://feeds.soundcloud.com/stream/309444172-youarenotsosmart-096-progress.mp3",
                "Do we have the power to change the outcome of history? Is progress inevitable? Is it natural? Are we headed somewhere definite, or is " +
                        "change just chaos that seems organized in hindsight? In this episode we explore these questions with University of Chicago historian " +
                        "Ada Palmer", 3900, channel3, epDate7, "David McRaney",
                "Social Sciences", 96);
        episode8 = new Episode("055 - Weird People - Steven J. Heine", "http://feeds.soundcloud.com/stream/217283672-youarenotsosmart-055-weird-people-steven-j-heine.mp3",
                "JIs psychology too WEIRD? That's what this episode's guest, psychologist Steven J. Heine suggested when he and his colleagues published a " +
                        "paper suggesting that psychology wasn't the study of the human mind, but the study of one kind of human mind, the sort generated by the " +
                        "kinds of brains that happen to be conveniently located near the places where research is usually conducted - North American college " +
                        "undergraduates.", 2880, channel3, epDate8, "David McRaney",
                "Social Sciences", 55);
        episode9 = new Episode("049 - Rejection - Jia Jiang", "http://feeds.soundcloud.com/stream/204471744-youarenotsosmart-049-rejection-jia-jiang.mp3",
                "What if you could give yourself a superpower? That's what Jia Jiang wondered when he began a quest to remove the fear of rejection from his " +
                        "brain and become the risk-taking, adventurous person he always wanted to be.", 3240, channel3 ,epDate9, "David McRaney", "Social Sciences", 48);
    }

    @Test
    public void testAccessChannels()
    {
        System.out.println("Starting Integration test of AccessChannels to persistence");

        ChannelList sortedByTitleChannelList = new ChannelList();
        ChannelList sortedByDateChannelList = new ChannelList();
        ChannelList channelList = new ChannelList();
        ChannelList resultCh = new ChannelList();

        channelList.add(channel1);
        channelList.add(channel2);
        channelList.add(channel3);

        sortedByTitleChannelList.add(channel2);
        sortedByTitleChannelList.add(channel1);
        sortedByTitleChannelList.add(channel3);

        sortedByDateChannelList.add(channel1);
        sortedByDateChannelList.add(channel3);
        sortedByDateChannelList.add(channel2);

        // get channels -> check info -> sort -> check info
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));
        assertEquals(resultCh.get(0).getTitle(),"NBA Hang Time" );
        assertEquals(resultCh.get(0).getPublishDate(), chDate2 );
        assertEquals(resultCh.get(0).getUrl(), "http://www.nba.com/podcast#/" );
        ac.sortChannel(resultCh, "date");
        assertTrue(resultCh.equals(sortedByDateChannelList));
        assertEquals(resultCh.get(0).getTitle(), "The Joe Rogan Experience");
        assertEquals(resultCh.get(0).getPublishDate(), chDate1 );
        assertEquals(resultCh.get(0).getAuthor(), "Joe Rogan" );

        // get channels -> multiple sorts -> check info
        ac.getChannels(resultCh);
        assertTrue(resultCh.equals(channelList));
        ac.sortChannel(resultCh, "date");
        ac.sortChannel(resultCh, "title");
        ac.sortChannel(resultCh, "date");
        assertTrue(resultCh.equals(sortedByDateChannelList));
        assertEquals(resultCh.get(0).getTitle(), "The Joe Rogan Experience");
        assertEquals(resultCh.get(0).getPublishDate(), chDate1 );
        assertEquals(resultCh.get(0).getAuthor(), "Joe Rogan" );

        // get channels -> "wrong" sort -> check info
        ac.getChannels(resultCh);
        ac.sortChannel(resultCh, "apple");
        assertTrue(resultCh.equals(channelList));

        System.out.println("Finished Integration test of AccessChannels to persistence");
    }

    @Test
    public void testAccessEpisodes()
    {
        System.out.println("Starting Integration test of AccessEpisodes to persistence");

        EpisodeList sortedByTitleEpisodeList = new EpisodeList();
        EpisodeList sortedByDateEpisodeList = new EpisodeList();
        EpisodeList sortedByLengthEpisodeList = new EpisodeList();
        EpisodeList episodeList = new EpisodeList();
        EpisodeList resultEp = new EpisodeList();
        EpisodeList channelEpisodesList = new EpisodeList();
        EpisodeList dummy = new EpisodeList();
        Channel ch = new Channel("Seafood Mania", "Chickens only",
                "https://seafoodmania.com/", epDate2, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com");

        episodeList.add(episode1);
        episodeList.add(episode2);
        episodeList.add(episode3);
        episodeList.add(episode4);
        episodeList.add(episode5);
        episodeList.add(episode6);
        episodeList.add(episode7);
        episodeList.add(episode8);
        episodeList.add(episode9);

        sortedByDateEpisodeList.add(episode9);
        sortedByDateEpisodeList.add(episode8);
        sortedByDateEpisodeList.add(episode6);
        sortedByDateEpisodeList.add(episode3);
        sortedByDateEpisodeList.add(episode7);
        sortedByDateEpisodeList.add(episode5);
        sortedByDateEpisodeList.add(episode2);
        sortedByDateEpisodeList.add(episode4);
        sortedByDateEpisodeList.add(episode1);

        sortedByLengthEpisodeList.add(episode4);
        sortedByLengthEpisodeList.add(episode6);
        sortedByLengthEpisodeList.add(episode8);
        sortedByLengthEpisodeList.add(episode9);
        sortedByLengthEpisodeList.add(episode5);
        sortedByLengthEpisodeList.add(episode7);
        sortedByLengthEpisodeList.add(episode2);
        sortedByLengthEpisodeList.add(episode1);
        sortedByLengthEpisodeList.add(episode3);

        channelEpisodesList.add(episode1);
        channelEpisodesList.add(episode2);
        channelEpisodesList.add(episode3);

        // get episodes -> check info -> sort -> check info
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));
        assertEquals(resultEp.get(0).getTitle(), "#890 - Fight Breakdown");
        assertEquals(resultEp.get(0).getPublishDate(), epDate3);
        assertEquals(resultEp.get(0).getEpNum(), 890);
        ae.sortEpisode(resultEp, "date");
        assertTrue(resultEp.equals(sortedByDateEpisodeList));
        assertEquals(resultEp.get(0).getTitle(), "049 - Rejection - Jia Jiang");
        assertEquals(resultEp.get(0).getPublishDate(), epDate9);
        assertEquals(resultEp.get(0).getCategory(), "Social Sciences");

        // get episodes -> check info -> multiple sorts -> check info
        ae.getEpisodes(resultEp);
        assertTrue(resultEp.equals(episodeList));
        ae.sortEpisode(resultEp, "length");
        ae.sortEpisode(resultEp, "date");
        ae.sortEpisode(resultEp, "date");
        assertTrue(resultEp.equals(sortedByLengthEpisodeList));
        assertEquals(resultEp.get(0).getTitle(), "049 - Rejection - Jia Jiang");
        assertEquals(resultEp.get(0).getPublishDate(), epDate9);
        assertEquals(resultEp.get(0).getCategory(), "Social Sciences");

        // get episodes -> "wrong" sort -> check info
        ae.getEpisodes(resultEp);
        ae.sortEpisode(resultEp, "soup");
        assertTrue(resultEp.equals(episodeList));

        sortedByTitleEpisodeList.add(episode3);
        sortedByTitleEpisodeList.add(episode2);
        sortedByTitleEpisodeList.add(episode1);

        // get episodes based from channel -> check info -> sort -> check info
        ae.getChannelEpisodes(resultEp, channel1);
        assertTrue(resultEp.equals(channelEpisodesList));
        assertEquals(resultEp.get(0).getTitle(), "#890 - Fight Breakdown");
        assertEquals(resultEp.get(0).getPublishDate(), epDate3);
        assertEquals(resultEp.get(0).getEpNum(), 890);
        ae.sortEpisode(resultEp, "title");
        assertTrue(resultEp.equals(sortedByTitleEpisodeList));
        assertEquals(resultEp.get(0).getTitle(), "#890 - Fight Breakdown");
        assertEquals(resultEp.get(0).getPublishDate(), epDate3);
        assertEquals(resultEp.get(0).getEpNum(), 890);

        sortedByLengthEpisodeList = new EpisodeList();
        sortedByLengthEpisodeList.add(episode2);
        sortedByLengthEpisodeList.add(episode1);
        sortedByLengthEpisodeList.add(episode3);

        // get episodes based from channel -> multiple sorts -> check info
        ae.getChannelEpisodes(resultEp, channel1);
        assertTrue(resultEp.equals(channelEpisodesList));
        ae.sortEpisode(resultEp, "date");
        ae.sortEpisode(resultEp, "title");
        ae.sortEpisode(resultEp, "length");
        assertTrue(resultEp.equals(sortedByLengthEpisodeList));
        assertEquals(resultEp.get(0).getTitle(), "#990 - Jamie Foxx");

        // get episodes based from  a non-existing channel -> multiple sorts -> check info
        ae.getChannelEpisodes(resultEp, ch);
        ae.sortEpisode(resultEp, "date");
        ae.sortEpisode(resultEp, "title");
        ae.sortEpisode(resultEp, "length");
        assertTrue(resultEp.equals(dummy));

        System.out.println("Finished Integration test of AccessEpisodes to persistence");
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
        System.out.println();
    }


}
