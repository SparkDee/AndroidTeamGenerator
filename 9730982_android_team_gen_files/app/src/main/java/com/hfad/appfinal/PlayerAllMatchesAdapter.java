package com.hfad.appfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerAllMatchesAdapter extends RecyclerView.Adapter<PlayerAllMatchesAdapter.MyViewHolder> {

    private Context context;
    private List<PlayerAllMatchesModel> playerFullMatchList;

    public PlayerAllMatchesAdapter(Context context, List<PlayerAllMatchesModel> playerFullMatchList) {
        this.context = context;
        this.playerFullMatchList = playerFullMatchList;
    }

    @NonNull
    @NotNull
    @Override
    public PlayerAllMatchesAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.player_view_all_matches_complete,parent,false);
        PlayerAllMatchesAdapter.MyViewHolder viewHolder=new PlayerAllMatchesAdapter.MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlayerAllMatchesAdapter.MyViewHolder holder, int position) {

        PlayerAllMatchesModel matchesModel=playerFullMatchList.get(position);

        String matchDate=matchesModel.getDate();
        String matchTime=matchesModel.getTime();
        String matchVenue=matchesModel.getVenue();
        holder.textViewDate.setText(matchDate);
        holder.textViewTime.setText(matchTime);
        holder.textViewVenue.setText(matchVenue);

    }

    @Override
    public int getItemCount() {
        return playerFullMatchList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDate;
        private TextView textViewTime;
        private TextView textViewVenue;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewDate=itemView.findViewById(R.id.list_all_matches_complete_date);
            textViewTime=itemView.findViewById(R.id.list_all_matches_complete_time);
            textViewVenue=itemView.findViewById(R.id.list_all_matches_complete_venue);

        }
    }
}
