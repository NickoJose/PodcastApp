package comp3350.podcast.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import comp3350.podcast.R;
import comp3350.podcast.business.AccessPlaylists;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Playlist;

public class PlaylistActivity extends AppCompatActivity {

    private List<Episode> recList;
    private ArrayList<Integer> recIds;
    private List<Playlist> playlists;
    private Playlist playlist;
    private AccessPlaylists accessPlaylists;
    private String playlistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        playlistName = (String) getIntent().getSerializableExtra("playlist");
        playlists = new ArrayList<>();
        accessPlaylists = new AccessPlaylists();
        recIds = new ArrayList<>();
        String result;

        getSupportActionBar().setTitle(playlistName);

        result = accessPlaylists.getPlaylists(playlists);
        if (result == null) {
            populatePlaylist();
        } else {
            Toast.makeText(this, "Database loading failed.", Toast.LENGTH_LONG).show();
        }

        Button newPlaylistBtn = findViewById(R.id.delButton);
        newPlaylistBtn.setOnClickListener(deleteHandler);
    }

    View.OnClickListener deleteHandler = new View.OnClickListener() {
        public void onClick(View v) {
            accessPlaylists.deletePlaylist(playlist);
            finish();
            Intent episodeIntent = new Intent(PlaylistActivity.this, MainActivity.class);
            episodeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(episodeIntent);
        }
    };

    /**
     * Populates the activity with playlist contents
     *
     * @return - void.
     */
    public void populatePlaylist() {
        boolean match = false;
        for (Playlist a : playlists) {
            if (a.getName().equalsIgnoreCase(playlistName)) {
                match = true;
                recList = a.getEpisodes();
                playlist = a;
                break;
            }
        }

        if (match && recList.size() == 0) {
            Toast.makeText(getApplicationContext(), "This Playlist is Empty", Toast.LENGTH_LONG).show();
        }
        else if (match) {
            View.OnClickListener handler1 = new View.OnClickListener() {
                public void onClick(View v) {
                    if (v instanceof CardViewPC) {
                        CardViewPC a = (CardViewPC) v;
                        //Toast.makeText(getApplicationContext(), "You clicked title: " + a.getWhoDis(), Toast.LENGTH_LONG).show();

                        Intent episodeIntent = new Intent(PlaylistActivity.this, ViewEpisodeActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("episode", a.getEp());
                        episodeIntent.putExtras(b);
                        startActivity(episodeIntent);
                    }
                }
            };
            CardList.createEpisodeCardList(handler1, findViewById(R.id.resultLayout), this, R.layout.card_search, recList, recIds);
        }
    }
}
