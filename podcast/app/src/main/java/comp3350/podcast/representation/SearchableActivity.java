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
import java.util.LinkedList;

import comp3350.podcast.R;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.objects.Episode;

public class SearchableActivity extends AppCompatActivity {
    private ArrayList<Integer> recIds;
    private ArrayList<Episode> tempList;
    private LinkedList<Episode> recList;
    private AccessEpisodes accessEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        // get database content
        recIds = new ArrayList<>();
        tempList = new ArrayList<>();
        recList = new LinkedList<>();
        accessEpisodes = new AccessEpisodes();
        String result = accessEpisodes.getEpisodes(tempList);

        String search = (String)getIntent().getSerializableExtra("search");
        if (result == null)
        {
            recList = getRelavenceList(tempList, search);
            populateResultList();
        }

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
    /**
     * Populates a linkedlist with episodes similar to key.
     *
     * @return - void
     */
    // todo: move to business eventualy
    public LinkedList<Episode> getRelavenceList(ArrayList<Episode> in, String key)
    {
        LinkedList<Episode> out2 = new LinkedList<>();


        int l;
        int bestL = 0;
        int decent = 3;
        int index;

        // very janky but quick
        for(Episode a : in)
        {
            l = lcs(a.getTitle(), key).length();

            if (l > bestL || bestL == l)
            {
                bestL = l;
                out2.addFirst(a);
            }
            else out2.addLast(a);

        }

        return out2;
    }
// todo: move to business with the above
    public static String lcs(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int[][] lengths = new int[a.length()+1][b.length()+1];

        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < b.length(); j++)
                if (a.charAt(i) == b.charAt(j))
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                            Math.max(lengths[i+1][j], lengths[i][j+1]);

        // recover string from matrix
        StringBuffer sb = new StringBuffer();
        for (int x = a.length(), y = b.length();
             x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x-1][y])
                x--;
            else if (lengths[x][y] == lengths[x][y-1])
                y--;
            else {
                assert a.charAt(x-1) == b.charAt(y-1);
                sb.append(a.charAt(x-1));
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }

}
