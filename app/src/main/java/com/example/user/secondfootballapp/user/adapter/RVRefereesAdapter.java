package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.CheckName;
import com.example.user.secondfootballapp.FullScreenImage;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.user.activity.RefereeFragment;
import com.example.user.secondfootballapp.user.activity.RefereesMatches;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.example.user.secondfootballapp.Controller.BASE_URL;

public class RVRefereesAdapter extends RecyclerView.Adapter<RVRefereesAdapter.ViewHolder>{
    RefereeFragment context;
    PersonalActivity activity;
    private List<Person> referees;
    public RVRefereesAdapter(Activity activity, RefereeFragment context, List<Person> referees){
        this.context =  context;
        this.referees =  referees;
        this.activity = (PersonalActivity) activity;
    }
    @NonNull
    @Override
    public RVRefereesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.referee, parent, false);
        RVRefereesAdapter.ViewHolder holder = new RVRefereesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVRefereesAdapter.ViewHolder holder, int position) {
        CheckName checkName = new CheckName();
        Person person = referees.get(position);
        String str = checkName.check(person.getSurname(), person.getName(), person.getLastname());
        holder.textName.setText(str);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.optionalCircleCrop();
        requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
        requestOptions.error(R.drawable.ic_logo2);
        requestOptions.override(500, 500);
        requestOptions.priority(Priority.HIGH);
        try {
            String uriPic = BASE_URL;
            uriPic += "/" + person.getPhoto();
            URL url = new URL(uriPic);
            Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .apply(requestOptions)
                    .into(holder.image);
            final String finalUriPic = uriPic;
            holder.image.setOnClickListener(v -> {
                if (finalUriPic.contains(".jpg") || finalUriPic.contains(".jpeg") || finalUriPic.contains(".png")) {
                    Intent intent = new Intent(context.getContext(), FullScreenImage.class);
                    intent.putExtra("player_photo", finalUriPic);
                    context.startActivity(intent);
                }

            });
        } catch (MalformedURLException e) {
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_logo2)
                    .apply(requestOptions)
                    .into(holder.image);
        }
        holder.buttonShow.setOnClickListener(v -> {
            Intent intent = new Intent(activity, RefereesMatches.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("REFEREESMATCHES", person);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
        if (position==(referees.size()-1)){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return referees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textName;
        LinearLayout buttonShow;
        View line;
        public ViewHolder(View item) {
            super(item);
            image = item.findViewById(R.id.refereePhoto);
            textName = item.findViewById(R.id.refereeName);
            buttonShow = item.findViewById(R.id.refereeButtonShow);
            line = item.findViewById(R.id.refereeLine);
        }
    }
}
