package com.example.roller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roller.domain.House
import com.example.roller.domain.LocatedAt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val house = House(
            0,
            LocatedAt(25.566666666666666, 84.53333333333333),
            "Arrah",
            "Bihar"
        )

        val locator = Locator()
        locator.initiator(house)
    }
}