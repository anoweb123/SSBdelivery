package com.ali.ssbdeliveryboy.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ali.ssbdeliveryboy.R;
import com.ali.ssbdeliveryboy.holderclasses.holdershoworders;
import com.ali.ssbdeliveryboy.interfaceapis.showorderapi;
import com.ali.ssbdeliveryboy.interfaceapis.statuschangeapi;
import com.ali.ssbdeliveryboy.modelclasses.modelorders;
import com.ali.ssbdeliveryboy.modelclasses.orderId;

import java.util.ArrayList;
import java.util.List;
import 	android.util.Log;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.ali.ssbdeliveryboy.selectshop.mysharedpref;


public class orders extends Fragment implements holdershoworders.onlocclick,holdershoworders.onstatusclick,holdershoworders.onstatusDELk {

    String Boyid;
    RecyclerView rec;
    List<modelorders> list;
    List<modelorders> list1;

    holdershoworders adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_orders, container, false);
        list=new ArrayList<>();
        list1=new ArrayList<>();
        rec=view.findViewById(R.id.orderrec);


        showorders();

        return view;
    }

    @Override
    public void onlocclicker(String latitue, String longitude, String name) {
                MapsFragment details = new MapsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, details);

                Bundle bundle=new Bundle();
                bundle.putString("lati",latitue);
                bundle.putString("longi",longitude);
                bundle.putString("name",name);
                details.setArguments(bundle);

                fragmentTransaction.commit();

    }

    @Override
    public String onstaclicker(String id, String status, String deliveredto) {
        if (deliveredto.equals("gotofragment")||deliveredto.contains("gotofragment")){
            orderproducts productfragment = new orderproducts();
            FragmentManager fragmentManagerpro = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransactionpro = fragmentManagerpro.beginTransaction();
            fragmentTransactionpro.replace(R.id.fragment, productfragment);
            Bundle bundle=new Bundle();
            bundle.putString("id",id);
            productfragment.setArguments(bundle);
            fragmentTransactionpro.commit();

        }
        else {
            SharedPreferences prefs = getContext().getSharedPreferences(mysharedpref, MODE_PRIVATE);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + prefs.getString("ipv4", "10.0.2.2") + ":5000/delivery/updatestatus/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            statuschangeapi api = retrofit.create(statuschangeapi.class);
            Call<ResponseBody> listCall = api.status(id, status, deliveredto,prefs.getString("dboyid",""));

            listCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@Nullable Call<ResponseBody> call, @Nullable Response<ResponseBody> response) {

                    if (response.isSuccessful()){
                        Toast.makeText(getContext(), "Status Updated", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (deliveredto.equals("N/A"))
        return "N/A";
        else
          return  "delivered";
    }
    @Override
    public void onstadel() {
        showorders();
    }

    private void showorders() {
        SharedPreferences prefss = getContext().getSharedPreferences(mysharedpref,MODE_PRIVATE);

        Boyid=prefss.getString("dboyid","m");

        Retrofit retrofitcat = new Retrofit.Builder()
                .baseUrl("http://"+prefss.getString("ipv4","10.0.2.2")+":5000/dboy/assignedOrders/"+Boyid+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        showorderapi apicat = retrofitcat.create(showorderapi.class);
        Call<List<modelorders>> listCall = apicat.list();

        listCall.enqueue(new Callback<List<modelorders>>() {
            @Override
            public void onResponse(Call<List<modelorders>> call, Response<List<modelorders>> response) {
                if (response.isSuccessful()){
                    list1=new ArrayList<>();
                    list=new ArrayList<>();
                    list=response.body();
                    for (int i=0;i<list.size();i++){
                        if (!list.get(i).getOrderId().getStatus().equals("delivered")){
                            list1.add(list.get(i));
                        }
                    }
                    if (list1.size()==0){
                        BlankFragmentorders productfragment = new BlankFragmentorders();
                        FragmentManager fragmentManagerpro = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransactionpro = fragmentManagerpro.beginTransaction();
                        fragmentTransactionpro.replace(R.id.fragment, productfragment);
                        fragmentTransactionpro.commit();
                    }
                    else {
                    adapter=new holdershoworders(list1,getContext());
                    rec.setHasFixedSize(true);
                    rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
                    rec.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    adapter.setMonstatusDel(orders.this);
                    adapter.onlocclick(orders.this);
                    adapter.onstaclick(orders.this);
                    }
                }
                }


            @Override
            public void onFailure(Call<List<modelorders>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}