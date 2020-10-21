package org.brv.brv_logistics.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import org.brv.brv_logistics.R;

public class NewUserDialog extends AppCompatDialogFragment {
    private TextInputEditText userName;
    private TextInputEditText phone;
    private TextInputEditText password;

    private BRVDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.new_user,null);
        builder.setView(view)
                .setTitle("Create Account")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String uName=userName.getText().toString();
                        String uMail=phone.getText().toString();
                        String uPass=password.getText().toString();

                        listener.applyText(uName,uMail,uPass);
                    }
                });
        userName=view.findViewById(R.id.UserName1);
        phone=view.findViewById(R.id.PhoneNumber1);
        password=view.findViewById(R.id.Password1);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener=(BRVDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement Cynix Dialog Listener");
        }

    }

    public interface BRVDialogListener{
        void applyText(String TxtUserName, String TxtPhone, String TxtPassword);
    }

}