/*
Jackson Barker
Software Engineering group K
*/
package comp3350.podcast.objects;


import android.support.annotation.NonNull;

import java.io.Serializable;

public class Episode implements Serializable {
    //Vars
    private String title;
    private String subtitle;
    private String desc; //description
    private String url;
    private int length; //length in seconds
    private Channel ch; //channel this episode belongs to
    private int timeStamp; //if previously played, episode will resume from this point
    private Date publishDate;
    private String author;
    private String category; // /genre
    private int epNum; //episode number


    ///something for the thumbnail...

    public Episode(String title, String url, String desc,
                   int length, Channel ch,Date publishDate,
                   String author, String category,int epNum)
    {
        this.title = title;
        this.url = url;
        this.desc = desc;
        this.length = length;
        this.ch = ch;
        this.publishDate = publishDate;
        this.author = author;
        this.category = category;
        this.epNum = epNum;
        timeStamp = 0;

        publishDate = new Date();


    }//constructor

//================================ GETTERS ==========================================//
public String getTitle(){return title;}
public String getUrl(){return url;}
public String getDesc(){return desc;}
public int getLength(){return length;}
public Channel getChannel(){return ch;} //for reference to actual channel
public Date getPublishDate(){return publishDate;}
public String getChannelTitle(){return ch.getTitle();} //for displaying just the name of the channel
public String getAuthor(){return author;}
public String getCategory(){return category;}
public int getEpNum(){return epNum;}
public double getTimeStamp(){return timeStamp;}


//================================ SETTERS ==========================================//
//these probably have to go in a separate business class?

    //if it didn't have one associated in the first place for some reason
    //returns false if channel doesn't exist
    public boolean setChannel(Channel ch) {
        boolean result = false;
        if (ch != null) {
            this.ch = ch;
            result = true;
        }
        return result;
    }//setChannel

    //set the Episodes timestamp given where it paused last
    public void setTimeStamp(int t) {
        if (t <= length) //just in case
        {
            timeStamp = t;
        } else {
            timeStamp = 0;
        }
    }//setTimeStamp

    public String toString() {
        return ("Episode #"+epNum+"\t\"" + title+"\"");
    }

    public void incTimeStamp(){
        timeStamp++;
    }

    public boolean equals(Object obj) {
        boolean result = false;
        Episode ep;

        if (obj instanceof Episode) {
            ep = (Episode) obj;
            if (ep.getTitle().equals(title) && ep.getUrl().equals(url)) {
                result = true;
            }
        }
        return result;
    }//equals

    /**
     * Compare this episode's title/published date/category/length to the target
     *
     * Returns -1 if episode var is less than var
     * Returns 0 if episode var is equal than var
     * Returns 1 if episode var is greater than var
     *
     * @param var  - var to compare
     * @return see description
     */
    public int compareTo(@NonNull Object var) {
        int ret = 0;

        if (var instanceof String) // compare title
        {
            String otherVar = (String)var;
            if ((ret = compareTitle(this.title, otherVar)) != 0)
            {
                return ret;
            }
        }

        else if (var instanceof Date)  // compare date published
        {
            Date otherVar = (Date)var;
            if ((ret = this.publishDate.compareTo(otherVar)) != 0)
            {
                return ret;
            }
        }

        else if (var instanceof Integer) // compare length
        {
            Integer otherVar = (Integer)var;
            if ((ret = compareLength(this.length, otherVar)) != 0)
            {
                return ret;
            }
        }

        return ret;
    }

    private int compareTitle(String thisTitle, String otherTitle) {
        int ret = 0;

        if (thisTitle.compareTo(otherTitle) < 0)
        {
            ret = -1;
        }

        else if (thisTitle.compareTo(otherTitle) > 0)
        {
            ret = 1;
        }

        return ret;
    }

    private int compareLength(int thisLength, int otherLength) {
        int ret = 0;

        if (thisLength < otherLength)
        {
            ret = -1;
        }

        else if (thisLength > otherLength)
        {
            ret = 1;
        }

        return ret;
    }

    private String timeString(int sec){

        String result = "";

        int hours = sec/3600;
        int minutes  = (sec-(hours*3600))/60;
        int seconds = (sec-(hours*3600))%60;

        if(hours > 0){
            result += hours+":";
        }

        if(minutes < 10){
            result +="0";
        }

        result+=minutes+":";

        if(seconds < 10){
            result+="0";
        }
        result += seconds;

        return result;
    }

    public String getTimeStampString(){
        return timeString(timeStamp);
    }

    public String getLengthString(){
        return timeString(length);
    }
}//episode
