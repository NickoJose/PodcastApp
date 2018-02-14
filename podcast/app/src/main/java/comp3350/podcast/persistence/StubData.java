package comp3350.podcast.persistence;

/**
 * Created by Nicko on 2018-01-29.
 */

import java.util.ArrayList;
import java.util.List;

import comp3350.podcast.application.Main;
import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Date;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Playlist;

public class StubData {
    private String dbName;
    private String dbType = "stub";
    private ArrayList<Channel> channels;
    private ArrayList<Episode> episodes;
    private Playlist playlist;

    public StubData(String dbName) {
        this.dbName = dbName;
    }

    public StubData() {this(Main.dbName);}    

    
    /**
     * Opens a connection to the database via this object
     *
     * @return - void
     */
    public void open(String dbName) {
        Channel channel;
        Episode episode;
        Date date;

        channels = new ArrayList<>();
        episodes = new ArrayList<>();
        playlist = new Playlist();

        date = new Date(2009, 12, 24);
        channel = new Channel("The Joe Rogan Experience", "The Joe Rogan Experience podcast is a long form conversation hosted by comedian, " +
                "UFC color commentator, and actor Joe Rogan with friends and guests that have included comedians, actors, musicians, MMA instructors and " +
                "commentators, authors, artists, and porn stars.", "http://joerogan.net/podcasts/", date, "Joe Rogan", "Comedy", "Brian Redban", "BOOKDEATHSQUAD@GMAIL.COM");
        channels.add(channel);
        date = new Date(2016, 2, 7);
        channel = new Channel("NBA Hang Time", "Veteran NBA writer Sekou Smith and former player Greg Anthony analyze the latest NBA news, " +
                "storylines, and more with guests from around the NBA world.", "http://www.nba.com/podcast#/", date, "Sekou Smith", "Sports", "NBA Digital", "@NBA");
        channels.add(channel);
        date = new Date(2012, 4, 22);
        channel = new Channel("You Are Not So Smart", "You Are Not So Smart is a celebration of self delusion that explores topics related to " +
                "cognitive biases, heuristics, and logical fallacies. David McRaney interviews scientists about their research into how the mind works, " +
                "and then he eats a cookie", "https://youarenotsosmart.com/", date, "David McRaney", "Social Sciences", "David McRaney", "davidmcraney@gmail.com");
        channels.add(channel);

        date = new Date(2018, 1, 12);
        episode = new Episode("JRE MMA Show #10 with Tyron Woodley", "http://traffic.libsyn.com/joeroganexp/mmashow010.mp3?dest-id=19997",
                "Joe Rogan sits down with UFC Welterweight Champion Tyron Woodley", 1.42, channels.get(0), date, "Joe Rogan", "Sports", 10);
        channels.get(0).incNumEps();
        episodes.add(episode);
        date = new Date(2017, 7, 27);
        episode = new Episode("#990 - Jamie Foxx", "http://traffic.libsyn.com/joeroganexp/p990.mp3?dest-id=19997",
                "Jamie Foxx is an Academy Award winning actor, singer, and comedian. He can currently be seen hosting \"Beat Shazam\" on Fox and " +
                        "in the movie \"Baby Driver\" in theaters now.", 1.8, channels.get(0), date, "Joe Rogan", "Comedy", 990);
        channels.get(0).incNumEps();
        episodes.add(episode);
        date = new Date(2016, 12, 27);
        episode = new Episode("#890 - Fight Breakdown", "http://traffic.libsyn.com/joeroganexp/p890.mp3?dest-id=19997",
                "Joe sits down with Eddie Bravo & Brendan Schaub to discuss upcoming fights in MMA", 3.49, channels.get(0) , date, "Joe Rogan", "Sports", 890);
        channels.get(0).incNumEps();
        episodes.add(episode);

        date = new Date(2017, 12, 14);
        episode = new Episode("State of the Philadelphia 76ers", "http://media.adknit.com/a/f/13/nba-hangtime/xpe2c8.1-1.mp3",
                "Sixers insider Jessica Camerato of NBC Sports Philadelphia joins us to dig deep into the state of the 76ers. We talk about Joel Embiid, " +
                        "Ben Simmons, chemistry, playoff hopes, Jahlil Okafor trade, and much more. Then John Schuhmann calls in with his weekly trivia question.",
                0.37, channels.get(1), date, "Sekou Smith", "Sports", 82);
        channels.get(1).incNumEps();
        episodes.add(episode);
        date = new Date(2017, 6, 23);
        episode = new Episode("2017 NBA Draft: Instant Analysis", "http://media.adknit.com/a/f/13/nba-hangtime/rl1o6s.1-1.mp3",
                "Lang Whitaker, John Schuhmann, Scott-Howard Cooper and Trey Kerby recap Thursday's draft.", 0.6, channels.get(1), date, "Sekou Smith", "Sports", 61);
        channels.get(1).incNumEps();
        episodes.add(episode);
        date = new Date(2016, 10, 21);
        episode = new Episode("More or Less? Predicting Win Totals For All 30 Nba Teams", "http://media.adknit.com/a/f/13/nba-hangtime/htwxqy.1-1.mp3",
                "Which NBA teams will be better this season? Which will be worse? Sekou Smith and Lang Whitaker predict how many games each team will win " +
                        "during the 2016-17 NBA season.", 0.44, channels.get(1), date, "Sekou Smith", "Sports", 15);
        channels.get(1).incNumEps();
        episodes.add(episode);

        date = new Date(2017, 2, 25);
        episode = new Episode("096 - Progress", "http://feeds.soundcloud.com/stream/309444172-youarenotsosmart-096-progress.mp3",
                "Do we have the power to change the outcome of history? Is progress inevitable? Is it natural? Are we headed somewhere definite, or is " +
                        "change just chaos that seems organized in hindsight? In this episode we explore these questions with University of Chicago historian " +
                        "Ada Palmer", 1.05, channels.get(2), date, "David McRaney",
                "Social Sciences", 96);
        channels.get(2).incNumEps();
        episodes.add(episode);
        date = new Date(2017, 2, 25);
        episode = new Episode("055 - Weird People - Steven J. Heine", "http://feeds.soundcloud.com/stream/217283672-youarenotsosmart-055-weird-people-steven-j-heine.mp3",
                "JIs psychology too WEIRD? That's what this episode's guest, psychologist Steven J. Heine suggested when he and his colleagues published a " +
                        "paper suggesting that psychology wasn't the study of the human mind, but the study of one kind of human mind, the sort generated by the " +
                        "kinds of brains that happen to be conveniently located near the places where research is usually conducted - North American college " +
                        "undergraduates.", 0.48, channels.get(2), date, "David McRaney",
                "Social Sciences", 96);
        channels.get(2).incNumEps();
        episodes.add(episode);
        date = new Date(2015, 2, 5);
        episode = new Episode("049 - Rejection - Jia Jiang", "http://feeds.soundcloud.com/stream/204471744-youarenotsosmart-049-rejection-jia-jiang.mp3",
                "What if you could give yourself a superpower? That's what Jia Jiang wondered when he began a quest to remove the fear of rejection from his " +
                        "brain and become the risk-taking, adventurous person he always wanted to be.", 0.54, channels.get(2) , date, "David McRaney", "Social Sciences", 48);
        channels.get(2).incNumEps();
        episodes.add(episode);

        playlist.addChannel(channels.get(0));
        playlist.addChannel(channels.get(2));

        playlist.addEpisode(episodes.get(0));
        playlist.addEpisode(episodes.get(2));
        playlist.addEpisode(episodes.get(6));
        playlist.addEpisode(episodes.get(7));
        playlist.addEpisode(episodes.get(8));


        System.out.println("Opened " +dbType +" database " +dbName);
    }

    /**
     * Closes a connection to the database through this object
     *
     * @return - void
     */
    public void close()
    {
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
        channelResult.addAll(channels);
        return null;
    }

    /**
     * Inserts a new channel into the database.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - channel to be inserted into database
     * @return - null. This pattern was taken from sample project
     */
    public String insertChannel(Channel currentChannel)
    {
        int index;

        index = channels.indexOf(currentChannel);
        if (index < 0) // check duplicates
        {
            channels.add(currentChannel);
        }
        return null;
    }

    /**
     * Removes a channel into the database.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - channel to be removed from database
     * @return - null. This pattern was taken from sample project
     */
    public String deleteChannel(Channel currentChannel)
    {
        int index;

        index = channels.indexOf(currentChannel);
        if (index >= 0)
        {
            channels.remove(index);
        }
        return null;
    }

    /**
     * Updates a channel's entry in the database
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - channel to be updated
     * @return - null. This pattern was taken from sample project
     */
    public String updateChannel(Channel currentChannel)
    {
        int index;

        index = channels.indexOf(currentChannel);
        if (index >= 0)
        {
            channels.set(index, currentChannel);
        }
        return null;
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
        episodeResult.addAll(episodes);
        return null;
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
        int index;

        index = channels.indexOf(currentChannel);
        if (index >= 0)
        {
            for (int i = 0; i < episodes.size(); i++) {
                if (episodes.get(i).getChannel().equals(currentChannel)) {
                    episodeResult.add(episodes.get(i));
                }
            }
        }
        return null;
    }
    
    /**
     * Inserts an episode into the database, without a parent Channel.
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentEpisode - episode to be inserted into database
     * @return - null. This pattern was taken from sample project
     */
    public String insertEpisode(Episode currentEpisode)
    {
        // don't bother checking for duplicates
        episodes.add(currentEpisode);
        return null;
    }

    /**
     * Deletes an episode from the database
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentChannel - channel to be removed from the database
     * @return - null. This pattern was taken from sample project
     */
    public String deleteEpisode(Episode currentEpisode)
    {
        int index;

        index = episodes.indexOf(currentEpisode);
        if (index >= 0)
        {
            episodes.remove(index);
        }
        return null;
    }

    /**
     * Updates an episode in the database
     * The input/output follows a design pattern from the sample project.
     *
     * @param currentEpisode - Episode to be updated
     * @return - null. This pattern was taken from sample project
     */
    public String updateEpisode(Episode currentEpisode)
    {
        int index;

        index = episodes.indexOf(currentEpisode);
        if (index >= 0)
        {
            episodes.set(index, currentEpisode);
        }
        return null;
    }
   
    /**
     * Puts all the channels in a playlist into a given List<Channel> object. The source playlist is stored in this object
     * The input/output follows a design pattern from the sample project.
     *
     * @param channelResult - the List where the result will be stored
     * @return - null. This pattern was taken from sample project
     */
    public String getPlaylistChannelSequential(List<Channel> channelResult)
    {
        channelResult.addAll(playlist.getChannels());
        return null;
    }

    /**
     * Puts all the episodes in a playlist into a given List<Channel> object. The source playlist is stored in this object
     * The input/output follows a design pattern from the sample project.
     *
     * @param episodeResult - the List where the result will be stored
     * @return - null. This pattern was taken from sample project
     */
    public String getPlayListEpisodeSequential(List<Episode> episodeResult)
    {
        episodeResult.addAll(playlist.getEpisodes());
        return null;
    }

    /**
     * Inserts a given channel into the playlist stored in this object.
     *
     * @param currentChannel - the channel to be inserted into the database
     * @return - Returns success
     */
    public boolean insertPlaylistChannel(Channel currentChannel)
    {
        return playlist.addChannel(currentChannel);
    }
    
    /**
     * Inserts a given channel into the playlist stored in this object.
     *
     * @param currentEpisode - the episode to be inserted into the database
     * @return - Returns success
     */
    public boolean insertPlaylistEpisode(Episode currentEpisode)
    {
        return playlist.addEpisode(currentEpisode);
    }

    /**
     * Removes a channel from the current playlist
     *
     * @param channelResult - the channel to be removed from the current playlist
     * @return - Returns success
     */
    public boolean deletePlaylistChannel(Channel currentChannel)
    {
        return playlist.removeChannel(currentChannel);
    }

    /**
     * Removes a given episode from the current playlist
     *
     * @param currentEpisode - the episode to be removed from the playlist
     * @return - Returns success
     */
    public boolean deletePlaylistEpisode(Episode currentEpisode)
    {
        return playlist.removeEpisode(currentEpisode);
    }
}
