package com.erickjsm.padoca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.obj.Login;

public class LoginActivity extends AppCompatActivity {

    EditText etxt_login;
    EditText etxt_password;
    Button btn_login;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        etxt_login = findViewById(R.id.etxt_login);
        etxt_password = findViewById(R.id.etxt_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etxt_login.getText().toString().equals("") || etxt_password.getText().toString().equals(""))){
                    DAO dao = new DAO(getApplicationContext());
                    Login login = new Login();
                    login.setLogin(etxt_login.getText().toString());
                    login.setSenha(etxt_password.getText().toString());

                    if(login.getLogin().equals("admin") && login.getSenha().equals("417864")){
                        Intent tela_menu = new Intent(LoginActivity.this, MenuActivity.class);
                        finish();
                        startActivity(tela_menu);
                        dao.close();

                        etxt_login.setText("");
                        etxt_login.requestFocus();
                        etxt_password.setText("");
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Acesso negado!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Por favor preeencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}