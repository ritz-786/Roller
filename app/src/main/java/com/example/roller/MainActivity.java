package com.example.roller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.roller.domain.House;
import com.example.roller.domain.LocatedAt;
import com.example.roller.domain.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity

        implements BottomNavigationView.OnNavigationItemSelectedListener, User.UserInfo, LocationListener {

    private static final String TAG = "MainActivity";
    BottomNavigationView bnv;
    User userFragment;
    Retailer retailerFragment;
    Warehouse warehouseFragment;
    HashMap<String, Integer> User_Fragement_Data;
    Double lattitude, longitude;

    //Instatiate Location manager
    LocationManager locationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        User_Fragement_Data = new HashMap<>();
        userFragment = new User();
        retailerFragment = new Retailer();
        warehouseFragment = new Warehouse();
        List<Product> products = Data.getProducts();
        HashMap<Product, Integer> orderedProducts = new HashMap<>();
        orderedProducts.put(products.get(0), User_Fragement_Data.get("Shoe"));
        orderedProducts.put(products.get(1), User_Fragement_Data.get("Watch"));
        orderedProducts.put(products.get(2), User_Fragement_Data.get("Shirt"));

        House requiredWareHouse = Data.findWareHouse(orderedProducts, new LocatedAt(lattitude, longitude));
        House nearestWareHouse = Data.findNearestWareHouse(new LocatedAt(lattitude, longitude));
        Log.d(TAG, " " + requiredWareHouse.getCity() + " " + nearestWareHouse.getCity());

        if(nearestWareHouse != null && requiredWareHouse != null){
            Locator locator = new Locator();
            locator.initiator(nearestWareHouse,requiredWareHouse);
        }

        bnv = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bnv.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        bnv.setSelectedItemId(R.id.user);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.user:
                userFragment.getContext(MainActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void Info_User(HashMap<String, Integer> User_Info) {
        User_Fragement_Data = User_Info;
        Toast.makeText(this, "=====  " + User_Fragement_Data.get("Shoe"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lattitude = location.getLatitude();
        longitude = location.getLongitude();
        Toast.makeText(this, "=====================    "+lattitude, Toast.LENGTH_SHORT).show();
    }
}