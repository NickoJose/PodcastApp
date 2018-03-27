package comp3350.podcast.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChannelListTest {

    private ChannelList list;

    private Channel ch1;
    private Channel ch2;
    private Channel ch3;
    private Date chDate1;
    private Date chDate2;
    private Date chDate3;


    @Before
    public void setUp() throws Exception{
        chDate1 = new Date(2009, 12, 24);
        chDate2 = new Date(2016, 2, 17);
        chDate3 = new Date(2012, 4, 22);

        ch1 = new Channel("The Joe Rogan Experience", "The Joe Rogan Experience podcast is a long form conversation hosted by comedian, " +
                "UFC color commentator, and actor Joe Rogan with friends and guests that have included comedians, actors, musicians, MMA instructors and " +
                "commentators, authors, artists, and porn stars.", "http://joerogan.net/podcasts/", chDate1, "Joe Rogan", "Comedy", "Brian Redban", "BOOKDEATHSQUAD@GMAIL.COM", "");
        ch2 = new Channel("NBA Hang Time", "Veteran NBA writer Sekou Smith and former player Greg Anthony analyze the latest NBA news, " +
                "storylines, and more with guests from around the NBA world.", "http://www.nba.com/podcast#/", chDate2, "Sekou Smith", "Sports", "NBA Digital", "@NBA", "");
        ch3 = new Channel("You Are Not So Smart", "You Are Not So Smart is a celebration of self delusion that explores topics related to " +
                "cognitive biases, heuristics, and logical fallacies. David McRaney interviews scientists about their research into how the mind works, " +
                "and then he eats a cookie", "https://youarenotsosmart.com/", chDate3, "David McRaney", "Social Sciences", "David McRaney", "davidmcraney@gmail.com", "");

        list = new ChannelList();
    }

    @Test
    public void add() throws Exception {
        System.out.println("\nStarting ChannelListTest : add channels");
        assertTrue(list.isEmpty());

        // Add first item
        assertTrue(list.add(ch1));
        assertFalse(list.isEmpty());
        assertTrue(list.contains(ch1));
        assertFalse(list.add(ch1));
        assertEquals(list.size(), 1);

        // Add seconds item
        assertTrue(list.add(ch2));
        assertFalse(list.isEmpty());
        assertTrue(list.contains(ch2));
        assertTrue(list.contains(ch1));
        assertFalse(list.add(ch2));
        assertEquals(list.size(), 2);

        // Add third item
        assertTrue(list.add(ch3));
        assertFalse(list.isEmpty());
        assertTrue(list.contains(ch3));
        assertTrue(list.contains(ch2));
        assertTrue(list.contains(ch1));
        assertFalse(list.add(ch3));
        assertEquals(list.size(), 3);

        // Add null item
        try{
            list.add(null);
            fail("list.add(null) expected NullPointerException");
        } catch(NullPointerException ex){}
        System.out.println("Finished ChannelListTest : add channels");
    }

    @Test
    public void addAtIndex() throws Exception {
        System.out.println("\nStarting ChannelListTest : add channels by position");
        // List is empty
        list.add(0, ch1);

        assertTrue(list.contains(ch1));
        assertEquals(list.indexOf(ch1), 0);

        // Insert at beginning
        list.add(0, ch2);

        assertTrue(list.contains(ch1));
        assertTrue(list.contains(ch2));

        assertEquals(list.indexOf(ch2), 0);
        assertEquals(list.indexOf(ch1), 1);

        // Insert at end of list
        list.add(2, ch3);

        assertTrue(list.contains(ch1));
        assertTrue(list.contains(ch2));
        assertTrue(list.contains(ch3));

        assertEquals(list.indexOf(ch2), 0);
        assertEquals(list.indexOf(ch1), 1);
        assertEquals(list.indexOf(ch3), 2);

        // Insert at middle of list
        list.remove(ch3);

        list.add(1, ch3);

        assertTrue(list.contains(ch1));
        assertTrue(list.contains(ch2));
        assertTrue(list.contains(ch3));

        assertEquals(list.indexOf(ch2), 0);
        assertEquals(list.indexOf(ch3), 1);
        assertEquals(list.indexOf(ch1), 2);


        // Prep for exceptions
        list.remove(ch3);

        // Insert null value
        try{
            list.add(1,null);
            fail("list.add(1,null) should result in NullPointerException");
        } catch(NullPointerException ex) {}

        // Index of -1
        try{
            list.add(-1, ch3);
            fail("list.add(-1, ch3) should result in IndexOutOfBoundsException");
        } catch(IndexOutOfBoundsException ex) {}

        // Index after the end of the list
        try{
            list.add(list.size() + 1, ch3);

            fail("list.add(list.size(), ch3) should result in IndexOutOfBoundsException");
        } catch(IndexOutOfBoundsException ex) {}
        System.out.println("Finished ChannelListTest : add channels by position");
    }

    @Test
    public void equals() throws Exception {
        System.out.println("\nStarting ChannelListTest : match channels");
        ChannelList empty = new ChannelList();
        ChannelList different = new ChannelList();
        ChannelList same = new ChannelList();

        // Are empty lists equal?
        assertTrue(list.equals(empty));
        assertTrue(empty.equals(list));

        // Are lists with one item equal? is an empty list unequal to a list with an item?
        list.add(ch1);
        same.add(ch1);

        different.add(ch2);

        assertTrue(list.equals(same));
        assertTrue(same.equals(list));

        assertFalse(list.equals(different));
        assertFalse(different.equals(list));
        assertFalse(list.equals(empty));
        assertFalse(empty.equals(list));

        // Lists with >1 item
        list.add(ch2);
        same.add(ch2);

        assertTrue(list.equals(same));
        assertTrue(same.equals(list));

        // Lists with different sizes
        assertFalse(list.equals(different));
        assertFalse(different.equals(list));
        assertFalse(list.equals(empty));
        assertFalse(empty.equals(list));

        // Lists that are the same size but unequal
        different.add(ch3);

        assertFalse(list.equals(different));
        assertFalse(different.equals(list));

        // Lists that are in different order are still equal if they contain the same items exactly
        same.remove(ch2);
        same.add(0, ch2);

        assertTrue(list.equals(same));
        assertTrue(same.equals(list));
        System.out.println("Finished ChannelListTest : match channels");
    }

}