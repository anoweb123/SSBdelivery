package com.ali.ssbdeliveryboy.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ali.ssbdeliveryboy.R;
import com.ali.ssbdeliveryboy.holderclasses.holdershowitemspro;
import com.ali.ssbdeliveryboy.interfaceapis.showorderitemapi;
import com.ali.ssbdeliveryboy.interfaceapis.statuschangeapi;
import com.ali.ssbdeliveryboy.modelclasses.modelorderitem;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.ali.ssbdeliveryboy.selectshop.mysharedpref;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link orderproducts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class orderproducts extends Fragment {


    RecyclerView recyclerView;
    String orderid;
    holdershowitemspro adapter;
    List<modelorderitem> list;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public orderproducts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment orderproducts.
     */
    // TODO: Rename and change types and number of parameters
    public static orderproducts newInstance(String param1, String param2) {
        orderproducts fragment = new orderproducts();
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
        View view= inflater.inflate(R.layout.fragment_orderproducts, container, false);

        recyclerView=view.findViewById(R.id.rec);

        orderid=getArguments().getString("id");

        SharedPreferences prefs = getContext().getSharedPreferences(mysharedpref, MODE_PRIVATE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + prefs.getString("ipv4", "10.0.2.2") + ":5000/orders/searchOrderItems/"+orderid+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        showorderitemapi api = retrofit.create(showorderitemapi.class);
        Call<List<modelorderitem>> listCall = api.list();

        listCall.enqueue(new Callback<List<modelorderitem>>() {
            @Override
            public void onResponse(Call<List<modelorderitem>> call, Response<List<modelorderitem>> response) {
                if (response.isSuccessful()){
                    list=new ArrayList<>();

                    list=response.body();
                    recyclerView.hasFixedSize();
                    adapter=new holdershowitemspro(list,getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<modelorderitem>> call, Throwable t) {

            }
        });

        return view;
    }
}