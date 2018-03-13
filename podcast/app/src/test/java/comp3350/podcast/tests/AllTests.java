package comp3350.podcast.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.podcast.tests.business.AccessChannelsTest;
import comp3350.podcast.tests.business.AccessEpisodesTest;
import comp3350.podcast.tests.business.AccessPlaylistsTest;
import comp3350.podcast.tests.business.AccessSubscriptionsTest;
import comp3350.podcast.tests.business.SearchTest;
import comp3350.podcast.tests.business.SortTest;
import comp3350.podcast.tests.objects.ChannelListTest;
import comp3350.podcast.tests.objects.ChannelTest;
import comp3350.podcast.tests.objects.DateTest;
import comp3350.podcast.tests.objects.EpisodeListTest;
import comp3350.podcast.tests.objects.EpisodeTest;
import comp3350.podcast.tests.persistence.AccessDataTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessChannelsTest.class,
        AccessEpisodesTest.class,
        AccessPlaylistsTest.class,
        AccessSubscriptionsTest.class,
        SortTest.class,
        SearchTest.class,
        ChannelTest.class,
        EpisodeTest.class,
        DateTest.class,
        ChannelListTest.class,
        EpisodeListTest.class,
        AccessDataTest.class
})

public class AllTests {
}
