package comp3350.podcast.representation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.R;
import comp3350.podcast.application.Main;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.objects.Episode;


public final class CardList {

    private CardList(){}

    public static void createEpisodeCardList(View.OnClickListener handler1, View layout, Context context,
                                             int resource,List<Episode> recList, ArrayList<Integer> recIds)
    {
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
