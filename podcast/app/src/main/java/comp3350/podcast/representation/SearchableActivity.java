package comp3350.podcast.representation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.podcast.R;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.objects.Episode;

public class SearchableActivity extends AppCompatActivity {
    private ArrayList<Integer> recIds;
    private ArrayList<Episode> recList;
    private AccessEpisodes accessEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        // get database content
        recIds = new ArrayList<>();
        recList = new ArrayList<>();
        accessEpisodes = new AccessEpisodes();
        String result = accessEpisodes.getEpisodes(recList);

        if (result == null)
        {
            populateResultList();
        }

        String search = (String)getIntent().getSerializableExtra("search");

    }

    /**
     * Populates Result list with matching episodes. Sets onClick handlers for the episodes.
     *
     * @return - void
     */
    private void populateResultList()
    {
        int index = 0;
        Episode temp;
        TextView title = null;
        TextView description = null;
        View view;
        View.OnClickListener handler1 = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (v instanceof CardViewPC) {
                    CardViewPC a = (CardViewPC) v;
                    Toast.makeText(getApplicationContext(), "You clicked title: " + a.getWhoDis(), Toast.LENGTH_LONG).show();

                    Intent episodeIntent = new Intent(SearchableActivity.this, viewEpisode.class);
                    Bundle b = new Bundle();
                    b.putSerializable("episode", a.getEp());
                    episodeIntent.putExtras(b);
                    startActivity(episodeIntent);
                }
            }
        };

        // update titles

        for (index = 0; index < recList.size(); index++) {
            // get layouts
            LinearLayout ll = findViewById(R.id.resultLayout);
            view = LayoutInflater.from(this).inflate(R.layout.card_search, ll, false);
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
