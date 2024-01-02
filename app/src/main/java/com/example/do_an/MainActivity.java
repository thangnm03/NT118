package com.example.do_an;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, signupButton;
    private TextView loginfailTextView;
    private ImageButton googlebutton;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.et_mail);
        passwordEditText = findViewById(R.id.et_pwd);
        loginButton = findViewById(R.id.btn_login);
        loginfailTextView =findViewById(R.id.login_fail);
        signupButton = findViewById(R.id.btn_register);
        googlebutton = findViewById(R.id.ibtn_google);

        firebaseAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!user.isEmpty() && !password.isEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(user, password)
                            .addOnCompleteListener( MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Toast.makeText(MainActivity.this, "Đăng nhập thanh công.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        loginfailTextView.setVisibility(View.VISIBLE);
                                        passwordEditText.setText("");

                                    }
                                }
                            });
                } else {
                    Toast.makeText(MainActivity.this, "Empty fields are not allowed.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



}