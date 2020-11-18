package com.ali.ssbdeliveryboy.holderclasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.ssbdeliveryboy.R;
import com.ali.ssbdeliveryboy.modelclasses.modelshops;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class holdershops extends RecyclerView.Adapter<holdershops.holder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<modelshops> models;
//    onshopclicklistener monshopclicklistener;
private int selectedPosition = -1;
onselect monclicklistener;
    private  static String mysharedpref="mysharedpref";

    public holdershops(Context context, List<modelshops> models) {
        this.context = context;
        this.models = models;
    }


    public interface onselect{
        public void onselect(String name,String shopid);
    }
    public void onclick(onselect listener){
        monclicklistener=listener;
    }


    @NonNull
    @Override
    public holdershops.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selectshoplayout,parent,false);
        return new holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull holdershops.holder holder, int position) {

        try {
            SharedPreferences prefs = context.getSharedPreferences(mysharedpref, MODE_PRIVATE);
            String path=models.get(position).getImage().replaceFirst("localhost",prefs.getString("ipv4","10.0.2.2"));
            Picasso.get().load(path).networkPolicy(NetworkPolicy.NO_STORE).into(holder.imageView);
        }
        catch (Exception e){
        }
        holder.title.setText(models.get(position).getShopName());
        holder.cat.setText(models.get(position).getShopCategory());

        if (models.get(position).getShopName().length()>16){
            holder.title.setText(models.get(position).getShopName().substring(0,15).concat("..."));
        }
        if (models.get(position).getShopCategory().length()>35){
            holder.cat.setText(models.get(position).getShopCategory().substring(0,35).concat("..."));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, models.get(position).get_id().toString(), Toast.LENGTH_SHORT).show();
//                monshopclicklistener.onshopqclick(String.valueOf(models.get(position).get_id()),models.get(position).getShopName(),models.get(position).getShopCategory());
            }
        });

        holder.checkBox.setOnClickListener(view -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();
            monclicklistener.onselect(models.get(position).getShopName(),models.get(position).get_id());
        });

        if (selectedPosition==position){
            holder.checkBox.setChecked(true);
        }
        else {
            holder.checkBox.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title,cat;
        CheckBox checkBox;
        public holder(@NonNull View itemView) {
            super(itemView);

            checkBox=itemView.findViewById(R.id.check);
            imageView=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.name);
            cat=itemView.findViewById(R.id.cat);
        }

    }
}


