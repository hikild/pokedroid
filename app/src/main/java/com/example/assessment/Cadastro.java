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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Cadastro extends AppCompatActivity {
    EditText nome, email, senha, confirmarSenha, cpf;
    private boolean verificar;

    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity);


    }

    public void salvarCadastro(View view) {
        verificarCampos();
    }

    public void verificarCampos() {
        nome = findViewById(R.id.nome_edit);
        email = findViewById(R.id.email_edit);
        senha = findViewById(R.id.senha_edit);
        confirmarSenha = findViewById(R.id.confirmarSenha_edit);
        cpf = findViewById(R.id.cpf_edit);
        if ((nome.getText().toString().isEmpty()) || (email.getText().toString().isEmpty()) || (senha.getText().toString().isEmpty())
                || (confirmarSenha.getText().toString().isEmpty()) || (cpf.getText().toString().isEmpty())) {
            Toast.makeText(this, "Campos em branco", Toast.LENGTH_SHORT).show();
        }
         else if ((senha.getText().toString()).equals(confirmarSenha.getText().toString())) {
            Intent next = new Intent(this, TelaPrincipal.class);
            startActivity(next);
        } else {
            Toast.makeText(this, "Senhas diferentes.Por favor preencher os campos iguais.", Toast.LENGTH_SHORT).show();
        }
    }



//    public void verificarSenhas(){
//        if ((senha.getText().toString()) != (confirmarSenha.getText().toString())){
//            Toast.makeText(this, "Senhas diferentes.Por favor preencher os campos iguais.", Toast.LENGTH_SHORT).show();
//        } else {
//            Intent intent = new Intent(this, TelaPrincipal.class);
//            startActivity(intent);
//        }
//    }
    }

