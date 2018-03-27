package comp3350.podcast.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.ChannelList;
import comp3350.podcast.objects.Date;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.EpisodeList;
import comp3350.podcast.objects.Playlist;

import static org.junit.Assert.*;

public class AccessDataTest
{
    private AccessData accessData;
    private ChannelList channelList;
    private ChannelList resultCh;
    private ChannelList playlistChannelList;
    private ChannelList subs;
    private EpisodeList episodeList;
    private EpisodeList channelEpisodesList;
    private EpisodeList resultEp;
    private EpisodeList playlistEpisodesList;
    private ArrayList<Playlist> playlistList;
    private ArrayList<Playlist> resultPl;
    private Channel channel1;
    private Channel channel2;
    private Channel channel3;
    private Channel channel4;
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
    private Date chDate1;
    private Date chDate2;
    private Date chDate3;
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
    private Playlist playlist1;
    private Playlist playlist2;
    private Playlist playlist3;
    private Playlist playlist4;

    public AccessDataTest() { super(); }

    @Before
    public void setUp()
    {
        accessData = new StubData();
        accessData.open("Stub");
        System.out.println("Starting Persistence test AccessData (using stub)");

        initializeExpectedResult();
    }

    private void initializeExpectedResult()
    {
        chDate1 = new Date(2009, 12, 24);
        chDate2 = new Date(2016, 2, 17);
        chDate3 = new Date(2012, 4, 22);
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

        playlist1 = new Playlist("Chill");
        playlist2 = new Playlist("Relax");
        playlist3 = new Playlist("Put a little make up");
        playlist4 = new Playlist("Jaaam");

        channelList = new ChannelList();
        episodeList = new EpisodeList();
        playlistList = new ArrayList<>();
        playlistChannelList = new ChannelList();
        playlistEpisodesList = new EpisodeList();
        subs = new ChannelList();

        channelList.add(channel1);
        channelList.add(channel2);
        channelList.add(channel3);

        episodeList.add(episode1);
        episodeList.add(episode2);
        episodeList.add(episode3);
        episodeList.add(episode4);
        episodeList.add(episode5);
        episodeList.add(episode6);
        episodeList.add(episode7);
        episodeList.add(episode8);
        episodeList.add(episode9);

        playlistChannelList.add(channelList.get(1));
        playlistChannelList.add(channelList.get(2));

        playlistEpisodesList.add(episodeList.get(6));
        playlistEpisodesList.add(episodeList.get(4));
        playlistEpisodesList.add(episodeList.get(0));

        playlistList.add(playlist1);
        playlistList.add(playlist2);

        subs.add(channel2);
    }

    public static void accessDataTest(AccessData accessData)
    {
        AccessDataTest accessDataTest = new AccessDataTest();
        accessDataTest.accessData = accessData;
        accessDataTest.initializeExpectedResult();
        accessDataTest.testChannelPersistence();
        accessDataTest.testEpisodesPersistence();
        accessDataTest.testPlaylistPersistence();
        accessDataTest.testSubPersistence();
    }

    @Test
    public void testChannelPersistence()
    {
        System.out.println("Starting AccessDataTest: ChannelPersistence");

        resultCh = new ChannelList();

        // test retrieving the channels from the database
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));

        System.out.println("Finished AccessDataTest: ChannelPersistence");
    }

    @Test
    public void testEpisodesPersistence()
    {
        System.out.println("Starting AccessDataTest: EpisodesPersistence");

        resultEp = new EpisodeList();
        channelEpisodesList = new EpisodeList();
        channelEpisodesList.add(episode1);
        channelEpisodesList.add(episode2);
        channelEpisodesList.add(episode3);

        // test retrieving the episodes from the database
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test retrieving the episodes based from a channel
        accessData.getChannelEpisodeSequential(resultEp, channelList.get(0));
        assertTrue(resultEp.equals(channelEpisodesList));

        // test retrieving the episodes based from a channel that does not exist
        accessData.getChannelEpisodeSequential(resultEp, channel5);
        channelEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(channelEpisodesList));

        System.out.println("Finished AccessDataTest: EpisodesPersistence");
    }

    @Test
    public void testPlaylistPersistence()
    {
        System.out.println("Starting AccessDataTest: PlaylistPersistence");
        resultPl = new ArrayList<>();
        resultCh = new ChannelList();
        resultEp = new EpisodeList();
        Playlist pl = null;

        // test retrieving the playlists from the database
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting a playlist
        playlistList.add(playlist3);
        accessData.insertPlaylist(playlist3);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test deleting a playlist
        playlistList.remove(playlist3);
        accessData.insertPlaylist(playlist3);
        accessData.deletePlaylist(playlist3);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting multiple playlists
        playlistList.add(playlist3);
        playlistList.add(playlist4);
        accessData.deletePlaylist(playlist3);
        accessData.insertPlaylist(playlist3);
        accessData.insertPlaylist(playlist4);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test deleting multiple playlists
        playlistList.remove(playlist3);
        playlistList.remove(playlist4);
        accessData.deletePlaylist(playlist3);
        accessData.deletePlaylist(playlist4);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting duplicate playlist
        accessData.insertPlaylist(playlist1);
        accessData.getPlaylistSequential(resultPl);

        // test deleting a playlist that does not exist
        accessData.deletePlaylist(playlist3);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting a null playlist
        try {
            accessData.insertPlaylist(pl);
            accessData.getPlaylistSequential(resultPl);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        // test retrieving the channels based from a playlist
        accessData.getPlaylistChannelSequential(resultCh, playlist1);
        assertTrue(resultCh.equals(playlistChannelList));

        // test inserting a channel into a playlist
        playlistChannelList.add(channelList.get(0));
        accessData.insertPlaylistChannel(channelList.get(0), playlist1);
        accessData.getPlaylistChannelSequential(resultCh, playlist1);
        assertTrue(playlistChannelList.equals(resultCh));

        // test inserting duplicate channel into a playlist
        accessData.insertPlaylistChannel(channelList.get(0), playlist1);
        accessData.getPlaylistChannelSequential(resultCh, playlist1);
        assertTrue(playlistChannelList.equals(resultCh));

        // test deleting a channel into a playlist
        playlistChannelList.remove(channelList.get(0));
        accessData.insertPlaylistChannel(channelList.get(0), playlist1);
        accessData.deletePlaylistChannel(channelList.get(0), playlist1);
        accessData.getPlaylistChannelSequential(resultCh, playlist1);
        assertTrue(playlistChannelList.equals(resultCh));

        // test deleting channel into a non existent playlist
        accessData.deletePlaylistChannel(channelList.get(2), playlist3);
        accessData.getPlaylistChannelSequential(resultCh, playlist3);
        playlistChannelList = new ChannelList();
        assertTrue(playlistChannelList.equals(resultCh));

        // test inserting channel into a non existent playlist
        accessData.insertPlaylistChannel(channelList.get(0), playlist3);
        accessData.getPlaylistChannelSequential(resultCh, playlist3);
        playlistChannelList = new ChannelList();
        assertTrue(playlistChannelList.equals(resultCh));

        // test retrieving the episodes based from a playlist
        accessData.getPlaylistEpisodeSequential(resultEp, playlist1);
        assertTrue(resultEp.equals(playlistEpisodesList));

        // test inserting an episode into a playlist
        playlistEpisodesList.add(episodeList.get(1));
        accessData.insertPlaylistEpisode(episodeList.get(1), playlist1);
        accessData.getPlaylistEpisodeSequential(resultEp, playlist1);
        assertTrue(playlistEpisodesList.equals(resultEp));

        // test inserting duplicate episode into a playlist
        accessData.insertPlaylistEpisode(episodeList.get(1), playlist1);
        accessData.getPlaylistEpisodeSequential(resultEp, playlist1);
        assertTrue(playlistEpisodesList.equals(resultEp));

        // test deleting an episode from a playlist
        playlistEpisodesList.remove(episodeList.get(1));
        accessData.insertPlaylistEpisode(episodeList.get(1), playlist1);
        accessData.deletePlaylistEpisode(episodeList.get(1), playlist1);
        accessData.getPlaylistEpisodeSequential(resultEp, playlist1);
        assertTrue(playlistEpisodesList.equals(resultEp));

        // test deleting an episode into a non existent playlist
        accessData.deletePlaylistEpisode(episodeList.get(0), playlist4);
        accessData.getPlaylistEpisodeSequential(resultEp, playlist4);
        playlistEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(playlistEpisodesList));

        // test inserting episode into a non existent playlist
        accessData.insertPlaylistEpisode(episodeList.get(3), playlist4);
        accessData.getPlaylistEpisodeSequential(resultEp, playlist4);
        playlistEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(playlistEpisodesList));

        // test retrieving the channels based from a playlist that does not exist
        accessData.getPlaylistChannelSequential(resultCh, playlist4);
        playlistChannelList = new ChannelList();
        assertTrue(resultCh.equals(playlistChannelList));

        // test retrieving the episodes based from a playlist that does not exist
        accessData.getPlaylistEpisodeSequential(resultEp, playlist4);
        playlistEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(playlistEpisodesList));

        System.out.println("Finished AccessDataTest: PlaylistPersistence");
    }

    @Test
    public void testSubPersistence()
    {
        System.out.println("Starting AccessDataTest: SubPersistence");
        resultCh = new ChannelList();

        // test retrieving the subscriptions from the database
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));

        // test inserting a subscription
        subs.add(channel3);
        accessData.insertSub(channel3);
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));

        // test deleting a subscription
        subs.remove(channel3);
        accessData.insertSub(channel3);
        accessData.deleteSub(channel3);
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));

        // test inserting duplicate subscription
        accessData.insertSub(channel2);
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));

        // test deleting a subscription that does not exist
        accessData.deleteSub(channel4);
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));

        System.out.println("Finished AccessDataTest: SubPersistence");
    }

    private boolean comparePlaylistLists(ArrayList<Playlist> obj, ArrayList<Playlist> actual)
    {
        if (obj.size() == actual.size()) {
            Iterator iter = actual.iterator();

            while (iter.hasNext()) {
                if (!obj.contains((Playlist) iter.next()))
                    return false;
            }
        } else
            return false;

        return true;
    }

    @After
    public void tearDown()
    {
        System.out.println("Finished Persistence test AccessData (using stub)");
        accessData.close();
        System.out.println();
    }
}
