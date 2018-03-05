package comp3350.podcast.application;

import comp3350.podcast.persistence.StubData;

public class Services
{
    private static StubData accessData = null;

    /**
     * Opens database connection and gets local copy of data with identifying name
     *
     * @param dbName - name identifying user's database
     * @return - NEW StubData object containing user's database
     */
    public static StubData createDataAccess(String dbName)
    {
        if (accessData == null)
        {
            accessData = new StubData(dbName);
            accessData.open(Main.dbName);
        }
        return accessData;
    }

    /**
     * Gets local copy of data with identifying name from an already open Database
     *
     * @param dbName - name identifying user's database
     * @return - EXISTING StubData object containing user's database
     */
    public static StubData getDataAccess(String dbName)
    {
        if (accessData == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return accessData;
    }
    
    
    /**
     * Closes database access across this application session
     *
     * @return - void
     */
    public static void closeDataAccess()
    {
        if (accessData != null)
        {
            accessData.close();
        }
        accessData = null;
    }
}
