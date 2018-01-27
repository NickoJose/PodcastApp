/*
Jackson Barker
Software Engineering group K
*/
package comp3350.podcast.objects;


public class Episode
{
    //Vars
    private String title;
    private String desc; //description
    private String url;
    private double length;
    private Channel ch; //channel this episode belongs to
    private double timeStamp; //if previously played, episode will resume from this point
    ///something for the thumbnail...

    public Episode(String title, String url, String desc, double length, Channel ch)
    {
        this.title = title;
        this.url = url;
        this.desc = desc;
        this.length = length;
        this.ch = ch;
        timeStamp = 0.0;
    }//constructor

//================================ GETTERS ==========================================//
public String getTitle(){return title;}
public String getUrl(){return url;}
public String getDesc(){return desc;}
public double getLength(){return length;}
public Channel getChannel(){return ch;} //for reference to actual channel
public String getChannelTitle(){return ch.getTitle();} //for displaying just the name of the channel
public double getTimeStamp(){return timeStamp;}


//================================ SETTERS ==========================================//
//these probably have to go in a separate business class?

    //if it didn't have one associated in the first place for some reason
    //returns false if channel doesn't exist
    public boolean setChannel(Channel ch)
    {  
        boolean result = false;
        if(ch != null)
        {
            this.ch = ch;
            result = true;
        }
        return result;
    }//setChannel

    //set the Episodes timestamp given where it paused last
    public void setTimeStamp(double t)
    {
        if(t <= length) //just in case
        {
            timeStamp = t;
        } 
        else
        {
            timeStamp = 0.0;
        }
    }//setTimeStamp

    public String toString()
    {
        return ("Episode name: "+title);
    }

    public boolean equals(Object obj)
    {
        boolean result = false;
        Episode ep;

        if(obj instanceof Episode)
        {
            ep = (Episode)obj;
            if(ep.getTitle() == title && ep.getUrl() == url)
            {
                result = true;
            }
        }
        return result;
    }//equals

}//episode