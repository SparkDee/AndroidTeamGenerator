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

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {

    //initialise context and call java model class, set as a list
    private Context context;
    private List<TeamModel> teamModelList;



    //create constructor
    public TeamAdapter(Context context, List<TeamModel> teamModelList) {
        this.context = context;
        this.teamModelList = teamModelList;
    }


    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //set view layout
        View view= LayoutInflater.from(context).inflate(R.layout.team_list,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);

        //onclickListener on the LinearLayout
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set string Vars to get all information
                //Will show all details from TeamModelList
                //getAdapterPosition to get position of particular item
                //the open the full details
                String name=teamModelList.get(viewHolder.getAdapterPosition()).getTeamName();
                String teamId=teamModelList.get(viewHolder.getAdapterPosition()).getTeamId();

                //create intent to TeamDetailsActivity
                Intent intent=new Intent(context,ActivityTeamDetails.class);

                //set keys to the intent to locate item details
                intent.putExtra("team_name",name);
                intent.putExtra("team_id",teamId);

                context.startActivity(intent);

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TeamAdapter.MyViewHolder holder, int position) {
        //bind teams from team model class to layout
        //set recycler view in view teams
        //need to call java team model class

        TeamModel teamModel=teamModelList.get(position); //get position and bind to RecView Adapter
        //set variable
        String name= teamModel.getTeamName();
        //item from team model bound to card view
        holder.textViewName.setText(name);

    }

    @Override
    public int getItemCount() {
        //return the whole list of items from java model class
        return teamModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //Initialise the vars from the team_list
        private TextView textViewName;
        private LinearLayout linearLayout;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.list_name);
            linearLayout=itemView.findViewById(R.id.list_container);

        }
    }
}
