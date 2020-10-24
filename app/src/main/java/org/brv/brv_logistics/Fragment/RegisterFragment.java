package org.brv.brv_logistics.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import org.brv.brv_logistics.R;


public class RegisterFragment extends Fragment {

    View v;

    public RegisterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       try {
           v=inflater.inflate(R.layout.register_cargo,container,false);
       }catch (Exception ex){
           Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
       }
        return v;
    }


}
