package comp3350.podcast.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.objects.*;
import comp3350.podcast.R;
import comp3350.podcast.objects.Episode;

public class ViewChannelActivity extends AppCompatActivity {

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

        updateText();
        populateList();
    }

    /**
     * Populates episode ListView on GUI
     *
     * @return - void
     */
    public void populateList()
    {

        View.OnClickListener handler1 = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (v instanceof CardViewPC) {
                    CardViewPC a = (CardViewPC) v;

                    Intent episodeIntent = new Intent(ViewChannelActivity.this, ViewEpisodeActivity.class);
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
