/*
Jackson Barker
Software Engineering group K
*/
package comp3350.podcast.objects;

public class Channel{

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

    /**
     * Compare this channel's title/published date/category to the target
     *
     * Returns -1 if channel is less than obj
     * Returns 0 if channel is equal than obj
     * Returns 1 if channel is greater than obj
     *
     * @param obj  - channel to compare with
     * @param var  - var to compare
     * @return see description
     */
    public int compareTo(@NonNull Object obj, @NonNull Object var) { // THIS IS NOT FINAL
        int ret = 0;


        return ret;
    }
    
    public boolean equals(Object obj)
    {
        boolean result = false;
        Channel ch;

        if(obj instanceof Channel)
        {
            ch = (Channel)obj;
            if(ch.getTitle() == title && ch.getUrl() == url)
            {
                result = true;
            }
        }
        return result;
    }//equals

    public String toString() {
        return ("Channel name: " + title);
    }

    public void incNumEps() {
        numEps++;
    }//incNumEps
}//channel
