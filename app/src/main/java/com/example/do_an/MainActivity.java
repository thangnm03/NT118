package com.example.do_an;

import androidx.annotation.NonNull;
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
=======
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
>>>>>>> b4d7492124c5426f4e2461dad1dab0a0bf077132
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
=======
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
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;
>>>>>>> b4d7492124c5426f4e2461dad1dab0a0bf077132

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, signupButton;
    private TextView loginfailTextView;
    private ImageButton googlebutton;
<<<<<<< HEAD
    private FirebaseAuth firebaseAuth;
=======
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;


    int RC_SIGN_IN = 20;
>>>>>>> b4d7492124c5426f4e2461dad1dab0a0bf077132
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
<<<<<<< HEAD

=======
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
>>>>>>> b4d7492124c5426f4e2461dad1dab0a0bf077132
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
<<<<<<< HEAD
                                        Toast.makeText(MainActivity.this, "Đăng nhập thanh công.", Toast.LENGTH_SHORT).show();
=======
                                        Intent intent = new Intent(MainActivity.this, Home.class);

                                        startActivity(intent);
>>>>>>> b4d7492124c5426f4e2461dad1dab0a0bf077132
                                    } else {
                                        loginfailTextView.setVisibility(View.VISIBLE);
                                        passwordEditText.setText("");

                                    }
                                }
                            });
<<<<<<< HEAD
                } else {
                    Toast.makeText(MainActivity.this, "Empty fields are not allowed.", Toast.LENGTH_SHORT).show();

                }
=======
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
>>>>>>> b4d7492124c5426f4e2461dad1dab0a0bf077132
            }
        });
    }

<<<<<<< HEAD

=======
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
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                        }

                        else {
                            Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            finish();
        }
    }
>>>>>>> b4d7492124c5426f4e2461dad1dab0a0bf077132

}