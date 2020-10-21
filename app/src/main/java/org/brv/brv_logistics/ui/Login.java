package org.brv.brv_logistics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.brv.brv_logistics.Constants.Constants;
import org.brv.brv_logistics.R;
import org.brv.brv_logistics.dialog.NewUserDialog;
import org.brv.brv_logistics.models.Users;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity implements NewUserDialog.BRVDialogListener {
    @BindView(R.id.login) Button mLogin;
    @BindView(R.id.newUser) Button mNewUser;
    @BindView(R.id.PhoneNumber) TextInputEditText mPhone;
    @BindView(R.id.Password) TextInputEditText mPassword;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private ProgressDialog mAuthProgressDialog;
    private String phone;
    private String password;
    DatabaseReference RootRef;
    public ArrayList<Users> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        newUser();
        login();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        createAuthProgressDialog();
    }

    private void newUser() {
        mNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewUserDialog newUserDialog = new NewUserDialog();
                newUserDialog.show(getSupportFragmentManager(), "new user dialog");
            }
        });
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("BRV Logistics");
        mAuthProgressDialog.setMessage("Authenticating Credentials...");
        mAuthProgressDialog.setCancelable(false);
    }

    private void login() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mPhone.getText().toString().trim();
                password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(phone)) {
                    mAuthProgressDialog.dismiss();
                    mPhone.setError("Please enter your Phone");
                }else if (TextUtils.isEmpty(password)) {
                    mAuthProgressDialog.dismiss();
                    mPassword.setError("Please enter yor password");
                } else {
//                    if(Integer.parseInt(phone)<=9){
//                        mAuthProgressDialog.dismiss();
//                        mPhone.setError("Phone number provided is less");
//                    }
                    mAuthProgressDialog.show();
                    AllowAccess(phone, password);
                }
            }
        });
    }

    private void AllowAccess(final String phone, final String password) {
        mUsers = new ArrayList<Users>();
        RootRef = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_USERS);
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Users usersData = dataSnapshot1.getValue(Users.class);
                    if (usersData.getPhone().equals(phone)) {
                        if (usersData.getPassword().equals(password) && !usersData.getPassword().equals("Blocked")) {
                            if (Constants.FIREBASE_CHILD_USERS.equals("Users")) {
                                mAuthProgressDialog.dismiss();
                                new SweetAlertDialog(Login.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Login Successful")
                                        .show();

                                String name = usersData.getUserName();
                                Intent intent = new Intent(Login.this, DashBoard.class);
                                intent.putExtra("userNames", name);
                                startActivity(intent);
                                finish();
                            }
                        } else if (usersData.getPassword().equals("Blocked")) {
                            mAuthProgressDialog.dismiss();
                            new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Your account is Blocked")
                                    .show();
                        } else {
                            mAuthProgressDialog.dismiss();
                            new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Wrong Password")
                                    .show();
//                            counterCheck();
                        }
                    } else {
                        mAuthProgressDialog.dismiss();
                        new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Wrong Email")
                                .show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mAuthProgressDialog.dismiss();
                new SweetAlertDialog(Login.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("The credentials provided doesn't exist in the database")
                        .show();
            }
        });
    }

    @Override
    public void applyText(String TxtUserName, String TxtPhone, String TxtPassword) {
        if (TextUtils.isEmpty(TxtUserName)) {
            new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Username Field is Empty")
                    .show();
        } else if (TextUtils.isEmpty(TxtPhone)) {
            new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Email Field is Empty")
                    .show();
        } else if (TextUtils.isEmpty(TxtPassword)) {
            new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Password Field is Empty")
                    .show();
        } else {
            String mUserName = TxtUserName;
            String phone = TxtPhone;
            String pass = TxtPassword;

            new SweetAlertDialog(Login.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Success")
                    .show();

            Users users;
            users=new Users(phone,pass,mUserName);


            rootNode = FirebaseDatabase.getInstance();

            reference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_USERS);

            DatabaseReference pushRef = reference.push();

            pushRef.setValue(users);

        }
    }
}