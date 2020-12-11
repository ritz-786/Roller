package com.example.roller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.roller.domain.House;
import com.example.roller.domain.LocatedAt;
import com.example.roller.domain.Product;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, User.UserInfo {

    private static final String TAG = "MainActivity";
    BottomNavigationView bnv;
    User userFragment;
    Retailer retailerFragment;
    Warehouse warehouseFragment;
    HashMap<String, Integer> User_Fragment_Data;

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User_Fragment_Data = new HashMap<>();
        userFragment = new User();
        retailerFragment = new Retailer();
        warehouseFragment = new Warehouse();


        bnv = findViewById(R.id.bottom_navigation_view);
        bnv.setOnNavigationItemSelectedListener(this);
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


    HashMap<Product, Integer> orderedProducts = new HashMap<>();

    @Override
    public void Info_User(HashMap<String, Integer> User_Info) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        User_Fragment_Data = User_Info;
        List<Product> products = Data.getProducts();

        if (User_Info.get("Shoe") > 0)
            orderedProducts.put(products.get(0), User_Fragment_Data.get("Shoe"));

        if (User_Info.get("Watch") > 0)
            orderedProducts.put(products.get(1), User_Fragment_Data.get("Watch"));

        if (User_Info.get("Shirt") > 0)
            orderedProducts.put(products.get(2), User_Fragment_Data.get("Shirt"));

        if (User_Info.get("Belts") > 0)
            orderedProducts.put(products.get(3), User_Fragment_Data.get("Belts"));

        if (User_Info.get("Trousers") > 0)
            orderedProducts.put(products.get(4), User_Fragment_Data.get("Trousers"));

        if (User_Info.get("Socks") > 0)
            orderedProducts.put(products.get(5), User_Fragment_Data.get("Socks"));

        if (User_Info.get("Laptops") > 0)
            orderedProducts.put(products.get(6), User_Fragment_Data.get("Laptops"));


        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        } else {
            getCurrentLocation(orderedProducts);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation(orderedProducts);
            } else {
                Toast.makeText(this, "Permission Denied ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation(HashMap<Product,Integer> orderedProducts) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

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

        LocationServices.getFusedLocationProviderClient(MainActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);

                        LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                .removeLocationUpdates(this);

                        if (locationRequest != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();

                            Log.d(TAG, orderedProducts + "");

                            House requiredWareHouse = Data.findWareHouse(orderedProducts, new LocatedAt(latitude, longitude));
                            House nearestWareHouse = Data.findNearestWareHouse(new LocatedAt(latitude, longitude));
                            Log.d(TAG, " " + requiredWareHouse.getCity() + " " + nearestWareHouse.getCity());

                            Log.d(TAG, requiredWareHouse + "");
                            Log.d(TAG, nearestWareHouse + "");

                            if(nearestWareHouse != null && requiredWareHouse != null){
                                Locator locator = new Locator();
                                String path = locator.initiator(nearestWareHouse,requiredWareHouse);
                                Log.d("Path:=", path);
                            }
//
//                            Toast.makeText(MainActivity.this, latitude + " " + longitude, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, Looper.getMainLooper());

    }
}