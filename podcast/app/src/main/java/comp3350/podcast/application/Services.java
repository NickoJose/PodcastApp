package comp3350.podcast.application;

import comp3350.podcast.persistence.StubData;

/**
 * Created by Almach on 2018-01-30.
 */

public class Services
{
    private static StubData accessData = null;

    public static StubData createDataAccess(String dbName)
    {
        if (accessData == null)
        {
            accessData = new StubData(dbName);
            accessData.open(Main.dbName);
        }
        return accessData;
    }

    public static StubData getDataAccess(String dbName)
    {
        if (accessData == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return accessData;
    }

    public static void closeDataAccess()
    {
        if (accessData != null)
        {
            accessData.close();
        }
        accessData = null;
    }
}
