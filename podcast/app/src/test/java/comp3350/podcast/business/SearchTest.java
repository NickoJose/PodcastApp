package comp3350.podcast.business;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Date;
import comp3350.podcast.objects.Episode;

import static org.junit.Assert.*;

public class SearchTest {

    private ArrayList<Episode> episodeList;
    private ArrayList<Channel> channelList;
    private Channel channel1;
    private Channel channel2;
    private Channel channel3;
    private Episode episode1;
    private Episode episode2;
    private Episode episode3;
    private Date chDate1;
    private Date chDate2;
    private Date chDate3;
    private Date epDate1;
    private Date epDate2;
    private Date epDate3;

    @Before
    public void setUp()
    {
        chDate1 = new Date(2009, 12, 24);
        chDate2 = new Date(2016, 2, 17);
        chDate3 = new Date(2012, 4, 22);

        epDate1 = new Date(2018, 1, 12);
        epDate2 = new Date(2016, 7, 27);
        epDate3 = new Date(2017, 12, 27);

        channel1 = new Channel("The Joe Rogan Experience", "The Joe Rogan Experience podcast is a long form conversation hosted by comedian, " +
                "UFC color commentator, and actor Joe Rogan with friends and guests that have included comedians, actors, musicians, MMA instructors and " +
                "commentators, authors, artists, and porn stars.", "http://joerogan.net/podcasts/", chDate1, "Joe Rogan", "Comedy", "Brian Redban", "BOOKDEATHSQUAD@GMAIL.COM","");
        channel2 = new Channel("NBA Hang Time", "Veteran NBA writer Sekou Smith and former player Greg Anthony analyze the latest NBA news, " +
                "storylines, and more with guests from around the NBA world.", "http://www.nba.com/podcast#/", chDate2, "Sekou Smith", "Sports", "NBA Digital", "@NBA","");
        channel3 = new Channel("You Are Not So Smart", "You Are Not So Smart is a celebration of self delusion that explores topics related to " +
                "cognitive biases, heuristics, and logical fallacies. David McRaney interviews scientists about their research into how the mind works, " +
                "and then he eats a cookie", "https://youarenotsosmart.com/", chDate3, "David McRaney", "Social Sciences", "David McRaney", "davidmcraney@gmail.com","");

        episode1 = new Episode("JRE MMA Show #10 with Tyron Woodley", "http://traffic.libsyn.com/joeroganexp/mmashow010.mp3?dest-id=19997",
                "Joe Rogan sits down with UFC Welterweight Champion Tyron Woodley", 6120, channel1, epDate1, "Joe Rogan", "Sports", 10,"");
        episode2 = new Episode("#990 - Jamie Foxx", "http://traffic.libsyn.com/joeroganexp/p990.mp3?dest-id=19997",
                "Jamie Foxx is an Academy Award winning actor, singer, and comedian. He can currently be seen hosting \"Beat Shazam\" on Fox and " +
                        "in the movie \"Baby Driver\" in theaters now.", 4080, channel1, epDate2, "Joe Rogan", "Comedy", 990,"");
        episode3 = new Episode("#890 - Fight Breakdown", "http://traffic.libsyn.com/joeroganexp/p890.mp3?dest-id=19997",
                "Joe sits down with Eddie Bravo & Brendan Schaub to discuss upcoming fights in MMA", 13740, channel1 , epDate3, "Joe Rogan", "Sports", 890,"");

        channelList = new ArrayList<>();
        episodeList = new ArrayList<>();

        channelList.add(channel1);
        channelList.add(channel2);
        channelList.add(channel3);

        episodeList.add(episode1);
        episodeList.add(episode2);
        episodeList.add(episode3);
    }

    @Test
    public void testMatchHeuristic()
    {
        System.out.println("\nStarting SearchTest: MatchHeuristic");

        Search search = new Search();
        int out = search.matchHeuristic("String","String");
        assertTrue(out > 0); // a match was found

        out = search.matchHeuristic("String","z");
        assertTrue(out < 1); // a match was not found

        // test relative heuristic quality
        int out1 = search.matchHeuristic("String","Str");
        int out2 = search.matchHeuristic("String","Stri");
        int out3 = search.matchHeuristic("String","Strin");
        assertTrue(out1 < out2 && out2 < out3);

        // test char case
        out = search.matchHeuristic("words","WORDS");
        assertTrue(out > 0);

        // test spaces
        out = search.matchHeuristic("the brown fox","thebrownfox");
        assertTrue(out > 0);

        // test special chars
        search.matchHeuristic("\0","\0");
        assertTrue(out > 0);

        // test empty strings
        search.matchHeuristic("","");
        assertTrue(out > 0);

        System.out.println("Finished SearchTest: MatchHeuristic");
    }

    @Test
    public void testGetRelavenceList()
    {
        System.out.println("\nStarting SearchTest: getRelavenceList");
        Search search = new Search();

        LinkedList<Episode> list = search.getRelavenceList(episodeList, "JRE MMA Show #10 with Tyron Woodley");
        assertTrue( list.get(0).getTitle().equals( "JRE MMA Show #10 with Tyron Woodley" ));


        search = new Search();

        list = search.getRelavenceList(episodeList, "");
        assertTrue(list instanceof LinkedList);


        search = new Search();

        list = search.getRelavenceList(episodeList, "!F %\n __+=");
        assertTrue(list instanceof LinkedList);


        search = new Search();

        list = search.getRelavenceList(episodeList, "jamie foxx");
        assertTrue(list.get(0).getTitle().equals( "#990 - Jamie Foxx" ));

        search = new Search();

        list = search.getRelavenceList(episodeList, "jamy fox");
        assertTrue(list.get(0).getTitle().equals( "#990 - Jamie Foxx" ));

        search = new Search();

        list = search.getRelavenceList(episodeList, "Wow this string sure is long I wonder what would happen if someone where to use it as a search query");
        assertTrue(list instanceof LinkedList);

        search = new Search();

        list = search.getRelavenceList(episodeList, "nospacesforu");
        assertTrue(list instanceof LinkedList);

        search = new Search();

        list = search.getRelavenceList(episodeList, "\0");
        assertTrue(list instanceof LinkedList);

        search = new Search();

        list = search.getRelavenceList(episodeList, "890 break down");
        assertTrue(list.get(0).getTitle().equals( "#890 - Fight Breakdown" ));

        System.out.println("Finished SearchTest: getRelavanceList");
    }
}
