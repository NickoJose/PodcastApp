package comp3350.podcast.presentation;

import android.content.Context;
import android.util.AttributeSet;

import comp3350.podcast.objects.Playlist;

public class PlaylistBtn extends android.support.v7.widget.AppCompatButton {

    static private int btnId = 0;
    private int btnIdUnique;
    private Playlist pl;

    public PlaylistBtn(Context context) {super(context);
    btnIdUnique = btnId; btnId++;}

    public PlaylistBtn(Context context, AttributeSet attrs) {super(context, attrs);
    btnIdUnique = btnId; btnId++;}

    public PlaylistBtn(Context context, AttributeSet attrs, int defStyleAttr) {super(context, attrs, defStyleAttr);
    btnIdUnique = btnId; btnId++;}

    //public int getBtnId(){return super.getText().toString();}
    public Playlist getPlaylist(){
        return pl;
    }
    public void setPlaylist(Playlist pl)
    {
        this.pl = pl;
    }
}
