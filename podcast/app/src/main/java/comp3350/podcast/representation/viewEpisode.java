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
import android.widget.Toast;

import comp3350.podcast.objects.*;

import comp3350.podcast.R;
import comp3350.podcast.objects.Episode;
import java.io.Serializable;


public class viewEpisode extends AppCompatActivity {

    private String title;
    private String author;
    private String url;
    private String desc;
    private String category;
    private String date;
    private Double length;
    private int epnum;
    private String chName;
    private Channel ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_episode);





       /* title = b.getString("title");
        author = b.getString("author");
        url = b.getString("url");
        desc = b.getString("desc");
        category = b.getString("category");
        date = b.getString("date");
        length = b.getDouble("length");
        epnum = b.getInt("epnum");
*/
        Episode ep = (Episode)getIntent().getSerializableExtra("episode");
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


        Button channelButton = findViewById(R.id.back_to_channel);
        channelButton.setOnClickListener(backToChannel);

        Button playButton = findViewById(R.id.play);
        playButton.setOnClickListener(play);

        //Toast.makeText(getApplicationContext(),"Test Toast",Toast.LENGTH_LONG).show();
        updateText();

    }
    View.OnClickListener backToChannel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent channelIntent = new Intent(viewEpisode.this,viewChannel.class);
            Bundle b = new Bundle();
            b.putSerializable("channel",ch);
            channelIntent.putExtras(b);
            startActivity(channelIntent);
        }
    };

    private void updateText()
    {
        TextView newText = (TextView) findViewById(R.id.title);
        //String date = (ep.getPublishDate().toString());
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
            Toast.makeText(getApplicationContext(),"You clicked the Play button",Toast.LENGTH_LONG).show();
            ///
        }
    };

}
