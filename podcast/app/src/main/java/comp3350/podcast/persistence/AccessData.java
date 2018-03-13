package comp3350.podcast.persistence;


import java.util.List;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Episode;
import comp3350.podcast.objects.Playlist;

public interface AccessData
{
    void open(String dbName);

    void close();

    String getChannelSequential(List<Channel> channelResult);

    String insertChannel(Channel currentChannel);

    String deleteChannel(Channel currentChannel);

    String updateChannel(Channel currentChannel);

    String getEpisodesSequential(List<Episode> episodeResult);

    String getChannelEpisodeSequential(List<Episode> episodeResult, Channel currentChannel);

    String insertEpisode(Episode currentEpisode);

    String deleteEpisode(Episode currentEpisode);

    String updateEpisode(Episode currentEpisode);

    String getPlaylistSequential(List<Playlist> playlistResult);

    String getPlaylistChannelSequential(List<Channel> channelResult, Playlist currentPlaylist);

    String getPlaylistEpisodeSequential(List<Episode> episodeResult, Playlist currentPlaylist);

    String insertPlaylist(Playlist currentPlaylist);

    String deletePlaylist(Playlist currentPlaylist);

    boolean insertPlaylistChannel(Channel currentChannel, Playlist currentPlaylist);

    boolean insertPlaylistEpisode(Episode currentEpisode, Playlist currentPlaylist);

    boolean deletePlaylistChannel(Channel currentChannel, Playlist currentPlaylist);

    boolean deletePlaylistEpisode(Episode currentEpisode, Playlist currentPlaylist);

    String insertSub(Channel currentChannel);

    String deleteSub(Channel currentChannel);

    String getSubSequential(List<Channel> channelResult);
}
