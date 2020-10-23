package org.brv.brv_logistics.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.brv.brv_logistics.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends Fragment implements PlaceSelectionListener {
    @BindView(R.id.from) SearchView from;
    @BindView(R.id.to) SearchView to;

    View v;

    public RegisterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       v=inflater.inflate(R.layout.register_cargo,container,false);
        ButterKnife.bind(this,v);
        from.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getActivity(), "from", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        to.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getActivity(), "To", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return v;
    }

    @Override
    public void onPlaceSelected(@NonNull Place place) {

    }

    @Override
    public void onError(@NonNull Status status) {

    }
}
