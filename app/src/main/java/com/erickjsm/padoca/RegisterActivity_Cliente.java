package com.erickjsm.padoca;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.obj.Cliente;

public class RegisterActivity_Cliente extends AppCompatActivity {

    EditText etxt_Cad_ClienteTel;
    EditText etxt_Cad_ClienteCpf;
    EditText etxt_Cad_ClienteMinimal;
    EditText etxt_Cad_ClienteUNome;
    EditText etxt_Cad_ClienteEnd;
    EditText etxt_Cad_ClienteDataNasc;
    EditText etxt_Cad_ClientePNome;

    Button btn_criarCliente;
    Switch switch_Cad_Cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cliente);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        btn_criarCliente = findViewById(R.id.btn_criarCliente);
        etxt_Cad_ClienteTel = findViewById(R.id.etxt_Cad_FuncionarioTel);
        etxt_Cad_ClienteCpf = findViewById(R.id.etxt_Cad_FuncionarioCpf);
        etxt_Cad_ClienteMinimal = findViewById(R.id.etxt_Cad_FuncionarioMinimal);
        etxt_Cad_ClienteUNome = findViewById(R.id.etxt_Cad_FuncionarioUNome);
        etxt_Cad_ClienteEnd = findViewById(R.id.etxt_Cad_FuncionarioEnd);
        etxt_Cad_ClienteDataNasc = findViewById(R.id.etxt_Cad_FuncionarioDataNasc);
        etxt_Cad_ClientePNome = findViewById(R.id.etxt_Cad_FuncionarioPNome);

        switch_Cad_Cliente = findViewById(R.id.swith_Cad_ClienteSexo);

        btn_criarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etxt_Cad_ClienteCpf.getText().toString().equals("") || etxt_Cad_ClientePNome.getText().toString().equals("") || etxt_Cad_ClienteUNome.getText().toString().equals("") || etxt_Cad_ClienteTel.getText().toString().equals(""))){
                    DAO dao = new DAO(getApplicationContext());
                    Cliente cliente = new Cliente();

                    String sexo;
                    if(switch_Cad_Cliente.isChecked()){
                        sexo = "F";
                    }
                    else {
                        sexo = "M";
                    }

                    cliente.setSexo(sexo);
                    cliente.setCPF(etxt_Cad_ClienteCpf.getText().toString());
                    cliente.setDataNasc(etxt_Cad_ClienteDataNasc.getText().toString());
                    cliente.setEndereco(etxt_Cad_ClienteEnd.getText().toString());
                    cliente.setMinimal(etxt_Cad_ClienteMinimal.getText().toString());
                    cliente.setpNome(etxt_Cad_ClientePNome.getText().toString());
                    cliente.setuNome(etxt_Cad_ClienteUNome.getText().toString());
                    cliente.setTelefone(etxt_Cad_ClienteTel.getText().toString());

                    dao.insertCliente(cliente);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Os campos Nome, Telefone e CPF são obrigatorios!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent tela_menuCadrastro = new Intent(this, MenuCadastroActivity.class);
        startActivity(tela_menuCadrastro);
        super.onBackPressed();
    }
}