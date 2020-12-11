package com.example.roller;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User extends Fragment {




    // Instantiate Interface User_info
    UserInfo userInfo;

    public void getContext(Activity activity)
    {
        try {
            userInfo = (UserInfo)activity;
        }catch (ClassCastException e){
            Toast.makeText(activity, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static User newInstance(String param1, String param2) {
        User fragment = new User();
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

    Button Ordered;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        EditText shoe =  view.findViewById(R.id.Shoes);
        EditText watch = view.findViewById(R.id.Watch);
        EditText shirt = view.findViewById(R.id.Shirts);
        EditText belts = view.findViewById(R.id.Belts);
        EditText  trouser = view.findViewById(R.id.Trousers);
        EditText socks = view.findViewById(R.id.Socks);
        EditText laptops = view.findViewById(R.id.Laptops);


        Ordered = view.findViewById(R.id.Order);

        Ordered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Integer> hashMap = new HashMap<>();

                if(shoe.getText().toString().isEmpty())
                    shoe.setText("0");
                if(watch.getText().toString().isEmpty())
                    watch.setText("0");
                if(shirt.getText().toString().isEmpty())
                    shirt.setText("0");
                if(belts.getText().toString().isEmpty())
                    belts.setText("0");
                if(trouser.getText().toString().isEmpty())
                    trouser.setText("0");
                if(socks.getText().toString().isEmpty())
                    socks.setText("0");
                if(laptops.getText().toString().isEmpty())
                    laptops.setText("0");
                hashMap.put("Shoe",Integer.parseInt( shoe.getText().toString() ));
                hashMap.put("Watch", Integer.parseInt(watch.getText().toString()));
                hashMap.put("Shirt", Integer.parseInt(shirt.getText().toString()));
                hashMap.put("Belts", Integer.parseInt(belts.getText().toString()));
                hashMap.put("Trousers", Integer.parseInt(trouser.getText().toString()));
                hashMap.put("Socks", Integer.parseInt(socks.getText().toString()));
                hashMap.put("Laptops", Integer.parseInt(laptops.getText().toString()));
                userInfo.Info_User(hashMap);
            }
        });

        return view;
    }

    public interface UserInfo{

        public void Info_User(HashMap<String, Integer> hashMap);
    }
}