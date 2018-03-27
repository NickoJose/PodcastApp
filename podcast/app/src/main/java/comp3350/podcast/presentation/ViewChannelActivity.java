package comp3350.podcast.presentation;

import android.app.Activity;
import android.widget.CompoundButton;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.podcast.R;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.business.AccessSubscriptions;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.ChannelList;
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
        channel = (Channel) getIntent().getSerializableExtra("channel");
        eps = new ArrayList<>();
        epIds = new ArrayList<>();
        accessEpisodes.getChannelEpisodes(eps,channel);

        accessEpisodes.getChannelEpisodes(eps, channel);

        updateText();
        populateList();
        setupSubscribeButton();
    }

    private void setupSubscribeButton() {
        ToggleButton subBtn = (ToggleButton) findViewById(R.id.subscribedTggl);
        AccessSubscriptions accessSubs = new AccessSubscriptions();

        ChannelList subs = new ChannelList();
        accessSubs.getSubs(subs);

        subBtn.setChecked(subs.contains(channel));

//        Button homeBtn = (Button)findViewById(R.id.backToHome);
//        homeBtn.setOnClickListener(homeBtnHandler);

        subBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                  AccessSubscriptions accessSubs = new AccessSubscriptions();

                                                  if (isChecked) {
                                                      accessSubs.insertSub(channel);

                                                  } else {
                                                      accessSubs.deleteSub(channel);
                                                  }


                                                  // updateText();
                                                  // populateList();
                                              }
                                          });}

//    View.OnClickListener homeBtnHandler = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            Intent intent = new Intent(ViewChannelActivity.this,MainActivity.class);
//            startActivity(intent);
//        }
//    };
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
                    //Toast.makeText(getApplicationContext(), "You clicked title: " + a.getWhoDis(), Toast.LENGTH_LONG).show();

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
