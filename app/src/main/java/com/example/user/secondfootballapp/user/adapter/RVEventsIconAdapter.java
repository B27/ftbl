package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.activity.AddEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RVEventsIconAdapter extends RecyclerView.Adapter<RVEventsIconAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(AddEvent.class);
    AddEvent context;
    List<String> list;
    private List<Boolean> list2;
    private ListAdapterListener mListener;
    int selectedPosition;
    public int[] imgArray ={
            R.drawable.ic_con,
            R.drawable.ic_yellow_card,
            R.drawable.ic_red_card,
            R.drawable.ic_faul,
            R.drawable.ic_goal,
            R.drawable.ic_penalty
    };
    public interface ListAdapterListener {
        void onClickSwitch(int position);
    }
    public RVEventsIconAdapter(Activity context, List<String> list, List<Boolean> list2, ListAdapterListener mListener){
        this.context = (AddEvent) context;
        this.list = list;
        this.list2 = list2;
        this.mListener = mListener;
    }
    @NonNull
    @Override
    public RVEventsIconAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_icon, parent, false);
        RVEventsIconAdapter.ViewHolder holder = new RVEventsIconAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RVEventsIconAdapter.ViewHolder holder, final int position) {
        if (!list2.get(position) && list2.contains(true)){
            holder.badgeView.visibleBadge(false);
            holder.textName.setTextColor(context.getResources().getColor(R.color.colorLightGrayForText));
        }
        String str;
        str = list.get(position);
        holder.textName.setText(str);
        Glide.with(context)
                .asBitmap()
                .load(imgArray[position])
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.badgeView);

        final int p = position;
        holder.badgeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.badgeView.isVisibleBadge()){
                    holder.badgeView.visibleBadge(false);
                    holder.textName.setTextColor(context.getResources().getColor(R.color.colorLightGrayForText));
                    AddEvent.list2.set(p, false);
                    mListener.onClickSwitch(100);
                }
                else {
                    holder.textName.setTextColor(context.getResources().getColor(R.color.colorBottomNavigationUnChecked));
                    holder.badgeView.visibleBadge(true);
                    for (int i=0; i< list2.size(); i++){
                        AddEvent.list2.set(i, false);
                    }
                    AddEvent.list2.set(p, true);

                    mListener.onClickSwitch(p);
                }

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textName;
        View itemView;
        ru.nikartm.support.ImageBadgeView badgeView;
        public ViewHolder(View item) {
            super(item);
            itemView = item;
            badgeView = (ru.nikartm.support.ImageBadgeView) item.findViewById(R.id.ibv_icon);
            textName = (TextView) item.findViewById(R.id.eventTitle);
//            itemView.setOnClickListener(this);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectedPosition = getAdapterPosition();
//                    if( selectedPosition == RecyclerView.NO_POSITION) return;
//                    recyclerView.OnItemClickListener.onItemSelect(itemView, getAdapterPosition()); //Custom listener - in turn calls your highlightButton method
//
//                    notifyDataSetChanged();// or notifyItemRangeChanged();
//                }
//            });
        }
//        @Override
//        public void onClick(View v) {
//            // Below line is just like a safety check, because sometimes holder could be null,
//            // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
//            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
//
//            // Updating old as well as new positions
//            notifyItemChanged(selectedPosition);
//            selectedPosition = getAdapterPosition();
//            notifyItemChanged(selectedPosition);
//
//            // Do your another stuff for your onClick
//        }
    }


}
