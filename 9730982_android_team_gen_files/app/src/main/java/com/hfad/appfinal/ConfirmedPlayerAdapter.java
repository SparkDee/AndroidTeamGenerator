package com.hfad.appfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ConfirmedPlayerAdapter extends RecyclerView.Adapter<ConfirmedPlayerAdapter.MyViewHolder> {

    //vars
    private Context context;
    private List<ConfirmedPlayerModel> playerList;


    //const
    public ConfirmedPlayerAdapter(Context context, List<ConfirmedPlayerModel> playerList) {
        this.context = context;
        this.playerList = playerList;
    }




    @NonNull
    @NotNull
    @Override
    public ConfirmedPlayerAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //set view layout
        View view= LayoutInflater.from(context).inflate(R.layout.admin_confirmed_player_list,parent,false);
        ConfirmedPlayerAdapter.MyViewHolder viewHolder=new ConfirmedPlayerAdapter.MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ConfirmedPlayerAdapter.MyViewHolder holder, int position) {

        ConfirmedPlayerModel playerModel=playerList.get(position);

        String playerName=playerModel.getName();
        holder.textViewName.setText(playerName);


    }

    @Override
    public int getItemCount() {

        return playerList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;

        private LinearLayout linearLayout;

        public MyViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);

            textViewName=itemView.findViewById(R.id.list_name_admin_confirmed_player_name);
            linearLayout=itemView.findViewById(R.id.list_container_admin_confirmed_player);
        }
    }
}
