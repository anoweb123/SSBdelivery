package com.ali.ssbdeliveryboy.holderclasses;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.ali.ssbdeliveryboy.modelclasses.modelorders;

import java.util.List;

public class holdershoworders extends RecyclerView.Adapter<holdershoworders.holder> {

    onlocclick monloc;
    List<modelorders> list;
    Context context;

    onstatusclick monstatusclick;

    onstatusDELk monstatusDel;
    public holdershoworders(List<modelorders> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public interface onlocclick{
        public void onlocclicker(String latitue, String longitude, String name);
    }
    public void onlocclick(onlocclick listener){
        monloc=listener;
    }


    public interface onstatusclick{
        public String onstaclicker(String id,String status, String deliveredto);
    }
    public void onstaclick(onstatusclick listener){
        monstatusclick=listener;
    }


    public interface onstatusDELk{
        public void onstadel();
    }
    public void setMonstatusDel(onstatusDELk listener){
        monstatusDel=listener;
    }


    @NonNull
    @Override
    public holdershoworders.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderlayout,parent,false);
        return new holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull holdershoworders.holder holder, int position) {

            holder.orderid.setText("Order#: " + list.get(position).getOrderId().get_id());
            holder.amount.setText("Total Amount: " + list.get(position).getOrderId().getGrandTotal());
            holder.date.setText("Ordered time : " + list.get(position).getOrderId().getOrderTime().substring(0, 24));
            holder.address.setText("Address: " + list.get(position).getOrderId().getAddress());

            holder.name.setText("Customer name: " + list.get(position).getOrderId().getName());
            holder.status.setText("Payment status: " + list.get(position).getOrderId().getPaymentStatus());
            holder.method.setText("Payment method: " + list.get(position).getOrderId().getPaymentMethod());
            holder.orderstatus.setText("Order Status: " + list.get(position).getOrderId().getStatus());


            if (list.get(position).getOrderId().getStatus().equals("Picked up and on the way")){
                holder.picked.setBackgroundColor(Color.RED);
                holder.picked.setClickable(false);
                holder.picked.setText("Picked up");
            }



            holder.location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    monloc.onlocclicker(list.get(position).getOrderId().getLatitude(), list.get(position).getOrderId().getLongnitude(), list.get(position).getOrderId().getName());
                }
            });

            holder.phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "03147569736"));

                    try {
                        context.startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.picked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.picked.getText().equals("Picked up")){
                        Toast.makeText(context, "Already Picked", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        String s=monstatusclick.onstaclicker(list.get(position).getOrderId().get_id(), "Picked up and on the way","N/A");
                        if (s.equals("N/A")){
                            holder.picked.setText("Picked up");
                            holder.picked.setBackgroundColor(Color.RED);
                            holder.picked.setClickable(false);
                            monstatusDel.onstadel();
                        }
                    }
                }
            });
            holder.delievered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.picked.getText().equals("Picked up")){

                        String s=monstatusclick.onstaclicker(list.get(position).getOrderId().get_id(), "delivered", "haider");
//                        monstatusDel.onstadel();
                        if (s.equals("delivered")){
                            holder.delievered.setBackgroundColor(Color.GRAY);
                            Toast.makeText(context, "Order delivered", Toast.LENGTH_SHORT).show();
                            holder.delievered.setClickable(false);
                            holder.delievered.setActivated(false);
                            monstatusDel.onstadel();
                        }

                    }
                    else {
                        Toast.makeText(context, "You must pick order to deliver", Toast.LENGTH_SHORT).show();

                    }}
            });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monstatusclick.onstaclicker(list.get(position).getOrderId().get_id(), "delivered", "gotofragment");
            }
        });


    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView date,address,amount,status,name,orderid,method,orderstatus;
        Button picked,delievered;
        ImageView location,phone;
        public holder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.date);
            amount=itemView.findViewById(R.id.amount);
            address=itemView.findViewById(R.id.address);
            method=itemView.findViewById(R.id.method);
            location=itemView.findViewById(R.id.loc);
            phone=itemView.findViewById(R.id.phone);

            status=itemView.findViewById(R.id.paystatus);
            orderstatus=itemView.findViewById(R.id.status);
            name=itemView.findViewById(R.id.name);
            orderid=itemView.findViewById(R.id.orderid);

            picked=itemView.findViewById(R.id.picked);
            delievered=itemView.findViewById(R.id.delivered);

        }

    }
}
