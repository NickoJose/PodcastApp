package comp3350.podcast.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.podcast.R;
import comp3350.podcast.objects.Channel;

class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ChannelViewHolder> {
    private ArrayList<Channel> channels;
    private Activity parent;

    public ChannelListAdapter(ArrayList<Channel> channels, Activity parent) {
        this.channels = channels;
        this.parent = parent;
    }

    @Override
    public ChannelListAdapter.ChannelViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_card, parent, false);

        ChannelViewHolder vh = new ChannelViewHolder(constraintLayout);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ChannelViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Channel ch = channels.get(position);
        Context context = holder.imgView.getContext();

        holder.titleView.setText(ch.getTitle());
        holder.descriptionView.setText(ch.getDesc());
        holder.episodeCountView.setText("" + ch.getNumEps());
        holder.authorView.setText(ch.getAuthor());
        holder.urlView.setText(ch.getUrl());
        int imgID = context.getResources().getIdentifier(ch.getImg(),"drawable",context.getPackageName());
        holder.imgView.setImageResource(imgID);


        holder.channelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent channelIntent = new Intent(parent.getApplicationContext(),ViewChannelActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("channel",ch);
                channelIntent.putExtras(b);
                parent.startActivity(channelIntent);
            }
        });

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
        public final ImageView imgView;

        public ChannelViewHolder(final ConstraintLayout channelLayout) {
            super(channelLayout);
            this.channelLayout = channelLayout;
            titleView = (TextView) channelLayout.findViewById(R.id.title);
            descriptionView = (TextView) channelLayout.findViewById(R.id.description);
            episodeCountView = (TextView) channelLayout.findViewById(R.id.episodeCount);
            authorView = (TextView) channelLayout.findViewById(R.id.author);
            urlView = (TextView) channelLayout.findViewById(R.id.url);
            imgView = (ImageView)channelLayout.findViewById(R.id.imageView2);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleView.getText() + "'";
        }
    }
}
