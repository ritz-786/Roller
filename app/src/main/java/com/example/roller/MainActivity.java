package com.example.roller;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.roller.domain.House;
import com.example.roller.domain.LocatedAt;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    BottomNavigationView bnv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        House house = new House(
//                0,
//                new LocatedAt(25.566666666666666, 84.53333333333333),
//                "Arrah",
//                "Bihar"
//        );
//
//        Locator locator = new Locator();
//        locator.initiator(house);



        bnv = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bnv.setOnNavigationItemSelectedListener(this);
        bnv.setSelectedItemId(R.id.user);

    }


    User userFragment = new User();
    Retailer retailerFragment = new Retailer();
    Warehouse warehouseFragment = new Warehouse();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.user:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, userFragment).commit();
                return true;

            case R.id.warehouse:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, warehouseFragment).commit();
                return true;

            case R.id.retailer:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, retailerFragment).commit();
                return true;
        }

        return false;
    }
}