package comp3350.podcast.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.podcast.R;
import comp3350.podcast.objects.Episode;

class EpisodeListAdapter extends RecyclerView.Adapter<EpisodeListAdapter.EpisodeViewHolder> {
    private ArrayList<Episode> episodes;
    private Activity parent;

    // Provide a suitable constructor (depends on the kind of dataset)
    public EpisodeListAdapter(ArrayList<Episode> episodes, Activity parent) {
        this.episodes = episodes;
        this.parent = parent;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        // create a new view
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.episode_card, parent, false);

        EpisodeViewHolder vh = new EpisodeViewHolder(constraintLayout);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Episode ep = episodes.get(position);

        holder.titleView.setText(ep.getTitle());
        holder.descriptionView.setText(ep.getDesc());
        holder.episodeNumberView.setText("# " + ep.getEpNum());

        String secStr = "00";
        String minStr = "00";
        String hrStr = "00";
        int seconds = ep.getLength();
        int minutes = seconds / 60;
        seconds = seconds - minutes * 60;
        int hours = minutes / 60;
        minutes = minutes - hours * 60;
        if(seconds > 0){
            secStr = "" + seconds;
        }
        if(minutes > 0){
            minStr = "" + minutes;
        }
        if(hours > 0){
            hrStr = "" + hours;
        }
        holder.lengthView.setText("Length: " + hrStr + ":" + minStr + ":" + secStr);
        holder.episodeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent channelIntent = new Intent(parent.getApplicationContext(),ViewEpisodeActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("episode",ep);
                channelIntent.putExtras(b);
                parent.startActivity(channelIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder {
        public final ConstraintLayout episodeLayout;
        public final TextView titleView;
        public final TextView descriptionView;
        public final TextView episodeNumberView;
        public final TextView lengthView;

        public EpisodeViewHolder(ConstraintLayout episodeLayout) {
            super(episodeLayout);
            this.episodeLayout = episodeLayout;
            titleView = (TextView) episodeLayout.findViewById(R.id.title);
            lengthView = (TextView) episodeLayout.findViewById(R.id.episodeLength);
            descriptionView = (TextView) episodeLayout.findViewById(R.id.description);
            episodeNumberView = (TextView) episodeLayout.findViewById(R.id.episodeNumber);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleView.getText() + "'";
        }
    }
}
