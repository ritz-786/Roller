package com.example.roller

import android.location.Location
import com.example.roller.domain.LocatedAt
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class Util {
    companion object {
        @JvmStatic
        fun findDistance(startPoint: LocatedAt, endPoint: LocatedAt): Float {
            val startP = Location("locationA")
            startP.latitude = startPoint.latitude * (Math.PI / 180) // convert degrees to radians
            startP.longitude = startPoint.longitude

            val endP = Location("locationB")
            endP.latitude = endPoint.latitude * (Math.PI / 180) // convert degrees to radians
            endP.longitude = endPoint.longitude


            val R = 6371.0710;
            val difflon = (endP.longitude - startP.longitude) * (Math.PI / 180)

            val d = R * acos(
                sin(startP.latitude) * sin(endP.latitude) + cos(startP.latitude) * cos(endP.latitude) * cos(
                    difflon.toFloat()
                )
            )
            return d.toFloat()
        }
    }
}