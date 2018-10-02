package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.activity.MatchEvents;

public class RVMatchEventsAdapter extends RecyclerView.Adapter<RVMatchEventsAdapter.ViewHolder>{
    MatchEvents context;
    public RVMatchEventsAdapter(Activity context){
        this.context = (MatchEvents) context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.half_events, parent, false);
        RVMatchEventsAdapter.ViewHolder holder = new RVMatchEventsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int count = position + 1;
        String str = String.valueOf(count);
        holder.textHalf.setText("Тайм " + str);
        RVEventsAdapter adapter = new RVEventsAdapter(context);
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));




//        holder.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                MatchEvents.fab.hide();
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    MatchEvents.fab.show();
//                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    MatchEvents.fab.hide();
//                }
//                if (MatchEvents.scrollStatus) {
//                    MatchEvents.fab.hide();
//                }
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textHalf;
        RecyclerView recyclerView;
        public ViewHolder(View item) {
            super(item);
            textHalf = (TextView) item.findViewById(R.id.matchEventsHalf);
            recyclerView = (RecyclerView) item.findViewById(R.id.recyclerViewHalfEvents);
        }
    }
}
