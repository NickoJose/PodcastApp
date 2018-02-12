package comp3350.podcast.application;

/**
 * Created by Almach on 2018-01-30.
 */

public class Main
{
    public static final String dbName = "Pod";

    public static void main(String[] args)
    {
        startUp();

        shutDown();

        System.out.println("\nAll done");
    }

    public static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        Services.closeDataAccess();
    }
}
