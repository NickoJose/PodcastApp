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
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private ArrayList<Channel> channels;
    private ArrayList<Episode> episodes;
    private Playlist playlist;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public ObjectData(String dbname) { this.dbName = dbName; }

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
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

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

    public Date createDate(int objYear, int objMonth, int objDay, int objHour, int objMinute, int objSecond)
    {
        Date date = new Date();
        date.year = objYear;
        date.month = objMonth;
        date.day = objDay;
        date.hour = objHour;
        date.minute = objMinute;
        date.second = objSecond;

        return date;
    }

    public String getChannelSequential(List<Channel> channelResult)
    {
        Channel channel;
        String myTitle, myDesc, myUrl, myAuthor, myCategory, myOwner, myOwnerEmail;
        Date myPublishDate;

        // Not sure about EOF

        result = null;
        try
        {
            cmdString = "Select * from Channels";
            rs2 = st1.executeQuery(cmdString);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        try
        {
            while (rs2.next())
            {
                myTitle = rs2.getString("Title");
                myDesc = rs2.getString("Desc");
                myUrl = rs2.getString("Url");
                myPublishDate = (Date)rs2.getObject("PublishDate");
                myAuthor = rs2.getString("Author");
                myCategory = rs2.getString("Category");
                myOwner = rs2.getString("Owner");
                myOwnerEmail = rs2.getString("OwnerEmail");
                channel = new Channel(myTitle, myDesc, myUrl, myPublishDate, myAuthor, myCategory, myOwner, myOwnerEmail);
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

    public String insertChannel(Channel currentChannel)
    {
        String values;

        result = null;
        try
        {
            values = "'" + currentChannel.getTitle()
                    +"', '" + currentChannel.getDesc()
                    +"', '" + currentChannel.getUrl()
                    +"', '" + currentChannel.getPublishDate()
                    +"', '" + currentChannel.getAuthor()
                    +"', '" + currentChannel.getCategory()
                    +"', '" + currentChannel.getOwner()
                    +"', '" + currentChannel.getOwnerEmail()
                    +"'";
            cmdString = "Insert into Channels " +" Values(" +values +")";
            //System.out.println(cmdString); // TODO TESTING
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String deleteChannel(Channel currentChannel)
    {
        String values;

        result = null;
        try
        {
            values = currentChannel.getUrl();
            cmdString = "Delete from Channels where Url='" +values +"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;

    }

    public String updateChannel(Channel currentChannel)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Todo SHOULD CHECK FoR EMPTY VALUES AND NOT UPDATE THEM
            values = "Title='" +currentChannel.getTitle()
                    +"', Desc='" + currentChannel.getDesc()
                    +"', PublishDate='" + currentChannel.getPublishDate()
                    +"', Author='" + currentChannel.getAuthor()
                    +"', Category='" + currentChannel.getCategory()
                    +"', Owner='" + currentChannel.getOwner()
                    +"', OwnerEmail'" + currentChannel.getOwnerEmail()
                    +"'";
            where = "where Url='" + currentChannel.getUrl() +"'"; // TODO TEMPORARY PRIMARY KEY
            cmdString = "Update Channels " +" Set " +values +" " +where;
            //System.out.println(cmdString); // TODO TESTING
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String getEpisodesSequential(List<Episode> episodeResult)
    {
        Episode episode;
        String myTitle, myUrl, myDesc, myAuthor, myCategory;
        int myLength, myEpNum;
        Channel myCh;
        Date myPublishDate;

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
                myCh = (Channel)rs5.getObject("Ch");
                myPublishDate = (Date)rs5.getObject("PublishDate");
                myAuthor = rs5.getString("Author");
                myCategory = rs5.getString("Category");
                myEpNum = rs5.getInt("EpNum");
                episode = new Episode(myTitle, myUrl, myDesc, myLength, myCh, myPublishDate, myAuthor, myCategory, myEpNum);
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

    public String getChannelEpisodeSequential(List<Episode> episodeResult, Channel currentChannel)
    {
        // TODO IMPLEMENT THIS

        result = null;
        return result;
    }

    public String insertEpisode(Episode currentEpisode)
    {
        String values;

        result = null;
        try
        {
            values = "'" + currentEpisode.getTitle()
                    +"', '" + currentEpisode.getUrl()
                    +"', '" + currentEpisode.getDesc()
                    +"', '" + currentEpisode.getLength()
                    +"', '" + currentEpisode.getChannel()
                    +"', '" + currentEpisode.getPublishDate()
                    +"', '" + currentEpisode.getAuthor()
                    +"', '" + currentEpisode.getCategory()
                    +"', '" + currentEpisode.getEpNum()
                    +"'";
            cmdString = "Insert into Episodes " +" Values(" +values +")";
            //System.out.println(cmdString); // TODO TESTING
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteEpisode(Episode currentEpisode)
    {
        String values;

        result = null;
        try
        {
            values = "" + currentEpisode.getEpNum();
            cmdString = "Delete from Episodes where EpNum='" +values +"'";
            //System.out.println(cmdString); // TODO TESTING
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String updateEpisode(Episode currentEpisode)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Todo SHOULD CHECK FoR EMPTY VALUES AND NOT UPDATE THEM
            values = "Title='" +currentEpisode.getTitle()
                    +"', Url='" + currentEpisode.getUrl()
                    +"', Desc='" + currentEpisode.getDesc()
                    +"', Length='" + currentEpisode.getLength()
                    +"', Ch='" + currentEpisode.getChannel()
                    +"', PublishDate='" + currentEpisode.getPublishDate()
                    +"', Author'" + currentEpisode.getAuthor()
                    +"', Category'" + currentEpisode.getCategory()
                    +"'";
            where = "where EpNum='" +currentEpisode.getEpNum() +"'";
            cmdString = "Update Episodes " +" Set " +values +" " +where;
            //System.out.println(cmdString);  // TODO TESTING
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String getPlaylistChannelSequential(List<Channel> channelResult)
    {
        result = null;
        // TODO IMPLEMENT THIS
        return result;
    }

    public String getPlayListEpisodeSequential(List<Episode> episodeResult)
    {
        result = null;
        // TODO IMPLEMENT THIS
        return result;
    }

    public boolean insertPlaylistChannel(Channel currentChannel)
    {
        // TODO IMPLEMENT THIS
        return true;
    }

    public boolean insertPlaylistEpisode(Episode currentEpisode)
    {
        // TODO IMPLEMENT THIS
        return true;
    }

    public boolean deletePlaylistChannel(Channel currentChannel)
    {
        // TODO IMPLEMENT THIS
        return true;
    }

    public boolean deletePlaylistEpisode(Episode currentEpisode)
    {
        // TODO IMPLEMENT THIS
        return true;
    }

    public String checkWarning(Statement st, int updateCount)
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

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();
        e.printStackTrace();
        return result;
    }
}
