package com.example.do_an;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText nameEdittext, emailEditText, passwordEditText, confirmEdittext;
    private Button signupButton;
    private TextView failName,failEmail,failPassword, failConfirm;
    private ImageButton backImageButton;
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;

    boolean[] check = new boolean[4];
    boolean allTrue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        nameEdittext = findViewById(R.id.et_name);
        emailEditText= findViewById(R.id.et_mail);
        passwordEditText= findViewById(R.id.et_pwd);
        confirmEdittext = findViewById(R.id.et_cf_pwd);
        signupButton = findViewById(R.id.btn_signup);
        failName = findViewById(R.id.warn_name);
        failEmail = findViewById(R.id.warn_mail);
        failPassword = findViewById(R.id.warn_pwd);
        failConfirm = findViewById(R.id.warn_cf_pwd);
        backImageButton = findViewById(R.id.ibtn_back);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();



        nameEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String name = nameEdittext.getText().toString().trim();
                if(!b){
                    if(name.equals("")){
                        failName.setVisibility(View.VISIBLE);
                        nameEdittext.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.warning_background));
                        check[0]=false;
                    }
                    else check[0]=true;
                    enable_signup(check);
                }
                else {
                    failName.setVisibility(View.GONE);
                    nameEdittext.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.border_card));
                }

            }
        });

        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String email = emailEditText.getText().toString().trim();
                if(!b){
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        failEmail.setVisibility(View.VISIBLE);
                        emailEditText.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.warning_background));
                        check[1]=false;
                    }
                    else check[1]=true;
                    enable_signup(check);
                }
                else {
                    failEmail.setVisibility(View.GONE);
                    emailEditText.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.border_card));
                }

            }
        });


        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String password = passwordEditText.getText().toString();
                if(!b){
                    if(password.length()<8){
                        failPassword.setVisibility(View.VISIBLE);
                        passwordEditText.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.warning_background));
                        check[2]=false;
                    }
                    else check[2]=true;
                    enable_signup(check);
                }
                else {
                    failPassword.setVisibility(View.GONE);
                    passwordEditText.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.border_card));
                }

            }
        });

        confirmEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String password = passwordEditText.getText().toString();
                String confirm = confirmEdittext.getText().toString();
                if(!b){
                    if(!confirm.equals(password)){
                        failConfirm.setVisibility(View.VISIBLE);
                        confirmEdittext.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.warning_background));
                        check[3]=false;
                    }
                    else check[3]=true;
                    enable_signup(check);
                }
                else {
                    failConfirm.setVisibility(View.GONE);
                    confirmEdittext.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.border_card));
                }

            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdittext.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();
                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(Register.this, "Empty fields are not allowed.", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener( Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        String userId = user.getUid();
                                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

                                        Map<String, Object> userData = new HashMap<>();
                                        userData.put("name", name);
                                        userData.put("avatarUrl", "https://firebasestorage.googleapis.com/v0/b/nt118-1f48c.appspot.com/o/avatar%2FNone_Avatar.jpg?alt=media&token=15f8f087-fe84-4431-89d7-459db8c2bfb3");

                                        userRef.setValue(userData);
                                        Intent intent = new Intent(Register.this, Home.class);
                                        startActivity(intent);

                                    }
                                    else {
                                        Toast.makeText(Register.this, "Đăng ký thất bại. Tài khoản đã tồn tại hoặc thông tin không hợp lệ.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void enable_signup (boolean[] check){
        allTrue = true;
        for (boolean value : check) {
            if (!value) {
                allTrue = false;
                break;
            }
        }
        if(allTrue){
            signupButton.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.background_button));
            signupButton.setEnabled(true);
        }
        else {
            signupButton.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.border_card));
            signupButton.setEnabled(false);
        }
    }

}