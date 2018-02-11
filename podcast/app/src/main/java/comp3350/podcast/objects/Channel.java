/*
Jackson Barker
Software Engineering group K
*/
package comp3350.podcast.objects;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Channel implements Serializable{

    //vars
    private String title;
    private String desc; //description of the channel
    private String url;
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
                   String owner, String ownerEmail)
    {
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.publishDate = publishDate;
        this.author = author;
        this.category = category;
        this.owner = owner;
        this.ownerEmail = ownerEmail;
        numEps = 0;
        lastPlayed = null;
        lastUpdate = new Date();
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
    public Episode getLastPlayed(){return lastPlayed;}
    public int getNumEps(){return numEps;}
    public Date getLastUpdate(){return lastUpdate;}

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

    public String toString() {
        return ("Channel name: " + title);
    }

    public void incNumEps() {
        numEps++;
    }//incNumEps
}//channel
