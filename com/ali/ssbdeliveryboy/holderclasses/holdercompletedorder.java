package com.ali.ssbdeliveryboy.holderclasses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.ssbdeliveryboy.R;
import com.ali.ssbdeliveryboy.modelclasses.modelcompleteorder;
import com.ali.ssbdeliveryboy.modelclasses.modelorders;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.ali.ssbdeliveryboy.selectshop.mysharedpref;

public class holdercompletedorder extends RecyclerView.Adapter<holdercompletedorder.holder> {
    holdershoworders.onlocclick monloc;
    List<modelcompleteorder> list;
    Context context;



    holdershoworders.onstatusclick monstatusclick;

    public holdercompletedorder(List<modelcompleteorder> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public interface onlocclick{
        public void onlocclicker(String latitue,String longitude,String name);
    }
    public void onlocclick(holdershoworders.onlocclick listener){
        monloc=listener;
    }

    public interface onstatusclick{
        public void onstaclicker(String id,String status, String deliveredto);
    }
    public void onstaclick(holdershoworders.onstatusclick listener){
        monstatusclick=listener;
    }


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layoutcompeted,parent,false);
        return new holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull holdercompletedorder.holder holder, int position) {

        SharedPreferences sharedPreferences=context.getSharedPreferences(mysharedpref,MODE_PRIVATE);


        holder.name.setText("Customer: "+list.get(position).getOrderId().getName());
        holder.amount.setText("Amount: RS. "+list.get(position).getOrderId().getGrandTotal());
        holder.orderid.setText(list.get(position).getOrderId().get_id());
        holder.address.setText("Delivered at: "+list.get(position).getOrderId().getAddress());
        holder.delboyname.setText("Delivery boy : "+sharedPreferences.getString("name","no name"));
        holder.orderdate.setText("Order time : "+list.get(position).getOrderId().getOrderTime().substring(0,25));
        holder.deldate.setText("Delivery time :"+list.get(position).getOrderId().getDeliveryTime().substring(0,25));

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView orderdate,address,amount,delboyname,name,orderid,deldate;
        public holder(@NonNull View itemView) {
            super(itemView);

            orderdate=itemView.findViewById(R.id.orderdate);
            deldate=itemView.findViewById(R.id.deldate);
            amount=itemView.findViewById(R.id.amount);
            address=itemView.findViewById(R.id.address);
            delboyname=itemView.findViewById(R.id.delboyname);

            name=itemView.findViewById(R.id.name);
            orderid=itemView.findViewById(R.id.orderid);

        }

    }

}
