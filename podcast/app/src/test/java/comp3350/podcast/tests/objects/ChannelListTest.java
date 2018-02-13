package comp3350.podcast.tests.objects;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.ChannelList;
import comp3350.podcast.persistence.StubData;

import static org.junit.Assert.*;

/**
 * Created by Russell on 2018-02-10.
 */
public class ChannelListTest {

    private StubData data;

    private ChannelList list;

    private Channel ch1;
    private Channel ch2;
    private Channel ch3;


    @Before
    public void setUp() throws Exception{
        data = new StubData("LIES");
        data.open("Lies");

        ArrayList<Channel> tempList = new ArrayList<>();
        data.getChannelSequential(tempList);

        ch1 = tempList.get(0);
        ch2 = tempList.get(1);
        ch3 = tempList.get(2);

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