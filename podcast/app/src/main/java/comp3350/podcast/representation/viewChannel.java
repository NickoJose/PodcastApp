package comp3350.podcast.representation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.objects.*;
import comp3350.podcast.R;
import comp3350.podcast.objects.Episode;

public class viewChannel extends AppCompatActivity {

    private AccessEpisodes accessEpisodes;
    private Channel channel;
    private ArrayList<Episode> eps;
    private ArrayList<Integer> epIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_channel);

        accessEpisodes = new AccessEpisodes();
        channel = (Channel)getIntent().getSerializableExtra("channel");
        eps = new ArrayList<>();
        epIds = new ArrayList<>();
        accessEpisodes.getChannelEpisodes(eps,channel);

        Button homeBtn = (Button)findViewById(R.id.backToHome);
        homeBtn.setOnClickListener(homeBtnHandler);


        updateText();
        populateList();
    }

    View.OnClickListener homeBtnHandler = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(viewChannel.this,MainActivity.class);
            startActivity(intent);
        }
    };
    /**
     * Populates episode ListView on GUI
     *
     * @return - void
     */
    public void populateList()
    {

        LinearLayout list = (LinearLayout) findViewById(R.id.episode_list);

        View.OnClickListener handler1 = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (v instanceof CardViewPC) {
                    CardViewPC a = (CardViewPC) v;
                    Toast.makeText(getApplicationContext(), "You clicked title: " + a.getWhoDis(), Toast.LENGTH_LONG).show();

                    Intent episodeIntent = new Intent(viewChannel.this, viewEpisode.class);
                    Bundle b = new Bundle();
                    b.putSerializable("episode", a.getEp());
                    episodeIntent.putExtras(b);
                    startActivity(episodeIntent);
                }
            }
        };
        CardList.createEpisodeCardList(handler1,findViewById(R.id.episode_list),this,R.layout.card_search,eps,epIds);
    }

    /**
     * Updates the information displayed on a channel according to the latest channel state
     *
     * @return - void
     */
    public void updateText()
    {
        TextView newText = (TextView) findViewById(R.id.channel_info);

        newText.setText("Title:\t"+channel.getTitle()+"\n"
                +"Author:\t"+channel.getAuthor()+"\n"
                +"Category:\t"+channel.getCategory()+"\n"
                +"Publish Date:\t"+channel.getPublishDate().toString()+"\n\n"
                +channel.getDesc()+"\n\n"
                +"Url:\t"+channel.getUrl());


    }
}
