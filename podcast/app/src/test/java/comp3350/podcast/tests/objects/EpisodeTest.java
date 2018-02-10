package comp3350.podcast.tests.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Date;
import comp3350.podcast.objects.Episode;

/**
 * Created by Almach on 2018-02-09.
 */

public class EpisodeTest
{
    private Episode episode;
    private Channel channel;
    private Date date;

    public EpisodeTest() {super(); }

    @Before
    public void setUp()
    {
        date = new Date(2015, 2, 24);
        channel = new Channel("Seafood Mania", "In Seafood Mania, all we talk about is seafood and only seafood.",
                "https://seafoodmania.com/", date, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com");
        episode = new Episode("Seafood Mania - Lobstah Madness", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "We talk about lobster and lobster only", 1.30, channel, date, "Joe Jones",
                "Food",13 );
    }

    @Test
    public void testEpisodeInfo()
    {
        System.out.println("\nStarting EpisodeTest : episode info");
        assertEquals("Seafood Mania - Lobstah Madness", episode.getTitle());
        assertEquals("http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                episode.getUrl());
        assertEquals("We talk about lobster and lobster only", episode.getDesc());
        assertEquals(1.30, episode.getLength(), 0.1);
        assertTrue(episode.getChannel().equals(channel));
        assertEquals(0, date.compareTo(episode.getPublishDate()));
        assertEquals("Joe Jones", episode.getAuthor());
        assertEquals("Food", episode.getCategory());
        assertEquals(13, episode.getEpNum());
        assertEquals(channel.getTitle(), episode.getChannelTitle());

        System.out.println("Finished EpisodeTest : episode info");
    }

    @Test
    public void testCompareByType() {
        Date dateAfter = new Date(2015, 9, 21);
        Date dateBefore = new Date(2006, 5, 3);

        Episode newEpisode = new Episode("Seafood Mania - Lobstah Madness", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=19997",
                "We talk about lobster and lobster only", 1.30, channel, date, "Joe Jones",
                "Food", 13);
        Episode anotherEpisode = new Episode("Seafood Mania - Crabs Madness", "http://traffic.libsyn.com/seafoodmania/p890.mp3?dest-id=11111",
                "We talk about lobster and lobster only", 1.30, channel, date, "Joe Jones",
                "Food", 13);

        System.out.println("\nStarting EpisodeTest : compare by type");
        assertEquals(0, episode.compareTo("Seafood Mania - Lobstah Madness"));
        assertEquals(-1, episode.compareTo("Zoomba"));
        assertEquals(1, episode.compareTo("Apple Bees"));
        assertEquals(0, episode.compareTo(date));
        assertEquals(-1, episode.compareTo(dateAfter));
        assertEquals(1, episode.compareTo(dateBefore));
        assertTrue(episode.equals(newEpisode));
        assertFalse(episode.equals(anotherEpisode));

        System.out.println("Finished EpisodeTest : compare by type");
    }
}
