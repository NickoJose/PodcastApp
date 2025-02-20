package comp3350.podcast.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Date;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Playlist;

public class ObjectData implements AccessData
{
    private Statement st1, st2, st3, stTemp;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rsTemp;

    private String dbName;
    private String dbType;

    private String cmdString;
    private int updateCount;
    private String result;

    public ObjectData(String dbname) { this.dbName = dbName; }

    /**
     * Opens a connection to the database via this object
     *
     * @return - void
     */
    public void open(String dbPath)
    {
        String url;
        try
        {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
            stTemp = c1.createStatement();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        dbName = dbPath;
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    /**
     * Closes a connection to the database through this object
     *
     * @return - void
     */
    public void close()
    {
        try
        {
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    /**
     * Puts the ordered list of all channels into a given List<Channel>
     * The input/output follows a design pattern from the sample project.
     *
     * @param channelResult - a list of channels object that we will store the result in.
     * @return - null. This pattern was taken from sample project
     */
    public String getChannelSequential(List<Channel> channelResult)
    {
        channelResult.clear();
        Channel channel;
        String myTitle, myDesc, myUrl, myAuthor, myCategory, myOwner, myOwnerEmail, img;
        Date myPublishDate = new Date();
        String date;
        String[] tokens;

        result = null;
        try
        {
            cmdString = "Select * from Channels";
            rs2 = st1.executeQuery(cmdString);

            while (rs2.next())
            {
                myTitle = rs2.getString("Title");
                myDesc = rs2.getString("Desc");
                myUrl = rs2.getString("Url");
                date = rs2.getString("PublishDate");
                myAuthor = rs2.getString("Author");
                myCategory = rs2.getString("Category");
                myOwner = rs2.getString("Owner");
                myOwnerEmail = rs2.getString("OwnerEmail");
                img = rs2.getString("Image");

                tokens = date.split(" ");
                if (tokens.length == 3)
                {
                    myPublishDate = new Date(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                }

                channel = new Channel(myTitle, myDesc, myUrl, myPublishDate, myAuthor, myCategory, myOwner, myOwnerEmail, img);
                channelResult.add(channel);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    /**
     * Puts an ordered list of all the episodes into a given List<Episode> object
     * The input/output follows a design pattern from the sample project.
     *
     * @param episodeResult - The list that will store the result
     * @return - null. This pattern was taken from sample project
     */
    public String getEpisodesSequential(List<Episode> episodeResult)
    {
        episodeResult.clear();
        Episode episode;
        String myTitle, myUrl, myDesc, myAuthor, myCategory, img;
        int myLength, myEpNum;
        Channel myCh;
        Date myPublishDate = new Date();
        String chTitle;
        String date;
        String[] tokens;

        result = null;
        try
        {
            cmdString = "Select * from Episodes";
            rs5 = st3.executeQuery(cmdString);
            // ResultSetMetaData md5 = rs5.getMetaData();
            while (rs5.next())
            {
                myTitle = rs5.getString("Title");
                myUrl = rs5.getString("Url");
                myDesc = rs5.getString("Desc");
                myLength = rs5.getInt("Length");
                chTitle = rs5.getString("Ch");
                date = rs5.getString("PublishDate");
                myAuthor = rs5.getString("Author");
                myCategory = rs5.getString("Category");
                myEpNum = rs5.getInt("EpNum");
                img = rs5.getString("Image");

                tokens = date.split(" ");
                if (tokens.length == 3)
                {
                    myPublishDate = new Date(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                }

                myCh = getChannelInfo(chTitle);

                episode = new Episode(myTitle, myUrl, myDesc, myLength, myCh, myPublishDate, myAuthor, myCategory, myEpNum, img);
                episodeResult.add(episode);
            }
            rs5.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    /**
     * Find a channel that matches the param title then return that channel
     * The input/output follows a design pattern from the sample project.
     *
     * @param title - The title that will be compared
     * @return - Channel. Null if unsuccessful and channel if successful
     */
    private Channel getChannelInfo(String title)
    {
        ArrayList<Channel> channels = new ArrayList<>();
        getChannelSequential(channels);
        Channel ch = null;

        for (int i = 0; i < channels.size(); i ++)
        {
            if (title.equals(channels.get(i).getTitle()))
            {
                ch = channels.get(i);
                break;
            }
        }

        return ch;
    }

    /**
     * Find a channel in the subscription table that matches the param title then return that channel
     * The input/output follows a design pattern from the sample project.
     *
     * @param title - The title that will be compared
     * @return - Channel. Null if unsuccessful and channel if successful
     */
    private Channel getSubInfo(String title)
    {
        ArrayList<Channel> channels = new ArrayList<>();
        getSubSequential(channels);
        Channel ch = null;

        for (int i = 0; i < channels.size(); i ++)
        {
            if (title.equals(channels.get(i).getTitle()))
            {
                ch = channels.get(i);
                break;
            }
        }

        return ch;
    }

    /**
     * Find an episode that matches the param title then return that episode
     * The input/output follows a design pattern from the sample project.
     *
     * @param title - The title that will be compared
     * @return - Episode. Null if unsuccessful and episode if successful
     */
    private Episode getEpisodeInfo(String title)
    {
        ArrayList<Episode> episodes = new ArrayList<>();
        getEpisodesSequential(episodes);
        Episode ep = null;

        for (int i = 0; i < episodes.size(); i ++)
        {
            if (title.equals(episodes.get(i).getTitle()))
            {
                ep = episodes.get(i);
                break;
            }
        }

        return ep;
    }

    /**
     * Find a playlist that matches the param name then return that playlist
     * The input/output follows a design pattern from the sample project.
     *
     * @param name - The title that will be compared
     * @return - Playlist. Null if unsuccessful and playlist if successful
     */
    private Playlist getPlaylistInfo(String name)
    {
        ArrayList<Playlist> playlists = new ArrayList<>();
        getPlaylistSequential(playlists);
        Playlist playlist = null;

        for (int i = 0; i < playlists.size(); i ++)
        {
            if (name.equals(playlists.get(i).getName()))
            {
                playlist = playlists.get(i);
                break;
            }
        }

        return playlist;
    }

    /**
     * Find an entry that matches the param name of the playlist and the title of the channel
     * The input/output follows a design pattern from the sample project.
     *
     * @param title - The title that will be compared
     * @param currentPlaylist - The playlist that will be compared
     * @return - Boolean. False if unsuccessful and true if successful
     */
    private Boolean getPlaylistChannelInfo(String title, Playlist currentPlaylist)
    {
        boolean found = false;
        ArrayList<Channel> channels = new ArrayList<>();
        getPlaylistChannelSequential(channels, currentPlaylist);

        for (int i = 0; i < channels.size(); i ++)
        {
            if (title.equals(channels.get(i).getTitle()))
            {
                found = true;
                break;
            }
        }

        return found;
    }

    /**
     * Find an entry that matches the param name of the playlist and the title of the episode
     * The input/output follows a design pattern from the sample project.
     *
     * @param title - The title that will be compared
     * @param currentPlaylist - The playlist that will be compared
     * @return - Boolean. False if unsuccessful and true if successful
     */
    private Boolean getPlaylistEpisodeInfo(String title, Playlist currentPlaylist)
    {
        boolean found = false;
        ArrayList<Episode> episodes = new ArrayList<>();
        getPlaylistEpisodeSequential(episodes, currentPlaylist);

        for (int i = 0; i < episodes.size(); i ++)
        {
            if (title.equals(episodes.get(i).getTitle()))
            {
                found = true;
                break;
            }
        }

        return found;
    }

    /**
     * Takes all the episodes from a channel and stores it in a List<Episode> object.
     * The input/output follows a design pattern from the sample project.
     *
     * @param episodeResult - The list that will store the result
     * @param currentChannel - channel to be inserted into database
     * @return - null. This pattern was taken from sample project
     */
    public String getChannelEpisodeSequential(List<Episode> episodeResult, Channel currentChannel)
    {
        episodeResult.clear();
        String channelTitle = currentChannel.getTitle();
        Episode episode;
        String myTitle, myUrl, myDesc, myAuthor, myCategory, img;
        int myLength, myEpNum;
        Channel myCh;
        Channel ch;
        Date myPublishDate = new Date();
        String chTitle;
        String date;
        String[] tokens;
        result = null;

        ch = getChannelInfo(currentChannel.getTitle());
        if (ch != null) // channel exists in the database
        {
            try
            {
                cmdString = "Select * from Episodes where ch = '" + channelTitle + "'";
                rs5 = st3.executeQuery(cmdString);

                while (rs5.next())
                {
                    myTitle = rs5.getString("Title");
                    myUrl = rs5.getString("Url");
                    myDesc = rs5.getString("Desc");
                    myLength = rs5.getInt("Length");
                    chTitle = rs5.getString("Ch");
                    date = rs5.getString("PublishDate");
                    myAuthor = rs5.getString("Author");
                    myCategory = rs5.getString("Category");
                    myEpNum = rs5.getInt("EpNum");
                    img = rs5.getString("Image");

                    tokens = date.split(" ");
                    if (tokens.length == 3)
                    {
                        myPublishDate = new Date(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    }

                    myCh = getChannelInfo(chTitle);

                    episode = new Episode(myTitle, myUrl, myDesc, myLength, myCh, myPublishDate, myAuthor, myCategory, myEpNum, img);
                    episodeResult.add(episode);
                }
                rs5.close();
            }
            catch (Exception e)
            {
                result = processSQLError(e);
            }
        }

        return result;
    }

    /**
     * Puts the ordered list of all playlist into a given List<Playlist>
     * The input/output follows a design pattern from the sample project.
     *
     * @param playlistResult - a list of playlist object that we will store the result in.
     * @return - null. This pattern was taken from sample project
     */
    public String getPlaylistSequential(List<Playlist> playlistResult)
    {
        playlistResult.clear();
        Playlist playlist;
        String myName;

        result = null;
        try
        {
            cmdString = "Select * from Playlists";
            rs4 = st3.executeQuery(cmdString);

            while (rs4.next())
            {
                myName = rs4.getString("Name");
                playlist = new Playlist(myName);

                cmdString = "SELECT * from EPISODELIST WHERE Name = '"+playlist.getName()+"'";
                rsTemp = stTemp.executeQuery(cmdString);
               while (rsTemp.next()) {
                   myName = rsTemp.getString("Title");
                   playlist.addEpisode(getEpisodeInfo(myName));
                }

                playlistResult.add(playlist);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    /**
     * Puts all the channels in a playlist into a given List<Channel> object. The source playlist is stored in this object
     * The input/output follows a design pattern from the sample project.
     *
     * @param channelResult - the List where the result will be stored
     * @param currentPlaylist - The playlist to be searched
     * @return - null. This pattern was taken from sample project
     */
    public String getPlaylistChannelSequential(List<Channel> channelResult, Playlist currentPlaylist)
    {
        channelResult.clear();
        Channel myCh;
        String playlistName = currentPlaylist.getName();
        String chTitle;

        result = null;
        try
        {
            cmdString = "Select * from Channellist where name = '" + playlistName + "'";
            rs3 = st3.executeQuery(cmdString);

            while (rs3.next())
            {
                chTitle = rs3.getString("Title");
                myCh = getChannelInfo(chTitle);
                channelResult.add(myCh);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    /**
     * Puts all the episodes in a playlist into a given List<Channel> object. The source playlist is stored in this object
     * The input/output follows a design pattern from the sample project.
     *
     * @param episodeResult - the List where the result will be stored
     * @param currentPlaylist - The playlist to be searched
     * @return - null. This pattern was taken from sample project
     */
    public String getPlaylistEpisodeSequential(List<Episode> episodeResult, Playlist currentPlaylist)
    {
        episodeResult.clear();
        Episode myEp;
        String playlistName = currentPlaylist.getName();
        String epTitle;
        result = null;
        try
        {
            cmdString = "Select * from Episodelist where name = '" + playlistName + "'";
            rs3 = st3.executeQuery(cmdString);

            while (rs3.next())
            {
                epTitle = rs3.getString("Title");
                myEp = getEpisodeInfo(epTitle);
                episodeResult.add(myEp);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    /**
     * Inserts a new playlist into the database.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentPlaylist - channel to be inserted into database
     * @return - null. This pattern was taken from sample project
     */
    public String insertPlaylist(Playlist currentPlaylist)
    {
        String values;
        Playlist playlist;
        result = null;

        if(currentPlaylist == null){
            throw new NullPointerException("Playlist must not be Null");
        }

        playlist = getPlaylistInfo(currentPlaylist.getName());
        if (playlist == null) // does not exist in the database
        {
            try
            {
                values = "'" + currentPlaylist.getName() +"'";
                cmdString = "Insert into Playlists " +" Values(" +values +")";
                updateCount = st2.executeUpdate(cmdString);
                result = checkWarning(st2, updateCount);
            }
            catch (Exception e)
            {
                result = processSQLError(e);
            }
        }

        return result;
    }

    /**
     * Deletes a given playlist from the database
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentPlaylist - the playlist being removed from the database
     * @return - null. This pattern was taken from sample project
     */
    public String deletePlaylist(Playlist currentPlaylist)
    {
        String values;

        result = null;
        try
        {
            values = "" + currentPlaylist.getName();

            cmdString = "Delete from Channellist where Name='" + values +"'";
            updateCount = st2.executeUpdate(cmdString);
            result = checkWarning(st2, updateCount);

            cmdString = "Delete from Episodelist where Name='" + values +"'";
            updateCount = st2.executeUpdate(cmdString);
            result = checkWarning(st2, updateCount);

            cmdString = "Delete from Playlists where Name='" + values +"'";
            updateCount = st2.executeUpdate(cmdString);
            result = checkWarning(st2, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    /**
     * Inserts a given channel into the playlist stored in this object.
     *
     * @param currentChannel - the channel to be inserted into the database
     * @param currentPlaylist - target playlist
     * @return - Boolean. False if unsuccessful and true if successful
     */
    public boolean insertPlaylistChannel(Channel currentChannel, Playlist currentPlaylist)
    {
        String values;
        boolean success = false;
        boolean found;

        if (!getPlaylistChannelInfo(currentChannel.getTitle(), currentPlaylist) && getPlaylistInfo(currentPlaylist.getName()) != null) // does not exist in the database

    {
            try
            {
                values = "'" + currentChannel.getTitle()
                        +"', '" + currentPlaylist.getName()
                        +"'";
                cmdString = "Insert into Channellist " +" Values(" +values +")";
                updateCount = st2.executeUpdate(cmdString);
                result = checkWarning(st2, updateCount);
                success = true;
            }
            catch (Exception e)
            {
                result = processSQLError(e);
            }
        }

        return success;
    }

    /**
     * Inserts a given episode into the playlist stored in this object.
     *
     * @param currentEpisode - the episode to be inserted into the database
     * @param currentPlaylist - target playlist
     * @return - Boolean. False if unsuccessful and true if successful
     */
    public boolean insertPlaylistEpisode(Episode currentEpisode, Playlist currentPlaylist)
    {
        String values;
        boolean success = false;
        boolean found;

        if (!getPlaylistEpisodeInfo(currentEpisode.getTitle(), currentPlaylist) && getPlaylistInfo(currentPlaylist.getName()) != null) // does not exist in the database

    {
            try
            {
                values = "'" + currentEpisode.getTitle()
                        +"', '" + currentPlaylist.getName()
                        +"'";
                cmdString = "Insert into Episodelist " +" Values(" +values +")";
                updateCount = st2.executeUpdate(cmdString);
                result = checkWarning(st2, updateCount);
                success = true;
            }
            catch (Exception e)
            {
                result = processSQLError(e);
            }
        }

        return success;
    }

    /**
     * Removes a channel from the current playlist
     *
     * @param currentChannel - the channel to be removed from the current playlist
     * @param currentPlaylist - target playlist
     * @return - Boolean. False if unsuccessful and true if successful
     */
    public boolean deletePlaylistChannel(Channel currentChannel, Playlist currentPlaylist)
    {
        String values;
        String nextValues;
        boolean success = false;
        try
        {
            values = currentChannel.getTitle();
            nextValues = currentPlaylist.getName();
            cmdString = "Delete from Channellist where Title='" +values +"'" + " and Name='" +nextValues +"'" ;
            updateCount = st2.executeUpdate(cmdString);
            result = checkWarning(st2, updateCount);
            success = true;
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return success;
    }

    /**
     * Removes a given episode from the current playlist
     *
     * @param currentEpisode - the episode to be removed from the playlist
     * @param currentPlaylist - target playlist
     * @return - Boolean. False if unsuccessful and true if successful
     */
    public boolean deletePlaylistEpisode(Episode currentEpisode, Playlist currentPlaylist)
    {
        String values;
        String nextValues;
        boolean success = false;
        try
        {
            values = currentEpisode.getTitle();
            nextValues = currentPlaylist.getName();
            cmdString = "Delete from Episodelist where Title='" +values +"'" + " and Name='" +nextValues +"'" ;
            updateCount = st2.executeUpdate(cmdString);
            result = checkWarning(st2, updateCount);
            success = true;
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return success;
    }

    /**
     * Puts the ordered list of all subscriptions into a given List<Channel>
     * The input/output follows a design pattern from the sample project.
     *
     * @param channelResult - a list of channels object that we will store the result in.
     * @return - null. This pattern was taken from sample project
     */
    public String getSubSequential(List<Channel> channelResult)
    {
        channelResult.clear();
        Channel myCh;
        String chTitle;

        result = null;
        try
        {
            cmdString = "Select * from Subscriptions";
            rs3 = st3.executeQuery(cmdString);
            while (rs3.next())
            {
                chTitle = rs3.getString("Title");
                myCh = getChannelInfo(chTitle);
                channelResult.add(myCh);
            }
        }

        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    /**
     * Inserts a new subscription into the database.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - channel to be inserted into database
     * @return - null. This pattern was taken from sample project
     */
    public String insertSub(Channel currentChannel)
    {
        String values;
        Channel ch;

        result = null;

        ch = getSubInfo(currentChannel.getTitle());
        if (ch == null) // does not exist in the database
        {
            try
            {
                values = "'" + currentChannel.getTitle() + "'";
                cmdString = "Insert into Subscriptions " +" Values(" +values +")";
                updateCount = st2.executeUpdate(cmdString);
                result = checkWarning(st2, updateCount);
            }
            catch (Exception e)
            {
                result = processSQLError(e);
            }
        }

        return result;
    }

    /**
     * Removes a subscription into the database.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - channel to be removed from database
     * @return - null. This pattern was taken from sample project
     */
    public String deleteSub(Channel currentChannel)
    {
        String values;

        result = null;
        try
        {
            values = currentChannel.getTitle();
            cmdString = "Delete from Subscriptions where Title='" +values +"'";
            updateCount = st2.executeUpdate(cmdString);
            result = checkWarning(st2, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    /**
     * Returns the status of the sql statement
     *
     * @param st - the object used for executing a sql statement
     * @param updateCount - determines if the sql statement succeeded
     * @return - null if no errors, error message if there is
     */
    private String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    /**
     * Returns the exception caught when accessing the database
     *
     * @param e- the caught exception
     * @return - error message
     */
    private String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();
        e.printStackTrace();
        return result;
    }
}
