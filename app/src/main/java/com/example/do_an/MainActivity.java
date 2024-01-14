package com.example.do_an;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.do_an.Navigation.Acti_Navi;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, signupButton;
    private TextView loginfailTextView, forgotfailTextView;
    private ImageButton googlebutton;
    private LinearLayout layoutForgotPassword;
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;


    int RC_SIGN_IN = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.et_mail);
        passwordEditText = findViewById(R.id.et_pwd);
        loginButton = findViewById(R.id.btn_login);
        loginfailTextView = findViewById(R.id.login_fail);
        forgotfailTextView = findViewById(R.id.forgot_password_fail);
        signupButton = findViewById(R.id.btn_register);
        googlebutton = findViewById(R.id.ibtn_google);
        layoutForgotPassword = findViewById(R.id.forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        BeginSignInRequest signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
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
                                        Intent intent = new Intent(MainActivity.this, Acti_Navi.class);

                                        startActivity(intent);
                                    } else {
                                        loginfailTextView.setVisibility(View.VISIBLE);
                                        passwordEditText.setText("");

                                    }
                                }
                            });
                }
                else {
                    loginfailTextView.setVisibility(View.VISIBLE);
                    passwordEditText.setText("");
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        googlebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });

        layoutForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickForgotPassword();
            }
        });
    }

    private void googleSignIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseauth(account.getIdToken());

            }
            catch (Exception e){
                Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT);
            }
        }

    }

    private void firebaseauth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("id",user.getUid());
                            map.put("name",user.getDisplayName());
                            map.put("profile",user.getPhotoUrl()).toString();

                            database.getReference().child("users").child(user.getUid()).setValue(map);
                            Intent intent = new Intent(MainActivity.this, Acti_Navi.class);
                            startActivity(intent);
                        }

                        else {
                            Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(MainActivity.this, Acti_Navi.class);
            startActivity(intent);
            finish();
        }
    }

    private void onClickForgotPassword(){
        String user =emailEditText.getText().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = user;

        if(!user.isEmpty()){
            auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Email sent", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else{
            forgotfailTextView.setVisibility(View.VISIBLE);
            passwordEditText.setText("");
        }
    }


}