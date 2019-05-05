package com.iavariav.wisbasmartwisatabatangsmart.metode;

public class Haversine {

    private static final int RADIUS_BUMI = 6371; //km

    public static double hitungJarak(double latUser, double longUser,
                                     double latTujuan, double longTujuan) {

        double dLat = Math.toRadians((latTujuan - latUser));
//        dLat = latTujuan-latUser;
        double dLong = Math.toRadians((longTujuan - longUser));
//        dLong = longTujuan - longUser;

        latUser = Math.toRadians(latUser);
        latTujuan = Math.toRadians(latTujuan);

        double a = haversin(dLat) + Math.cos(latUser) * Math.cos(latTujuan) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIUS_BUMI * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
