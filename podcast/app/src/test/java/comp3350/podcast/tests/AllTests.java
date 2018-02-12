package comp3350.podcast.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.podcast.tests.business.SortTest;
import comp3350.podcast.tests.objects.ChannelTest;
import comp3350.podcast.tests.objects.DateTest;
import comp3350.podcast.tests.objects.EpisodeTest;

/**
 * Created by Almach on 2018-02-11.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SortTest.class,
        ChannelTest.class,
        EpisodeTest.class,
        DateTest.class
})

public class AllTests {
}
