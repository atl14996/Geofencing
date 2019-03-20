package com.example.week6day2homework;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;

import java.util.List;

public class GeofencingManager {
    public static final int GEOFENCE_RADIUS_IN_METERS = 500;
    public static final String MONTWOOD_REQUEST_ID = "MONTWOOD";
    public static final String MAC_REQUEST = "MAC";
    MapsActivity mapsActivity;
    List<Geofence> geofenceList;

    public void BuildGeofence() {
        geofenceList.add(new Geofence.Builder()
                // Set the request ID of the geofence. This is a string to identify this
                // geofence.
                .setRequestId(MONTWOOD_REQUEST_ID)

                .setCircularRegion(
                        mapsActivity.Montwood.latitude,
                        mapsActivity.Montwood.longitude,
                       GEOFENCE_RADIUS_IN_METERS
                )

                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());

        geofenceList.add(new Geofence.Builder()
                // Set the request ID of the geofence. This is a string to identify this
                // geofence.
                .setRequestId(MAC_REQUEST)

                .setCircularRegion(
                        mapsActivity.MACOffice.latitude,
                        mapsActivity.MACOffice.longitude,
                        GEOFENCE_RADIUS_IN_METERS
                )

                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());




    }

    public GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofenceList);
        return builder.build();
    }




}
