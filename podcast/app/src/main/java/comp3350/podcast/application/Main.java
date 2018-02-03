package comp3350.podcast.application;

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.persistence.StubData;

/**
 * Created by Almach on 2018-01-30.
 */

public class Main
{
    public static final String dbName = "Pod";

    public static void main(String[] args)
    {
        //startUp();
        StubData accessData = null;
        accessData = new StubData(dbName);
        accessData.open(Main.dbName);

        List<Channel> myList = new ArrayList();
        List<Episode> myList2 = new ArrayList();
        accessData.getChannelSequential(myList);

        System.out.println("\nChannel List\n");
        for (int i = 0; i < myList.size(); i++ )
        {
            System.out.println(myList.get(i).getTitle());
        }
        System.out.println();

        accessData.getChannelEpisodeSequential(myList2, myList.get(0));
        System.out.println("Episode List\n");
        for (int i = 0; i < myList2.size(); i++ )
        {
            System.out.println(myList2.get(i).getTitle());
        }

        //CLI.run();

        shutDown();
        System.out.println("All done");
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
