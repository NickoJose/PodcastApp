package comp3350.podcast.application;

import comp3350.podcast.persistence.AccessData;
import comp3350.podcast.persistence.ObjectData;

public class Services
{
    private static AccessData accessData = null;

    /**
     * Opens database connection and gets local copy of data with identifying name
     *
     * @param dbName - name identifying user's database
     * @return - NEW AccessData object containing user's database
     */
    public static AccessData createDataAccess(String dbName)
    {
        if (accessData == null)
        {
            accessData = new ObjectData(dbName);
            accessData.open(Main.getDBPathName());
        }
        return accessData;
    }

    /**
     * Sets the database to an alternate database
     *
     * @param alternateDataAccessService - database to replace the current one
     * @return - Alternate database
     */
    public static AccessData createDataAccess(AccessData alternateDataAccessService)
    {
        if (accessData == null)
        {
            accessData = alternateDataAccessService;
            accessData.open(Main.getDBPathName());
        }
        return accessData;
    }

    /**
     * Gets local copy of data with identifying name from an already open Database
     *
     * @param dbName - name identifying user's database
     * @return - EXISTING AccessData object containing user's database
     */
    public static AccessData getDataAccess(String dbName)
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
