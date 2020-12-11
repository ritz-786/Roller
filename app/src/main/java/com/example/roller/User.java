package com.example.roller;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User extends Fragment {


    // Instantiate Interface User_info
    UserInfo userInfo;

    public void getContext(Activity activity) {
        try {
            userInfo = (UserInfo) activity;
        } catch (ClassCastException e) {
            Toast.makeText(activity, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
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

        EditText shoe = view.findViewById(R.id.Shoes);
        EditText watch = view.findViewById(R.id.Watch);
        EditText shirt = view.findViewById(R.id.Shirts);
        EditText belts = view.findViewById(R.id.Belts);
        EditText trouser = view.findViewById(R.id.Trousers);
        EditText socks = view.findViewById(R.id.Socks);
        EditText laptops = view.findViewById(R.id.Laptops);


        Ordered = view.findViewById(R.id.Order);

        Ordered.setOnClickListener(view1 -> {

            HashMap<String, Integer> hashMap = new HashMap<>();


            hashMap.put("Shoe", Integer.parseInt(shoe.getText().toString().equals("") ? "0" : shoe.getText().toString()));
            hashMap.put("Watch", Integer.parseInt(watch.getText().toString().equals("") ? "0" : watch.getText().toString()));
            hashMap.put("Shirt", Integer.parseInt(shirt.getText().toString().equals("") ? "0" : shirt.getText().toString()));
            hashMap.put("Belts", Integer.parseInt(belts.getText().toString().equals("") ? "0" : belts.getText().toString()));
            hashMap.put("Trousers", Integer.parseInt(trouser.getText().toString().equals("") ? "0" : trouser.getText().toString()));
            hashMap.put("Socks", Integer.parseInt(socks.getText().toString().equals("") ? "0" : socks.getText().toString()));
            hashMap.put("Laptops", Integer.parseInt(laptops.getText().toString().equals("") ? "0" : laptops.getText().toString()));
            userInfo.Info_User(hashMap);
        });

        return view;
    }

    public interface UserInfo {

        public void Info_User(HashMap<String, Integer> hashMap);
    }
}