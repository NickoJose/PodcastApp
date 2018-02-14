package comp3350.podcast.representation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.podcast.R;
import comp3350.podcast.application.Main;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.objects.Episode;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> recIds;
    private ArrayList<Episode> recList;
    private AccessEpisodes accessEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.activity_main);

        recIds = new ArrayList<>();
        recList = new ArrayList<>();
        accessEpisodes = new AccessEpisodes();
        String result = accessEpisodes.getEpisodes(recList);

        if (result == null)
        {
            populateRecList();
        }


        Button btn = findViewById(R.id.newPlaylist);
        btn.setOnClickListener(playlistHandler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    View.OnClickListener playlistHandler = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Toast.makeText(getApplicationContext(), "You clicked New Playlist", Toast.LENGTH_LONG).show();
            makePlaylist();
        }
    };

    /**
     * Creates a button for a new playlist.
     *
     * @param label - button label
     * @return - void
     */
    private void createNewPlaylistBtn(String label) {
        View view;
        Button btn;

        LinearLayout ll = findViewById(R.id.playlistsLayout);
        view = LayoutInflater.from(this).inflate(R.layout.playlist_link, ll, false);

        btn = view.findViewById(R.id.playlist_link);
        btn.setText(label);

        ll.addView(view);
    }

    /**
     * Makes a new playlist. Prompts user to name it
     *
     * @return - void
     */
    private void makePlaylist(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter playlist name");
        // Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createNewPlaylistBtn(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    /**
     * Populates recommended list with suggestions. Sets onClick handlers for recommended episodes.
     *
     * @return - void
     */
    private void populateRecList()
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

                    Intent episodeIntent = new Intent(MainActivity.this, viewEpisode.class);
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
                    LinearLayout ll = findViewById(R.id.recLayout);
                    view = LayoutInflater.from(this).inflate(R.layout.card, ll, false);
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
