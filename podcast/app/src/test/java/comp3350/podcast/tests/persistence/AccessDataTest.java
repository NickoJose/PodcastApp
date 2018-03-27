package comp3350.podcast.tests.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.ChannelList;
import comp3350.podcast.objects.Date;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.EpisodeList;
import comp3350.podcast.objects.Playlist;
import comp3350.podcast.persistence.AccessData;

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
    private Channel channel6;
    private Channel channel7;
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
    private Date epDate10;
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
        epDate10 = new Date(2015, 2, 24);

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
        channel5 = new Channel("Seafood Mania", "Chickens only",
                "https://seafoodmania.com/", chDate4, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com","");
        channel6 = new Channel("Gimme dem beers", "All day baby",
                "https://seafoodmania.com/", chDate4, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com","");
        channel7 = new Channel("Gimme dem beers", "For the boys",
                "https://seafoodmania.com/", chDate4, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com","");

        episode1 = new Episode("JRE MMA Show #10 with Tyron Woodley", "http://traffic.libsyn.com/joeroganexp/mmashow010.mp3?dest-id=19997",
                "Joe Rogan sits down with UFC Welterweight Champion Tyron Woodley", 6120, channel1, epDate1, "Joe Rogan", "Sports", 10,"");
        episode2 = new Episode("#990 - Jamie Foxx", "http://traffic.libsyn.com/joeroganexp/p990.mp3?dest-id=19997",
                "Jamie Foxx is an Academy Award winning actor, singer, and comedian. He can currently be seen hosting \"Beat Shazam\" on Fox and " +
                        "in the movie \"Baby Driver\" in theaters now.", 4080, channel1, epDate2, "Joe Rogan", "Comedy", 990,"");
        episode3 = new Episode("#890 - Fight Breakdown", "http://traffic.libsyn.com/joeroganexp/p890.mp3?dest-id=19997",
                "Joe sits down with Eddie Bravo & Brendan Schaub to discuss upcoming fights in MMA", 13740, channel1 , epDate3, "Joe Rogan", "Sports", 890,"");
        episode4 = new Episode("State of the Philadelphia 76ers", "http://media.adknit.com/a/f/13/nba-hangtime/xpe2c8.1-1.mp3",
                "Sixers insider Jessica Camerato of NBC Sports Philadelphia joins us to dig deep into the state of the 76ers. We talk about Joel Embiid, " +
                        "Ben Simmons, chemistry, playoff hopes, Jahlil Okafor trade, and much more. Then John Schuhmann calls in with his weekly trivia question.",
                2220, channel2, epDate4, "Sekou Smith", "Sports", 82,"");
        episode5 = new Episode("2017 NBA Draft: Instant Analysis", "http://media.adknit.com/a/f/13/nba-hangtime/rl1o6s.1-1.mp3",
                "Lang Whitaker, John Schuhmann, Scott-Howard Cooper and Trey Kerby recap Thursday's draft.", 3600, channel2, epDate5, "Sekou Smith", "Sports", 61,"");
        episode6 = new Episode("More or Less? Predicting Win Totals For All 30 Nba Teams", "http://media.adknit.com/a/f/13/nba-hangtime/htwxqy.1-1.mp3",
                "Which NBA teams will be better this season? Which will be worse? Sekou Smith and Lang Whitaker predict how many games each team will win " +
                        "during the 2016-17 NBA season.", 2640, channel2, epDate6,
                "Sekou Smith", "Sports", 15,"");
        episode7 = new Episode("096 - Progress", "http://feeds.soundcloud.com/stream/309444172-youarenotsosmart-096-progress.mp3",
                "Do we have the power to change the outcome of history? Is progress inevitable? Is it natural? Are we headed somewhere definite, or is " +
                        "change just chaos that seems organized in hindsight? In this episode we explore these questions with University of Chicago historian " +
                        "Ada Palmer", 3900, channel3, epDate7, "David McRaney",
                "Social Sciences", 96,"");
        episode8 = new Episode("055 - Weird People - Steven J. Heine", "http://feeds.soundcloud.com/stream/217283672-youarenotsosmart-055-weird-people-steven-j-heine.mp3",
                "JIs psychology too WEIRD? That's what this episode's guest, psychologist Steven J. Heine suggested when he and his colleagues published a " +
                        "paper suggesting that psychology wasn't the study of the human mind, but the study of one kind of human mind, the sort generated by the " +
                        "kinds of brains that happen to be conveniently located near the places where research is usually conducted - North American college " +
                        "undergraduates.", 2880, channel3, epDate8, "David McRaney",
                "Social Sciences", 55,"");
        episode9 = new Episode("049 - Rejection - Jia Jiang", "http://feeds.soundcloud.com/stream/204471744-youarenotsosmart-049-rejection-jia-jiang.mp3",
                "What if you could give yourself a superpower? That's what Jia Jiang wondered when he began a quest to remove the fear of rejection from his " +
                        "brain and become the risk-taking, adventurous person he always wanted to be.", 3240, channel3 ,epDate9, "David McRaney", "Social Sciences", 48,"");
        episode10 = new Episode("Seafood Mania - Lobstah Madness", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "We talk about lobster and lobster only", 3900, channel5, epDate10, "Joe Jones",
                "Food",13,"" );
        episode11 = new Episode("Seafood Mania - Lobstah Madness", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "CRABBBBBBS", 3900, channel5, epDate10, "Joe Jones",
                "Food",13,"" );
        episode12 = new Episode("Gimme dem beers", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "All day baby", 3900, channel1, epDate10, "Joe Jones",
                "Food",13,"" );
        episode13 = new Episode("Gimme dem beers", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "For the boys", 3900, channel1, epDate10, "Joe Jones",
                "Food",13,"" );

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

        subs.add(channel1);
    }

    @Test
    public void testInsertChannel()
    {
        System.out.println("\nStarting AccessDataTest: InsertChannel");
        resultCh = new ChannelList();
        Channel ch = null;

        // test retrieving the channels from the database
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test inserting a channel
        channelList.add(channel4);
        accessData.insertChannel(channel4);
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test inserting multiple of channels
        channelList.add(channel6);
        accessData.deleteChannel(channel3);
        accessData.deleteChannel(channel4);
        accessData.insertChannel(channel3);
        accessData.insertChannel(channel4);
        accessData.insertChannel(channel6);
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test inserting duplicate channel
        accessData.insertChannel(channel4);
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test inserting a null channel
        try {
            accessData.insertChannel(ch);
            accessData.getChannelSequential(resultCh);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        System.out.println("Finished AccessDataTest: InsertChannel");
    }

    @Test
    public void testDeleteChannel()
    {
        System.out.println("\nStarting AccessDataTest: DeleteChannel");
        resultCh = new ChannelList();

        channelList.add(channel4);

        // test deleting a channel
        channelList.remove(channel4);
        accessData.insertChannel(channel4);
        accessData.deleteChannel(channel4);
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test deleting multiple channels
        channelList.remove(channel3);
        channelList.remove(channel2);
        accessData.deleteChannel(channel3);
        accessData.deleteChannel(channel2);
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));

        // test deleting a channel that does not exist
        accessData.deleteChannel(channel4);
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));
        System.out.println("Finished AccessDataTest: DeleteChannel");
    }

    @Test
    public void testUpdateChannel()
    {
        System.out.println("\nStarting AccessDataTest: UpdateChannel");
        resultCh = new ChannelList();

        channelList.add(channel4);
        accessData.insertChannel(channel4);

        // test updating a channel
        accessData.updateChannel(channel5);
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));
        assertEquals(resultCh.get(resultCh.size()-1).getDesc(), channel5.getDesc());

        // test updating a channel that does not exist
        accessData.updateChannel(channel7);
        accessData.getChannelSequential(resultCh);
        assertEquals(resultCh.get(resultCh.size()-1).getDesc(), channel5.getDesc());

        // test updating multiple channels
        channelList.add(channel6);
        accessData.insertChannel(channel6);
        accessData.updateChannel(channel7);
        accessData.updateChannel(channel4);
        accessData.getChannelSequential(resultCh);
        assertTrue(resultCh.equals(channelList));
        assertEquals(resultCh.get(resultCh.size()-1).getDesc(), channel7.getDesc());
        assertEquals(resultCh.get(resultCh.size()-2).getDesc(), channel4.getDesc());
        System.out.println("Finished AccessDataTest: UpdateChannel");
    }

    @Test
    public void testInsertEpisode()
    {
        System.out.println("\nStarting AccessDataTest: InsertEpisode");
        resultEp = new EpisodeList();
        Episode ep = null;

        // test retrieving the episodes from the database
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test inserting a episodes
        episodeList.add(episode10);
        accessData.insertEpisode(episode10);
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test inserting multiple of episodes
        episodeList.add(episode11);
        accessData.deleteEpisode(episode3);
        accessData.deleteEpisode(episode4);
        accessData.insertEpisode(episode3);
        accessData.insertEpisode(episode4);
        accessData.insertEpisode(episode11);
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test inserting duplicate episode
        accessData.insertEpisode(episode10);
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test inserting a null episode
        try {
            accessData.insertEpisode(ep);
            accessData.getEpisodesSequential(resultEp);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        System.out.println("Finished AccessDataTest: InsertEpisode");
    }

    @Test
    public void testDeleteEpisode()
    {
        System.out.println("\nStarting AccessDataTest: DeleteEpisode");
        resultEp = new EpisodeList();

        episodeList.add(episode10);

        // test deleting a episode
        episodeList.remove(episode10);
        accessData.insertEpisode(episode10);
        accessData.deleteEpisode(episode10);
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test deleting multiple episodes
        episodeList.remove(episode1);
        episodeList.remove(episode2);
        accessData.deleteEpisode(episode1);
        accessData.deleteEpisode(episode2);
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));

        // test deleting a episode that does not exist
        accessData.deleteEpisode(episode13);
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));

        System.out.println("Finished AccessDataTest: DeleteEpisode");
    }

    @Test
    public void testUpdateEpisode()
    {
        System.out.println("\nStarting AccessDataTest: UpdateEpisode");
        resultEp = new EpisodeList();

        episodeList.add(episode10);
        accessData.insertEpisode(episode10);

        // test updating a episode
        accessData.updateEpisode(episode11);
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));
        assertEquals(resultEp.get(resultEp.size()-1).getDesc(), episode11.getDesc());

        // test updating a episode that does not exist
        accessData.updateEpisode(episode13);
        accessData.getEpisodesSequential(resultEp);
        assertEquals(resultEp.get(resultEp.size()-1).getDesc(), episode11.getDesc());

        // test updating multiple channels
        episodeList.add(episode12);
        accessData.insertEpisode(episode12);
        accessData.updateEpisode(episode13);
        accessData.updateEpisode(episode10);
        accessData.getEpisodesSequential(resultEp);
        assertTrue(resultEp.equals(episodeList));
        assertEquals(resultEp.get(resultEp.size()-1).getDesc(), episode13.getDesc());
        assertEquals(resultEp.get(resultEp.size()-2).getDesc(), episode10.getDesc());

        System.out.println("Finished AccessDataTest: UpdateEpisode");
    }

    @Test
    public void testChannelEpisodeList()
    {
        System.out.println("\nStarting AccessDataTest: ChannelEpisodeList");
        resultEp = new EpisodeList();
        channelEpisodesList = new EpisodeList();
        channelEpisodesList.add(episode1);
        channelEpisodesList.add(episode2);
        channelEpisodesList.add(episode3);

        // test retrieving the episodes based from a channel
        accessData.getChannelEpisodeSequential(resultEp, channelList.get(0));
        assertTrue(resultEp.equals(channelEpisodesList));

        // test retrieving the episodes based from a channel that does not exist
        accessData.getChannelEpisodeSequential(resultEp, channel5);
        channelEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(channelEpisodesList));

        System.out.println("Finished AccessDataTest: ChannelEpisodeList");
    }

    @Test
    public void testInsertPlaylist()
    {
        System.out.println("\nStarting AccessDataTest: InsertPlaylist");
        resultPl = new ArrayList<>();
        Playlist pl = null;

        // test retrieving the playlists from the database
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting a playlist
        playlistList.add(playlist3);
        accessData.insertPlaylist(playlist3);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting multiple playlists
        playlistList.add(playlist4);
        accessData.deletePlaylist(playlist3);
        accessData.insertPlaylist(playlist3);
        accessData.insertPlaylist(playlist4);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting duplicate playlist
        accessData.insertPlaylist(playlist1);
        accessData.getPlaylistSequential(resultPl);

        // test inserting a null playlist
        try {
            accessData.insertPlaylist(pl);
            accessData.getPlaylistSequential(resultPl);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        System.out.println("Finished AccessDataTest: InsertPlaylist");
    }

    @Test
    public void testDeletePlaylist()
    {
        System.out.println("\nStarting AccessDataTest: DeletePlaylist");
        resultPl = new ArrayList<>();

        playlistList.add(playlist3);

        // test deleting a channel
        playlistList.remove(playlist3);
        accessData.insertPlaylist(playlist3);
        accessData.deletePlaylist(playlist3);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test deleting multiple channels
        playlistList.remove(playlist1);
        playlistList.remove(playlist2);
        accessData.deletePlaylist(playlist1);
        accessData.deletePlaylist(playlist2);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test deleting a channel that does not exist
        accessData.deletePlaylist(playlist1);
        accessData.getPlaylistSequential(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));
        System.out.println("Finished AccessDataTest: DeletePlaylist");
    }

    @Test
    public void testInsertPlaylistChannel()
    {
        System.out.println("\nStarting AccessDataTest: InsertPlaylistChannel");
        resultCh = new ChannelList();

        // test inserting a channel into a playlist
        playlistChannelList.add(channelList.get(0));
        accessData.insertPlaylistChannel(channelList.get(0), playlist1);
        accessData.getPlaylistChannelSequential(resultCh, playlist1);
        assertTrue(playlistChannelList.equals(resultCh));

        // test inserting duplicate channel into a playlist
        accessData.insertPlaylistChannel(channelList.get(0), playlist1);
        accessData.getPlaylistChannelSequential(resultCh, playlist1);
        assertTrue(playlistChannelList.equals(resultCh));

        // test inserting channel into a non existent playlist
        accessData.insertPlaylistChannel(channelList.get(0), playlist3);
        accessData.getPlaylistChannelSequential(resultCh, playlist3);
        playlistChannelList = new ChannelList();
        assertTrue(playlistChannelList.equals(resultCh));

        System.out.println("Finished AccessDataTest: InsertPlaylistChannel");
    }

    @Test
    public void testInsertPlaylistEpisode()
    {
        System.out.println("\nStarting AccessDataTest: InsertPlaylistEpisode");
        resultEp = new EpisodeList();

        // test inserting an episode into a playlist
        playlistEpisodesList.add(episodeList.get(1));
        accessData.insertPlaylistEpisode(episodeList.get(1), playlist1);
        accessData.getPlaylistEpisodeSequential(resultEp, playlist1);
        assertTrue(playlistEpisodesList.equals(resultEp));

        // test inserting duplicate episode into a playlist
        accessData.insertPlaylistEpisode(episodeList.get(1), playlist1);
        accessData.getPlaylistEpisodeSequential(resultEp, playlist1);
        assertTrue(playlistEpisodesList.equals(resultEp));

        // test inserting episode into a non existent playlist
        accessData.insertPlaylistEpisode(episodeList.get(3), playlist4);
        accessData.getPlaylistEpisodeSequential(resultEp, playlist4);
        playlistEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(playlistEpisodesList));

        System.out.println("Finished AccessDataTest: InsertPlaylistEpisode");
    }

    @Test
    public void testDeletePlaylistChannel()
    {
        System.out.println("\nStarting AccessDataTest: DeletePlaylistChannel");
        resultCh = new ChannelList();

        playlistChannelList.add(channelList.get(0));

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
        System.out.println("Finished AccessDataTest: DeletePlaylistChannel");
    }

    @Test
    public void testDeletePlaylistEpisode()
    {
        System.out.println("\nStarting AccessDataTest: DeletePlaylistEpisode");
        resultEp = new EpisodeList();

        playlistEpisodesList.add(episodeList.get(1));

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

        System.out.println("Finished AccessDataTest: DeletePlaylistEpisode");
    }

    @Test
    public void testPlaylistChannelList()
    {
        System.out.println("\nStarting AccessDataTest: PlaylistChannelList");
        resultCh = new ChannelList();

        // test retrieving the channels based from a playlist
        accessData.getPlaylistChannelSequential(resultCh, playlist1);
        assertTrue(resultCh.equals(playlistChannelList));

        // test retrieving the episodes based from a playlist that does not exist
        accessData.getPlaylistChannelSequential(resultCh, playlist4);
        playlistChannelList = new ChannelList();
        assertTrue(resultCh.equals(playlistChannelList));
        System.out.println("Finished AccessDataTest: PlaylistChannelList");
    }

    @Test
    public void testPlaylistEpisodeList()
    {
        System.out.println("\nStarting AccessDataTest: PlaylistEpisodeList");
        EpisodeList resultEp = new EpisodeList();

        // test retrieving the episodes based from a playlist
        accessData.getPlaylistEpisodeSequential(resultEp, playlist1);
        assertTrue(resultEp.equals(playlistEpisodesList));

        // test retrieving the episodes based from a playlist that does not exist
        accessData.getPlaylistEpisodeSequential(resultEp, playlist4);
        playlistEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(playlistEpisodesList));
        System.out.println("Finished AccessDataTest: PlaylistEpisodeList");
    }

    @Test
    public void testInsertSub()
    {
        System.out.println("\nStarting AccessDataTest: InsertSub");
        resultCh = new ChannelList();

        // test retrieving the subscriptions from the database
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));

        // test inserting a subscription
        subs.add(channel4);
        accessData.insertSub(channel4);
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));

        // test inserting duplicate subscription
        accessData.insertSub(channel4);
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));

        System.out.println("Finished AccessDataTest: InsertSub");
    }

    @Test
    public void testDeleteSub()
    {
        System.out.println("\nStarting AccessDataTest: DeleteSub");
        resultCh = new ChannelList();

        subs.add(channel4);

        // test deleting a subscription
        subs.remove(channel4);
        accessData.insertSub(channel4);
        accessData.deleteSub(channel4);
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));

        // test deleting a subscription that does not exist
        accessData.deleteSub(channel4);
        accessData.getSubSequential(resultCh);
        assertTrue(resultCh.equals(subs));
        System.out.println("Finished AccessDataTest: DeleteSub");
    }

    private boolean comparePlaylistLists(ArrayList<Playlist> origPlaylist, ArrayList<Playlist> otherPlaylist)
    {
        Boolean result = false;
        if (origPlaylist.size() == otherPlaylist.size())
        {
            result = true;
            for ( int i = 0; i < origPlaylist.size(); i++ )
            {
                if ( !origPlaylist.get(i).equals(otherPlaylist.get(i)) )
                {
                    result = false;
                    return result;
                }
            }
        }

        return result;
    }

    @After
    public void tearDown()
    {
        System.out.println("Finished Persistence test DataAccess (using stub)");
    }
}
