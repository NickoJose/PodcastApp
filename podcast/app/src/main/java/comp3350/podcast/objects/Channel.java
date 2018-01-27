/*
Jackson Barker
Software Engineering group K
*/
package comp3350.podcast.objects;

public class Channel
{

    //vars
    private String title;
    private String desc; //description of the channel
    public String url;
    private Episode lastPlayed;
    private int numEps;

    public Channel(String title , String desc, String url)
    {
        this.title = title;
        this.desc = desc;
        this.url = url;
        numEps = 0;
        lastPlayed = null;
    }
    //================================ GETTERS ==========================================//
    public String getTitle(){return title;}
    public String getDesc(){return desc;}
    public String getUrl(){return url;}
    public Episode getLastPlayed(){return lastPlayed;}
    public int getNumEps(){return numEps;}

    public boolean equals(Object obj)
    {
        boolean result = false;
        Channel ch;

        if(obj instanceof Episode)
        {
            ch = (Channel)obj;
            if(ch.getTitle() == title && ch.getUrl() == url)
            {
                result = true;
            }
        }
        return result;
    }//equals

    public String toString()
    {
        return ("Channel name: "+title);
    }

    public void incNumEps()
    {
        numEps++;
    }//incNumEps

}//channel