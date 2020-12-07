package com.example.packaginglibrary

import android.location.Location
import com.example.packaginglibrary.domain.LocatedAt

class Util {
    companion object {
        @JvmStatic
        fun findDistance(startPoint: LocatedAt, endPoint: LocatedAt): Float {
            val startP = Location("locationA")
            startP.latitude = startPoint.latitude
            startP.longitude = startPoint.longitude

            val endP = Location("locationB")
            endP.latitude = endPoint.latitude
            endP.longitude = endPoint.longitude

            return startP.distanceTo(endP)
        }
    }
}