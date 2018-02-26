package comp3350.podcast.representation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

import comp3350.podcast.R;
import comp3350.podcast.objects.Episode;

import static android.support.v4.app.ServiceCompat.START_STICKY;

public class playContent extends AppCompatActivity {

    Episode ep;
    boolean isPaused = true;
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

        ep = (Episode)getIntent().getSerializableExtra("episode");
        updateScreen();
        //Make fields read only
        findViewById(R.id.title).setFocusable(false);
        findViewById(R.id.time).setFocusable(false);

        //Event handlers

        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isPaused = !isPaused;
                updateScreen();
            }
        });

        findViewById(R.id.playContent_progressBar).setOnDragListener(new View.OnDragListener(){
            @Override
            public boolean onDrag(View v, DragEvent de){
                return true;
            }
        });




        //TODO: Do the thing, Zhu-li!
        handler.post(handlerTimer);
    }


    private void timerAction(){
        if(!isPaused){
            ep.incTimeStamp();
            updateScreen();
        }
    }

    private void updateScreen(){
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(ep.getTitle());

        Button button = findViewById(R.id.playButton);

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
