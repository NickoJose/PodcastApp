package comp3350.podcast.business;

import java.util.ArrayList;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;

public class Sort {

    public static void channel(ArrayList<Channel> current, String type)
    {
        Channel temp;
        int i,k;

        if (type.equals("title")) // sort by title
        {
            for( i = 0; i < current.size(); i++) {
                temp = current.get(i);
                k = i;
                while ((k > 0) && (current.get(k-1).compareTo(temp.getTitle())) > 0) {
                    current.set(k, current.get(k - 1));
                    k--;
                }
                current.set(k, temp);
            }
        }

        else if (type.equals("date"))  // sort by date published
        {
            for( i = 0; i < current.size(); i++) {
                temp = current.get(i);
                k = i;
                while ((k > 0) && (current.get(k-1).compareTo(temp.getPublishDate())) > 0) {
                    current.set(k, current.get(k - 1));
                    k--;
                }
                current.set(k, temp);
            }
        }
    }

    public static void episode(ArrayList<Episode> current, String type)
    {
        Episode temp;
        int i,k;

        if (type.equals("title")) // sort by title
        {
            for( i = 0; i < current.size(); i++) {
                temp = current.get(i);
                k = i;
                while ((k > 0) && (current.get(k-1).compareTo(temp.getTitle())) > 0) {
                    current.set(k, current.get(k - 1));
                    k--;
                }
                current.set(k, temp);
            }
        }

        else if (type.equals("date"))  // sort by date published
        {
            for( i = 0; i < current.size(); i++) {
                temp = current.get(i);
                k = i;
                while ((k > 0) && (current.get(k-1).compareTo(temp.getPublishDate())) > 0) {
                    current.set(k, current.get(k - 1));
                    k--;
                }
                current.set(k, temp);
            }
        }

        else if (type.equals("length"))  // compare date published
        {
            for( i = 0; i < current.size(); i++) {
                temp = current.get(i);
                k = i;
                while ((k > 0) && (current.get(k-1).compareTo(temp.getLength())) > 0) {
                    current.set(k, current.get(k - 1));
                    k--;
                }
                current.set(k, temp);
            }
        }
    }
}
