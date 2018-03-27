package comp3350.podcast.objects;
import android.support.annotation.NonNull;
import java.io.Serializable;

public class Episode extends DescribedObject implements Serializable {

    private String title;
    private String desc;
    private String url;
    private int length; //length in seconds
    private Channel ch; //channel this episode belongs to
    private int timeStamp; //if previously played, episode will resume from this point
    private Date publishDate;
    private String author;
    private String category; // AKA genre
    private int epNum; //episode number
    private String img;

    public Episode(String title, String url, String desc,
                   int length, Channel ch,Date publishDate,
                   String author, String category,int epNum, String img)
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
        this.timeStamp = 0;
        this.img = img;
    }

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
public int getTimeStamp(){return timeStamp;}
public String getImg(){return img;}


//================================ SETTERS ==========================================//

   /**
     * Sets the time stamp as a percentage of completion
     * 
     * @param t - Percent complete that episode is
     * @return void
     */
    public void setTimeStampPercent(double t) {
        t=t/100;
        if(t>=0 && t<100){
            timeStamp = (int)(t*length);
        }
    }

    /**
     * Sets the time stamp as the number of seconds this episode as been playing for
     *
     * @param i - Percent complete that episode is
     * @return void
     */
    public void setTimeStampInt(int i){
        if(i>=0 && i<length){
           timeStamp = i;
        }
    }

    /**
     * To String
     * 
     * @return Returns string representing episode. Formatted for convenient GUI use.
     */ 

    public String toString() {
        return ("Episode #"+epNum+"\t\"" + title+"\"");
    }

    /**
     * Increments this episode's timestamp by 1 second
     *
     * @return void
     */
    public void incTimeStamp(){
        timeStamp++;
    }

    /**
     * Checks if a given object represents the same episode. Checks title and source URL.
     * 
     * @param obj  - obj to compare
     * @return Returns true on success.
     */
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
    }

    /**
     * Compare this episode's title/published date/category/length to the target
     *
     * Returns -1 if episode var is less than var
     * Returns 0 if episode var is equal than var
     * Returns 1 if episode var is greater than var
     *
     * @param var  - var to compare
     * @return Returns integer comparison to be used for episode sorting. Episodes are ordered by title, then date posted, then length.
     */
    public int compareTo(@NonNull Object var) {
        int result = 0;

        if (var instanceof String) // compare title
        {
            result = compareTitle(title,(String)var);
        }

        else if (var instanceof Date)  // compare date published
        {
            result = publishDate.compareTo((Date)var);
        }

        else if (var instanceof Integer) // compare length
        {
            result = compareLength(length,(Integer)var);
        }

        return result;
    }

    /**
     * Lexiographically compares two titles (as strings)
     * 
     * @param thisTitle  - First title to be compared
     * @param otherTitle - Title being compared to thisTitle
     * @return Returns lexiographic string comparison
     */
    private int compareTitle(String thisTitle, String otherTitle) {
        int result = 0;

        if (thisTitle.compareTo(otherTitle) < 0)
        {
            result = -1;
        }

        else if (thisTitle.compareTo(otherTitle) > 0)
        {
            result = 1;
        }

        return result;
    }

    /**
     * Compares lengths of two episodes as doubles
     * 
     * @param thisLength - first length being compared
     * @param otherLength - second length being compared
     * @return Returns sign of (thisLength - otherLength)
     */    
    private int compareLength(int thisLength, int otherLength) {
        int result = 0;


        if (thisLength > otherLength)
        {
            result = -1;
        }

        else if (thisLength < otherLength)
        {
            result = 1;
        }

        return result;
    }

    /**
     * Returns a string listing the given number of seconds in hh:mm:ss format
     *
     * @param sec - Time to be converted to hh:mm:ss, as a number of seconds
     * @return String representing time formatted in hh:mm:ss
     */
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
