package com.example.googlesignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {

    Button btn,btn_create;
    EditText password, email;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();


        initializeUI();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUserAccount();

            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });

    }

    private void loginUserAccount() {
        progressBar.setVisibility(View.VISIBLE);

        String emailStore, passwordStore;
        emailStore = email.getText().toString();
        passwordStore = password.getText().toString();

        if (TextUtils.isEmpty(emailStore)) {
            Toast.makeText(LogIn.this, " Enter email address ...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(emailStore)) {
            Toast.makeText(LogIn.this, " Enter Password ....", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(emailStore,passwordStore)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LogIn.this, " Login Successfull",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(LogIn.this,HomeActivity.class);
                            startActivity(intent);
                        } else {
                           Toast.makeText(LogIn.this, " Login Failed",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
        }

    private void initializeUI() {
        btn = findViewById(R.id.btn_login);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        progressBar = findViewById(R.id.progressBar);
        btn_create = findViewById(R.id.btn_create);
    }
}