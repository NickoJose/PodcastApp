package comp3350.podcast.representation;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import comp3350.podcast.R;
import comp3350.podcast.application.Main;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.objects.DescribedObject;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Channel;


public final class CardList {

    private CardList(){}
/*
    private static void createCardList(View.OnClickListener handler1, View layout, Context context,
    int resource,List<Channel> recList, ArrayList<Integer> recIds, DescribedObject temp)
    {
        int index = 0;
        //DescribedObject temp;
        TextView title = null;
        TextView description = null;
        View view;

        if(temp instanceof Episode)
        {
            temp = (Episode)temp;
        }
        else if (temp instanceof Channel)
        {
            temp = (Channel)temp;
        }

        // update titles

        for (index = 0; index < recList.size(); index++) {
            // get layouts
            LinearLayout ll = (LinearLayout)layout;
            view = LayoutInflater.from(context).inflate(resource, ll, false);
            recIds.add(view.getId());

            temp = recList.get(index);

            // find this cards title
            title = view.findViewById(R.id.recTitle);

            // find this cards description
            description = view.findViewById(R.id.recDsc);

            // set this cards properties
            if (temp != null)
            {
                description.setText(temp.getDesc());
                title.setText(temp.getTitle());

                if (view instanceof CardViewPC)
                {
                    CardViewPC a = (CardViewPC) view;
                    a.setWhoDis(temp.getTitle());
                    a.setCh(temp);
                }
            }

            // set listener
            view.setOnClickListener(handler1);
            ll.addView(view);
        }

    }*/

    public static void createChannelCardList(View.OnClickListener handler1, View layout, Context context,
                                             int resource,List<Channel> recList, ArrayList<Integer> recIds)
    {
       //Channel temp = null;
       //createCardList(handler1,layout,context,resource,recList,recIds,temp);
        int index = 0;
        Channel temp;
        TextView title = null;
        TextView description = null;
        View view;

        // update titles

        for (index = 0; index < recList.size(); index++) {
            // get layouts
            LinearLayout ll = (LinearLayout)layout;
            view = LayoutInflater.from(context).inflate(resource, ll, false);
            recIds.add(view.getId());

            temp = recList.get(index);

            // find this cards title
            title = view.findViewById(R.id.recTitle);

            // find this cards description
            description = view.findViewById(R.id.recDsc);

            // set this cards properties
            if (temp != null)
            {
                description.setText(temp.getDesc());
                title.setText(temp.getTitle());

                if (view instanceof CardViewPC)
                {
                    CardViewPC a = (CardViewPC) view;
                    a.setWhoDis(temp.getTitle());
                    a.setCh(temp);
                }
            }

            // set listener
            view.setOnClickListener(handler1);
            ll.addView(view);
        }
    }

    public static void createEpisodeCardList(View.OnClickListener handler1, View layout, Context context,
                                             int resource,List<Episode> recList, ArrayList<Integer> recIds)
    {
        //Episode temp = null;
        //createCardList(handler1,layout,context,resource,recList,recIds,temp);

        int index = 0;
        Episode temp;
        TextView title = null;
        TextView description = null;
        View view;

        // update titles

        for (index = 0; index < recList.size(); index++) {
            // get layouts
            LinearLayout ll = (LinearLayout)layout;
            view = LayoutInflater.from(context).inflate(resource, ll, false);
            recIds.add(view.getId());

            temp = recList.get(index);

            // find this cards title
            title = view.findViewById(R.id.recTitle);

            // find this cards description
            description = view.findViewById(R.id.recDsc);

            // set this cards properties
            if (temp != null)
            {
                description.setText(temp.getDesc());
                title.setText(temp.getTitle());

                if (view instanceof CardViewPC)
                {
                    CardViewPC a = (CardViewPC) view;
                    a.setWhoDis(temp.getTitle());
                    a.setEp(temp);
                }
            }

            // set listener
            view.setOnClickListener(handler1);
            ll.addView(view);
        }

    }
}
