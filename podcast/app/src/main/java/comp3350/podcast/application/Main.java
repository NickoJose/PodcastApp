package comp3350.podcast.application;

public class Main
{
    public static final String dbName = "Pod";
    private static String dbPathName = "database/Pod";

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

    //TODO COMMENTS

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

    //TODO COMMENTS

    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }
}
