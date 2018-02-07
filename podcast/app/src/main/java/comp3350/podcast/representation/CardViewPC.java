package comp3350.podcast.representation;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by Gareth on 2018-02-01.
 */

public class CardViewPC extends CardView {
    private String whoDis;
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

}
