package com.hfad.appfinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerMatchAdapter extends RecyclerView.Adapter<PlayerMatchAdapter.MyViewHolder> {

    private Context context;
    private List<PlayerMatchModel> matchModelList;

    public PlayerMatchAdapter(Context context, List<PlayerMatchModel> matchModelList) {
        this.context = context;
        this.matchModelList = matchModelList;
    }


    @NonNull
    @NotNull
    @Override
    public PlayerMatchAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.match_team_list,parent,false);
        PlayerMatchAdapter.MyViewHolder viewHolder=new PlayerMatchAdapter.MyViewHolder(view);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set string Vars to get all information
                //Will show all details from TeamModelList
                //getAdapterPosition to get position of particular item
                //the open the full details

                String matchId=matchModelList.get(viewHolder.getAdapterPosition()).getMatchId();
                String date=matchModelList.get(viewHolder.getAdapterPosition()).getDate();
                String time=matchModelList.get(viewHolder.getAdapterPosition()).getTime();
                String venue=matchModelList.get(viewHolder.getAdapterPosition()).getVenue();

                //create intent to TeamDetailsActivity
                Intent intent=new Intent(context,ActivityPlayerMatchDetails.class);

                //set keys to the intent to locate item details
                intent.putExtra("match_id",matchId);
                intent.putExtra("date",date);
                intent.putExtra("time",time);
                intent.putExtra("venue",venue);

                context.startActivity(intent);

            }
        });

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlayerMatchAdapter.MyViewHolder holder, int position) {
        PlayerMatchModel matchModel=matchModelList.get(position); //get position and bind to RecView Adapter
        //set variable
        //String matchId= matchModel.getMatchId();
        String matchDate=matchModel.getDate();
        //item from team model bound to card view
        //holder.textViewName.setText(matchId);
        holder.textViewName.setText(matchDate);

    }

    @Override
    public int getItemCount() {

        return matchModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView textViewName;

        private LinearLayout linearLayout;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.list_name_match_id);
            linearLayout=itemView.findViewById(R.id.list_container_match);

        }
    }
}
