package comp3350.podcast.representation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_channel);

        accessEpisodes = new AccessEpisodes();
        channel = (Channel)getIntent().getSerializableExtra("channel");
        eps = new ArrayList<>();
        accessEpisodes.getChannelEpisodes(eps,channel);



        updateText();
        populateList();
    }

    // TODO make list entries go to relevant episode on click
    public void populateList()
    {

        ListView list = (ListView) findViewById(R.id.episode_list);

        ArrayAdapter<Episode> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,eps);
        list.setAdapter(adapter);


    }

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
