package comp3350.podcast.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BusinessPersistenceSeamTest.class,
        AccessDataHSQLDBTest.class
})

public class IntegrationTests{}
