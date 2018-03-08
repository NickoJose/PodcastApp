package comp3350.podcast.business;

import java.util.*;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;

enum CompMode{
    Title,
    Date,
    Length
}

class ChannelComparator implements Comparator<Channel>
{
    //Not familiar enough with java to merge this comparator class with the below. If someone is,
    //please merge ASAP.

    CompMode mode = CompMode.Title;

    public ChannelComparator(CompMode mode){
        this.mode = mode;
    }

    @Override
    public int compare(Channel a, Channel b)
    {
        int result =0;
        switch(mode){
            case Title: result = a.getTitle().compareToIgnoreCase(b.getTitle());  break;
            case Date: result = a.getPublishDate().compareTo(b.getPublishDate()); break;
            case Length: result = a.getLastUpdate().compareTo(b.getLastUpdate()); break;
        }
        return result;
    }
}


class EpisodeComparator implements Comparator<Episode>
{
    //Not familiar enough with java to merge this comparator class with the above. If someone is,
    //please merge ASAP.
    CompMode mode = CompMode.Title;

    public EpisodeComparator(CompMode mode){
        this.mode = mode;
    }

    @Override
    public int compare(Episode a, Episode b)
    {
        int result =0;
        switch(mode){
            case Title: result = a.getTitle().compareToIgnoreCase(b.getTitle());  break;
            case Date: result = a.getPublishDate().compareTo(b.getPublishDate()); break;
            case Length: result = a.getLength()-b.getLength(); break;
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
        switch(type){
            case "title": Collections.sort(current,new ChannelComparator(CompMode.Title));break;
            case "date": Collections.sort(current,new ChannelComparator(CompMode.Date));break;
            case "length": Collections.sort(current,new ChannelComparator(CompMode.Length));break;
        }

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
        switch(type){
            case "title":Collections.sort(current,new EpisodeComparator(CompMode.Title));break;
            case "date":Collections.sort(current,new EpisodeComparator(CompMode.Date)); break;
            case "length":Collections.sort(current,new EpisodeComparator(CompMode.Length)); break;
        }
    }
}
