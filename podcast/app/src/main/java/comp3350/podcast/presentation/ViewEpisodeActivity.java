package comp3350.podcast.presentation;

import android.content.Intent;
import android.widget.ImageView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import comp3350.podcast.objects.*;

import comp3350.podcast.R;
import comp3350.podcast.objects.Episode;


public class ViewEpisodeActivity extends AppCompatActivity {

    private Episode ep; //Needed for PlayContentActivity call
    private String title;
    private String author;
    private String url;
    private String desc;
    private String category;
    private String date;
    private int length;
    private int epnum;
    private String chName;
    private Channel ch;


    /**
     * Launches the PlayContentActivity activity with this episode bundled in
     *
     * @return - void
     */
    private void tryPlay(View v){
        Intent episodeIntent = new Intent(ViewEpisodeActivity.this, PlayContentActivity.class);
        Bundle b = new Bundle();

        b.putSerializable("episode", ep);
        episodeIntent.putExtras(b);
        startActivity(episodeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_episode);

        ep = (Episode)getIntent().getSerializableExtra("episode");
        title = ep.getTitle();
        author = ep.getAuthor();
        url = ep.getUrl();
        desc = ep.getDesc();
        category = ep.getCategory();
        date = ep.getPublishDate().toString();
        length = ep.getLength();
        epnum = ep.getEpNum();
        chName = ep.getChannelTitle();
        ch = ep.getChannel();

        //Just us the back button that is part of the base android UI (little triangle)
//        Button channelButton = findViewById(R.id.back_to_channel);
//        channelButton.setOnClickListener(backToChannel);

        Button playButton = findViewById(R.id.play);
        playButton.setOnClickListener(play);

        updateText();

        ImageView imgView = (ImageView)findViewById(R.id.viewEpisode_thumbnail);
        int imgID = getResources().getIdentifier(ep.getImg(),"drawable",getPackageName());
        imgView.setImageResource(imgID);

//        Button homeBtn = (Button)findViewById(R.id.backToHome);
//        homeBtn.setOnClickListener(homeBtnHandler);

        Button addToBtn = findViewById(R.id.addToPlayL);
        addToBtn.setOnClickListener(addtoBtnHandler);

    }

    View.OnClickListener addtoBtnHandler;

    {
        addtoBtnHandler = new View.OnClickListener() {
            public void onClick(View v) {
                Intent episodeIntent = new Intent(ViewEpisodeActivity.this, AddToPlaylistActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("playlist", title);
                episodeIntent.putExtras(b);
                startActivity(episodeIntent);
            }
        };
    }
//
//    View.OnClickListener homeBtnHandler = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            Intent intent = new Intent(ViewEpisodeActivity.this,MainActivity.class);
//            startActivity(intent);
//        }
//    };
//
//    View.OnClickListener backToChannel = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent channelIntent = new Intent(ViewEpisodeActivity.this,ViewChannelActivity.class);
//            Bundle b = new Bundle();
//            b.putSerializable("channel",ch);
//            channelIntent.putExtras(b);
//            startActivity(channelIntent);
//        }
//    };

    /**
     * Updates text on the episode view, according to the latest object state.
     *
     * @return - void
     */
    private void updateText()
    {
        TextView newText = (TextView) findViewById(R.id.title);

        newText.setText("Title:\t"+title+"\n"
                +"Author:\t"+author+"\n"
                +"Url:\t"+url);//crashes without concat for some reason

        newText = (TextView) findViewById(R.id.extrainfo);
        newText.setText("Category:\t"+category+"\n"
                +"Publish Date:\t"+date+"\n"
                +"Length:\t"+length+"\n"
                +"Episode #:\t"+epnum+"\n"
                +"Channel:\t"+chName);


        newText = (TextView) findViewById(R.id.description);
        newText.setText(""+desc);//crashes without concat for some reason
    }



    View.OnClickListener play = new View.OnClickListener() {
        ///@Override
        public void onClick(View v) {
            tryPlay(v);
        }
    };

}
