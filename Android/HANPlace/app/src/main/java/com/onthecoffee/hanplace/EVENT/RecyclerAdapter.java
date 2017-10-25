package com.onthecoffee.hanplace.EVENT;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.onthecoffee.hanplace.DOMAIN.HG_Event;
import com.onthecoffee.hanplace.R;
import android.support.v7.widget.CardView;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by BAEJJI on 2017-05-03.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<HG_Event> eventList;
    int layout;

    public RecyclerAdapter(Context context, ArrayList<HG_Event> eventList, int layout) {
        this.context = context;
        this.eventList = eventList;
        this.layout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HG_Event event = eventList.get(position);
        //Drawable drawable = ContextCompat.getDrawable(context, item.getImage());
        //holder.image.setBackground(drawable);
        holder.event_title.setText(event.getEvent_title());
        holder.event_start_date.setText(event.getEvent_start_date());
        holder.event_content.setText(event.getEvent_content());
        holder.event_loc.setText(event.getEvent_loc());
        holder.cardView.setId(event.getEvent_id());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventViewActivity.class);
                intent.putExtra("event", event);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView event_title, event_start_date, event_loc, event_content, event_favorite_count;
        ImageView event_picture, eventReadBtn;

        public ViewHolder(View eventView) {
            super(eventView);
            cardView = (CardView) eventView.findViewById(R.id.cardView);
            event_title = (TextView) eventView.findViewById(R.id.event_title);
            event_start_date = (TextView) eventView.findViewById(R.id.event_start_date);
            event_loc = (TextView) eventView.findViewById(R.id.event_loc);
            event_content = (TextView) eventView.findViewById(R.id.event_content);
            event_favorite_count = (TextView) eventView.findViewById(R.id.event_favorite_count);
            eventReadBtn = (ImageView) eventView.findViewById(R.id.eventReadBtn);
            event_picture = (ImageView) eventView.findViewById(R.id.event_picture);

        }
    }

/*    public View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), EventViewActivity.class);
            HG_Event event = new HG_Event();

            for(int i=0; i<eventList.size(); i++) {

                if(eventList.get(i).getEvent_id() == v.getId()) {

                    event = eventList.get(i);
                    break;

                }

            }

            intent.putExtra("event", event);
            v.getContext().startActivity(intent);

        }

    };*/

}