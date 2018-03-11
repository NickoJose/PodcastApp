package comp3350.podcast.application;

public class Main
{
    public static final String dbName = "Podcast";
    private static String dbPathName = "database/Podcast";

    public static void main(String[] args)
    {
        startUp();


        shutDown();

        System.out.println("\nAll done");
    }

    
    /**
     * Opens database for this session
     *
     * @return - void
     */
    public static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    /**
     * Closes database for this session
     *
     * @return - void
     */
    public static void shutDown()
    {
        Services.closeDataAccess();
    }

    /**
     * Returns the database's name
     *
     * @return - String
     */
    public static String getDBPathName()
    {
        if (dbPathName == null)
        {
            return dbName;
        }
        else
        {
            return dbPathName;
        }
    }

    /**
     * sets the database's name
     *
     * @return - void
     */
    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }
}