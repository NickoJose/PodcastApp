package comp3350.podcast.presentation;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import comp3350.podcast.R;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Channel;
import android.widget.ImageView;


public final class CardList {

    private CardList(){}

    /**
     * Creates a list of channel cards in a given layout/list
     *
     * @param handler1 - the OnClickListener for the items you want listed
     * @param layout - the layout the cards will be placed in
     * @param context - the calling activity's context
     * @param resource - either card or card_search (what is being displayed)
     * @param recList - list of items to be made into cards
     * @param recIds - integer ids of each item in recList
     *
     * @return void
     */
    public static void createChannelCardList(View.OnClickListener handler1, View layout, Context context,
                                             int resource,List<Channel> recList, ArrayList<Integer> recIds)
    {
        int index = 0;
        Channel temp;
        TextView title = null;
        TextView description = null;
        ImageView imgView;
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

    /**
     * Creates a list of episode cards in a given layout/list
     *
     * @param handler1 - the OnClickListener for the items you want listed
     * @param layout - the layout the cards will be placed in
     * @param context - the calling activity's context
     * @param resource - either card or card_search (what is being displayed)
     * @param recList - list of items to be made into cards
     * @param recIds - integer ids of each item in recList
     *
     * @return void
     */
    public static void createEpisodeCardList(View.OnClickListener handler1, View layout, Context context,
                                             int resource,List<Episode> recList, ArrayList<Integer> recIds)
    {
        int index = 0;
        Episode temp;
        TextView title = null;
        TextView desc = null;
        ImageView imgView;
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
            desc = view.findViewById(R.id.recDsc);

            imgView = view.findViewById(R.id.recImageView);

            // set this cards properties
            if (temp != null)
            {
                desc.setText(temp.getDesc());
                title.setText(temp.getTitle());

                int imgID = context.getResources().getIdentifier(temp.getImg(),"drawable",context.getPackageName());
                imgView.setImageResource(imgID);

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
