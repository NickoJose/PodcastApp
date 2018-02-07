package comp3350.podcast.application;

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.business.Sort;
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
        startUp();
        StubData accessData = null;
        accessData = new StubData(dbName);
        accessData.open(Main.dbName);

        List<Channel> myList = new ArrayList();
        List<Episode> myList2 = new ArrayList();

        accessData.getChannelSequential(myList);
        System.out.println("\nDatabase Channel List");
        for (int i = 0; i < myList.size(); i++ )
        {
            System.out.println(myList.get(i).getTitle());
        }

        System.out.println("\nSorted Channel List by Date");
        Sort.channel(accessData.getChannelList(), "date");
        myList.clear();
        accessData.getChannelSequential(myList);
        for (int i = 0; i < myList.size(); i++ )
        {
            System.out.println(myList.get(i).getTitle() + " Date " +
                    myList.get(i).getPublishDate().year + " " +
                    myList.get(i).getPublishDate().month + " " +
                    myList.get(i).getPublishDate().day);
        }

        accessData.getChannelEpisodeSequential(myList2, myList.get(0));
        System.out.println("\nEpisode List of " + myList.get(0));
        for (int i = 0; i < myList2.size(); i++ )
        {
            System.out.println(myList2.get(i).getTitle());
        }

        System.out.println("\nSorted Episode List of " + myList.get(0) + " by Length");
        myList2.clear();
        myList2 = accessData.getChannelEpisodeList(myList.get(0));
        Sort.episode((ArrayList<Episode>)myList2, "length");
        for (int i = 0; i < myList2.size(); i++ )
        {
            System.out.println(myList2.get(i).getTitle() + " Length: "  +myList2.get(i).getLength());
        }
        //CLI.run();

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
