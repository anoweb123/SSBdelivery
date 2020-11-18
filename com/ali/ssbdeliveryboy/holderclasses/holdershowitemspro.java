package com.ali.ssbdeliveryboy.holderclasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.ssbdeliveryboy.R;
import com.ali.ssbdeliveryboy.modelclasses.modelorderitem;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.ali.ssbdeliveryboy.selectshop.mysharedpref;

public class holdershowitemspro extends RecyclerView.Adapter<holdershowitemspro.holder> {


    List<modelorderitem> list;
    Context context;

    public holdershowitemspro(List<modelorderitem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holdershowitemspro.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutorderitem,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holdershowitemspro.holder holder, int position) {

        SharedPreferences preferences=context.getSharedPreferences(mysharedpref,Context.MODE_PRIVATE);
        holder.title.setText(list.get(position).getProductName());
        holder.color.setText("Color: "+list.get(position).getColor());
        holder.price.setText("Rs. "+list.get(position).getPrice());
        holder.size.setText("Size: "+list.get(position).getSize());
        holder.quan.setText("Qty: "+list.get(position).getQuantity());
        Picasso.get().load(list.get(position).getImage().replaceFirst("localhost",preferences.getString("ipv4","10.0.2.2"))).networkPolicy(NetworkPolicy.NO_STORE).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView title,price,quan,color,size,id;
        ImageView imageView;
        public holder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
            quan=itemView.findViewById(R.id.quan);
            color=itemView.findViewById(R.id.color);
            size=itemView.findViewById(R.id.size);
            id=itemView.findViewById(R.id.ids);

        }
    }
}
