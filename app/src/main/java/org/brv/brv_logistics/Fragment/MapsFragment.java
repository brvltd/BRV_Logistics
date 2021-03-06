package org.brv.brv_logistics.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.brv.brv_logistics.R;



import static org.brv.brv_logistics.Constants.Constants.DEFAULT_ZOOM;

public class MapsFragment extends Fragment {
    GoogleMap map;
    private View mapView;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap googleMap) {
            try {
                map=googleMap;
                LatLng home = new LatLng(-1.2840425907023074, 36.70896668144134);
                map.addMarker(new MarkerOptions().position(home).title("Home"));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(home,DEFAULT_ZOOM ));


                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);

                if (mapView!=null && mapView.findViewById(Integer.parseInt("1"))!=null){
                    View locationButton=((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                    RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams)locationButton.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
                    layoutParams.setMargins(0,0,40,180);
                }
            }catch (Exception e){
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        try {
            mapView=inflater.inflate(R.layout.fragment_maps,container,false);
//            PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
//                    getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//                @Override
//                public void onPlaceSelected(Place place) {
//                    map.clear();
//                    map.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
//                    map.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
//                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 12.0f));
//                }
//
//                @Override
//                public void onError(Status status) {
//
//                }
//            });
        }catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return mapView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }


}