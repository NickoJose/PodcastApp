package comp3350.podcast.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import comp3350.podcast.business.Sort;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Date;
import comp3350.podcast.objects.Episode;


public class SortTest {
    private ArrayList<Channel> channelList;
    private ArrayList<Channel> sortedByTitleChannelList;
    private ArrayList<Channel> sortedByDateChannelList;
    private ArrayList<Episode> episodeList;
    private ArrayList<Episode> sortedByTitleEpisodeList;
    private ArrayList<Episode> sortedByDateEpisodeList;
    private ArrayList<Episode> sortedByLengthEpisodeList;
    private Channel channel1;
    private Channel channel2;
    private Channel channel3;
    private Episode episode1;
    private Episode episode2;
    private Episode episode3;
    private Date chDate1;
    private Date chDate2;
    private Date chDate3;
    private Date epDate1;
    private Date epDate2;
    private Date epDate3;
    private ArrayList<Channel> chCopy;
    private ArrayList<Episode> epCopy;

    public SortTest() { super(); }

    @Before
    public void setUp()
    {
        chDate1 = new Date(2009, 12, 24);
        chDate2 = new Date(2016, 2, 17);
        chDate3 = new Date(2012, 4, 22);

        epDate1 = new Date(2018, 1, 12);
        epDate2 = new Date(2016, 7, 27);
        epDate3 = new Date(2017, 12, 27);

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

        channelList = new ArrayList<>();
        episodeList = new ArrayList<>();
        sortedByTitleChannelList = new ArrayList<>();
        sortedByDateChannelList = new ArrayList<>();
        sortedByTitleEpisodeList = new ArrayList<>();
        sortedByDateEpisodeList = new ArrayList<>();
        sortedByLengthEpisodeList = new ArrayList<>();

        channelList.add(channel1);
        channelList.add(channel2);
        channelList.add(channel3);

        episodeList.add(episode1);
        episodeList.add(episode2);
        episodeList.add(episode3);

        sortedByTitleChannelList.add(channel2);
        sortedByTitleChannelList.add(channel1);
        sortedByTitleChannelList.add(channel3);

        sortedByDateChannelList.add(channel1);
        sortedByDateChannelList.add(channel3);
        sortedByDateChannelList.add(channel2);

        sortedByTitleEpisodeList.add(episode3);
        sortedByTitleEpisodeList.add(episode2);
        sortedByTitleEpisodeList.add(episode1);

        sortedByDateEpisodeList.add(episode2);
        sortedByDateEpisodeList.add(episode3);
        sortedByDateEpisodeList.add(episode1);

        sortedByLengthEpisodeList.add(episode3);
        sortedByLengthEpisodeList.add(episode1);
        sortedByLengthEpisodeList.add(episode2);
    }

    @Test
    public void testNullList()
    {
        System.out.println("\nStarting SortTest: null list");
        try {
            Sort.channel(null, "title");
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        try {
            Sort.channel(null, "date");
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        try {
            Sort.episode(null,"title");
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        try {
            Sort.episode(null,"date");
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        try {
            Sort.episode(null,"length");
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        System.out.println("Finished SortTest: null list");
    }

    @Test
    public void testNullType()
    {
        System.out.println("\nStarting SortTest: null type");

        chCopy = (ArrayList<Channel>)channelList.clone();
        epCopy = (ArrayList<Episode>)episodeList.clone();

        try {
            Sort.channel(chCopy, null);
            assertTrue(compareChannelLists(chCopy, channelList));
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        try {
            Sort.episode(epCopy,null);
            assertTrue(compareEpisodeLists(epCopy, episodeList));
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        System.out.println("Finished SortTest: null type");
    }

    @Test
    public void testInvalidType()
    {
        System.out.println("\nStarting SortTest: invalid type");

        chCopy = (ArrayList<Channel>)channelList.clone();
        epCopy = (ArrayList<Episode>)episodeList.clone();

        Sort.channel(chCopy, "category");
        assertTrue(compareChannelLists(chCopy, channelList));

        Sort.episode(epCopy,"category");
        assertTrue(compareEpisodeLists(epCopy, episodeList));

        System.out.println("Finished SortTest: invalid type");
    }

    @Test
    public void testEmptyList()
    {
        System.out.println("\nStarting SortTest: empty list");
        chCopy = new ArrayList<>();
        epCopy = new ArrayList<>();

        ArrayList<Channel> chTemp = new ArrayList<>();
        ArrayList<Episode> epTemp = new ArrayList<>();

        Sort.channel(chCopy, "title");
        assertTrue(compareChannelLists(chTemp, chCopy));

        Sort.channel(chCopy, "date");
        assertTrue(compareChannelLists(chTemp, chCopy));

        Sort.episode(epCopy, "title");
        assertTrue(compareEpisodeLists(epTemp, epCopy));

        Sort.episode(epCopy, "date");
        assertTrue(compareEpisodeLists(epTemp, epCopy));

        Sort.episode(epCopy, "length");
        assertTrue(compareEpisodeLists(epTemp, epCopy));

        System.out.println("Finished SortTest: empty list");
    }

    @Test
    public void testOneEntry()
    {
        System.out.println("\nStarting SortTest: one entry");
        chCopy = new ArrayList<>();
        epCopy = new ArrayList<>();
        ArrayList<Channel> chTemp = new ArrayList<>();
        ArrayList<Episode> epTemp = new ArrayList<>();

        chCopy.add(channel1);
        chTemp.add(channel1);
        epCopy.add(episode1);
        epTemp.add(episode1);

        Sort.channel(chCopy, "title");
        assertTrue(compareChannelLists(chTemp, chCopy));

        Sort.channel(chCopy, "date");
        assertTrue(compareChannelLists(chTemp, chCopy));

        Sort.episode(epCopy, "title");
        assertTrue(compareEpisodeLists(epTemp, epCopy));

        Sort.episode(epCopy, "date");
        assertTrue(compareEpisodeLists(epTemp, epCopy));

        Sort.episode(epCopy, "length");
        assertTrue(compareEpisodeLists(epTemp, epCopy));

        System.out.println("Finished SortTest: one entry");
    }

    @Test
    public void testMultipleEntries()
    {
        System.out.println("\nStarting SortTest: multiple entries");

        chCopy = (ArrayList<Channel>)channelList.clone();
        epCopy = (ArrayList<Episode>)episodeList.clone();

        Sort.channel(chCopy, "title");
        assertTrue(compareChannelLists(sortedByTitleChannelList, chCopy));
        assertFalse(compareChannelLists(channelList, chCopy));

        Sort.channel(chCopy, "date");
        assertTrue(compareChannelLists(sortedByDateChannelList, chCopy));
        assertFalse(compareChannelLists(channelList, chCopy));

        Sort.episode(epCopy, "title");
        assertTrue(compareEpisodeLists(sortedByTitleEpisodeList, epCopy));
        assertFalse(compareEpisodeLists(episodeList, epCopy));

        Sort.episode(epCopy, "date");
        assertTrue(compareEpisodeLists(sortedByDateEpisodeList, epCopy));
        assertFalse(compareEpisodeLists(episodeList, epCopy));

        Sort.episode(epCopy, "length");
        assertTrue(compareEpisodeLists(sortedByLengthEpisodeList, epCopy));
        assertFalse(compareEpisodeLists(episodeList, epCopy));

        System.out.println("Finished SortTest: multiple entries");

    }

    private boolean compareChannelLists(ArrayList<Channel> origChannel, ArrayList<Channel>otherChannel)
    {
        Boolean result = false;
        if (origChannel.size() == otherChannel.size())
        {
            result = true;
            for ( int i = 0; i < origChannel.size(); i++ )
            {
                if ( !origChannel.get(i).equals(otherChannel.get(i)) )
                {
                    result = false;
                    return result;
                }
            }
        }

        return result;
    }

    private boolean compareEpisodeLists(ArrayList<Episode> origEpisode, ArrayList<Episode>otherEpisode)
    {
        Boolean result = false;
        if (origEpisode.size() == otherEpisode.size())
        {
            result = true;
            for ( int i = 0; i < origEpisode.size(); i++ )
            {
                if ( !origEpisode.get(i).equals(otherEpisode.get(i)) )
                {
                    result = false;
                    return result;
                }
            }
        }

        return result;
    }
}
