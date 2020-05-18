package com.example.nikola_lenivetsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail,userPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private ProgressBar progressLogin;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;
    private ImageView loginPhoto;

    //private TextInputLayout textInputLayout;
    //private TextInputEditText textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.login_email);

        //textInputLayout = findViewById(R.id.login_email);
        //textInputEditText

        userPassword = findViewById(R.id.login_password);
        buttonLogin = findViewById(R.id.login_button);
        buttonRegister = findViewById(R.id.register_button);
        progressLogin = findViewById(R.id.login_progress);

        mAuth = FirebaseAuth.getInstance();
        HomeActivity = new Intent(this,Home.class);

        loginPhoto = findViewById(R.id.login_photo);

        /*loginPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerActivity = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerActivity);
                finish();


            }
        });*/

        progressLogin.setVisibility(View.INVISIBLE);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressLogin.setVisibility(View.VISIBLE);
                buttonLogin.setVisibility(View.INVISIBLE);

                final String mail = userEmail.getText().toString();
                final String password = userPassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    showMessage("Заполните все поля!");
                    buttonLogin.setVisibility(View.VISIBLE);
                    progressLogin.setVisibility(View.INVISIBLE);
                }
                else
                {
                    signIn(mail,password);
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerActivity);
                finish();
            }
        });

    }

    private void signIn(String mail, String password) {

    mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {

                progressLogin.setVisibility(View.INVISIBLE);
                buttonLogin.setVisibility(View.VISIBLE);
                updateUI();

            } else {
                showMessage(task.getException().getMessage());
                buttonLogin.setVisibility(View.VISIBLE);
                progressLogin.setVisibility(View.INVISIBLE);
            }
        }
    });


    }

    private void updateUI() {

        startActivity(HomeActivity);
        finish();

    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null)
            //Автологин
            updateUI();
    }
}
