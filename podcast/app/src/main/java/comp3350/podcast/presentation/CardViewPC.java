package comp3350.podcast.presentation;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import comp3350.podcast.objects.DescribedObject;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Channel;

public class CardViewPC extends CardView{
    private String whoDis;
    //private Episode episode;
    private DescribedObject obj;
    public CardViewPC(Context context) {super(context);}
    public CardViewPC(Context context, AttributeSet attrs) {super(context, attrs);}
    public CardViewPC(Context context, AttributeSet attrs, int defStyleAttr) {super(context, attrs, defStyleAttr);}

    public void setWhoDis(String whoDis){
        this.whoDis = whoDis;
    }
    public String getWhoDis()
    {
        return whoDis;
    }
    public void setEp(Episode episode) { this.obj = episode; }
    public void setCh(Channel channel) { this.obj = channel; }
    public Episode getEp()
    {
        return (Episode)obj;
    }
    public Channel getCh()
    {
        return (Channel)obj;
    }

    public DescribedObject getObj()
    {
        if(obj instanceof Episode)
        {
            return getEp();
        }
        else
        {
            return getCh();
        }
    }



}
