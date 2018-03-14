package comp3350.podcast.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import comp3350.podcast.R;
import comp3350.podcast.application.Main;
import comp3350.podcast.business.AccessChannels;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.business.AccessSubscriptions;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<Integer> recIds;
    private ArrayList<Episode> recList;
    private AccessEpisodes accessEpisodes;

    private AccessChannels accessChannels;
    private AccessSubscriptions accessSubs;
    private ArrayList<Channel> chList;

    private static Context context = null;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        copyDatabaseToDevice();
        Main.startUp();
        setContentView(R.layout.activity_main);

        MainActivity.context = getContext();

        recIds = new ArrayList<>();
        recList = new ArrayList<>();
        accessEpisodes = new AccessEpisodes();

        chList = new ArrayList<>();
        accessChannels = new AccessChannels();
        accessSubs = new AccessSubscriptions();


        String result = accessEpisodes.getEpisodes(recList);

        if (result == null) {
            populateRecList();
        } else {
            Toast.makeText(this, "Database loading failed.", Toast.LENGTH_LONG).show();
        }

        result = accessChannels.getChannels(chList);

        if (result == null) {
            recyclerView = (RecyclerView) findViewById(R.id.bodyRecyclerView);

            // use a linear layout manager
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            displayAllChannels();
        }

        Button newPlaylistBtn = findViewById(R.id.newPlaylist);
        newPlaylistBtn.setOnClickListener(playlistHandler);

        Button subsBtn = findViewById(R.id.subsButton);
        subsBtn.setOnClickListener(subsButtonHandler);

        Button channelsBtn = findViewById(R.id.channelsButton);
        channelsBtn.setOnClickListener(allChannelsButtonHandler);

        Button allBtn = findViewById(R.id.allButton);
        allBtn.setOnClickListener(allEpisiodesButtonHandler);

        Button searchBtn = findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(searchHandler);
    }

    View.OnClickListener subsButtonHandler = new View.OnClickListener() {
        public void onClick(View v) {
            recyclerView.removeAllViews();
            displaySubscribedChannels();
            Toast.makeText(getApplicationContext(), "Showing subscribed Channels", Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener allChannelsButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recyclerView.removeAllViews();
            displayAllChannels();
            Toast.makeText(getApplicationContext(), "Showing all Channels", Toast.LENGTH_LONG).show();
        }
    };


    View.OnClickListener allEpisiodesButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recyclerView.removeAllViews();
            displayAllEpisodes();
            Toast.makeText(getApplicationContext(), "Showing all episodes", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    View.OnClickListener playlistHandler = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "You clicked New Playlist", Toast.LENGTH_LONG).show();
            makePlaylist();
        }
    };

    View.OnClickListener searchHandler = new View.OnClickListener() {
        public void onClick(View v) {

            TextInputLayout text = findViewById(R.id.searchString);

            String searchString = text.getEditText().getText().toString();

            Intent searchIntent = new Intent(MainActivity.this, SearchableActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("search", searchString);
            searchIntent.putExtras(b);
            startActivity(searchIntent);
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
    private void makePlaylist() {
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
    private void populateRecList() {
        View.OnClickListener handler1 = new View.OnClickListener() {
            public void onClick(View v) {
                if (v instanceof CardViewPC) {
                    CardViewPC a = (CardViewPC) v;

                    Intent episodeIntent = new Intent(MainActivity.this, ViewEpisodeActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("episode", a.getEp());
                    episodeIntent.putExtras(b);
                    startActivity(episodeIntent);
                }
            }
        };
        CardList.createEpisodeCardList(handler1, findViewById(R.id.recLayout), this, R.layout.card, recList, recIds);
    }

    /**
     * Populates list with channels. Sets onClick handlers for each channel.
     *
     * @return - void
     */
    private void displayAllChannels() {
        accessChannels.getChannels(chList);
        recyclerView.setAdapter(new ChannelListAdapter(chList, this));
    }

    /**
     * Populates list with channels. Sets onClick handlers for each channel.
     *
     * @return - void
     */
    private void displaySubscribedChannels() {
        String result = accessSubs.getSubs(chList);
        if (result == null)
            recyclerView.setAdapter(new ChannelListAdapter(chList, this));
        else
            System.out.println(result);
    }

    /**
     * Populates quick list with all episodes. Sets onClick handlers for each episode.
     *
     * @return - void
     */
    private void displayAllEpisodes() {
        ArrayList<Episode> eps = new ArrayList<>();
        accessEpisodes.getEpisodes(eps);

        recyclerView.setAdapter(new EpisodeListAdapter(eps, this));
    }

    /**
     * Copies the database to the device
     *
     * @return - void
     */
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
            Toast.makeText(this, "Unable to access application data: " + ioe.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Copies the database to the directory specified
     *
     * @param assets    - database to be copied
     * @param directory - target directory
     * @return - void
     */
    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }


}
