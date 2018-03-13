package comp3350.podcast.representation;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.podcast.R;
import comp3350.podcast.objects.Channel;

/**
 * Created by Russell on 2018-03-12.
 */

class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ChannelViewHolder> {
    private ArrayList<Channel> channels;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChannelListAdapter(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChannelListAdapter.ChannelViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_card, parent, false);

        ChannelViewHolder vh = new ChannelViewHolder(constraintLayout);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Channel ch = channels.get(position);

        holder.titleView.setText(ch.getTitle());
        holder.descriptionView.setText(ch.getDesc());
        holder.episodeCountView.setText("" + ch.getNumEps());
        holder.authorView.setText(ch.getAuthor());
        holder.urlView.setText(ch.getUrl());

    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder {
        public final ConstraintLayout channelLayout;
        public final TextView titleView;
        public final TextView descriptionView;
        public final TextView episodeCountView;
        public final TextView authorView;
        public final TextView urlView;

        public ChannelViewHolder(ConstraintLayout channelLayout) {
            super(channelLayout);
            this.channelLayout = channelLayout;
            titleView = (TextView) channelLayout.findViewById(R.id.title);
            descriptionView = (TextView) channelLayout.findViewById(R.id.description);
            episodeCountView = (TextView) channelLayout.findViewById(R.id.episodeCount);
            System.out.println(episodeCountView.getText());
            authorView = (TextView) channelLayout.findViewById(R.id.author);
            urlView = (TextView) channelLayout.findViewById(R.id.url);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleView.getText() + "'";
        }
    }
}
