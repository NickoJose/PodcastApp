package comp3350.podcast;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.podcast.business.BusinessTests;
import comp3350.podcast.objects.ObjectTests;
import comp3350.podcast.persistence.PersistenceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ObjectTests.class,
        BusinessTests.class,
        PersistenceTests.class
})

public class RunUnitTests {
}
