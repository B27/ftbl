package com.example.user.secondfootballapp.user.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.activity.InvitationFragment;

public class RVInvitationAdapter extends RecyclerView.Adapter<RVInvitationAdapter.ViewHolder>{
    InvitationFragment context;
    public RVInvitationAdapter(InvitationFragment context){
        this.context =  context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invitation, parent, false);
        RVInvitationAdapter.ViewHolder holder = new RVInvitationAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String str = "Тренер: ";
        str += "Иванов В.В.";
        holder.textCoach.setText(str);
        str = "Команда: ";
        str += "Лара";
        holder.textCommand.setText(str);
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_fin)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image);
        holder.buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //post
                int newPosition = holder.getAdapterPosition();
                notifyItemRemoved(newPosition);
            }
        });

        holder.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //post
                int newPosition = holder.getAdapterPosition();
                notifyItemRemoved(newPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView image;
        TextView textTournamentTitle;
        TextView textTournamentDate;
        TextView textCommand;
        TextView textCoach;
        Button buttonCancel;
        Button buttonOk;
        public ViewHolder(View item) {
            super(item);
            image = (ImageView) item.findViewById(R.id.userTournamentInvLogo);
            textTournamentTitle = (TextView) item.findViewById(R.id.invTournamentTitle);
            textTournamentDate = (TextView) item.findViewById(R.id.invTournamentDate);
            textCommand = (TextView) item.findViewById(R.id.invTournamentCommandTitle);
            textCoach = (TextView) item.findViewById(R.id.invTournamentCoach);
            buttonCancel = (Button) item.findViewById(R.id.invTournamentButtonCancel);
            buttonOk = (Button) item.findViewById(R.id.invTournamentButtonOk);
        }
    }


}
