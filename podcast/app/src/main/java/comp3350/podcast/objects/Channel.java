
package comp3350.podcast.objects;
import android.support.annotation.NonNull;
import java.io.Serializable;

public class Channel extends DescribedObject implements Serializable{

    private String title;
    private String desc;
    private String url;
    private String img;
    private Episode lastPlayed;
    private int numEps;
    private Date publishDate;
    private String author;
    private String category;
    private String owner;
    private String ownerEmail;
    private Date lastUpdate;

    public Channel(String title , String desc, String url,
                   Date publishDate, String author, String category,
                   String owner, String ownerEmail, String img)
    {
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.publishDate = publishDate;
        this.author = author;
        this.category = category;
        this.owner = owner;
        this.ownerEmail = ownerEmail;
        this.numEps = 0;
        this.lastPlayed = null;
        this.lastUpdate = new Date();
        this.img = img;
    }

    //================================ GETTERS ==========================================//
    public String getTitle(){return title;}
    public String getDesc(){return desc;}
    public String getUrl(){return url;}
    public Date getPublishDate(){return publishDate;}
    public String getAuthor(){return author;}
    public String getCategory(){return category;}
    public String getOwner(){return owner;}
    public String getOwnerEmail(){return ownerEmail;}
    public int getNumEps(){return numEps;}
    public Date getLastUpdate(){return lastUpdate;}
    public String getImg(){return img;}

    
    /**
     * Checks if a given object represents the same channel. Checks title and source URL.
     * 
     * @param obj  - obj to compare
     * @return Returns true on success.
     */
    public boolean equals(Object obj)
    {
        boolean result = false;
        Channel ch;

        if(obj instanceof Channel)
        {
            ch = (Channel)obj;
            if(ch.getTitle().equals(title) && ch.getUrl().equals(url))
            {
                result = true;
            }
        }
        return result;
    }//equals

    /**
     * Compare this channel's title/published date/category to the target
     *
     * Returns -1 if channel var is less than var
     * Returns 0 if channel var is equal than var
     * Returns 1 if channel var is greater than var
     *
     * @param var  - var to compare
     * @return Returns integer comparison to be used for channel sorting. Episodes are ordered by title, then date posted, then length.
     */
    public int compareTo(@NonNull Object var) {
        int result = 0;

        if (var instanceof String) // compare title
        {
            String otherVar = (String)var;
            if ((result = compareTitle(this.title, otherVar)) != 0)
            {
                return result;
            }
        }

        else if (var instanceof Date)  // compare date published
        {
            Date otherVar = (Date)var;
            if ((result = this.publishDate.compareTo(otherVar)) != 0)
            {
                return result;
            }
        }

        return result;
    }

    /**
     * Lexiographically compares two channels (as strings)
     * 
     * @param thisTitle  - First channel to be compared
     * @param otherTitle - channel being compared to thisTitle
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
     * To String
     * 
     * @return Returns string representing the channel. Formatted as "Channel name: <TITLE>"
     */ 
    public String toString() {
        return ("Channel name: " + title);
    }
    
    /**
     * Increases the episode count for this channel
     * 
     * @return void
     */ 
    public void incNumEps() {
        numEps++;
    }
}
