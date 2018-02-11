package comp3350.podcast.representation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import comp3350.podcast.objects.*;
import comp3350.podcast.R;
import comp3350.podcast.objects.Episode;

public class viewChannel extends AppCompatActivity {

    private Channel channel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_channel);

        channel = (Channel)getIntent().getSerializableExtra("channel");

        updateText();
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
