package com.example.roller;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.roller.domain.House;
import com.example.roller.domain.LocatedAt;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        House house = new House(
                0,
                new LocatedAt(25.566666666666666, 84.53333333333333),
                "Arrah",
                "Bihar"
        );

        Locator locator = new Locator();
        locator.initiator(house);
    }
}