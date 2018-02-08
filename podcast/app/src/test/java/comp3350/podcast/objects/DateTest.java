package comp3350.podcast.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Russell on 2018-02-07.
 */
public class DateTest {

    Date blank = new Date();

    Date date = null;
    Date dateCopy = null;
    Date plusSecond = null;
    Date plusYear = null;
    Date nullDate = null;

    @Before
    public void setUp() throws Exception {
        blank = new Date();

        date = new Date(1996, 1, 1, 1, 1, 1);
        dateCopy =new Date(1996, 1, 1, 1, 1, 1);
        plusSecond = new Date(1996, 1, 1, 1, 1, 2);
        plusYear = new Date(1997, 1, 1, 1, 1, 1);
        nullDate = null;

    }

    @Test
    public void equals() throws Exception {

        assertTrue(blank.equals(blank));
        assertTrue(date.equals(dateCopy));
        assertFalse(blank.equals(date));

    }

    @Test
    public void compareTo() throws Exception {

        assertEquals( blank.compareTo(blank), 0);
        assertEquals( date.compareTo(date), 0);
        assertEquals( date.compareTo(dateCopy), 0);
        assertEquals( date.compareTo(plusSecond), -1);
        assertEquals( plusYear.compareTo(date), 1);
    }

}