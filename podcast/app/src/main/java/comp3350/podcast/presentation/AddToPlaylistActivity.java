package comp3350.podcast.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.R;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.business.AccessPlaylists;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Playlist;

public class AddToPlaylistActivity extends AppCompatActivity {

    String episodeName;
    private List<Playlist> playlists;
    private AccessPlaylists accessPlaylists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_playlist);

        episodeName = (String) getIntent().getSerializableExtra("playlist");

        playlists = new ArrayList<>();
        accessPlaylists = new AccessPlaylists();

        String result = accessPlaylists.getPlaylists(playlists);
        if (result == null) {
            for (Playlist a : playlists) {
                createNewPlaylistBtn(a.getName(), a);
            }
        }

        else {
            Toast.makeText(this, "Database loading failed.", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Adds a playlist button to the activity
     *
     * @param label - label of the button
     * @param pl - playlist this button holds
     * @return - void.
     */
    private void createNewPlaylistBtn(String label, Playlist pl) {
        View view;
        PlaylistBtn btn;

        LinearLayout ll = findViewById(R.id.playlistsLayout);
        view = LayoutInflater.from(this).inflate(R.layout.playlist_link, ll, false);

        btn = view.findViewById(R.id.playlist_link);
        btn.setText(label);

        btn.setPlaylist(pl);

        btn.setOnClickListener(handlerPlaylist);
        ll.addView(view);
        System.out.println(label);
    }

    View.OnClickListener handlerPlaylist = new View.OnClickListener() {
        public void onClick(View v) {
            if (v instanceof PlaylistBtn) {
                PlaylistBtn a = (PlaylistBtn)v;

                AccessEpisodes accessEpisodes = new AccessEpisodes();
                List<Episode> epList = new ArrayList<>();
                accessEpisodes.getEpisodes(epList);
                for (Episode b : epList)
                {
                    if (b.getTitle().equals(episodeName))
                    {
                        accessPlaylists.insertPlaylistEpisode(b, a.getPlaylist());
                        Toast.makeText(getApplicationContext(), "Added to playlist", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    }
                }
            }
        }
    };
}
