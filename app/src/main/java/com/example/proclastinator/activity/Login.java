package com.example.proclastinator.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proclastinator.R;

public class Login extends AppCompatActivity {
    EditText nome_usuario;
    EditText senha_usuario;
    Button  button_usuario;
    String  nome = "admin";
    String  senha= "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nome_usuario = findViewById(R.id.nome_usuario);
        senha_usuario = findViewById(R.id.senha_usuario);
        button_usuario = findViewById(R.id.button_usuario);

        button_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nome_usuario.getText().toString().equals(nome) && senha_usuario.getText().toString().equals(senha)){

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"login efetuado com sucesso",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(getApplicationContext(),"por favor verifique se os dados estão corretos",Toast.LENGTH_SHORT).show();


                }

            }
        });
    }
}
