package comp3350.podcast.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.podcast.application.Main;
import comp3350.podcast.application.Services;
import comp3350.podcast.business.AccessChannels;
import comp3350.podcast.business.AccessEpisodes;
import comp3350.podcast.business.AccessPlaylists;
import comp3350.podcast.objects.ChannelList;
import comp3350.podcast.objects.EpisodeList;
import comp3350.podcast.objects.Playlist;
import comp3350.podcast.tests.persistence.StubData;


import static org.junit.Assert.*;

public class AccessPlaylistsTest
{
    private static String dbName = Main.dbName;
    private AccessEpisodes ae;
    private AccessChannels ac;
    private AccessPlaylists ap;
    private ArrayList<Playlist> playlistList;
    private ArrayList<Playlist> resultPl;
    private ChannelList channelList;
    private ChannelList playlistChannelList;
    private ChannelList resultCh;
    private EpisodeList episodeList;
    private EpisodeList playlistEpisodesList;
    private EpisodeList resultEp;
    private Playlist playlist1;
    private Playlist playlist2;
    private Playlist playlist3;
    private Playlist playlist4;

    public AccessPlaylistsTest() { super(); }

    @Before
    public void setUp() {
        Services.closeDataAccess();
        Services.createDataAccess(new StubData(dbName));

        ae = new AccessEpisodes();
        ac = new AccessChannels();
        ap = new AccessPlaylists();
        channelList = new ChannelList();
        episodeList = new EpisodeList();
        playlistList = new ArrayList<>();
        playlistChannelList = new ChannelList();
        playlistEpisodesList = new EpisodeList();
        ac.getChannels(channelList);
        ae.getEpisodes(episodeList);

        playlist1 = new Playlist("Chill");
        playlist2 = new Playlist("Relax");
        playlist3 = new Playlist("Put a little make up");
        playlist4 = new Playlist("Jaaam");

        playlistChannelList.add(channelList.get(1));
        playlistChannelList.add(channelList.get(2));

        playlistEpisodesList.add(episodeList.get(6));
        playlistEpisodesList.add(episodeList.get(4));
        playlistEpisodesList.add(episodeList.get(0));

        playlistList.add(playlist1);
        playlistList.add(playlist2);
    }

    @Test
    public void testInsertPlaylist()
    {
        System.out.println("\nStarting AccessPlaylistsTest: InsertPlaylist");
        resultPl = new ArrayList<>();
        Playlist pl = null;

        // test retrieving the playlists from the database
        ap.getPlaylists(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting a playlist
        playlistList.add(playlist3);
        ap.insertPlaylist(playlist3);
        ap.getPlaylists(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting multiple playlists
        playlistList.add(playlist4);
        ap.deletePlaylist(playlist3);
        ap.insertPlaylist(playlist3);
        ap.insertPlaylist(playlist4);
        ap.getPlaylists(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test inserting duplicate playlist
        ap.insertPlaylist(playlist1);
        ap.getPlaylists(resultPl);

        // test inserting a null playlist
        try {
            ap.insertPlaylist(pl);
            ap.getPlaylists(resultPl);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        System.out.println("Finished AccessPlaylistsTest: InsertPlaylist");
    }

    @Test
    public void testDeletePlaylist()
    {
        System.out.println("\nStarting AccessPlaylistsTest: DeletePlaylist");
        resultPl = new ArrayList<>();

        playlistList.add(playlist3);

        // test deleting a channel
        playlistList.remove(playlist3);
        ap.insertPlaylist(playlist3);
        ap.deletePlaylist(playlist3);
        ap.getPlaylists(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test deleting multiple channels
        playlistList.remove(playlist1);
        playlistList.remove(playlist2);
        ap.deletePlaylist(playlist1);
        ap.deletePlaylist(playlist2);
        ap.getPlaylists(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));

        // test deleting a channel that does not exist
        ap.deletePlaylist(playlist1);
        ap.getPlaylists(resultPl);
        assertTrue(comparePlaylistLists(resultPl, playlistList));
        System.out.println("Finished AccessPlaylistsTest: DeletePlaylist");
    }
    @Test
    public void testInsertPlaylistChannel()
    {
        System.out.println("\nStarting AccessPlaylistsTest: InsertPlaylistChannel");
        resultCh = new ChannelList();

        // test inserting a channel into a playlist
        playlistChannelList.add(channelList.get(0));
        ap.insertPlaylistChannel(channelList.get(0), playlist1);
        ap.getPlaylistChannels(resultCh, playlist1);
        assertTrue(playlistChannelList.equals(resultCh));

        // test inserting duplicate channel into a playlist
        ap.insertPlaylistChannel(channelList.get(0), playlist1);
        ap.getPlaylistChannels(resultCh, playlist1);
        assertTrue(playlistChannelList.equals(resultCh));

        // test inserting channel into a non existent playlist
        ap.insertPlaylistChannel(channelList.get(0), playlist3);
        ap.getPlaylistChannels(resultCh, playlist3);
        playlistChannelList = new ChannelList();
        assertTrue(playlistChannelList.equals(resultCh));

        System.out.println("Finished AccessPlaylistsTest: InsertPlaylistChannel");
    }

    @Test
    public void testInsertPlaylistEpisode()
    {
        System.out.println("\nStarting AccessPlaylistsTest: InsertPlaylistEpisode");
        resultEp = new EpisodeList();

        // test inserting an episode into a playlist
        playlistEpisodesList.add(episodeList.get(1));
        ap.insertPlaylistEpisode(episodeList.get(1), playlist1);
        ap.getPlaylistEpisodes(resultEp, playlist1);
        assertTrue(playlistEpisodesList.equals(resultEp));

        // test inserting duplicate episode into a playlist
        ap.insertPlaylistEpisode(episodeList.get(1), playlist1);
        ap.getPlaylistEpisodes(resultEp, playlist1);
        assertTrue(playlistEpisodesList.equals(resultEp));

        // test inserting episode into a non existent playlist
        ap.insertPlaylistEpisode(episodeList.get(3), playlist4);
        ap.getPlaylistEpisodes(resultEp, playlist4);
        playlistEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(playlistEpisodesList));

        System.out.println("Finished AccessPlaylistsTest: InsertPlaylistEpisode");
    }

    @Test
    public void testDeletePlaylistChannel()
    {
        System.out.println("\nStarting AccessPlaylistsTest: DeletePlaylistChannel");
        resultCh = new ChannelList();

        playlistChannelList.add(channelList.get(0));

        // test deleting a channel into a playlist
        playlistChannelList.remove(channelList.get(0));
        ap.insertPlaylistChannel(channelList.get(0), playlist1);
        ap.deletePlaylistChannel(channelList.get(0), playlist1);
        ap.getPlaylistChannels(resultCh, playlist1);
        assertTrue(playlistChannelList.equals(resultCh));

        // test deleting channel into a non existent playlist
        ap.deletePlaylistChannel(channelList.get(2), playlist3);
        ap.getPlaylistChannels(resultCh, playlist3);
        playlistChannelList = new ChannelList();
        assertTrue(playlistChannelList.equals(resultCh));
        System.out.println("Finished AccessPlaylistsTest: DeletePlaylistChannel");
    }

    @Test
    public void testDeletePlaylistEpisode()
    {
        System.out.println("\nStarting AccessPlaylistsTest: DeletePlaylistEpisode");
        resultEp = new EpisodeList();

        playlistEpisodesList.add(episodeList.get(1));

        // test deleting an episode from a playlist
        playlistEpisodesList.remove(episodeList.get(1));
        ap.insertPlaylistEpisode(episodeList.get(1), playlist1);
        ap.deletePlaylistEpisode(episodeList.get(1), playlist1);
        ap.getPlaylistEpisodes(resultEp, playlist1);
        assertTrue(playlistEpisodesList.equals(resultEp));

        // test deleting an episode into a non existent playlist
        ap.deletePlaylistEpisode(episodeList.get(0), playlist4);
        ap.getPlaylistEpisodes(resultEp, playlist4);
        playlistEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(playlistEpisodesList));

        System.out.println("Finished AccessPlaylistsTest: DeletePlaylistEpisode");
    }

    @Test
    public void testPlaylistChannelList()
    {
        System.out.println("\nStarting AccessPlaylistsTest: PlaylistChannelList");
        resultCh = new ChannelList();

        // test retrieving the channels based from a playlist
        ap.getPlaylistChannels(resultCh, playlist1);
        assertTrue(resultCh.equals(playlistChannelList));

        // test retrieving the episodes based from a playlist that does not exist
        ap.getPlaylistChannels(resultCh, playlist4);
        playlistChannelList = new ChannelList();
        assertTrue(resultCh.equals(playlistChannelList));
        System.out.println("Finished AccessPlaylistsTest: PlaylistChannelList");
    }

    @Test
    public void testPlaylistEpisodeList()
    {
        System.out.println("\nStarting AccessPlaylistsTest: PlaylistEpisodeList");
        EpisodeList resultEp = new EpisodeList();

        // test retrieving the episodes based from a playlist
        ap.getPlaylistEpisodes(resultEp, playlist1);
        assertTrue(resultEp.equals(playlistEpisodesList));

        // test retrieving the episodes based from a playlist that does not exist
        ap.getPlaylistEpisodes(resultEp, playlist4);
        playlistEpisodesList = new EpisodeList();
        assertTrue(resultEp.equals(playlistEpisodesList));
    }

    private boolean comparePlaylistLists(ArrayList<Playlist> origPlaylist, ArrayList<Playlist> otherPlaylist)
    {
        Boolean result = false;
        if (origPlaylist.size() == otherPlaylist.size())
        {
            result = true;
            for ( int i = 0; i < origPlaylist.size(); i++ )
            {
                if ( !origPlaylist.get(i).equals(otherPlaylist.get(i)) )
                {
                    result = false;
                    return result;
                }
            }
        }

        return result;
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
    }
}
