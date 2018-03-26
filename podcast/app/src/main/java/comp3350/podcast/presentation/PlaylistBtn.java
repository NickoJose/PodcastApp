package comp3350.podcast.presentation;

import android.content.Context;
import android.util.AttributeSet;

public class PlaylistBtn extends android.support.v7.widget.AppCompatButton {

    static private int btnId = 0;
    private int btnIdUnique;

    public PlaylistBtn(Context context) {super(context);
    btnIdUnique = btnId; btnId++;}

    public PlaylistBtn(Context context, AttributeSet attrs) {super(context, attrs);
    btnIdUnique = btnId; btnId++;}

    public PlaylistBtn(Context context, AttributeSet attrs, int defStyleAttr) {super(context, attrs, defStyleAttr);
    btnIdUnique = btnId; btnId++;}

    public int getBtnId(){return  btnIdUnique;}
}
