package com.erickjsm.padoca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.obj.Funcionario;

public class RegisterActivity_Funcionarios extends AppCompatActivity {

    EditText etxt_Cad_FuncionarioTel;
    EditText etxt_Cad_FuncionarioCpf;
    EditText etxt_Cad_FuncionarioMinimal;
    EditText etxt_Cad_FuncionarioCargo;
    EditText etxt_Cad_FuncionarioSalario;
    EditText etxt_Cad_FuncionarioUNome;
    EditText etxt_Cad_FuncionarioEnd;
    EditText etxt_Cad_FuncionarioDataNasc;
    EditText etxt_Cad_FuncionarioPNome;

    Button btn_criarFuncionario;
    Switch switch_Cad_Funcionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_funcionario);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        btn_criarFuncionario = findViewById(R.id.btn_criarFuncionario);
        etxt_Cad_FuncionarioTel = findViewById(R.id.etxt_Cad_FuncionarioTel);
        etxt_Cad_FuncionarioCpf = findViewById(R.id.etxt_Cad_FuncionarioCpf);
        etxt_Cad_FuncionarioMinimal = findViewById(R.id.etxt_Cad_FuncionarioMinimal);
        etxt_Cad_FuncionarioCargo = findViewById(R.id.etxt_Cad_FuncionarioCargo);
        etxt_Cad_FuncionarioSalario = findViewById(R.id.etxt_Cad_FuncionarioSalario);
        etxt_Cad_FuncionarioUNome = findViewById(R.id.etxt_Cad_FuncionarioUNome);
        etxt_Cad_FuncionarioEnd = findViewById(R.id.etxt_Cad_FuncionarioEnd);
        etxt_Cad_FuncionarioDataNasc = findViewById(R.id.etxt_Cad_FuncionarioDataNasc);
        etxt_Cad_FuncionarioPNome = findViewById(R.id.etxt_Cad_FuncionarioPNome);
        switch_Cad_Funcionario = findViewById(R.id.swith_Cad_FuncSexo);

                btn_criarFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etxt_Cad_FuncionarioPNome.getText().toString().equals("") || etxt_Cad_FuncionarioUNome.getText().toString().equals("") || etxt_Cad_FuncionarioCpf.getText().toString().equals("") || etxt_Cad_FuncionarioCargo.getText().toString().equals("") || etxt_Cad_FuncionarioTel.getText().toString().equals(""))){
                    DAO dao = new DAO(getApplicationContext());
                    Funcionario funcionario = new Funcionario();


                    String sexo;
                    if(switch_Cad_Funcionario.isChecked()){
                        sexo = "F";
                    }
                    else {
                        sexo = "M";
                    }
                    funcionario.setSexo(sexo);
                    funcionario.setCargo(etxt_Cad_FuncionarioCargo.getText().toString());
                    funcionario.setSalario(etxt_Cad_FuncionarioSalario.getText().toString());
                    funcionario.setCPF(etxt_Cad_FuncionarioCpf.getText().toString());
                    funcionario.setDataNasc(etxt_Cad_FuncionarioDataNasc.getText().toString());
                    funcionario.setEndereco(etxt_Cad_FuncionarioEnd.getText().toString());
                    funcionario.setMinimal(etxt_Cad_FuncionarioMinimal.getText().toString());
                    funcionario.setpNome(etxt_Cad_FuncionarioPNome.getText().toString());
                    funcionario.setuNome(etxt_Cad_FuncionarioUNome.getText().toString());
                    funcionario.setTelefone(etxt_Cad_FuncionarioTel.getText().toString());

                    dao.insertFuncionario(funcionario);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Os campos Nomes, CPF, Telefone, Cargo e Salario são obrigatorios!", Toast.LENGTH_SHORT).show();
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