package comp3350.podcast;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import comp3350.podcast.objects.EpisodeList;
import comp3350.podcast.representation.CardViewPC;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> recIds = new ArrayList<Integer>();
    private String[] recThumbNails = {""};
    private String[] recTitles = {"Title 1", "Title 2", "Title 3", "Title 4", "Title 5"};
    private String[] recDescriptions = {"This one is about politics", "This ones about DND", "I\nLike trains", "long sentence how does the interface handle it, who knows?", "description 5"};
    private EpisodeList recList; // recommended podcasts list

    public MainActivity()
    {
        // todo: business get recommended titles/descriptions/thumbnail url arrays
        recList = new EpisodeList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateRecList(recTitles, recDescriptions);

        Button btn = findViewById(R.id.newPlaylist);
        btn.setOnClickListener(playlistHandler);
    }

    View.OnClickListener playlistHandler = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Toast.makeText(getApplicationContext(), "You clicked New Playlist", Toast.LENGTH_LONG).show();
            makePlaylist();
        }
    };

    private void createNewPlaylistBtn(String label) {
        View view;
        Button btn;

        LinearLayout ll = findViewById(R.id.playlistsLayout);
        view = LayoutInflater.from(this).inflate(R.layout.playlist_link, ll, false);

        btn = view.findViewById(R.id.playlist_link);
        btn.setText(label);

        ll.addView(view);
    }

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

    // Populates recommended video list.
    private void populateRecList(String[] titles, String[] descriptions)
    {
        int index = 0;
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
                }
            }
        };

        // update titles
        if (titles != null && descriptions != null) {
            if (titles.length == descriptions.length && titles.length > 0) {

                for (index = 0; index < descriptions.length; index++) {
                    // get layouts
                    LinearLayout ll = findViewById(R.id.recLayout);
                    view = LayoutInflater.from(this).inflate(R.layout.card, ll, false);
                    recIds.add(view.getId());
                    if (view instanceof CardViewPC)
                    {
                        CardViewPC a = (CardViewPC) view;
                        a.setWhoDis(titles[index]);
                    }

                    // todo: use this to load thumbnails or get rid of it
                    //Add thumbnail
                    /*
                    File imgFile = new File("C:\\Users\\Gareth\\StudioProjects\\SoftEngProject\\podcast\\app\\src\\main\\java\\comp3350\\podcast\\cat.jpg");
                    if (imgFile.exists()){

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                        ImageView myImage = view.findViewById(R.id.recImageView);

                        myImage.setImageBitmap(myBitmap);

                    }
                    */

                    // set this cards title
                    title = view.findViewById(R.id.recTitle);
                    title.setText(titles[index]);

                    // set this cards description
                    description = view.findViewById(R.id.recDsc);
                    description.setText(descriptions[index]);

                    // set listener
                    view.setOnClickListener(handler1);
                    ll.addView(view);
                }
            }
        }
    }


}
