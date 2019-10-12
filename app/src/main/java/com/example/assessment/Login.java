package com.example.assessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileInputStream;

public class Login extends AppCompatActivity {
    EditText email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


    }

    public void fazerLogin(View view) {
        verificarLogin();
    }
    public void verificarLogin(){
        email = findViewById(R.id.email_edit);
        senha = findViewById(R.id.senha_edit);

        if ((senha.getText().toString().isEmpty()) || (email.getText().toString().isEmpty())){
            Toast.makeText(this, "Campos em branco. Por favor digite seu email e senha.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, TelaPrincipal.class);
            startActivity(intent);
        }
    }
}
