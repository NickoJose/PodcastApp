package comp3350.podcast.objects;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.podcast.persistence.StubData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class EpisodeListTest {

    private StubData data;

    private EpisodeList list;

    private Episode ep1;
    private Episode ep2;
    private Episode ep3;

    private Channel ch1;

    private Date epDate1;
    private Date epDate2;
    private Date epDate3;

    private Date chDate1;


    @Before
    public void setUp() throws Exception {

        epDate1 = new Date(2018, 1, 12);
        epDate2 = new Date(2016, 7, 27);
        epDate3 = new Date(2017, 12, 27);

        chDate1 = new Date(2009, 12, 24);

        ch1 = new Channel("The Joe Rogan Experience", "The Joe Rogan Experience podcast is a long form conversation hosted by comedian, " +
                "UFC color commentator, and actor Joe Rogan with friends and guests that have included comedians, actors, musicians, MMA instructors and " +
                "commentators, authors, artists, and porn stars.", "http://joerogan.net/podcasts/", chDate1, "Joe Rogan", "Comedy", "Brian Redban", "BOOKDEATHSQUAD@GMAIL.COM", "");

        ep1 = new Episode("JRE MMA Show #10 with Tyron Woodley", "http://traffic.libsyn.com/joeroganexp/mmashow010.mp3?dest-id=19997",
                "Joe Rogan sits down with UFC Welterweight Champion Tyron Woodley", 6120, ch1, epDate1, "Joe Rogan", "Sports", 10, "");
        ep2 = new Episode("#990 - Jamie Foxx", "http://traffic.libsyn.com/joeroganexp/p990.mp3?dest-id=19997",
                "Jamie Foxx is an Academy Award winning actor, singer, and comedian. He can currently be seen hosting \"Beat Shazam\" on Fox and " +
                        "in the movie \"Baby Driver\" in theaters now.", 4080, ch1, epDate2, "Joe Rogan", "Comedy", 990, "");
        ep3 = new Episode("#890 - Fight Breakdown", "http://traffic.libsyn.com/joeroganexp/p890.mp3?dest-id=19997",
                "Joe sits down with Eddie Bravo & Brendan Schaub to discuss upcoming fights in MMA", 13740, ch1 , epDate3, "Joe Rogan", "Sports", 890, "");

        list = new EpisodeList();
    }

    @Test
    public void add() throws Exception {
        System.out.println("\nStarting EpisodeListTest : add episodes");
        assertTrue(list.isEmpty());

        // Add first item
        assertTrue(list.add(ep1));
        assertFalse(list.isEmpty());
        assertTrue(list.contains(ep1));
        assertFalse(list.add(ep1));
        assertEquals(list.size(), 1);

        // Add seconds item
        assertTrue(list.add(ep2));
        assertFalse(list.isEmpty());
        assertTrue(list.contains(ep2));
        assertTrue(list.contains(ep1));
        assertFalse(list.add(ep2));
        assertEquals(list.size(), 2);

        // Add third item
        assertTrue(list.add(ep3));
        assertFalse(list.isEmpty());
        assertTrue(list.contains(ep3));
        assertTrue(list.contains(ep2));
        assertTrue(list.contains(ep1));
        assertFalse(list.add(ep3));
        assertEquals(list.size(), 3);

        // Add null item
        try {
            Episode epNull = null;
            list.add(epNull);
            fail("list.add(epNull) expected NullPointerException");
        } catch (NullPointerException ex) {
        }

        // Add null iterable
        try {
            Iterable<Episode> iterNull = null;
            list.add(iterNull);
            fail("list.add(iterNull) expected NullPointerException");
        } catch (NullPointerException ex) {
        }
        System.out.println("Finished EpisodeListTest : add episodes");
    }

    @Test
    public void addAtIndex() throws Exception {
        System.out.println("\nStarting EpisodeListTest : add episodes by position");
        // List is empty
        list.add(0, ep1);

        assertTrue(list.contains(ep1));
        assertEquals(list.indexOf(ep1), 0);

        // Insert at beginning
        list.add(0, ep2);

        assertTrue(list.contains(ep1));
        assertTrue(list.contains(ep2));

        assertEquals(list.indexOf(ep2), 0);
        assertEquals(list.indexOf(ep1), 1);

        // Insert at end of list
        list.add(2, ep3);

        assertTrue(list.contains(ep1));
        assertTrue(list.contains(ep2));
        assertTrue(list.contains(ep3));

        assertEquals(list.indexOf(ep2), 0);
        assertEquals(list.indexOf(ep1), 1);
        assertEquals(list.indexOf(ep3), 2);

        // Insert at middle of list
        list.remove(ep3);

        list.add(1, ep3);

        assertTrue(list.contains(ep1));
        assertTrue(list.contains(ep2));
        assertTrue(list.contains(ep3));

        assertEquals(list.indexOf(ep2), 0);
        assertEquals(list.indexOf(ep3), 1);
        assertEquals(list.indexOf(ep1), 2);


        // Prep for exceptions
        list.remove(ep3);

        // Insert null value
        try {
            list.add(1, null);
            fail("list.add(1,null) should result in NullPointerException");
        } catch (NullPointerException ex) {
        }

        // Index of -1
        try {
            list.add(-1, ep3);
            fail("list.add(-1, ep3) should result in IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
        }

        // Index after the end of the list
        try {
            list.add(list.size() + 1, ep3);

            fail("list.add(list.size(), ep3) should result in IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
        }
        System.out.println("Finished EpisodeListTest : add episodes by position");
    }

    @Test
    public void equals() throws Exception {
        System.out.println("\nStarting EpisodeListTest : match episodes");
        EpisodeList empty = new EpisodeList();
        EpisodeList different = new EpisodeList();
        EpisodeList same = new EpisodeList();

        // Are empty lists equal?
        assertTrue(list.equals(empty));
        assertTrue(empty.equals(list));

        // Are lists with one item equal? is an empty list unequal to a list with an item?
        list.add(ep1);
        same.add(ep1);

        different.add(ep2);

        assertTrue(list.equals(same));
        assertTrue(same.equals(list));

        assertFalse(list.equals(different));
        assertFalse(different.equals(list));
        assertFalse(list.equals(empty));
        assertFalse(empty.equals(list));

        // Lists with >1 item
        list.add(ep2);
        same.add(ep2);

        assertTrue(list.equals(same));
        assertTrue(same.equals(list));

        // Lists with different sizes
        assertFalse(list.equals(different));
        assertFalse(different.equals(list));
        assertFalse(list.equals(empty));
        assertFalse(empty.equals(list));

        // Lists that are the same size but unequal
        different.add(ep3);

        assertFalse(list.equals(different));
        assertFalse(different.equals(list));

        // Lists that are in different order are still equal if they contain the same items exactly
        same.remove(ep2);
        same.add(0, ep2);

        assertTrue(list.equals(same));
        assertTrue(same.equals(list));
        System.out.println("Finished EpisodeListTest : match episodes");
    }


    @Test
    public void getEpisodesAfter() throws Exception {
        System.out.println("\nStarting EpisodeListTest : retrieve episodes based on date");
        Date beforeFirst = new Date();
        Date first = new Date(1, 1, 1);
        Date second = new Date(1, 1, 2);
        Date third = new Date(2);

        EpisodeList results;

        ep1 = new Episode("ep1", "", "", 0, null, first, "", "", 0, "");
        ep2 = new Episode("ep2", "", "", 0, null, second, "", "", 0, "");
        ep3 = new Episode("ep3", "", "", 0, null, third, "", "", 0, "");

        list.add(ep1);
        list.add(ep2);
        list.add(ep3);

        results = list.getEpisodesAfter(beforeFirst);

        assertTrue(list.equals(results));

        results = list.getEpisodesAfter(first);

        assertFalse(results.contains(ep1));
        assertTrue(results.contains(ep2));
        assertTrue(results.contains(ep3));

        results = list.getEpisodesAfter(second);

        assertFalse(results.contains(ep1));
        assertFalse(results.contains(ep2));
        assertTrue(results.contains(ep3));

        results = list.getEpisodesAfter(third);

        assertFalse(results.contains(ep1));
        assertFalse(results.contains(ep2));
        assertFalse(results.contains(ep3));
        System.out.println("Finished EpisodeListTest : retrieve episodes based on date");
    }

}