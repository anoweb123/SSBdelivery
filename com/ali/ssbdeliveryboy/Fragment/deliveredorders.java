package com.ali.ssbdeliveryboy.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ali.ssbdeliveryboy.R;
import com.ali.ssbdeliveryboy.holderclasses.holdercompletedorder;
import com.ali.ssbdeliveryboy.holderclasses.holdershoworders;
import com.ali.ssbdeliveryboy.interfaceapis.completeorderapi;
import com.ali.ssbdeliveryboy.interfaceapis.showorderapi;
import com.ali.ssbdeliveryboy.modelclasses.modelcompleteorder;
import com.ali.ssbdeliveryboy.modelclasses.modelorders;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.ali.ssbdeliveryboy.selectshop.mysharedpref;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link deliveredorders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class deliveredorders extends Fragment {

    String Boyid;
    RecyclerView rec;
    holdercompletedorder adapter;
    List<modelcompleteorder> list;
    List<modelcompleteorder> list1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public deliveredorders() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment deliveredorders.
     */
    // TODO: Rename and change types and number of parameters
    public static deliveredorders newInstance(String param1, String param2) {
        deliveredorders fragment = new deliveredorders();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_deliveredorders, container, false);

        rec=view.findViewById(R.id.orderrec);

        list=new ArrayList<>();
        list1=new ArrayList<>();


        SharedPreferences prefss = getContext().getSharedPreferences(mysharedpref,MODE_PRIVATE);

        Boyid=prefss.getString("dboyid","m");

        Retrofit retrofitcat = new Retrofit.Builder()
                .baseUrl("http://"+prefss.getString("ipv4","10.0.2.2")+":5000/dboy/assignedOrders/"+Boyid+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        completeorderapi apicat = retrofitcat.create(completeorderapi.class);
        Call<List<modelcompleteorder>> listCall = apicat.list();

        listCall.enqueue(new Callback<List<modelcompleteorder>>() {
            @Override
            public void onResponse(Call<List<modelcompleteorder>> call, Response<List<modelcompleteorder>> response) {
                if (response.isSuccessful()){

                    list=response.body();
                    for (int i=0;i<list.size();i++){
                        if (!list.get(i).getOrderId().getStatus().equals("delivered")){}
                        else {
                            list1.add(list.get(i));
                        }
                    }

                    adapter=new holdercompletedorder(list1,getContext());

                    rec.setHasFixedSize(true);
                    rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
                    rec.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
//                    adapter.onlocclick(orders.this);
//                    adapter.onstaclick(orders.this);
                }
            }

            @Override
            public void onFailure(Call<List<modelcompleteorder>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}