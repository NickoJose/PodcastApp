package comp3350.podcast.representation;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import comp3350.podcast.objects.Episode;

/**
 * Created by Gareth on 2018-02-01.
 */

public class CardViewPC extends CardView{
    private String whoDis;
    private Episode episode;
    public CardViewPC(Context context) {super(context);}
    public CardViewPC(Context context, AttributeSet attrs) {super(context, attrs);}
    public CardViewPC(Context context, AttributeSet attrs, int defStyleAttr) {super(context, attrs, defStyleAttr);}

    public void setWhoDis(String whoDis){
        this.whoDis = whoDis;
    }
    public void setEp(Episode episode) { this.episode = episode; }

    public Episode getEp()
    {
        return episode;
    }
    public String getWhoDis()
    {
        return whoDis;
    }

}
