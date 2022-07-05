package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherRVModel> weatherRVModelArrayList;

    public WeatherRVAdapter(Context context, ArrayList<WeatherRVModel> weatherRVModelArrayList) {
        this.context = context;
        this.weatherRVModelArrayList = weatherRVModelArrayList;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {
        WeatherRVModel model = weatherRVModelArrayList.get(position);
        holder.TVTemperature.setText(model.getTemperature() + "°c");
        holder.TVWindSpeed.setText(model.getWindSpeed()+"Km/H");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try{
            Date t = input.parse(model.getTime());
            holder.TVTime.setText(output.format(t));
        }catch (ParseException e){
            e.printStackTrace();
        }

        Picasso.get().load("http:".concat(model.getIcon())).into(holder.IVCondition);
    }

    @Override
    public int getItemCount() {
        return weatherRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView TVTime,TVTemperature,TVWindSpeed;
        private ImageView IVCondition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TVTime = itemView.findViewById(R.id.idTVTime);
            TVTemperature = itemView.findViewById(R.id.idTVTemperature);
            TVWindSpeed = itemView.findViewById(R.id.idTVWindspeed);
            IVCondition = itemView.findViewById(R.id.idIVCondition);
        }
    }
}
