package com.ali.ssbdeliveryboy.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ali.ssbdeliveryboy.R;
import com.ali.ssbdeliveryboy.dashboard;
import com.ali.ssbdeliveryboy.interfaceapis.applyforleaverapi;
import com.ali.ssbdeliveryboy.interfaceapis.loginapi;
import com.ali.ssbdeliveryboy.logindboypage;
import com.ali.ssbdeliveryboy.modelclasses.modellogin;

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
 * Use the {@link Applyforleave#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Applyforleave extends Fragment {

    Button apply;
    EditText detail;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Applyforleave() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Applyforleave.
     */
    // TODO: Rename and change types and number of parameters
    public static Applyforleave newInstance(String param1, String param2) {
        Applyforleave fragment = new Applyforleave();
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
        View view= inflater.inflate(R.layout.fragment_applyforleave, container, false);


        detail=view.findViewById(R.id.detail);
        apply=view.findViewById(R.id.apply);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detail.getText().toString().isEmpty()){
                    detail.setError("Enter detail");
                }
                else {
                    SharedPreferences prefs =getContext().getSharedPreferences(mysharedpref, MODE_PRIVATE);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://"+prefs.getString("ipv4","10.0.2.2")+":5000")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    applyforleaverapi api=retrofit.create(applyforleaverapi.class);
                    Call<ResponseBody> listCall=api.list(detail.getText().toString(),prefs.getString("dboyid",""),prefs.getString("shopid",""));

                    listCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(getContext(), "Requested", Toast.LENGTH_SHORT).show();
                                detail.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

        return view;
    }
}