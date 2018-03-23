package comp3350.podcast.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessChannelsTest.class,
        AccessEpisodesTest.class,
        AccessPlaylistsTest.class,
        AccessSubscriptionsTest.class,
        SortTest.class,
        SearchTest.class
})

public class BusinessTests {
}
