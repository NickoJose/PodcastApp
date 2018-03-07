package comp3350.podcast.persistence;


import java.util.List;

import comp3350.podcast.objects.Channel;
import comp3350.podcast.objects.Date;
import comp3350.podcast.objects.Episode;

public interface AccessData
{
    void open(String dbName);

    void close();

    Date createDate(int objYear, int objMonth, int objDay, int objHour, int objMinute, int objSecond);

    String getChannelSequential(List<Channel> channelResult);

    String insertChannel(Channel currentChannel);

    String deleteChannel(Channel currentChannel);

    String updateChannel(Channel currentChannel);

    String getEpisodesSequential(List<Episode> episodeResult);

    String getChannelEpisodeSequential(List<Episode> episodeResult, Channel currentChannel);

    String insertEpisode(Episode currentEpisode);

    String deleteEpisode(Episode currentEpisode);

    String updateEpisode(Episode currentEpisode);

    String getPlaylistChannelSequential(List<Channel> channelResult);

    String getPlayListEpisodeSequential(List<Episode> episodeResult);

    boolean insertPlaylistChannel(Channel currentChannel);

    boolean insertPlaylistEpisode(Episode currentEpisode);

    boolean deletePlaylistChannel(Channel currentChannel);

    boolean deletePlaylistEpisode(Episode currentEpisode);
}
