package comp3350.podcast.integration;

import org.junit.Test;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.persistence.AccessData;
import comp3350.podcast.persistence.AccessDataTest;

public class AccessDataHSQLDBTest
{
    public AccessDataHSQLDBTest() { super(); }

    @Test
    public void testAccessData()
    {
        AccessData accessData;

        Services.closeDataAccess();

        System.out.println("Starting Integration test AccessData (using default Podcast DB)");

        Services.createDataAccess(Main.dbName);
        accessData = Services.getDataAccess(Main.dbName);

        AccessDataTest.accessDataTest(accessData);

        Services.closeDataAccess();

        System.out.println("Finished Integration test DataAccess (using default Podcast DB)");
    }
}
