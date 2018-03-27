package comp3350.podcast.presentation;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import comp3350.podcast.R;
import comp3350.podcast.objects.Episode;

public class PlayContentActivity extends AppCompatActivity {

    SeekBar seekbar;
    Episode ep;
    boolean isPaused = false;
    Handler handler = new Handler();
    private Runnable handlerTimer = new Runnable(){
        @Override
        public void run(){
            handler.postDelayed(handlerTimer,1000);
            timerAction();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_content);


        seekbar = findViewById(R.id.playContent_seekBar);

        ep = (Episode)getIntent().getSerializableExtra("episode");
        updateScreen();
        //Make fields read only
        findViewById(R.id.title).setFocusable(false);
        findViewById(R.id.time).setFocusable(false);

        //Event handlers

        findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isPaused = !isPaused;

                if(ep.getTimeStamp() >= ep.getLength()){
                    ep.setTimeStampInt(0);
                    seekbar.setProgress(0);
                }

                updateScreen();
            }
        });

        seekbar.setMax(ep.getLength());
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                ep.setTimeStampInt(progress);
                updateScreen();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        ImageView imgView = (ImageView)findViewById(R.id.imageView3);
        int imgID = getResources().getIdentifier(ep.getImg(),"drawable",getPackageName());
        imgView.setImageResource(imgID);
        handler.post(handlerTimer);
    }


    private void timerAction(){
        if(!isPaused){
            ep.incTimeStamp();
            seekbar.setProgress(ep.getTimeStamp());
            if(ep.getTimeStamp() >= ep.getLength()){
                isPaused = true;
                updateScreen();
            }
        }
    }

    private void updateScreen(){
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(ep.getTitle());

        Button button = findViewById(R.id.searchButton);

        if(isPaused){
            button.setText("Play");
        }else{
            button.setText("Pause");
        }

        updateTime();
    }

    private void updateTime(){
        TextView time = (TextView) findViewById(R.id.time);
        time.setText(ep.getTimeStampString()+"/"+ep.getLengthString());
    }

}
