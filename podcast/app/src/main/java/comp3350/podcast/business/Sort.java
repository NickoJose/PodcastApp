package comp3350.podcast.business;

import java.util.*;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.DescribedObject;
import comp3350.podcast.objects.Episode;

enum CompMode{
    Title,
    Date,
    Length
}

class DescribedObjectComparator implements Comparator<DescribedObject>
{

    CompMode mode = CompMode.Title;
    public DescribedObjectComparator(CompMode mode){
        this.mode = mode;
    }

    @Override
    public int compare(DescribedObject a, DescribedObject b)
    {
        int result = 0;

        switch(mode) {
            case Title: result = a.getTitle().compareToIgnoreCase(b.getTitle()); break;
            case Date: result = a.getPublishDate().compareTo(b.getPublishDate()); break;
            case Length:
                if (a instanceof Channel && b instanceof Channel)
                    result = ((Channel) a).getLastUpdate().compareTo(((Channel) b).getLastUpdate());
                else if (a instanceof Episode && b instanceof Episode)
                    result = ((Episode)a).getLength()-((Episode)b).getLength();
        }
        return result;
    }
}

public class Sort {

    /**
     * Sorts a given ChannelList/ArrayList<Channel></> object according to a supported property. Currently supported are:
     * "title", "date", and "length"
     *
     * @param current  - The ChannelList object to have it's contents sorted
     * @param type - The supported field to be sorted by.
     * @return void
     */
    public static void channel(ArrayList<Channel> current, String type)
    {
        descObj(current, type);
    }

    /**
     * Sorts a given episode list according to a supported property. Currently supported are:
     * "title", "date", and "length"
     *
     * @param current  - The episode list to be sorted
     * @param type - The supported field to be sorted by.
     * @return void
     */
    public static void episode(ArrayList<Episode> current, String type)
    {
        descObj(current, type);
    }

    private static void descObj(ArrayList current, String type)
    {
        switch(type){
            case "title": Collections.sort(current,new DescribedObjectComparator(CompMode.Title));break;
            case "date": Collections.sort(current,new DescribedObjectComparator(CompMode.Date));break;
            case "length": Collections.sort(current,new DescribedObjectComparator(CompMode.Length));break;
        }
    }
}
