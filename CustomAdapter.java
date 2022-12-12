package com.example.lastdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastdemo.UpdateActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList trip_id, trip_title,trip_transport, trip_date, trip_returndate, trip_description, trip_participant;

    CustomAdapter(Activity activity, Context context, ArrayList trip_id, ArrayList trip_title,ArrayList trip_transport ,ArrayList trip_date, ArrayList trip_returndate, ArrayList trip_description,
                  ArrayList trip_participant){
        this.activity = activity;
        this.context = context;
        this.trip_id = trip_id;
        this.trip_title = trip_title;
        this.trip_transport = trip_transport;
        this.trip_date = trip_date;
        this.trip_returndate = trip_returndate;
        this.trip_description = trip_description;
        this.trip_participant = trip_participant;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.trip_title_txt.setText(String.valueOf(trip_title.get(position)));
        holder.trip_transport_txt.setText(String.valueOf(trip_transport.get(position)));
        holder.trip_date_txt.setText(String.valueOf(trip_date.get(position)));
        holder.trip_returndate_txt.setText(String.valueOf(trip_returndate.get(position)));

        holder.trip_description_txt.setText(String.valueOf(trip_description.get(position)));
        holder.trip_participant_txt.setText(String.valueOf(trip_participant.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(trip_id.get(position)));
                intent.putExtra("title", String.valueOf(trip_title.get(position)));
                intent.putExtra("transport", String.valueOf(trip_transport.get(position)));

                intent.putExtra("date", String.valueOf(trip_date.get(position)));
                intent.putExtra("returndate", String.valueOf(trip_returndate.get(position)));


                intent.putExtra("description", String.valueOf(trip_description.get(position)));
                intent.putExtra("participant", String.valueOf(trip_participant.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView trip_id_txt, trip_title_txt, trip_transport_txt, trip_date_txt, trip_returndate_txt,  trip_description_txt,  trip_participant_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            trip_title_txt = itemView.findViewById(R.id.trip_title_txt);
            trip_transport_txt = itemView.findViewById(R.id.trip_transport_txt);
            trip_date_txt = itemView.findViewById(R.id.trip_date_txt);
            trip_returndate_txt = itemView.findViewById(R.id.trip_returndate_txt);
            trip_description_txt = itemView.findViewById(R.id.trip_description_txt);
            trip_participant_txt = itemView.findViewById(R.id.trip_participant_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
