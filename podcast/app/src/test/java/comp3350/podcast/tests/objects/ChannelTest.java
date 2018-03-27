package comp3350.podcast.tests.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Date;

public class ChannelTest
{
    private Channel channel;
    private Date date;

    public ChannelTest() { super(); }

    @Before
    public void setUp()
    {
        date = new Date(2015, 3,12);
        channel = new Channel("Seafood Mania", "In Seafood Mania, all we talk about is seafood and only seafood.",
                "https://seafoodmania.com/", date, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com","");
    }

    @Test
    public void testChannelInfo()
    {
        System.out.println("\nStarting ChannelTest : channel info");

        assertEquals("Seafood Mania", (channel.getTitle()));
        assertEquals("In Seafood Mania, all we talk about is seafood and only seafood."
                ,(channel.getDesc()));
        assertEquals("https://seafoodmania.com/", (channel.getUrl()));
        assertEquals(0, date.compareTo(channel.getPublishDate()));
        assertEquals("Joe Jones", (channel.getAuthor()));
        assertEquals("Food", (channel.getCategory()));
        assertEquals("Jones Surfing Company", (channel.getOwner()));
        assertEquals("jonesjoe@gmail.com", (channel.getOwnerEmail()));
        assertEquals(0, channel.getNumEps());
        System.out.println("Finished ChannelTest : channel info");
    }

    @Test
    public void testCompareByType()
    {
        Date dateAfter = new Date(2015, 9, 21);
        Date dateBefore = new Date(2006, 5, 3);
        Channel newChannel = new Channel("Seafood Mania", "Food, food.",
                "https://seafoodmania.com/", date, "Mack Mack", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com","");
        Channel anotherChannel = new Channel("Seafood Mania", "I got none",
                "https://loophole.com/", date, "Joe Jones", "Food",
                "Jones Surfing Company", "jonesjoe@gmail.com","");

        System.out.println("\nStarting ChannelTest : compare by type");

        assertEquals(0, channel.compareTo("Seafood Mania"));
        assertEquals(-1, channel.compareTo("Zoomba"));
        assertEquals(1, channel.compareTo("Apple Bees"));
        assertEquals(0, channel.compareTo(date));
        assertEquals(-1, channel.compareTo(dateAfter));
        assertEquals(1, channel.compareTo(dateBefore));
        assertTrue(channel.equals(newChannel));
        assertFalse(channel.equals(anotherChannel));

        System.out.println("Finished ChannelTest : compare by type");
    }


}
