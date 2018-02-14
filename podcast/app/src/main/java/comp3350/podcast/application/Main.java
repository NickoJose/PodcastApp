package comp3350.podcast.application;

public class Main
{
    public static final String dbName = "Pod";

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
}
