package com.ali.ssbdeliveryboy.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.ssbdeliveryboy.R;
import com.ali.ssbdeliveryboy.interfaceapis.updateimageurl;
import com.ali.ssbdeliveryboy.logindboypage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.ali.ssbdeliveryboy.selectshop.mysharedpref;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile extends Fragment {

    TextView name,email,phone,address;

    ImageView namev,emailv,addressv,phonev,passv;
    TextView logout,applyforleave;

    String sname,sid;
    FloatingActionButton choseimg;
    Uri imageUri;
    ProgressBar bar;
    CircleImageView profileimage;
    InputStream imageStream;
    int CAMERA_PERMISSION_CODE=100;
    ImageView camera,gallary;
    AlertDialog alertDialog;
    private static final int REQUEST_IMAGE_CAPTURE = 1,imageGalary=2;
    AlertDialog.Builder builder;
    // TODO: Rename parameter arguments, choose name s that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profile.
     */
    // TODO: Rename and change types and number of parameters
    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
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
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        logout=view.findViewById(R.id.logout);
        addressv=view.findViewById(R.id.addressupdate);
        namev=view.findViewById(R.id.nameupdate);
        phonev=view.findViewById(R.id.phoneupdate);
        passv=view.findViewById(R.id.updatepassword);
        emailv=view.findViewById(R.id.emailupdate);
        applyforleave=view.findViewById(R.id.apply);

        applyforleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Applyforleave productfragment = new Applyforleave();
                FragmentManager fragmentManagerpro =getActivity().getSupportFragmentManager() ;
                FragmentTransaction fragmentTransactionpro = fragmentManagerpro.beginTransaction();
                fragmentTransactionpro.replace(R.id.fragment, productfragment);
                fragmentTransactionpro.commit();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),logindboypage.class));
                SharedPreferences.Editor sharedPreferences=getContext().getSharedPreferences(mysharedpref,MODE_PRIVATE).edit();
                sharedPreferences.putString("status","logout");

            }
        });

        addressv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateaddress productfragment = new updateaddress();
                FragmentManager fragmentManagerpro =getActivity().getSupportFragmentManager() ;
                FragmentTransaction fragmentTransactionpro = fragmentManagerpro.beginTransaction();
                fragmentTransactionpro.replace(R.id.fragment, productfragment);
                fragmentTransactionpro.commit();
            }
        });

        phonev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatephone productfragment = new updatephone();
                FragmentManager fragmentManagerpro =getActivity().getSupportFragmentManager() ;
                FragmentTransaction fragmentTransactionpro = fragmentManagerpro.beginTransaction();
                fragmentTransactionpro.replace(R.id.fragment, productfragment);
                fragmentTransactionpro.commit();
            }
        });
        namev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatename productfragment = new updatename();
                FragmentManager fragmentManagerpro =getActivity().getSupportFragmentManager() ;
                FragmentTransaction fragmentTransactionpro = fragmentManagerpro.beginTransaction();
                fragmentTransactionpro.replace(R.id.fragment, productfragment);
                fragmentTransactionpro.commit();
            }
        });

        passv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatepassword productfragment = new updatepassword();
                FragmentManager fragmentManagerpro =getActivity().getSupportFragmentManager() ;
                FragmentTransaction fragmentTransactionpro = fragmentManagerpro.beginTransaction();
                fragmentTransactionpro.replace(R.id.fragment, productfragment);
                fragmentTransactionpro.commit();
            }
        });

        emailv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateemail productfragment = new updateemail();
                FragmentManager fragmentManagerpro =getActivity().getSupportFragmentManager() ;
                FragmentTransaction fragmentTransactionpro = fragmentManagerpro.beginTransaction();
                fragmentTransactionpro.replace(R.id.fragment, productfragment);
                fragmentTransactionpro.commit();
            }
        });

        name=view.findViewById(R.id.name);
        phone=view.findViewById(R.id.phone);
        address=view.findViewById(R.id.address);
        email=view.findViewById(R.id.email);

        bar=view.findViewById(R.id.bar);
        bar.setVisibility(View.INVISIBLE);
        profileimage=view.findViewById(R.id.imageview_account_profile);
        choseimg=view.findViewById(R.id.floatingActionButton);

        SharedPreferences sharedPreferences=getContext().getSharedPreferences(mysharedpref,MODE_PRIVATE);

        String myimage=sharedPreferences.getString("image","");
        if (myimage.equals("")){}
        else {
        Picasso.get().load(myimage).into(profileimage);
        }

        name.setText(sharedPreferences.getString("name",""));
        email.setText(sharedPreferences.getString("email",""));
        phone.setText(sharedPreferences.getString("phone",""));
        address.setText(sharedPreferences.getString("address",""));

        choseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(getContext());
                View viewimg = LayoutInflater.from(getContext()).inflate(R.layout.dialogforimage, null);
                builder.setView(viewimg).setTitle("Select You Image");
                alertDialog = builder.create();
                alertDialog.show();
                camera = viewimg.findViewById(R.id.camraImage);
                gallary = viewimg.findViewById(R.id.galaryImage);
                gallary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        String[] mimeTypes = {"image/jpeg", "image/png"};
                        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                        startActivityForResult(intent, imageGalary);
                    }
                });
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        if (ContextCompat.checkSelfPermission(getContext(),
                                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                                try {
                                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                                } catch (Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            requestStoragePermission();
                        }
                    }
                });


            }
        });






        return view;
    }



    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of Camera")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == imageGalary && resultCode == RESULT_OK)
        {
            bar.setVisibility(View.VISIBLE);
            imageUri=data.getData();


            SharedPreferences prefs = getContext().getSharedPreferences(mysharedpref, MODE_PRIVATE);
            sname = prefs.getString("name", "Null");//"No name defined" is the default value.
            sid = prefs.getString("dboyid", "Null");

            FirebaseStorage.getInstance().getReference().child(sid).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    FirebaseStorage.getInstance().getReference().child(sid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            SharedPreferences.Editor editor=getContext().getSharedPreferences(mysharedpref,MODE_PRIVATE).edit();
                            editor.putString("image",uri.toString());
                            editor.apply();

                            SharedPreferences prefs = getContext().getSharedPreferences(mysharedpref, MODE_PRIVATE);
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://" + prefs.getString("ipv4", "10.0.2.2") + ":5000/dboy/updateimage/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            updateimageurl api = retrofit.create(updateimageurl.class);
                            Call<ResponseBody> listCalls=api.call(sid,prefs.getString("image",""));

                            listCalls.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()){
                                        Picasso.get().load(uri).into(profileimage);
                                        Toast.makeText(getContext(),"Image updated", Toast.LENGTH_SHORT).show();
                                        bar.setVisibility(View.INVISIBLE);}
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });

                        }
                    });
                }
            });
            profileimage.setImageURI(imageUri);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            bar.setVisibility(View.VISIBLE);
//            imageUri=data.getData();



            Bitmap b= (Bitmap) data.getExtras().get("data");
            profileimage.setImageBitmap(b);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), b, "Title", null);
            imageUri= Uri.parse(path);



            SharedPreferences prefs = getContext().getSharedPreferences(mysharedpref, MODE_PRIVATE);
            sname = prefs.getString("name", "Null");//"No name defined" is the default value.
            sid = prefs.getString("dboyid", "Null");

            FirebaseStorage.getInstance().getReference().child(sid).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    FirebaseStorage.getInstance().getReference().child(sid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            SharedPreferences.Editor editor=getContext().getSharedPreferences(mysharedpref,MODE_PRIVATE).edit();
                            editor.putString("image",uri.toString());
                            editor.apply();

                            SharedPreferences prefs = getContext().getSharedPreferences(mysharedpref, MODE_PRIVATE);
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://" + prefs.getString("ipv4", "10.0.2.2") + ":5000/dboy/updateimage/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            updateimageurl api = retrofit.create(updateimageurl.class);
                            Call<ResponseBody> listCalls=api.call(sid,prefs.getString("image",""));

                            listCalls.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()){
                                        Picasso.get().load(uri).into(profileimage);
                                        Toast.makeText(getContext(),"Image updated", Toast.LENGTH_SHORT).show();
                                        bar.setVisibility(View.INVISIBLE);}
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });

                        }
                    });
                }
            });


        }
    }
}