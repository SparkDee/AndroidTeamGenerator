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

public class PlayerTeamAdapter extends RecyclerView.Adapter<PlayerTeamAdapter.MyViewHolder> {


    private Context context;
    private List<PlayerTeamModel> teamModelList;

    //create constructor
    public PlayerTeamAdapter(Context context, List<PlayerTeamModel> teamModelList) {
        this.context = context;
        this.teamModelList = teamModelList;
    }


    @NonNull
    @NotNull
    @Override
    public PlayerTeamAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.player_team_list,parent,false);
        PlayerTeamAdapter.MyViewHolder viewHolder=new PlayerTeamAdapter.MyViewHolder(view);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set string Vars to get all information
                //Will show all details from TeamModelList
                //getAdapterPosition to get position of particular item
                //the open the full details
                String name=teamModelList.get(viewHolder.getAdapterPosition()).getTeamName();
                String teamId=teamModelList.get(viewHolder.getAdapterPosition()).getTeamId();
                String playerId=teamModelList.get(viewHolder.getAdapterPosition()).getPlayerId();

                //create intent to TeamDetailsActivity
                Intent intent=new Intent(context,ActivityTeamDetailsPlayer.class);

                //set keys to the intent to locate item details
                intent.putExtra("team_name",name);
                intent.putExtra("team_id",teamId);

                context.startActivity(intent);

            }
        });

        return viewHolder;



    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlayerTeamAdapter.MyViewHolder holder, int position) {


        PlayerTeamModel teamModel=teamModelList.get(position); //get position and bind to RecView Adapter
        //set variable
        //getPlayerName
        String name= teamModel.getTeamName();
        //item from team model bound to card view
        holder.textViewName.setText(name);

    }

    @Override
    public int getItemCount() {

        return teamModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //Initialise the vars from the team_list
        private TextView textViewName;
        private LinearLayout linearLayout;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.list_name_player);
            linearLayout=itemView.findViewById(R.id.list_container_player);

        }
    }
}
